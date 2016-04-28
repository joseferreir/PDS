/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.conexao;

/**
 *
 * @author susanneferraz
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public interface ConexaoIF {

	public Connection getConnection();

	public void closeAll(PreparedStatement stat) throws DataBaseException;

	public void closeAll(Statement stat) throws DataBaseException;
}
