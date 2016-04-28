/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.List;

/**
 *
 * @José
 */
public interface ParticipaGrupoDaoIF {

//    public boolean solicitarParticipacao(int idUsuario, int idGrupo);
//
//    public boolean aceitarusuario(int idUsuario);
    
    
    public boolean entrar(int idUsuario, int idGrupo);
    
    public boolean sair(int idUsuario, int idGrupo);
    
    public boolean ehParticipante(int idUsuario, int idGrupo);

    public List<Grupo> buscarGruposPorUsuario(int idUsuario);

    public List<Usuario> buscarParticipantes(int idGrupo);
}
