/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Avaliacao;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.AvaliarLivroBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class AvaliarLivro implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Livro livro = (Livro) request.getSession().getAttribute("livro");
        int rating = Integer.parseInt((String) request.getParameter("novaAvaliacao"));
        String comentario = request.getParameter("comentario");
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(usuario);
        avaliacao.setLivro(livro);
        avaliacao.setRating(rating);
        avaliacao.setComentario(comentario);
        
        boolean resultOperacao = new AvaliarLivroBo().registrar(avaliacao);
        
        if (resultOperacao) { 
            response.sendRedirect("/sislivros/logged/livro/pre/ver.do?isbn=" + livro.getIsbn());
        }
        
    }
    
}
