package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.ConexaoIF;
import br.edu.ifpb.sislivros.entidades.Amizade;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitacaoDao implements SolicitacaoDaoIF{
    private ConexaoIF conn;
    private AmizadeDao amizadeDao;
    
    @Override
    public boolean adicionar(int idSolicitante, int idSolicitado) {
        boolean result = false;
        
        if (existeSolicitacao(idSolicitante, idSolicitado)){
            return false;
        } else {
            
        }
        
        
        return result;
    }

    @Override
    public List<Amizade> buscarPorId(int idSolicitado) {
         List<Amizade> lista = new ArrayList<>();

        PreparedStatement pst = null;

        try {
            conn = new Conexao();
            pst = conn.getConnection().prepareStatement("SELECT * FROM Amizade WHERE (idSolicitante = ? OR idSolicitado) AND estah_aceito = false");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(montarAmizade(rs));
            }

            conn.closeAll(pst);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public boolean remover(int idSolicitante, int idSolicitado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existeSolicitacao(int idSolicitante, int idSolicitado) {
        return false;
    }
    
    private Amizade montarAmizade(ResultSet rs) throws SQLException {
        Amizade a = new Amizade();
        
        a.setSolicitante(DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().buscarPorId(rs.getInt("")));

        return a;
    }
    
}


///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.edu.ifpb.sislivros.DAO;
//
//import br.edu.ifpb.sislivros.conexao.Conexao;
//import br.edu.ifpb.sislivros.entidades.Solicitacoes;
//import br.edu.ifpb.sislivros.interfaces.InterfaceSolicitacoesDAO;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author José
// */
//public class SolicitacaoDAO implements InterfaceSolicitacoesDAO {
//
//    @Override
//    public boolean adicionar(Solicitacoes s) {
//        boolean result = false;
//        Connection con = null;
//        PreparedStatement stmt;
//        String sql = "insert into solicitacoes(id,emailUsuario, idQuaseAmigo) values (?, ?, ?)";
//        try {
//            try {
//                con = Conexao.abrirConexao();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(SolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            stmt = con.prepareStatement(sql);
//            stmt.setInt(1, s.getId());
//            stmt.setString(2, s.getEmailDoUsuario());
//            stmt.setString(3, s.getEmailDosolicitante());
//
//            result = true;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            Conexao.fecharConexao(con);
//        }
//
//        return result;
//    }
//
//    @Override
//    public boolean atualizar(Solicitacoes s) {
//        boolean result = false;
//        Connection con = null;
//
//        try {
//            try {
//                con = Conexao.abrirConexao();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(SolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            String sql = "update SOLICITACOES set pendente = ?, resposta = ? where id = ?";
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setBoolean(1, s.isPendente());
//            stmt.setBoolean(2, s.isResposta());
//            stmt.setInt(3, s.getId());
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            Conexao.fecharConexao(con);
//        }
//
//        return result;
//    }
//
//    @Override
//    public List<Solicitacoes> consultarPoremailDoUsuario(String emailUsuario) {
//        String sql = "select * solicitacoes where emailUsuario = " + emailUsuario + " and pendente = true and resposta = false";
//        List<Solicitacoes> s = new ArrayList<Solicitacoes>();
//        Connection con = null;
//        Statement stat;
//
//        try {
//            try {
//                con = Conexao.abrirConexao();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(SolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            stat = con.createStatement();
//            ResultSet rs = stat.executeQuery(sql);
//
//            while (rs.next()) {
//                Solicitacoes so = new Solicitacoes();
//                so.setId(rs.getInt("id"));
//                so.setEmailDsolicitante(rs.getString("idquaseamigo"));
//                so.setEmailDoUsuario(emailUsuario);
//                so.setPendente(true);
//                so.setResposta(false);
//                so.setEmailDsolicitante(null);
//
//                s.add(so);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            Conexao.fecharConexao(con);
//        }
//
//        return s;
//    }
//
//    @Override
//    public boolean remover(int id) {
//        Connection conn = null;
//        boolean result = false;
//        try {
//            try {
//                conn = Conexao.abrirConexao();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(SolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            String sql = "DELETE FROM  SOLICITACOES WHERE id = ?";
//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setInt(1, id);
//            stm.executeUpdate();
//
//            result = true;
//        } catch (SQLException e) {
//            System.err.println("Erro " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//
//            Conexao.fecharConexao(conn);
//        }
//
//        return result;
//    }
//
//}
