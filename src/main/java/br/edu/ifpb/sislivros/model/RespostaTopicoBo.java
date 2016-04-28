/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.RespostaTopico;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class RespostaTopicoBo {
    
    private DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
    
    
    public boolean adicionarResposta(RespostaTopico rt){
        boolean result = false; 
        
        Usuario u = rt.getUsuario();
        Grupo g = rt.getTopico().getGrupo();
        boolean ehUsuarioDoGrupo = factory.criaParticipaGrupoDao().ehParticipante(u.getId(), g.getId());
        if (ehUsuarioDoGrupo == true){
            result = factory.criaRespostaTopicoDao().adicionar(rt);
        }
        
        return result;
    }
    
    public List<RespostaTopico> buscarRespostas(int idTopico){
        return factory.criaRespostaTopicoDao().buscarRespostas(idTopico);
    }
    
}
