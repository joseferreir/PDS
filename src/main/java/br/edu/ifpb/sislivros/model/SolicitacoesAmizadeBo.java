/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Amizade;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class SolicitacoesAmizadeBo {
    
    private DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
    
    public List<Amizade> buscarSolicitacoes(Integer id){
        return factory.criaAmizadeDao().buscarSolicitacoes(id);
                
    }
    
    public boolean aceitarSolicitacao(int idSolicitante, int idSolicitado){
        return factory.criaAmizadeDao().confirmarAmizade(idSolicitante, idSolicitado);
    }
    
    public boolean rejeitarSolicitacao(int idSolicitante, int idSolicitado){
        return factory.criaAmizadeDao().removerSolicitacao(idSolicitante, idSolicitado);
    }
    
    public boolean solicitarAmizade(int idSolicitante, int IdSolicitado){
        AmizadeBo amizadeBo = new AmizadeBo();
        if (!amizadeBo.saoAmigos(idSolicitante, IdSolicitado) && !amizadeBo.ehSolicitanteAmizade(idSolicitante, IdSolicitado)) {
            return factory.criaAmizadeDao().adicionarSolicitacao(idSolicitante, IdSolicitado);
        }
        return false;
    }
    
}
