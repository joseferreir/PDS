/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.ConexaoIF;
import br.edu.ifpb.sislivros.conexao.DataBaseException;
import br.edu.ifpb.sislivros.entidades.RespostaTopico;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natarajan
 */
public class RespostaTopicoDao implements RespostaTopicoDaoIF {

    private ConexaoIF conn = null;
    private String sql;
    private static UsuarioDaoIF usuarioDao  = new UsuarioDao();
    private static TopicoDaoIF  topicoDao   = new TopicoDao();
    
    @Override
    public boolean adicionar(RespostaTopico rt) {
        boolean result = false;
        PreparedStatement st = null;
        sql = "INSERT INTO ComentarioTopico (idUsuario, idTopico, comentario, dateTime) VALUES (?, ?, ?, ?)";
        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);

            st.setInt(1, rt.getUsuario().getId());
            st.setInt(2, rt.getTopico().getId());
            st.setString(3, rt.getResposta());
            st.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
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
    public boolean remover(int idResposta){
        boolean result = false;
        PreparedStatement st = null;
        sql = "DELETE FROM ComentarioTopico WHERE id = ?";
        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);
            st.setInt(1, idResposta);
            
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
    public boolean atualizar(RespostaTopico rt, int idResposta){
        boolean result = false;
        PreparedStatement st = null;
        
        sql = "UPDATE ComentarioTopico SET idUsuario = ?, idTopico = ?, comentario = ? WHERE id = ?";
        try {
            if (conn == null) {
                conn = new Conexao();
            }
            st = conn.getConnection().prepareStatement(sql);
            st.setInt(1, rt.getUsuario().getId());
            st.setInt(2, rt.getTopico().getId());
            st.setString(3, rt.getResposta());
            st.setInt(4, idResposta);
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
    public List<RespostaTopico> buscarRespostas(int idTopico){
        List<RespostaTopico> result = new ArrayList<>();
        PreparedStatement st = null;

        try {
            if (conn == null) {
                conn = new Conexao();
            }
            
            sql = "SELECT * FROM ComentarioTopico WHERE idTopico = ? ";
            
            st = conn.getConnection().prepareStatement(sql);
            st.setInt(1, idTopico);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(montarResposta(rs));

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

    private RespostaTopico montarResposta(ResultSet rs) throws SQLException {
        RespostaTopico rt = new RespostaTopico();
        rt.setId(rs.getInt("id"));
        rt.setDataHora(rs.getTimestamp("dateTime").toLocalDateTime());
        rt.setUsuario(usuarioDao.buscarPorId(rs.getInt("idUsuario")));
        rt.setTopico(topicoDao.buscarPorId(rs.getInt("idTopico")));
        rt.setResposta(rs.getString("comentario"));
        return rt;
    }

}
