package org.example.DAO;

import org.example.modelos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.utils.ConexionDB;

public class CambioEstadoDAO {

    // ALTA
    public void insertar(CambioEstado cambio, int idEvento) {
        // Primero, cerramos el cambio anterior de ese evento (si existe)
        cerrarCambioAnterior(idEvento, cambio.getFechaHoraInicio());

        String sql = "INSERT INTO cambio_estado (id_evento, id_estado, fecha_hora_inicio, id_responsable) VALUES (?, (SELECT id_estado FROM estado WHERE nombre = ?), ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idEvento);
            stmt.setString(2, cambio.getEstado().getNombre());
            stmt.setObject(3, cambio.getFechaHoraInicio());
            
            if (cambio.getResponsable() != null) {
                // Idealmente buscar ID por mail/legajo
                stmt.setInt(4, 1); // ID Analista simulado
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Auxiliar: Cerrar estado anterior (UPDATE fecha_fin)
    private void cerrarCambioAnterior(int idEvento, java.time.LocalDateTime fechaFin) {
        String sql = "UPDATE cambio_estado SET fecha_hora_fin = ? WHERE id_evento = ? AND fecha_hora_fin IS NULL";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, fechaFin);
            stmt.setInt(2, idEvento);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // CONSULTA
    public List<CambioEstado> buscarPorIdEvento(int idEvento) {
        List<CambioEstado> cambios = new ArrayList<>();
        String sql = "SELECT ce.*, e.nombre as nombre_estado FROM cambio_estado ce JOIN estado e ON ce.id_estado = e.id_estado WHERE id_evento = ? ORDER BY fecha_hora_inicio";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            ResultSet rs = stmt.executeQuery();
            
            EstadoDAO estadoDAO = new EstadoDAO(); // Para instanciar clases
            
            while(rs.next()) {
                String nombreEstado = rs.getString("nombre_estado");
                // Factory manual o usar estadoDAO
                Estado est = estadoDAO.buscarPorNombre(nombreEstado); 
                
                java.time.LocalDateTime inicio = rs.getTimestamp("fecha_hora_inicio").toLocalDateTime();
                
                // Reconstruir Analista (lazy, solo nombre o null)
                Analista analista = null;
                if(rs.getInt("id_responsable") != 0) {
                     analista = new Analista("Analista", "BD", "mail", "tel"); // Mock, o llamar AnalistaDAO
                }
                
                CambioEstado ce = new CambioEstado(est, inicio, analista);
                if (rs.getTimestamp("fecha_hora_fin") != null) {
                    ce.setFechaHoraFin(rs.getTimestamp("fecha_hora_fin").toLocalDateTime());
                }
                cambios.add(ce);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return cambios;
    }
}