package org.example.dao;

import org.example.modelos.EstacionSismologica;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstacionSismologicaDAO {

    private final Connection con;

    public EstacionSismologicaDAO() {
        this.con = ConexionDB.getConnection();
    }

    private EstacionSismologica mapRow(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("codigo_estacion");
        String doc = rs.getString("documento_certificacion_adq");
        Date fecha = rs.getDate("fecha_solicitud_cert_adq");
        String latitud = rs.getString("latitud");
        String longitud = rs.getString("longitud");
        String nombre = rs.getString("nombre_estacion_sismologica");
        int nroCert = rs.getInt("nro_certificacion_adquisicion");
        return new EstacionSismologica(codigo, nombre, nroCert, latitud, longitud, fecha, doc);
    }

    public List<EstacionSismologica> getAll() throws SQLException {
        List<EstacionSismologica> lista = new ArrayList<>();
        String sql = "SELECT * FROM estacion_sismologica";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    public EstacionSismologica getByIdEstacion(int idEstacion) throws SQLException {
        String sql = "SELECT * FROM estacion_sismologica WHERE id_estacion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEstacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }
}
