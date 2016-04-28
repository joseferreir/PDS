package br.edu.ifpb.sislivros.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.entidades.Topico;
import com.sun.rowset.JdbcRowSetImpl;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.JdbcRowSet;

/**
 *
 * @author José
 */
public class TopicoDao implements TopicoDaoIF {

    private Conexao conn;

    public TopicoDao() {
    }

    @Override
    public int adicionar(Topico t) {
        int result = 0;

        conn = null;
        PreparedStatement ps;
        String sql = "INSERT INTO Topico (idGrupo, idLivro, descricao) values (?, ?, ?)";

        try {
            conn = new Conexao();
            ps = conn.getConnection().prepareStatement(sql);

            ps.setInt(1, t.getGrupo().getId());
            ps.setInt(2, t.getLivro().getId());
            ps.setString(3, t.getDescricao());
            if (ps.executeUpdate() > 0) {
                result = 0;
            }
            conn.closeAll(ps);

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TopicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int adicionar2(Topico t) {
        int result = 0;

        conn = null;
        String sqlInserir = "INSERT INTO Topico (idGrupo, idLivro, descricao) values (?, ?, ?)";
        String sqlBusca = "SELECT * FROM Topico WHERE idGrupo = ? AND idLivro = ?";

        try {
            conn = new Conexao();
            JdbcRowSet jrs = new JdbcRowSetImpl(conn.getConnection());
            jrs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            jrs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            
            jrs.setCommand(sqlBusca);

            jrs.setInt(1, t.getGrupo().getId());
            jrs.setInt(2, t.getLivro().getId());

            jrs.execute();

            if (jrs.next()) {
                result = jrs.getInt("id");
            } else {
                
                jrs.first();
                jrs.moveToInsertRow();
                jrs.updateInt("idGrupo", t.getGrupo().getId());
                jrs.updateInt("idLivro", t.getLivro().getId());
                jrs.updateString("descricao", t.getDescricao());
                jrs.insertRow();
                
                jrs.execute();
                
                jrs.first();
                result = jrs.getInt("id");
            }

            jrs.close();
            //conn.close();

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TopicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean remover(int idTopico) {
        boolean result = false;
        conn = null;
        try {

            conn = new Conexao();
            String sql = "DELETE FROM Topico WHERE id = ?";
            PreparedStatement rs = conn.getConnection().prepareStatement(sql);
            rs.setInt(1, idTopico);
            if (rs.executeUpdate() > 0) {
                result = true;
            }
            conn.closeAll(rs);

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TopicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean atualizar(Topico t, int idTopico) {
        boolean result = false;

        conn = null;

        try {
            conn = new Conexao();

            String sql = "UPDATE Topico SET idGrupo = ?, idLivro = ?, descricao = ? WHERE id = ?";
            PreparedStatement pst = conn.getConnection().prepareStatement(sql);
            pst.setInt(1, t.getGrupo().getId());
            pst.setInt(2, t.getLivro().getId());
            pst.setString(3, t.getDescricao());
            pst.setInt(4, idTopico);
            if (pst.executeUpdate() > 0) {
                result = true;
            }
            conn.closeAll(pst);

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TopicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public List<Topico> buscarPorGrupo(int idGrupo) {

        List<Topico> topicos = new ArrayList<>();

        conn = null;

        String sql = "SELECT * FROM Topico WHERE idGrupo =?";

        try {
            conn = new Conexao();

            PreparedStatement stat = conn.getConnection().prepareStatement(sql);
            stat.setInt(1, idGrupo);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {

                topicos.add(montaTopico(rs));
            }

            conn.closeAll(stat);

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(TopicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return topicos;
    }

    @Override
    public Topico buscarPorId(int idTopico) {
        Topico t = null;

        conn = null;

        try {
            conn = new Conexao();
            String sql = "SELECT * FROM Topico WHERE id = " + idTopico + "";
            Statement stat = conn.getConnection().createStatement();
            ResultSet rs = stat.executeQuery(sql);

            if (rs.next()) {
                return montaTopico(rs);
            }

            conn.closeAll(stat);

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TopicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    private Topico montaTopico(ResultSet rs) throws SQLException {
        LivroDaoIF livroDao = new LivroDao();
        GrupoDaoIF grupoDao = new GrupoDao();

        Topico t = new Topico();
        t.setId(rs.getInt("id"));
        t.setLivro(livroDao.buscarPorId(rs.getInt("idlivro")));
        t.setGrupo(grupoDao.buscarPorId(rs.getInt("idGrupo")));
        t.setDescricao(rs.getString("descricao"));

        return t;
    }
}
