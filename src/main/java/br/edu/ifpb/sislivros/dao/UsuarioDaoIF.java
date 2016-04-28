/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.List;

/**
 *
 * @author José
 */
public interface UsuarioDaoIF {

    public boolean adicionar(Usuario usuario);

    public boolean atualizar(Usuario usuario, Integer id);

    public boolean remover(String email);

    public Usuario buscarPorId(int id);

    public Usuario buscarPorEmail(String email);

    public Usuario login(String email, String senha);

    public boolean atualizarIsAdm(Integer id, boolean habilitar);

    public List<Usuario> buscarTodos();

    public List<Usuario> buscarPorNome(String nome);

}
