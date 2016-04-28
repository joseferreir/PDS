/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Recomendacao;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.util.List;

/**
 *
 * @author José
 */
public class RecomendacaoBuscarBo {

    public List<Recomendacao> buscarRemetente(int idUsuario) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaRecomendacao().buscaPorRemetente(idUsuario);
    }

    public List<Recomendacao> buscarDestinario(int idUsuario) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaRecomendacao().buscaPorDestinario(idUsuario);
    }
}
