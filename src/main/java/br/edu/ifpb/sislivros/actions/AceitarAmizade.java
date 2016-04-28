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
 * @author José
 */
public class AceitarAmizade implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SolicitacoesAmizadeBo solicBo = new SolicitacoesAmizadeBo();
        
        Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuario");
        int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
        
        
        if (solicBo.aceitarSolicitacao(idSolicitante, usuarioSessao.getId())) {
            response.getWriter().print("TRUE");
//            request.getRequestDispatcher("home?id=pessoas").forward(request, response);
            response.sendRedirect("home#pessoas");
        }
    }

}
