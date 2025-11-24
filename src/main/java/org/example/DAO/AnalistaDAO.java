package org.example.DAO;

import org.example.modelos.Analista;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.utils.ConexionDB;

public class AnalistaDAO {

    // ALTA (Create)
    public void insertar(Analista analista) {
        String sql = "INSERT INTO analista (nombre_analista, apellido, mail, legajo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, analista.tomarNombre());
            stmt.setString(2, analista.tomarApellido());
            stmt.setString(3, analista.tomarMail());
            stmt.setString(4, analista.tomarLegajo());
            stmt.executeUpdate();
            
            // Si necesitas recuperar el ID autogenerado:
            // ResultSet rs = stmt.getGeneratedKeys(); ...
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // CONSULTA (Read)
    public Analista buscarPorId(int id) {
        String sql = "SELECT * FROM analista WHERE id_analista = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Asumiendo que agregaste un constructor con ID o setters
                // return new Analista(id, rs.getString("nombre"), ...);
                return new Analista(rs.getString("nombre_analista"), rs.getString("apellido"), 
                                    rs.getString("mail"), rs.getString("legajo"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    // MODIFICACION y BAJA (Omitidos por brevedad, siguen la misma lógica con UPDATE y DELETE)
}