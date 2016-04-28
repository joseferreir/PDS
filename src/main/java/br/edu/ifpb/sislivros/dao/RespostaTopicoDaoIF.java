/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.RespostaTopico;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public interface RespostaTopicoDaoIF {

    boolean adicionar(RespostaTopico rt);

    boolean atualizar(RespostaTopico rt, int idResposta);

    List<RespostaTopico> buscarRespostas(int idTopico);

    boolean remover(int idResposta);
    
}
