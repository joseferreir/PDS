package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.ConexaoIF;
import br.edu.ifpb.sislivros.conexao.DataBaseException;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
import java.sql.Date;
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
public class UsuarioDao implements UsuarioDaoIF {

    private ConexaoIF conn;
    private String operacao = null;

    public UsuarioDao() {
    }

    @Override
    public boolean adicionar(Usuario usuario) {
        boolean result = false;

        PreparedStatement stat = null;

        try {
            conn = new Conexao();

            stat = conn.getConnection().prepareStatement("INSERT INTO Usuario (foto, email, senha, "
                    + "nomeCompleto, apelido, dataNascimento, cidade, estado)"
                    + "VALUES (?,?,?,?,?,?,?,?)");
            stat.setString(1, usuario.getFoto());
            stat.setString(2, usuario.getEmail());
            stat.setString(3, usuario.getSenha());
            stat.setString(4, usuario.getNome());
            stat.setString(5, usuario.getApelido());
            stat.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            stat.setString(7, usuario.getCidade());
            stat.setString(8, usuario.getEstado());

            if (stat.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conn.closeAll(stat);
            } catch (DataBaseException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;

    }

    @Override
    public boolean atualizar(Usuario usuario, Integer id) {
        boolean result = false;
        PreparedStatement pst = null;

        try {
            conn = new Conexao();
            String sql = "UPDATE Usuario SET nomeCompleto = ?, apelido = ?, cidade = ?, estado = ?, foto = ?, email = ?, senha= ? "
                    + "WHERE id = ?";
            pst = conn.getConnection().prepareStatement(sql);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getApelido());
            pst.setString(3, usuario.getCidade());
            pst.setString(4, usuario.getEstado());
            pst.setString(5, usuario.getFoto());
            pst.setString(6, usuario.getEmail());
            pst.setString(7, usuario.getSenha());
            pst.setInt(8, Integer.parseInt(id.toString()));
            if (pst.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(pst);
            } catch (DataBaseException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;

    }

    @Override
    public boolean remover(String email) {
        boolean result = false;
        PreparedStatement ps = null;

        try {
            conn = new Conexao();
            String sql = "DELETE FROM Usuario WHERE email = ?";
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(ps);
            } catch (DataBaseException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    @Override
    public Usuario login(String email, String senha) {

        PreparedStatement ps = null;
        Usuario user = null;
        conn = null;
        String sql = "SELECT * FROM Usuario WHERE email = '" + email + "' AND senha = '" + senha + "'";

        try {

            conn = new Conexao();
            ps = conn.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                user = montarUsuario(result);
            }
            conn.closeAll(ps);
        } catch (SQLException | IOException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

    @Override
    public boolean atualizarIsAdm(Integer id, boolean habilitar) {
        boolean result = false;
        PreparedStatement pst = null;

        try {
            conn = new Conexao();
            String sql = "UPDATE Usuario SET eh_admin = ? WHERE id = ?";
            pst = conn.getConnection().prepareStatement(sql);
            pst.setBoolean(1, habilitar);
            pst.setInt(2, id);
            if (pst.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(pst);
            } catch (DataBaseException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    @Override
    public Usuario buscarPorId(int id) {
        operacao = "SELECT * FROM Usuario WHERE id = '" + id + "'";

        return buscarUser(operacao).get(0);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario resultado = null;

        try {
            conn = new Conexao();
            String consulta = "SELECT * FROM Usuario WHERE email = '" + email + "'";
            PreparedStatement ps = conn.getConnection().prepareStatement(consulta);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultado = montarUsuario(rs);
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    @Override
    public List<Usuario> buscarPorNome(String nome) {
        
        List<Usuario> lista = new ArrayList<>();

        try {
            conn = new Conexao();
            String consulta = "SELECT * FROM Usuario WHERE nomeCompleto ilike '%" + nome + "%'";
            PreparedStatement ps = conn.getConnection().prepareStatement(consulta);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(montarUsuario(rs));
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public List<Usuario> buscarTodos() {
        operacao = "SELECT * FROM USUARIO";
        return buscarUser(operacao);

    }

    private List<Usuario> buscarUser(String sql) {
        List<Usuario> lista = new ArrayList<>();

        PreparedStatement pst = null;

        try {
            conn = new Conexao();
            pst = conn.getConnection().prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(montarUsuario(rs));
            }

            conn.closeAll(pst);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!lista.isEmpty()) {
            return lista;
        }
        return Collections.EMPTY_LIST;
    }

    private Usuario montarUsuario(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();
        user.setId(rs.getInt("id"));
        user.setNome(rs.getString("nomeCompleto"));
        user.setApelido(rs.getString("apelido"));
        user.setCidade(rs.getString("cidade"));
        user.setEstado(rs.getString("estado"));
        user.setFoto(rs.getString("foto"));
        user.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
        user.setAdmin(rs.getBoolean("eh_admin"));
        user.setEmail(rs.getString("email"));
        user.setSenha(rs.getString("senha"));

        return user;
    }

    
}
