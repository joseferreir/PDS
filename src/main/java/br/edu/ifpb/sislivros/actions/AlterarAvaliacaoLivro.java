/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Avaliacao;
import br.edu.ifpb.sislivros.model.AvaliarLivroBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class AlterarAvaliacaoLivro implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Avaliacao avaliacao = (Avaliacao) request.getSession().getAttribute("avaliacao");
        int rating = Integer.parseInt((String) request.getParameter("alterarAvaliacao"));
        String comentario = request.getParameter("comentario");
        
        avaliacao.setRating(rating);
        avaliacao.setComentario(comentario);
        
        boolean resultOperacao = new AvaliarLivroBo().alterar(avaliacao);
        
        if (resultOperacao) {
            response.sendRedirect(request.getContextPath() + "/verLivro.jsp?isbn=" + avaliacao.getLivro().getIsbn());
        }
        
    }
    
}
