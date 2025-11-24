package org.example.dao;

import org.example.modelos.ClasificacionSismo;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionSismoDAO {

    private final Connection con;

    public ClasificacionSismoDAO() {
        this.con = ConexionDB.getConnection();
    }

    private ClasificacionSismo mapRow(ResultSet rs) throws SQLException {
        int kmDesde = rs.getInt("km_desde");
        int kmHasta = rs.getInt("km_hasta");
        String nombre = rs.getString("nombre_clasificacion_sismo");
        return new ClasificacionSismo(nombre, kmDesde, kmHasta);
    }

    public List<ClasificacionSismo> getAll() throws SQLException {
        List<ClasificacionSismo> lista = new ArrayList<>();
        String sql = "SELECT * FROM clasificacion_sismo";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }
    public ClasificacionSismo getById(int id) throws SQLException {
    String sql = "SELECT * FROM clasificacion_sismo WHERE id_clasificacion = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int kmDesde = rs.getInt("km_desde");
                int kmHasta = rs.getInt("km_hasta");
                String nombre = rs.getString("nombre_clasificacion_sismo");
                return new ClasificacionSismo(nombre,kmDesde, kmHasta);
            }
        }
    }
    return null;
}

}
