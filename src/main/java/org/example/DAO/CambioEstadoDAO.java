package org.example.DAO;

import org.example.modelos.*;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CambioEstadoDAO {

    // ALTA
    public void insertar(CambioEstado cambio, int idEvento) {
        // 1. Primero cerramos el anterior. 
        // Como 'cerrarCambioAnterior' usa la misma conexión Singleton y NO la cierra, es seguro llamarlo aquí.
        cerrarCambioAnterior(idEvento, cambio.getFechaHoraInicio());

        String sql = "INSERT INTO cambio_estado (id_evento, id_estado, fecha_hora_inicio, id_responsable) " +
                     "VALUES (?, (SELECT id_estado FROM estado WHERE nombre = ? LIMIT 1), ?, ?)";
        
        
        Connection conn = ConexionDB.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idEvento);
            stmt.setString(2, cambio.getEstado().getNombre());
            stmt.setObject(3, cambio.getFechaHoraInicio());
            
            stmt.setInt(4, 1);
            /*if (cambio.getResponsable() != null) {
                // Opción A: Si tuvieras el ID en el objeto -> stmt.setInt(4, cambio.getResponsable().getId());
                // Opción B (Simulada por ahora): ID fijo o buscarlo
                stmt.setInt(4, 1); 
            } else {
                stmt.setNull(4, Types.INTEGER);
            }*/
            
            stmt.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
    }

    // Auxiliar: Cerrar estado anterior (UPDATE fecha_fin)
    private void cerrarCambioAnterior(int idEvento, java.time.LocalDateTime fechaFin) {
        String sql = "UPDATE cambio_estado SET fecha_hora_fin = ? WHERE id_evento = ? AND fecha_hora_fin IS NULL";
        
        
        Connection conn = ConexionDB.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, fechaFin);
            stmt.setInt(2, idEvento);
            stmt.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
    }

    // CONSULTA
    public List<CambioEstado> buscarPorIdEvento(int idEvento) {
        List<CambioEstado> cambios = new ArrayList<>();
        String sql = "SELECT ce.*, e.nombre as nombre_estado " +
                     "FROM cambio_estado ce " +
                     "JOIN estado e ON ce.id_estado = e.id_estado " +
                     "WHERE id_evento = ? " +
                     "ORDER BY fecha_hora_inicio";
        
        
        Connection conn = ConexionDB.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            ResultSet rs = stmt.executeQuery();
            
            // Usamos los DAOs auxiliares para reconstruir objetos
            EstadoDAO estadoDAO = new EstadoDAO(); 
            AnalistaDAO analistaDAO = new AnalistaDAO(); 
            
            while(rs.next()) {
                // 1. Reconstruir Estado
                String nombreEstado = rs.getString("nombre_estado");
                Estado est = estadoDAO.buscarPorNombre(nombreEstado); 
                
                java.time.LocalDateTime inicio = rs.getTimestamp("fecha_hora_inicio").toLocalDateTime();
                
                // 2. Reconstruir Analista (INTEGRACIÓN REAL)
                Analista analista = null;
                int idResponsable = rs.getInt("id_responsable");
                if(idResponsable != 0) {
                    // Usamos el DAO de Analista para traer el objeto real de la BD
                    analista = analistaDAO.buscarPorId(idResponsable);
                }
                
                // 3. Crear el objeto CambioEstado
                CambioEstado ce = new CambioEstado(est, inicio, analista);
                
                // 4. Setear fecha fin si existe
                Timestamp fechaFin = rs.getTimestamp("fecha_hora_fin");
                if (fechaFin != null) {
                    ce.setFechaHoraFin(fechaFin.toLocalDateTime());
                }
                
                cambios.add(ce);
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return cambios;
    }
}