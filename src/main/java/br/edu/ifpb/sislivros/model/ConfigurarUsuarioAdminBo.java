/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.entidades.Usuario;

/**
 *
 * @author Natarajan
 */
public class ConfigurarUsuarioAdminBo {

    public boolean execute(Usuario usuario, boolean habilitar) {
        boolean result = false;

        if (usuario != null) {
            return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().atualizarIsAdm(usuario.getId(), habilitar);
        }

        return result;
    }

}
