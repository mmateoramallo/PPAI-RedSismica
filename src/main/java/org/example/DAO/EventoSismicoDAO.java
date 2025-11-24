package org.example.dao;

import org.example.modelos.*;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoSismicoDAO {

    private final Connection con;
    private final SerieTemporalDAO serieDAO;

    public EventoSismicoDAO() {
        this.con = ConexionDB.getConnection();
        this.serieDAO = new SerieTemporalDAO();
    }

    public List<EventoSismico> buscarTodos() {
        List<EventoSismico> lista = new ArrayList<>();
        
        // 1. SQL: Aquí seleccionamos los nombres largos REALES de la base de datos
        String sql = "SELECT e.*, " +
                     "       est.nombre AS nombre_estado, " + // Estado suele ser 'nombre', usamos alias
                     "       alc.nombre_alcance, alc.descripcion AS desc_alcance, " + 
                     "       ori.nombre_origen_generacion, ori.descripcion AS desc_origen, " + 
                     "       mag.descripcion AS desc_magnitud, mag.numero AS num_magnitud, " +
                     "       cla.nombre_clasificacion_sismo, cla.km_desde, cla.km_hasta " + 
                     "FROM evento_sismico e " +
                     "LEFT JOIN estado est ON e.id_estado_actual = est.id_estado " +
                     "LEFT JOIN alcance_sismo alc ON e.id_alcance = alc.id_alcance " +
                     "LEFT JOIN origen_generacion ori ON e.id_origen = ori.id_origen " +
                     "LEFT JOIN magnitud_richter mag ON e.id_magnitud = mag.id_magnitud " +
                     "LEFT JOIN clasificacion_sismo cla ON e.id_clasificacion = cla.id_clasificacion";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EventoSismico evento = new EventoSismico();
                
                // --- Datos Primitivos ---
                // Verifica si en tu modelo es setId o setIdEvento
                evento.setIdEvento(rs.getInt("id_evento")); 
                
                Timestamp ts = rs.getTimestamp("fecha_hora_ocurrencia");
                if (ts != null) evento.setFechaHoraOcurrencia(ts.toLocalDateTime());
                
                evento.setLatitudEpicentro(rs.getInt("latitud_epicentro"));
                evento.setLongitudEpicentro(rs.getInt("longitud_epicentro"));
                evento.setLatitudHipocentro(rs.getInt("latitud_hipocentro"));
                evento.setLongitudHipocentro(rs.getInt("longitud_hipocentro"));
                evento.setValorMagnitud(rs.getInt("valor_magnitud"));

                // --- Objetos Relacionados ---
                // SOLUCIÓN: Usar exactamente el nombre de columna del SELECT
                
                // 1. ESTADO
                String nombreEstado = rs.getString("nombre_estado");
                evento.setEstadoActual(mapearEstado(nombreEstado));

                // 2. ALCANCE
                if (rs.getString("nombre_alcance") != null) {
                    evento.setAlcanceSismo(new AlcanceSismo(
                        rs.getString("nombre_alcance"), 
                        rs.getString("desc_alcance")
                    ));
                }

                // 3. ORIGEN (Aquí estaba el error)
                // Antes pedías "nombre_origen", pero en el select trajiste "nombre_origen_generacion"
                if (rs.getString("nombre_origen_generacion") != null) {
                    evento.setOrigenGeneracion(new OrigenDeGeneracion(
                        rs.getString("desc_origen"), 
                        rs.getString("nombre_origen_generacion")
                    ));
                }

                // 4. MAGNITUD
                if (rs.getString("desc_magnitud") != null) {
                    evento.setMagnitud(new MagnitudRichter(
                        rs.getString("desc_magnitud"), 
                        rs.getInt("num_magnitud")
                    ));
                }
                
                // 5. CLASIFICACION (También corregido)
                // Antes pedías "nombre_clasificacion", pero la columna es "nombre_clasificacion_sismo"
                if (rs.getString("nombre_clasificacion_sismo") != null) {
                     evento.setClasificacion(new ClasificacionSismo(
                         rs.getString("nombre_clasificacion_sismo"), 
                         rs.getInt("km_desde"), // Asegurate que existan estas columnas
                         rs.getInt("km_hasta")
                     )); 
                }
                List<SerieTemporal> seriesDelEvento = serieDAO.buscarPorEvento(this.con,evento.getIdEvento());
                evento.setSerieTemporal(seriesDelEvento);
                lista.add(evento);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar eventos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    public void actualizarEstado(EventoSismico evento) {
        // Asegúrate que la tabla estado tenga columna 'nombre' o ajusta aquí también
        String sql = "UPDATE evento_sismico SET id_estado_actual = (SELECT id_estado FROM estado WHERE nombre = ?) WHERE id_evento = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, evento.getEstado().getNombre());
            ps.setInt(2, evento.getIdEvento());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarDatos(EventoSismico evento) {
        // CORREGIDO: Usamos los nombres reales de las columnas en el WHERE
        String sql = "UPDATE evento_sismico SET " +
                     "id_alcance = (SELECT id_alcance FROM alcance_sismo WHERE nombre_alcance = ? LIMIT 1), " +
                     "id_origen = (SELECT id_origen FROM origen_generacion WHERE nombre_origen_generacion = ? LIMIT 1), " +
                     "id_magnitud = (SELECT id_magnitud FROM magnitud_richter WHERE descripcion = ? LIMIT 1) " +
                     "WHERE id_evento = ?";
                     
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, evento.getAlcanceSismo().getNombre());
            ps.setString(2, evento.getOrigenGeneracion().getNombre());
            
            // Magnitud usa descripcion
            ps.setString(3, evento.tomarMagnitud().getDescripcionMagnitud());
            
            ps.setInt(4, evento.getIdEvento());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Estado mapearEstado(String nombreDB) {
        if (nombreDB == null) return null;
        switch (nombreDB) {
            case "AutoDetectado": return new AutoDetectado();
            case "BloqueadoEnRevision": return new BloqueadoEnRevision();
            case "Rechazado": return new Rechazado();
            case "Confirmado": return new Confirmado(); 
            default: return new AutoDetectado(); 
        }
    }
}