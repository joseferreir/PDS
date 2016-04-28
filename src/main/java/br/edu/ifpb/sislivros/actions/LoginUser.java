/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.controle.*;
import br.edu.ifpb.sislivros.model.ControleUsuario;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José
 */

public class LoginUser implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        Usuario u = new ControleUsuario().login(email, senha);
        
        if (u == null) {

            response.getWriter().print("FALSE");
            
        } else {
            request.getSession().setAttribute("usuario", u);
            
//            request.getSession().setAttribute("solicitacoes", solicitacoes);
            response.getWriter().print("TRUE");
        }
    }

}