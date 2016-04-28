/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.exceptions.GrupoExistenteException;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.util.Map;

/**
 *
 * @author José
 */
public class AtualizarGrupoBo {
    public Map atualizar(Grupo grupo) throws GrupoExistenteException {
        Map<String, String> resultaso = VerificaGrupo.verificarGrupo(grupo);
        boolean cadastou = false;
        if (resultaso.get("passou").equalsIgnoreCase("true")) {
            cadastou = DaoFactory.createFactory(DaoFactory.DAO_BD).criaGrupoDAO().atualizar(grupo);
        }
        if (cadastou) {
            resultaso.put("passou", "true");
        } else {
            resultaso.put("passou", "false");
        }
        return resultaso;
    }
    
}
