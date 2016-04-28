package br.edu.ifpb.sislivros.dao;
import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.entidades.Amizade;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
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
public class AmizadeDao implements AmizadeDaoIF {

    private Conexao conn = null;
    private UsuarioDaoIF userDao = null;

    private boolean adicionarComStatus(int idSolicitante, int idSolicitado, boolean statusAceito) {
        boolean result = false;

        try {
            conn = new Conexao();
            String operacao = "INSERT INTO Amizade (idSolicitante, idSolicitado, estah_aceito) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.getConnection().prepareStatement(operacao);
            pst.setInt(1, idSolicitante);
            pst.setInt(2, idSolicitado);
            pst.setBoolean(3, statusAceito);

            int insertResult = pst.executeUpdate();

            if (insertResult > 0) {
                result = true;
            }

            conn.closeAll(pst);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }


    @Override
    public boolean adicionarSolicitacao(int idSolicitante, int idSolicitado) {
        boolean result = false;

        if (!saoAmigos(idSolicitante, idSolicitado)) {
            Amizade verificaSolicitacaoExistente = buscarSolicitacao(idSolicitante, idSolicitado);
            Amizade verificaSolicitacaoExistente2 = buscarSolicitacao(idSolicitado, idSolicitante);

            if (verificaSolicitacaoExistente == null & verificaSolicitacaoExistente2 == null) {
                adicionarComStatus(idSolicitante, idSolicitado, false);
                result = true;
            }
        }

        return result;
    }


    @Override
    public boolean removerSolicitacao(int idSolicitante, int idSolicitado) {
        boolean result = false;

        conn = null;

        try {
            conn = new Conexao();
            String busca = "DELETE FROM Amizade WHERE idSolicitante = ? AND idSolicitado = ? AND estah_aceito = false";

            PreparedStatement ps = conn.getConnection().prepareStatement(busca);
            ps.setInt(1, idSolicitante);
            ps.setInt(2, idSolicitado);

            int manipulacoes = ps.executeUpdate();

            conn.closeAll(ps);

            if (manipulacoes > 0) {
                return true;
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Amizade buscarSolicitacao(int idSolicitante, int idSolicitado) {
        Amizade solicitacao = null;
        conn = null;

        try {
            conn = new Conexao();
            String busca = "SELECT * FROM Amizade WHERE idSolicitante = ? AND idSolicitado = ? AND estah_aceito = false";

            PreparedStatement ps = conn.getConnection().prepareStatement(busca);
            ps.setInt(1, idSolicitante);
            ps.setInt(2, idSolicitado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                solicitacao = montarAmizade(rs, false);
            }
            

            conn.closeAll(ps);

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return solicitacao;
    }


    @Override
    public List<Amizade> buscarSolicitacoes(int idUsuarioSolicitado) {
        List<Amizade> solicitacoes = null;
        conn = null;

        try {
            conn = new Conexao();
            String busca = "SELECT * FROM Amizade WHERE idSolicitado = ? AND estah_aceito = false";
            PreparedStatement ps = conn.getConnection().prepareStatement(busca);
            ps.setInt(1, idUsuarioSolicitado);

            ResultSet rs = ps.executeQuery();

            solicitacoes = new ArrayList<>();

            while (rs.next()) {
                Amizade a = new Amizade();
                Usuario user1, user2 = null;

                userDao = new UsuarioDao();

                user1 = userDao.buscarPorId(rs.getInt("idSolicitante"));
                user2 = userDao.buscarPorId(rs.getInt("idSolicitado"));

                a.setSolicitante(user1);
                a.setSolicitado(user2);
                a.setConfirmada(false);
                solicitacoes.add(a);
            }
            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return solicitacoes;
    }

    private boolean atualizarStatus(Amizade amizade, boolean status) {
        boolean result = false;

        try {
            conn = new Conexao();
            String operacao = "UPDATE Amizade SET estah_aceito = ? WHERE idSolicitante = ? AND idSolicitado = ?";
            PreparedStatement pst = conn.getConnection().prepareStatement(operacao);
            pst.setBoolean(1, status);
            pst.setInt(2, amizade.getSolicitante().getId());
            pst.setInt(3, amizade.getSolicitado().getId());

            int insertResult = pst.executeUpdate();

            conn.closeAll(pst);

            if (insertResult > 0) {
                result = true;
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean confirmarAmizade(int idUser1, int idUser2) {
        boolean result = false;
        Amizade solicitacao = buscarSolicitacao(idUser1, idUser2);
        if (solicitacao != null) {
            result = atualizarStatus(solicitacao, true);
        }

        return result;
    }


    @Override
    public Amizade localizar(int idUser1, int idUser2) {
        Amizade a = null;
        try {
            conn = new Conexao();
            String consulta = "(SELECT * FROM Amizade WHERE idSolicitante = ? AND idSolicitado = ? AND estah_aceito = TRUE)"
                    + "UNION (SELECT * FROM Amizade WHERE idSolicitante = ? AND idSolicitado = ? AND estah_aceito = TRUE)";

            PreparedStatement ps = conn.getConnection().prepareStatement(consulta);
            ps.setInt(1, idUser1);
            ps.setInt(2, idUser2);
            ps.setInt(3, idUser2);
            ps.setInt(4, idUser1);

            ResultSet rs = ps.executeQuery();
            a = montarAmizade(rs, true);

            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    @Override
    public boolean remover(int idUser1, int idUser2) {
        boolean result = false;

        if (saoAmigos(idUser1, idUser2)) {
            try {
                conn = new Conexao();
                String operacao = "DELETE FROM Amizade WHERE ((idSolicitante = ? AND idSolicitado = ?) OR (idSolicitante = ? AND idSolicitado = ?)) AND estah_aceito = TRUE";

                PreparedStatement ps = conn.getConnection().prepareStatement(operacao);
                ps.setInt(1, idUser1);
                ps.setInt(2, idUser2);
                ps.setInt(3, idUser2);
                ps.setInt(4, idUser1);

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    result = true;
                }
                conn.closeAll(ps);
            } catch (SQLException | IOException | ClassNotFoundException ex) {
                Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public List<Amizade> localizarAmizades(int idUser) {
        List<Amizade> result = new ArrayList<>();

        try {
            conn = new Conexao();
            String consulta = "(SELECT * FROM Amizade WHERE (idSolicitante = ? OR idSolicitado = ?) AND estah_aceito = TRUE)";

            PreparedStatement ps = conn.getConnection().prepareStatement(consulta);
            ps.setInt(1, idUser);
            ps.setInt(2, idUser);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(montarAmizade(rs, true));
            }

            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public List<Usuario> carregarAmigos(int idUser) {
        List<Usuario> amigos = null;

        List<Amizade> amizades = localizarAmizades(idUser);

        if (amizades != null) {
            amigos = new ArrayList<>();

            for (Amizade a : amizades) {
                if (a.getSolicitante().getId() == idUser) {
                    amigos.add(a.getSolicitado());
                } else {
                    amigos.add(a.getSolicitante());
                }
            }
        }
        return amigos;
    }

    @Override
    public void excluiTodasAmizades(int idUsuario) {

        try {
            conn = new Conexao();
            String operacao = "DELETE FROM Amizade WHERE idSolicitante = ? OR idSolicitado = ?";

            PreparedStatement ps = conn.getConnection().prepareStatement(operacao);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();

            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Amizade montarAmizade(ResultSet rs, boolean saoAmigos) throws SQLException {
        Amizade a = new Amizade();
        Usuario user1, user2 = null;


        userDao = new UsuarioDao();

        user1 = userDao.buscarPorId(rs.getInt("idSolicitante"));
        user2 = userDao.buscarPorId(rs.getInt("idSolicitado"));

        a.setSolicitante(user1);
        a.setSolicitado(user2);
        a.setConfirmada(saoAmigos);


        return a;
    }
    @Override
    public boolean saoAmigos(int idUser1, int idUser2) {
        boolean result = false;
        try {
            conn = new Conexao();
            String consulta = "SELECT * FROM Amizade WHERE estah_aceito = TRUE AND"
                    + "((idSolicitante = ? AND idSolicitado = ?) OR (idSolicitante = ? AND idSolicitado = ?))";

            PreparedStatement ps = conn.getConnection().prepareStatement(consulta);
            ps.setInt(1, idUser1);
            ps.setInt(2, idUser2);
            ps.setInt(3, idUser2);
            ps.setInt(4, idUser1);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getBoolean("estah_aceito");
            } else {
            }

            conn.closeAll(ps);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
