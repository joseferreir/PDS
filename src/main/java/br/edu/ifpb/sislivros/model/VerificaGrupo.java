/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.exceptions.GrupoExistenteException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author José
 */
public class VerificaGrupo {
     public static Map<String, String> verificarGrupo(Grupo grupo) throws GrupoExistenteException {
        Map resultado = new HashMap();
        if (grupo.getNome() == null || grupo.getNome().trim().isEmpty()) {
            resultado.put("nome", "Informe o nome do grupo ");
        }
      
        if (grupo.getDescricao() == null || grupo.getDescricao().trim().isEmpty()) {
            resultado.put("descricao", "Informe a descrição do grupo");
        }
        if (resultado.isEmpty()) {
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }

        return resultado;
    }

}
