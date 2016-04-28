/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.dao.ParticipaGrupoDaoIF;
import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.util.List;

/**
 *
 * @author José
 */
public class ParticipanteGrupoBo {

    private ParticipaGrupoDaoIF dao = DaoFactory.createFactory(DaoFactory.DAO_BD).criaParticipaGrupoDao();
    
//    public boolean solicitarParticipacao(int idUsuario, int idGrupo) {
//        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaParticipaGrupoDao().solicitarParticipacao(idUsuario, idGrupo);
//    }
//    
//    public boolean aceitarusuario(int idUsuario) {
//        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaParticipaGrupoDao().aceitarusuario(idUsuario);
//        
//    }
    
    public boolean entrarGrupo (int idUsuario, int idGrupo){
        return dao.entrar(idUsuario, idGrupo);
    }
    
    public boolean sairGrupo(int idUsuario, int idGrupo) {
        return dao.sair(idUsuario, idGrupo);
    }
    
    public boolean ehParticipante (int idUsuario, int idGrupo) {
        return dao.ehParticipante(idUsuario, idGrupo);
    }
    
    public List<Usuario> buscarParticipantes (int idGrupo){
        return dao.buscarParticipantes(idGrupo);
    }
    
    public List<Grupo> buscarGrupos (int idUsuario){
        return dao.buscarGruposPorUsuario(idUsuario);
    }
  
    
}
