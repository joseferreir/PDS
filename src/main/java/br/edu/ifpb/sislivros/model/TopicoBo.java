/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Topico;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class TopicoBo {
    private DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
    
    
    public int adicionaTopico(Topico topico, Usuario usuarioLogado){
        int result = 0; 
        
        boolean ehUsuarioDoGrupo = factory.criaParticipaGrupoDao().ehParticipante(usuarioLogado.getId(), topico.getGrupo().getId());
        if (ehUsuarioDoGrupo == true){
            result = factory.criaTopicoDao().adicionar2(topico);
        }
        
        return result;
    }
    
    public Topico buscarPorId(int idTopico){
        return factory.criaTopicoDao().buscarPorId(idTopico);
    }
    
    public List<Topico> buscarPorGrupo(int idGrupo){
        return factory.criaTopicoDao().buscarPorGrupo(idGrupo);
    }
}
