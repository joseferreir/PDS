/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.dao.GrupoDaoIF;
import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.util.List;

/**
 *
 * @author José
 */
public class BuscarGrupoBo {

    GrupoDaoIF factoy = DaoFactory.createFactory(DaoFactory.DAO_BD).criaGrupoDAO();

    public BuscarGrupoBo() {
    }

    public Grupo buscarPorId(int idGrupo) {
        return factoy.buscarPorId(idGrupo);
    }

    public Grupo buscarPorNome(String nome) {
        return factoy.buscarPorNome(nome);

    }

    public List<Grupo> buscarTodos() {
        return factoy.buscarTodos();
    }

}
