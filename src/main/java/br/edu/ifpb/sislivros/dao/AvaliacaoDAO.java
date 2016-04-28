package br.edu.ifpb.sislivros.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.DataBaseException;
import br.edu.ifpb.sislivros.entidades.Avaliacao;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José
 */
public class AvaliacaoDAO implements AvaliacaoDaoIF {

    private Conexao conn = null;
    private String operacao;

    public AvaliacaoDAO() {
    }

    @Override
    public boolean adicionar(Avaliacao avaliacao) {
        boolean resut = false;
        PreparedStatement st = null;
        try {
            conn = new Conexao();
            operacao = "INSERT INTO avaliacao(idUsuario, idlivro, rating, comentario) values (?, ?, ?, ?)";
            st = conn.getConnection().prepareStatement(operacao);
            st.setInt(1, avaliacao.getUsuario().getId());
            st.setInt(2, avaliacao.getLivro().getId());
            st.setInt(3, avaliacao.getRating());
            st.setString(4, avaliacao.getComentario());
            if (st.executeUpdate() > 0) {
                resut = true;
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resut;
    }

    @Override
    public boolean atualizar(Avaliacao avaliacao) {
        boolean resut = false;
        PreparedStatement st = null;
        try {
            if (conn == null) {
                conn = new Conexao();
                operacao = "UPDATE AVALIACAO SET comentario = ?, rating = ? where id =?";
                st = conn.getConnection().prepareStatement(operacao);
                st.setString(1, avaliacao.getComentario());
                st.setInt(2, avaliacao.getRating());
                st.setInt(3, avaliacao.getId());
                if (st.executeUpdate() > 0) {
                    resut = true;
                }
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resut;
    }

    @Override
    public boolean remover(int idAvaliacao) {
        PreparedStatement st = null;
        boolean resul = false;
        try {
            if (conn == null) {
                conn = new Conexao();
            }

            operacao = "DELETE FROM avaliacao where id ='"+idAvaliacao+"'";
            st = conn.getConnection().prepareStatement(operacao);
            if (st.executeUpdate() > 0) {
                resul = true;
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resul;
    }

    @Override
    public Avaliacao buscar(Usuario usuario, Livro livro) {

        operacao = "SELECT * FROM Avaliacao WHERE idLivro ='" + livro.getId() + "' AND idUsuario ='" + usuario.getId() + "'";
        
        List<Avaliacao> result = buscarAvaliacao(operacao);
        if(!result.isEmpty())
            return result.get(0);
        return null;
    }

    @Override
    public Avaliacao buscarPorId(int id) {
        operacao = "SELECT * FROM Avaliacao WHERE id ='" + id + "' ";
        return buscarAvaliacao(operacao).get(0);
    }

    @Override
    public List<Avaliacao> buscarPorLivro(int idLivro) {
        operacao = "SELECT * FROM Avaliacao WHERE idlivro =' " + idLivro + " '";
        return buscarAvaliacao(operacao);

    }

    private Avaliacao montarAvaliacao(ResultSet rs) throws SQLException {
        DaoFactoryIF factoy = DaoFactory.createFactory(DaoFactory.DAO_BD);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(factoy.criaUsuarioDAO().buscarPorId(rs.getInt("idusuario")));
        avaliacao.setLivro(factoy.criaLivroDAO().buscarPorId(rs.getInt("idlivro")));
        avaliacao.setComentario(rs.getString("comentario"));
        avaliacao.setRating(rs.getInt("rating"));
        avaliacao.setId(Integer.parseInt(rs.getString("id")));
        return avaliacao;

    }

    private List<Avaliacao> buscarAvaliacao(String sql) {
        PreparedStatement st = null;
        
        List<Avaliacao> avaliacoes = new ArrayList<>();
        try {
            if (conn == null) {
                conn = new Conexao();
            }

            st = conn.getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                avaliacoes.add(montarAvaliacao(rs));
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(st);
            } catch (DataBaseException ex) {
                Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!avaliacoes.isEmpty()) {
            return avaliacoes;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}
