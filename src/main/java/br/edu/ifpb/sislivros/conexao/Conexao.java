/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Conexao implements ConexaoIF {

    private Connection conn;
    private static Properties prop = null;

    public Conexao() throws SQLException, IOException, ClassNotFoundException {
        try {
//            String driver = "org.postgresql.Driver";
//            String url = "jdbc:postgresql://localhost:5432/sislivros";
//            String user = "postgres";
//            String password = "123456";            
//            prop.load(new FileInputStream("/resources/connection.properties"));
//            prop.load(new FileInputStream(getClass().getResource("/resources/conexao.properties").getPath()));
            
            
            //carrega as propriedades do arquivo connection.properties da pasta resources
            prop = new Properties();            
            prop.load(new FileInputStream(getClass().getResource("/recursos/connection.properties").toURI().getPath()));
            
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (IOException | ClassNotFoundException | SQLException | URISyntaxException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }

    @Override
    public void closeAll(PreparedStatement stat) throws DataBaseException {
        try {
            stat.close();
            this.conn.close();

        } catch (SQLException e) {
            throw new DataBaseException("Falha ao fechar conexões: " + e.getMessage());
        }

    }

    @Override
    public void closeAll(Statement stat) throws DataBaseException {
        try {
            stat.close();
            this.conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("Falha ao fechar conexões: " + e.getMessage());
        }
    }

}
