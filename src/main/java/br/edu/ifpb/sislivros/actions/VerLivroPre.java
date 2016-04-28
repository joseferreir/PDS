/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Avaliacao;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.AmizadeBo;
import br.edu.ifpb.sislivros.model.AvaliarLivroBo;
import br.edu.ifpb.sislivros.model.ControleLivro;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José
 */
public class VerLivroPre implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        if (isbn == null) {
            request.getRequestDispatcher("home").forward(request, response);
        }
        Livro livro = new ControleLivro().buscarPorIsbn(isbn);
        request.getSession().setAttribute("livro", livro);

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario != null) {
            Avaliacao avaliacaoExistente = new AvaliarLivroBo().buscar(usuario, livro);
            request.getSession().setAttribute("avaliacao", avaliacaoExistente);

            List<Usuario> amigos = new AmizadeBo().buscarAmigos(usuario);
            if (amigos != null) {
                request.getSession().setAttribute("amigos", amigos);
            }

        }

        response.sendRedirect(request.getContextPath() + "/verLivro.jsp?isbn=" + livro.getIsbn());
    }

}
