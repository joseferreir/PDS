/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;

/**
 *
 * @author José
 */
public class ControleUsuario {

    public boolean adicionar(Usuario usuario) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().adicionar(usuario);
    }

    public boolean atualizar(Usuario usuario, Integer id) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().atualizar(usuario, id);
    }

    public boolean remover(String email) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().remover(email);
    }

//    public List<Usuario> consultarPorApelido(String userApelido) {
//        return DaoFactory.createFactory().criaUsuarioDAO().consultarPorApelido(userApelido);
//    }
//
//    public Usuario consultarPorEmail(String userEmail) {
//        return DaoFactory.createFactory().criaUsuarioDAO().consultarPorEmail(userEmail);
//    }

//    public boolean alterarSenha(Usuario usuario) {
//        return DaoFactory.createFactory().criaUsuarioDAO().atualizar(usuario);
//    }

    public Usuario login(String email, String senha) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().login(email, senha);
    }
    
    public Usuario buscarPorId(Integer id) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().buscarPorId(id);
    }

    public boolean atualizarParaAdm(Integer id, boolean habilitar) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().atualizarIsAdm(id, habilitar);
    }
    
//    public List<Amizade> getSolicitacoes(Integer id){
//        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaSolicitacaoDao().buscarPorId(id);
//    }

   
}
