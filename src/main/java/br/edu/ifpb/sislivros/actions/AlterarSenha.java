/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.model.AlterarSenhaBo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author José
 */
public class AlterarSenha implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String senhaAntiga = request.getParameter("senhaAntiga");
        String novaSenha = request.getParameter("novaSenha");

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        boolean resultado = new AlterarSenhaBo().execute(usuario, senhaAntiga, novaSenha);

        if (resultado) {
            usuario.setSenha(novaSenha);
            session.setAttribute("usuario", usuario);
            response.getWriter().print("OK");
        } else {
            response.getWriter().print("ERRO");
        }
    }

}
