package org.example.dao;

import org.example.modelos.TipoDeDato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDeDatoDAO {

    public TipoDeDatoDAO() {
    }


    public TipoDeDato getById(Connection con, int id) throws SQLException {
        String sql = "SELECT * FROM tipo_de_dato WHERE id_tipo_dato = ?";
        
        // Solo cerramos el PreparedStatement y ResultSet, NO la conexión
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    private TipoDeDato mapRow(ResultSet rs) throws SQLException {
        String denom = rs.getString("denominacion");
        String unidad = rs.getString("nombre_unidad");
        int umbral = rs.getInt("valor_umbral"); 
        return new TipoDeDato(denom, umbral, unidad);
    }
}