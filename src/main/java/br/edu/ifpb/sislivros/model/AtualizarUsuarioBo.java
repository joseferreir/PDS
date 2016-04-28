/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.exceptions.EmailExistenteException;
import java.util.Objects;

/**
 *
 * @author Natarajan
 */
public class AtualizarUsuarioBo {

    public boolean atualizar(Usuario usuario) throws EmailExistenteException {
        if (verificarDadosUsuario(usuario)) {
            return DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().atualizar(usuario, usuario.getId());
        }

        return false;
    }

    private boolean verificarDadosUsuario(Usuario usuario) throws EmailExistenteException {

        if (usuario == null) {
            return false;
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            return false;
        }

        if (usuario.getApelido() == null || usuario.getApelido().trim().isEmpty()) {
            return false;
        }

        if (usuario.getCidade() == null || usuario.getCidade().trim().isEmpty()) {
            return false;
        }

        if (usuario.getEstado() == null || usuario.getEstado().trim().isEmpty()) {
            return false;
        }

        if (usuario.getDataNascimento() == null) {
            return false;
        }

        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        Usuario usuarioEmail = factory.criaUsuarioDAO().buscarPorEmail(usuario.getEmail());

        if (usuarioEmail != null) {
            if (!Objects.equals(usuarioEmail.getId(), usuario.getId())) {
                throw new EmailExistenteException();
            }
        }

        return true;
    }

}
