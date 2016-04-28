/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.exceptions.GrupoExistenteException;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author José
 */
public class CriarGrupoBo {

    public Map criarGrupo(Grupo grupo, Usuario usuario) throws GrupoExistenteException {
        Grupo grupoExistente = DaoFactory.createFactory(DaoFactory.DAO_BD).criaGrupoDAO().buscarPorNome(grupo.getNome());
        if (grupoExistente != null) {
            throw new GrupoExistenteException();
        }
        Map<String, String> resultado = verificarGrupo(grupo);
        int idCadastrado = 0;
        if (resultado.get("passou").equalsIgnoreCase("true")) {
            idCadastrado = DaoFactory.createFactory(DaoFactory.DAO_BD).criaGrupoDAO().adicionar(grupo,usuario.getId());
        }
        if (idCadastrado != 0) {
            //adicionando o usuario que cadastrou como um participante do grupo
            ParticipanteGrupoBo participanteBo = new ParticipanteGrupoBo();
            participanteBo.entrarGrupo(usuario.getId(), idCadastrado);
            
            resultado.put("idGrupo", Integer.toString(idCadastrado));
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }
        return resultado;
    }

    private static Map verificarGrupo(Grupo grupo) {
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
