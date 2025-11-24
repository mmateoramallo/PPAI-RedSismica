package org.example.DAO;

import org.example.modelos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.utils.ConexionDB;

public class EstadoDAO {

    // CONSULTA (Read) - Convierte DB -> Objeto Java
    public Estado buscarPorId(int id) {
        String sql = "SELECT * FROM estado WHERE id_estado = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearEstado(rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Estado buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM estado WHERE nombre = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearEstado(rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // FACTORY METHOD: La magia del Patrón State en persistencia
    private Estado mapearEstado(String nombre, String descripcion) {
        switch (nombre) {
            case "AutoDetectado": return new AutoDetectado(); 
            case "BloqueadoEnRevision": return new BloqueadoEnRevision();
            case "Rechazado": return new Rechazado();
            // Agregar Confirmado, etc.
            default: return null; // O lanzar excepción
        }
    }
}