/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.entidades.Usuario;

/**
 *
 * @author Natarajan
 */
public class ExcluirUsuarioBo {
    
    public boolean excluir(Usuario usuario){
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().remover(usuario.getEmail());
    }
    
    //pode incluir outras regras de negócio aqui que devem ser verificadas antes de excluir um usuário
    //como por exemplo:
    //apagar os amigos dele antes, ou apagar as recomendações e avaliações. Pode??
    
}
