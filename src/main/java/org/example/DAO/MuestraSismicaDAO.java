package org.example.dao;

import org.example.modelos.DetalleMuestraSismica;
import org.example.modelos.MuestraSismica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MuestraSismicaDAO {

    private final DetalleMuestraSismicaDAO detalleDAO;

    public MuestraSismicaDAO() {
        this.detalleDAO = new DetalleMuestraSismicaDAO();
    }

    // CAMBIO: Recibe Connection con
    public ArrayList<MuestraSismica> getBySerie(Connection con, int idSerie) throws SQLException {
        ArrayList<MuestraSismica> lista = new ArrayList<>();
        String sql = "SELECT * FROM muestra_sismica WHERE id_serie = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSerie); // id_serie parece ser INT en tu base, no LONG
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Limpio y directo: mapeamos la fila y la agregamos
                    lista.add(mapRow(con, rs));
                }
            }
        }
        return lista;
    }

    private MuestraSismica mapRow(Connection con, ResultSet rs) throws SQLException {
        long idMuestra = rs.getLong("id_muestra");
        String fechaHora = rs.getTimestamp("fecha_hora_muestra").toLocalDateTime().toString(); 

        // AQUI ESTA LA MAGIA: Pasamos la conexión activa para buscar los detalles de ESTA muestra
        List<DetalleMuestraSismica> detalles = detalleDAO.getByMuestra(con, idMuestra);
        
        // Retornamos el objeto completo con sus detalles cargados
        return new MuestraSismica(new ArrayList<>(detalles), fechaHora); // Asumiendo que tu constructor es (List, String)
    }
}