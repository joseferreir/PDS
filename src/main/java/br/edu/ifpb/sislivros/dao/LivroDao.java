package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.conexao.Conexao;
import br.edu.ifpb.sislivros.entidades.Livro;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José
 */
public class LivroDao implements LivroDaoIF {

    private Conexao conn;

//    @Override
//    public Integer adicionar2(Livro livro) {
//        boolean result = false;
//        conn = null;
//
//        try {
//            conn = new Conexao();
//            
//            CachedRowSet crs = new CachedRowSetImpl();
//            
//            String sql = "INSERT INTO Livro(isbn, capa, titulo, ano, editora, autores, area_tema) VALUES (?, ?, ?, ?, ?, ?, ?)";
//            crs.setCommand(sql);
//            
//            crs.setString(1, livro.getIsbn());
//            crs.setString(2, livro.getCapa());
//            crs.setString(3, livro.getTitulo());
//            crs.setInt(4, livro.getAnoPublicacao());
//            crs.setString(5, livro.getEditora());
//            crs.setString(6, livro.getAutores());
//            crs.setString(6, livro.getAutores());
//            crs.setString(7, livro.getAreaTema());
//            
//            crs.acceptChanges(conn.getConnection());
//            
//            Integer id = crs.getInt("id");  
//            
//            conn.getConnection().close();
//            
//            return id;
//            
//
//        } catch (SQLException | IOException | ClassNotFoundException ex) {
//            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        //return result;
//        return -1;
//    }
    @Override
    public boolean adicionar(Livro livro) {
        boolean result = false;
        conn = null;
        PreparedStatement stmt;

        try {
            conn = new Conexao();

            String sql = "INSERT INTO Livro(isbn, capa, titulo, ano, editora, autores, area_tema) VALUES (?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.getConnection().prepareStatement(sql);

            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getCapa());
            stmt.setString(3, livro.getTitulo());
            stmt.setInt(4, livro.getAnoPublicacao());
            stmt.setString(5, livro.getEditora());
            stmt.setString(6, livro.getAutores());
            stmt.setString(6, livro.getAutores());
            stmt.setString(7, livro.getAreaTema());
            if (stmt.executeUpdate() > 0) {
                result = true;
            }
            conn.closeAll(stmt);

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean mudarCapa(int idLivro, String capa) {
        boolean result = false;
        conn = null;
        PreparedStatement stmt;
        try {
            conn = new Conexao();
            String sql = "UPDATE Livro SET capa = ? WHERE id = ?";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setString(1, capa);
            stmt.setInt(2, idLivro);
            if (stmt.executeUpdate() > 0) {
                result = true;
            }
            conn.closeAll(stmt);

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean remover(int id) {
        boolean result = false;
        conn = null;

        try {
            conn = new Conexao();
            String sql = "DELETE FROM Livro WHERE id ='" + id + "'";
            PreparedStatement stm = conn.getConnection().prepareStatement(sql);

            if (stm.executeUpdate() > 0) {
                result = true;
            }
            conn.closeAll(stm);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean atualizar(Livro livro) {
        boolean result = false;
        conn = null;

        try {
            conn = new Conexao();
            String sql = "UPDATE Livro SET isbn = ?, capa = ?, titulo = ?, ano = ?, "
                    + "editora = ?, autores = ?, area_tema = ? WHERE id = ?";
            PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getCapa());
            stmt.setString(3, livro.getTitulo());
            stmt.setInt(4, livro.getAnoPublicacao());
            stmt.setString(5, livro.getEditora());
            stmt.setString(6, livro.getAutores());
            stmt.setString(7, livro.getAreaTema());
            stmt.setInt(8, livro.getId());
            if (stmt.executeUpdate() > 0) {
                result = true;
            }
            conn.closeAll(stmt);
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Livro buscarPorId(int id) {

        String sql = "SELECT * FROM Livro WHERE id ='" + id + "'";

        return retornaLivro(sql).get(0);
    }

    @Override
    public Livro buscarPorIsbn(String isbn) {

        String sql = "SELECT * FROM Livro WHERE isbn ='" + isbn + "'";

        List<Livro> result = retornaLivro(sql);
        if (!result.isEmpty()) {
            return retornaLivro(sql).get(0);
        }
        return null;
    }

    public enum TipoBusca {

        INCLUSIVA(" OR "), EXCLUSIVA(" AND ");

        public String tipo;

        TipoBusca(String tipo) {
            this.tipo = tipo;
        }

    }

    private List<Livro> buscarPorAtributos(Map<String, String> atributos, TipoBusca tipo) {

        String min      = (String) atributos.get("min"); atributos.remove("min");
        String max      = (String) atributos.get("max"); atributos.remove("max");

        
        StringBuilder sql = new StringBuilder("SELECT * FROM Livro WHERE (");
        
        Set<String> keys = atributos.keySet();
        Iterator<String> it = keys.iterator();

        String key;
        while (it.hasNext()) {
            key = it.next();
            sql.append(key);
            sql.append(" ilike ");
            sql.append("'%").append(atributos.get(key)).append("%'");
            if (it.hasNext()) {
                sql.append(tipo.tipo);
            }
        }
        sql.append(") AND rating between ").append(min).append(" AND ").append(max);

        List<Livro> result = retornaLivro(sql.toString());
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }
    
    private List<Livro> buscarPorAtributosOrdenados(Map<String, String> atributos, TipoBusca tipo) {

        String ordem    = (String) atributos.get("ordem"); atributos.remove("ordem");
        String min      = (String) atributos.get("min"); atributos.remove("min");
        String max      = (String) atributos.get("max"); atributos.remove("max");
        String sentido  = (String) atributos.get("sentido"); atributos.remove("sentido");
        
        StringBuilder sql = new StringBuilder("SELECT * FROM Livro WHERE (");
        
        Set<String> keys = atributos.keySet();
        Iterator<String> it = keys.iterator();

        String key;
        while (it.hasNext()) {
            key = it.next();
            sql.append(key);
            sql.append(" ilike ");
            sql.append("'%").append(atributos.get(key)).append("%'");
            if (it.hasNext()) {
                sql.append(tipo.tipo);
            }
        }
        
        sql.append(") AND rating between ").append(min).append(" AND ").append(max);
        sql.append(" ORDER BY ").append(ordem).append(" ");
        sql.append(sentido);

        List<Livro> result = retornaLivro(sql.toString());
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

    @Override
    public List<Livro> buscarInclusivaPorAtributos(Map<String, String> atributos) {
        return buscarPorAtributosOrdenados(atributos, TipoBusca.INCLUSIVA);
    }

    @Override
    public List<Livro> buscarExclusivaPorAtributos(Map<String, String> atributos) {

        return buscarPorAtributos(atributos, TipoBusca.EXCLUSIVA);
    }

    @Override
    public List<Livro> retornarTodos() {

        String sql = "SELECT * FROM Livro ";
        List<Livro> result = retornaLivro(sql.toString());
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }

    }

    public List<Livro> retornaLivro(String sql) {
        List<Livro> livros = new ArrayList<>();

        conn = null;

        try {
            conn = new Conexao();

            PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livros.add(montarLivro(rs));

            }
            conn.closeAll(stmt);

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;

    }

    private Livro montarLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();

        livro.setId(rs.getInt("id"));
        livro.setIsbn(rs.getString("isbn"));
        livro.setCapa(rs.getString("capa"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAnoPublicacao(rs.getInt("ano"));
        livro.setEditora(rs.getString("editora"));
        livro.setAutores(rs.getString("autores"));
        livro.setAreaTema(rs.getString("area_tema"));
        livro.setRating(rs.getBigDecimal("rating"));
        return livro;
    }
}
