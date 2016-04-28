package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.ConexaoIF;
import br.edu.ifpb.sislivros.conexao.DataBaseException;
import br.edu.ifpb.sislivros.entidades.Recomendacao;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author José
 */
public class RecomendacaoDao implements RecomendacaoDaoIF {

    private ConexaoIF conn = null;
    private String sql;
    private static UsuarioDaoIF udao = new UsuarioDao();
    private static LivroDaoIF ldao = new LivroDao();

    public RecomendacaoDao() {
    }

    @Override
    public boolean adicionar(Recomendacao r) {
        boolean result = false;
        PreparedStatement st = null;
        sql = "INSERT INTO Recomendacao (idRemetente,idDestinatario,idLivro, dataRecomendacao) VALUES (?,?,?,?)";
        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);

            st.setInt(1, r.getRemetente().getId());
            st.setInt(2, r.getDestinatario().getId());
            st.setInt(3, r.getLivro().getId());
            st.setTimestamp(4, Timestamp.valueOf(r.getDataHora()));
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    @Override
    public List<Recomendacao> buscaPorRemetente(int idUsuario) {
        sql = "SELECT * FROM Recomendacao WHERE idRemetente = ? ";
        return buscarRecomendacao(sql, idUsuario);

    }

    @Override
    public List<Recomendacao> buscaPorDestinario(int idUsuario) {
        sql = "SELECT * FROM Recomendacao WHERE idDestinatario = ?";
        return buscarRecomendacao(sql, idUsuario);

    }

    @Override
    public boolean alterar(Recomendacao r, int idRecomendacao) {
        boolean result = false;
        PreparedStatement st = null;
        sql = "UPDATE Recomendacao SET idRemetente = ?, idDestinatario = ?, idLivro = ?, dataRecomendacao = ? WHERE id = ?";
        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);
            st.setInt(1, r.getRemetente().getId());
            st.setInt(2, r.getDestinatario().getId());
            st.setInt(3, r.getLivro().getId());
            st.setInt(4, idRecomendacao);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;

    }

    @Override
    public boolean apagar(int idDestinatario, int idRemetente) {
        boolean result = false;
        PreparedStatement st = null;
        sql = "DELETE FROM Recomendacao WHERE idDestinatario = ? AND idRemetente = ?";
        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);
            st.setInt(1, idDestinatario);
            st.setInt(2, idRemetente);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    private List<Recomendacao> buscarRecomendacao(String sql, int idUsuario) {

        List<Recomendacao> result = new ArrayList<>();
        PreparedStatement st = null;

        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);
            st.setInt(1, idUsuario);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(montarRecomendacao(rs));

            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(RecomendacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    private Recomendacao montarRecomendacao(ResultSet rs) throws SQLException {
        Recomendacao r = new Recomendacao();
        r.setId(rs.getInt("id"));
        r.setLivro(ldao.buscarPorId(rs.getInt("idlivro")));
        r.setDestinatario(udao.buscarPorId(rs.getInt("idDestinatario")));
        r.setRemetente(udao.buscarPorId(rs.getInt("idRemetente")));
        r.setDataHora(rs.getTimestamp("dataRecomendacao").toLocalDateTime());
        return r;

    }

}
