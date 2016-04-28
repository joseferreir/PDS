/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.dao.UsuarioDaoIF;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.List;
/**
 *
 * @author Natarajan
 */
public class BuscaUsuarioBo {
    
    private UsuarioDaoIF daoUsuario = null;
    private DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
    
    public Usuario buscarPorId(Integer id){
        daoUsuario = factory.criaUsuarioDAO();
        return daoUsuario.buscarPorId(id);
    }
    
    public Usuario buscarPorEmail(String email){
        daoUsuario = factory.criaUsuarioDAO();
        return daoUsuario.buscarPorEmail(email);
    }
    
    public List<Usuario> buscarPorNome(String nome){
        daoUsuario = factory.criaUsuarioDAO();
        return daoUsuario.buscarPorNome(nome);
    }
    
    public List<Usuario> buscarTodos(){
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().buscarTodos();
    }
    
}
