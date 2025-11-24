package org.example.dao;

import org.example.modelos.DetalleMuestraSismica;
import org.example.modelos.TipoDeDato;
import org.example.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleMuestraSismicaDAO {

    private final Connection con;
    private final TipoDeDatoDAO tipoDeDatoDAO;

    public DetalleMuestraSismicaDAO() {
        this.con = ConexionDB.getConnection();
        this.tipoDeDatoDAO = new TipoDeDatoDAO();
    }

private DetalleMuestraSismica mapRow(Connection con, ResultSet rs) throws SQLException {
        int idTipoDato = rs.getInt("id_tipo_dato");
        Integer valor = rs.getInt("valor"); 

        // AQUI ESTA LA MAGIA: Pasamos la conexión activa al hijo
        TipoDeDato tipo = tipoDeDatoDAO.getById(con, idTipoDato);
        
        return new DetalleMuestraSismica(tipo, valor);
    }

public List<DetalleMuestraSismica> getByMuestra(Connection con, long idMuestra) throws SQLException {
        List<DetalleMuestraSismica> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_muestra_sismica WHERE id_muestra = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idMuestra);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    
                    lista.add(mapRow(con, rs));
                }
            }
        }
        return lista;
    }
}
