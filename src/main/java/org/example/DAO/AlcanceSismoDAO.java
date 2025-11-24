package org.example.dao;

import org.example.modelos.AlcanceSismo;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlcanceSismoDAO {

    private final Connection con;

    public AlcanceSismoDAO() {
        this.con = ConexionDB.getConnection();
    }

    private AlcanceSismo mapRow(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre_alcance");
        String desc = rs.getString("descripcion");
        return new AlcanceSismo(nombre, desc);
    }

    public List<AlcanceSismo> getAll() throws SQLException {
        List<AlcanceSismo> lista = new ArrayList<>();
        String sql = "SELECT * FROM alcance_sismo";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }
    
    
}
