package org.example.dao;

import org.example.modelos.MagnitudRichter;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagnitudRichterDAO {

    private final Connection con;

    public MagnitudRichterDAO() {
        this.con = ConexionDB.getConnection();
    }

    private MagnitudRichter mapRow(ResultSet rs) throws SQLException {
        String desc = rs.getString("descripcion");
        int numero = rs.getInt("numero");
        return new MagnitudRichter(desc, numero);
    }

    public List<MagnitudRichter> getAll() throws SQLException {
        List<MagnitudRichter> lista = new ArrayList<>();
        String sql = "SELECT * FROM magnitud_richter";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs)); //Creamos los objetos de Magnitud Ritcher a traves de cada linea de respuesta rs
            }
        }
        return lista;
    }
}
