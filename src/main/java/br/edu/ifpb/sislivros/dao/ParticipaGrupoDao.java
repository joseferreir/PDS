package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.conexao.ConexaoIF;
import br.edu.ifpb.sislivros.conexao.DataBaseException;
import br.edu.ifpb.sislivros.entidades.Grupo;
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
public class ParticipaGrupoDao implements ParticipaGrupoDaoIF {

    private ConexaoIF conn;

    public ParticipaGrupoDao() {
    }

//    @Override
//    public boolean solicitarParticipacao(int idUsuario, int idGrupo) {
//        boolean result = false;
//        PreparedStatement pst = null;
//
//        try {
//            conn = new Conexao();
//            String sql = "INSERT INTO participagrupo (idUsuario,idGrupo) VALUES('" + idUsuario + "','" + idGrupo + "') ";
//            pst = conn.getConnection().prepareStatement(sql);
//
//            if (pst.executeUpdate() > 0) {
//                result = true;
//            }
//        } catch (SQLException | IOException e) {
//            System.err.println("Erro " + e.getMessage());
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                conn.closeAll(pst);
//            } catch (DataBaseException ex) {
//                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        return result;
//    }
//
//    @Override
//    public boolean aceitarusuario(int idUsuario) {
//        boolean result = false;
//        PreparedStatement pst = null;
//
//        try {
//            conn = new Conexao();
//            String sql = "UPDATE participagrupo SET eh_parpicipante = true WHERE idusuario ='" + idUsuario + "' AND eh_parpicipante = false";
//            pst = conn.getConnection().prepareStatement(sql);
//
//            if (pst.executeUpdate() > 0) {
//                result = true;
//            }
//        } catch (SQLException | IOException e) {
//            System.err.println("Erro " + e.getMessage());
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                conn.closeAll(pst);
//            } catch (DataBaseException ex) {
//                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        return result;
//    }
    @Override
    public List<Grupo> buscarGruposPorUsuario(int idUsuario) {
        PreparedStatement pst = null;
        List<Grupo> result = new ArrayList<>();

        try {
            conn = new Conexao();
//            String sql = "SELECT U.id idUser,U.nomecompleto participante, U.foto fotop FROM usuario U,participagrupo PG WHERE U.id= PG.idUsuario AND PG.idGrupo ='" + idGrupo + "'";
            String sql = "SELECT * FROM ParticipaGrupo WHERE idUsuario = '" + idUsuario + "' AND eh_participante = TRUE";
            pst = conn.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                GrupoDaoIF gdao = new GrupoDao();
                Grupo g = gdao.buscarPorId(rs.getInt("idGrupo"));
                if (g != null) {
                    result.add(g);
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
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
    public List<Usuario> buscarParticipantes(int idGrupo) {
        PreparedStatement pst = null;
        List<Usuario> result = new ArrayList<>();

        try {
            conn = new Conexao();
//            String sql = "SELECT U.id idUser,U.nomecompleto participante, U.foto fotop FROM usuario U,participagrupo PG WHERE U.id= PG.idUsuario AND PG.idGrupo ='" + idGrupo + "'";
            String sql = "SELECT * FROM ParticipaGrupo WHERE idGrupo = '" + idGrupo + "' AND eh_participante = TRUE";
            pst = conn.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                UsuarioDaoIF udao = new UsuarioDao();
                Usuario u = udao.buscarPorId(rs.getInt("idUsuario"));
                if (u != null) {
                    result.add(u);
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
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
    public boolean entrar(int idUsuario, int idGrupo) {
        boolean result = false;

        PreparedStatement pst = null;

        try {
            conn = new Conexao();

            String consulta = "SELECT * FROM ParticipaGrupo WHERE idUsuario = ? AND idGrupo = ?";
            pst = conn.getConnection().prepareStatement(consulta);
            pst.setInt(1, idUsuario);
            pst.setInt(2, idGrupo);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                result = alteraStatus(idUsuario, idGrupo, true);
            } else {
                consulta = "INSERT INTO ParticipaGrupo (idUsuario, idGrupo) VALUES('" + idUsuario + "','" + idGrupo + "') ";
                pst = conn.getConnection().prepareStatement(consulta);

                if (pst.executeUpdate() > 0) {
                    result = true;
                }
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

    private boolean alteraStatus(int idUsuario, int idGrupo, boolean participa) {
        boolean result = false;
        PreparedStatement pst = null;

        try {
            conn = new Conexao();
            String sql = "UPDATE participagrupo SET eh_participante = ? WHERE idusuario = ? AND idGrupo = ?";
            pst = conn.getConnection().prepareStatement(sql);
            pst.setBoolean(1, participa);
            pst.setInt(2, idUsuario);
            pst.setInt(3, idGrupo);

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
    public boolean sair(int idUsuario, int idGrupo) {
        if (ehParticipante(idUsuario, idGrupo)) {
            return alteraStatus(idUsuario, idGrupo, false);
        }
        return false;
    }

    @Override
    public boolean ehParticipante(int idUsuario, int idGrupo) {
        PreparedStatement pst = null;
        boolean result = false;

        try {
            conn = new Conexao();
            String sql = "SELECT * FROM ParticipaGrupo WHERE eh_Participante = 'TRUE' AND idUsuario = '" + idUsuario + "' AND idGrupo = '" + idGrupo + "'";
            pst = conn.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next() && rs.getBoolean("eh_participante") == true) {
                result = true;
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                conn.closeAll(pst);
            } catch (DataBaseException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

}
