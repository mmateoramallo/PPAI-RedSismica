package org.example.dao;

import org.example.modelos.EstacionSismologica;
import org.example.modelos.SerieTemporal;
import org.example.modelos.MuestraSismica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SerieTemporalDAO {
      
    // 1. Necesitamos el DAO hijo para pedirle las muestras
    private final MuestraSismicaDAO muestraDAO; 

    public SerieTemporalDAO() {
        // 2. Lo inicializamos
        this.muestraDAO = new MuestraSismicaDAO(); 
    }
   
    public List<SerieTemporal> buscarPorEvento(Connection con, int idEvento) {
        List<SerieTemporal> series = new ArrayList<>();
        
        // Agregamos "id_serie" al SELECT porque lo necesitamos para buscar las muestras
        String sql = "SELECT s.*, est.nombre_estacion_sismologica " +
                     "FROM serie_temporal s " +
                     "JOIN estacion_sismologica est ON s.id_estacion = est.id_estacion " +
                     "WHERE s.id_evento = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idEvento);
            
            try (ResultSet rs = stmt.executeQuery()) { 
                while (rs.next()) {
                    SerieTemporal s = new SerieTemporal();
                    
                    // --- 3. IMPORTANTE: Recuperar el ID de la Serie ---
                    int idSerie = rs.getInt("id_serie"); 
                    // s.setId(idSerie); // Si tu modelo tiene ID, descomenta esto.

                    // Mapeo básico
                    s.setCondicionAlarma(rs.getString("condicion_alarma"));
                    s.setFrecuenciaMuestreo(rs.getString("frecuencia_muestreo"));
                    
                    Timestamp tsInicio = rs.getTimestamp("fecha_hora_inicio_registro");
                    // Ajusta si tu setter recibe String o LocalDateTime (dejo String como en tu código)
                    if (tsInicio != null) s.setFechaHoraInicioRegistroMuestras(tsInicio.toLocalDateTime().toString());
                    
                    Timestamp tsFin = rs.getTimestamp("fecha_hora_registro");
                    if (tsFin != null) s.setFechaHoraRegistro(tsFin.toLocalDateTime().toString());
                    
                    // Estación
                    EstacionSismologica est = new EstacionSismologica();
                    est.setNombre(rs.getString("nombre_estacion_sismologica"));
                    s.setEstacionSismica(est);
                    
                    // --- 4. LA VERIFICACIÓN QUE FALTABA ---
                    
                    ArrayList<MuestraSismica> muestras = muestraDAO.getBySerie(con, idSerie);
                    
                    // Se las asignamos al objeto Serie
                    s.setMuestrasSismicas(muestras); 
                    
                    series.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }
}