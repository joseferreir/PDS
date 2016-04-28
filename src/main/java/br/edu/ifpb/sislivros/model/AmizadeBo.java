/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Amizade;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class AmizadeBo {
    
    private DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
    
    public boolean saoAmigos(int idUser1, int idUser2){
        return factory.criaAmizadeDao().saoAmigos(idUser1, idUser2);
    }
    
    public boolean ehSolicitanteAmizade(int idSolicitante, int idSolicitado){
        List<Amizade> solicitacoes = factory.criaAmizadeDao().buscarSolicitacoes(idSolicitado);
        
        for (Amizade a : solicitacoes){
            if (a.getSolicitante().getId() == idSolicitante)
                return true;
        }
        return false;
    }
    
    public List<Usuario> buscarAmigos(Usuario usuario){
        return factory.criaAmizadeDao().carregarAmigos(usuario.getId());
    }
    
}
