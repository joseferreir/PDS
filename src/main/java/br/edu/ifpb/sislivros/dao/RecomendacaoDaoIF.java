/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Recomendacao;
import java.util.List;

/**
 *
 * @author José
 */
public interface RecomendacaoDaoIF {

    public boolean adicionar(Recomendacao r);
    
    public List<Recomendacao> buscaPorRemetente(int idUsuario);

    public List<Recomendacao> buscaPorDestinario(int idUsuario);

    public boolean alterar(Recomendacao r, int idRecomendacao);
    
    public boolean apagar(int idDestinatario, int idRemetente);

}
