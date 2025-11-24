package org.example.dao;

import org.example.modelos.OrigenDeGeneracion;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrigenDeGeneracionDAO {

    private final Connection con;

    public OrigenDeGeneracionDAO() {
        this.con = ConexionDB.getConnection();
    }

    private OrigenDeGeneracion mapRow(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre_origen_generacion");
        String desc = rs.getString("descripcion");
        return new OrigenDeGeneracion(desc, nombre);
    }

    public List<OrigenDeGeneracion> getAll() throws SQLException {
        List<OrigenDeGeneracion> lista = new ArrayList<>();
        String sql = "SELECT * FROM origen_generacion";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }
}
