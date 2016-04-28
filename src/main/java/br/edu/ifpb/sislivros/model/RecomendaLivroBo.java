/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Recomendacao;
import br.edu.ifpb.sislivros.factory.DaoFactory;

/**
 *
 * @author José
 */
public class RecomendaLivroBo {

    public boolean recomendarLivro(Recomendacao r) {

        if (verificarRecomendacao(r)) {
            if (new AmizadeBo().saoAmigos(r.getDestinatario().getId(), r.getRemetente().getId())) {
                return DaoFactory.createFactory(DaoFactory.DAO_BD).criaRecomendacao().adicionar(r);
            }
        }

        return false;
    }

    public boolean alteraRecomendacao(Recomendacao r, int idRecomendacao) {
        if (verificarRecomendacao(r)) {
            return DaoFactory.createFactory(DaoFactory.DAO_BD).criaRecomendacao().alterar(r, idRecomendacao);
        } else {
            return false;
        }
    }

    public boolean remover(int idUserRemetente, int idUserDestinatario) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaRecomendacao().apagar(idUserRemetente, idUserDestinatario);
    }

    private static boolean verificarRecomendacao(Recomendacao r) {
        if (r.getRemetente() != null && r.getDestinatario() != null && r.getLivro() != null) {
            return true;
        }
        return false;
    }

}
