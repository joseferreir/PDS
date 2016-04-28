/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.BuscaUsuarioBo;
import br.edu.ifpb.sislivros.model.BuscarGrupoBo;
import br.edu.ifpb.sislivros.model.LivroBuscarBo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class UsuarioBuscar implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeBuscado = (String) request.getParameter("nomeBuscado");
        
        //buscando e retornando a busca
        List<Usuario> usuariosLocalizados  = new BuscaUsuarioBo().buscarPorNome(nomeBuscado);
        request.getSession().setAttribute("usuarios", usuariosLocalizados);
        
    }
    
}
