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
public class AlterarSenhaBo {
    
    public boolean execute(Usuario usuario, String senhaAntiga, String novaSenha){
        boolean result = false;
        
        if (usuario != null && senhaAntiga != null & novaSenha != null){
            if (usuario.getSenha().equals(senhaAntiga)){
                usuario.setSenha(novaSenha);
                return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().atualizar(usuario, usuario.getId());
            }
        }
        
        return result;
    }
}
