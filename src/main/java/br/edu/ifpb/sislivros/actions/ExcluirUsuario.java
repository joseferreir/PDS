/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.model.ExcluirUsuarioBo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Natarajan
 */
public class ExcluirUsuario implements Action {

    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        String erro = "";
        boolean resultado = false;
        if (usuario != null) {

            ExcluirUsuarioBo boExcluirUsuario = new ExcluirUsuarioBo();
            try {
                resultado = boExcluirUsuario.excluir(usuario);
            } catch (Exception ex) {
                Logger.getLogger(ExcluirUsuario.class.getName()).log(Level.SEVERE, null, ex);
                erro += ex.getMessage();
            } finally {
                if (resultado) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
            }
        }
    }

}
