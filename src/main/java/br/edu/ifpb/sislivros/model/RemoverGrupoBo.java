/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.factory.DaoFactory;

/**
 *
 * @author José
 */
public class RemoverGrupoBo {

    public boolean removerGrupo(Grupo grupo) {
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaGrupoDAO().remover(grupo.getId());

    }
}
