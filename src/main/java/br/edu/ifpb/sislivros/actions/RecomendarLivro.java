/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.RecomendaLivroBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.sislivros.entidades.Recomendacao;
import br.edu.ifpb.sislivros.model.BuscaUsuarioBo;
import java.time.LocalDateTime;

/**
 *
 * @author José
 */
public class RecomendarLivro implements Action {

    public RecomendarLivro() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecomendaLivroBo bo = new RecomendaLivroBo();
        boolean result = false;
        Recomendacao recomendacao = montarRecondacao(request);
        if (recomendacao != null) {
            result = bo.recomendarLivro(recomendacao);
            if (result == true) {
                request.setAttribute("recomendou", result);
//                response.sendRedirect(request.getContextPath() + "/sislivros/logged/livro/pre/ver.do?isbn=" + recomendacao.getLivro().getIsbn());
                response.sendRedirect(request.getContextPath() + "/home#recomendacoes");
            }
        }

    }

    private Recomendacao montarRecondacao(HttpServletRequest request) {
        Usuario remetente = (Usuario) request.getSession().getAttribute("usuario");
        Usuario destinatario = new BuscaUsuarioBo().buscarPorId(Integer.parseInt(request.getParameter("amigo")));
        Livro livro = (Livro) request.getSession().getAttribute("livro");
        Recomendacao r = new Recomendacao();
        r.setRemetente(remetente);
        r.setDestinatario(destinatario);
        r.setLivro(livro);
        r.setDataHora(LocalDateTime.now());
        return r;
    }

}
