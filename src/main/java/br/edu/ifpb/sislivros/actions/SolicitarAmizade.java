/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.SolicitacoesAmizadeBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class SolicitarAmizade implements Action{


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SolicitacoesAmizadeBo solicBo = new SolicitacoesAmizadeBo();
        
        Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuario");
        int idSolicitado = Integer.parseInt(request.getParameter("idSolicitado"));
        
        if (solicBo.solicitarAmizade(usuarioSessao.getId(), idSolicitado)) {
            response.sendRedirect(request.getContextPath() + "/home#pessoas");
        }
    }

}
