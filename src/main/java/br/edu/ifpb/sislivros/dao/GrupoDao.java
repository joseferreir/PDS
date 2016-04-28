package br.edu.ifpb.sislivros.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.DataBaseException;
import br.edu.ifpb.sislivros.entidades.Grupo;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José
 */
public class GrupoDao implements GrupoDaoIF {

    private Conexao conn;

    @Override
    public int adicionar(Grupo grupo, int idCriador) {
        int result = 0;
        conn = null;

        try {
            conn = new Conexao();

            String operacao = "INSERT INTO Grupo (nome, descricao) VALUES (?, ?)";

            PreparedStatement ps = conn.getConnection().prepareStatement(operacao);
            ps.setString(1, grupo.getNome());
            ps.setString(2, grupo.getDescricao());

            int numAtualizacoes = ps.executeUpdate();

            if (numAtualizacoes > 0) {
//                result = 1;
//                CallableStatement call = conn.getConnection().prepareCall("{call insetCriadorGrupo('"+idCriador+"')}");
//                call.execute();

                result = buscarPorNome(grupo.getNome()).getId();
            }
            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean remover(int idGrupo) {
        boolean result = false;
        conn = null;

        try {
            conn = new Conexao();

            String operacao = "DELETE FROM Grupo WHERE id = ?";

            PreparedStatement ps = conn.getConnection().prepareStatement(operacao);
            ps.setInt(1, idGrupo);

            int numAtualizacoes = ps.executeUpdate();

            if (numAtualizacoes > 0) {
                result = true;
            }
            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean atualizar(Grupo grupo) {
        boolean result = false;
        conn = null;

        try {
            conn = new Conexao();

            String operacao = "UPDATE Grupo SET nome = ?, descricao = ? WHERE id = ?";

            PreparedStatement ps = conn.getConnection().prepareStatement(operacao);
            ps.setString(1, grupo.getNome());
            ps.setString(2, grupo.getDescricao());
            ps.setInt(3, grupo.getId());

            int numAtualizacoes = ps.executeUpdate();

            if (numAtualizacoes > 0) {
                result = true;
            }
            conn.closeAll(ps);

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Grupo buscarPorId(int idGrupo) {
        Grupo result = null;
        conn = null;

        PreparedStatement ps = null;
        try {
            conn = new Conexao();
            String busca = "SELECT * FROM Grupo WHERE id = " + idGrupo;
            ps = conn.getConnection().prepareStatement(busca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = montarGrupo(rs);
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(ps);
            } catch (DataBaseException ex) {
                Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public Grupo buscarPorNome(String nome) {
        Grupo result = null;
        conn = null;
        PreparedStatement ps = null;

        try {
            conn = new Conexao();
            String busca = "SELECT * FROM Grupo WHERE nome =? ";
            ps = conn.getConnection().prepareStatement(busca);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = montarGrupo(rs);
            }

            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(ps);
            } catch (DataBaseException ex) {
                Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public List<Grupo> buscarTodos() {
        List<Grupo> result = null;
        conn = null;
        PreparedStatement ps = null;

        try {
            conn = new Conexao();

            result = new ArrayList<>();

            String busca = "SELECT * FROM Grupo";
            ps = conn.getConnection().prepareStatement(busca);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(montarGrupo(rs));

            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                conn.closeAll(ps);
            } catch (DataBaseException ex) {
                Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private Grupo montarGrupo(ResultSet rs) throws SQLException {

        Grupo g = new Grupo();
        g.setId(rs.getInt("id"));
        g.setNome(rs.getString("nome"));
        g.setDescricao(rs.getString("descricao"));

        return g;
    }

}
