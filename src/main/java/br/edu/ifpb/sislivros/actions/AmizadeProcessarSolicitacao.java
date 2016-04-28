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
public class AmizadeProcessarSolicitacao implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        SolicitacoesAmizadeBo solicBo = new SolicitacoesAmizadeBo();
        
        Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuario");
        int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
        
        String tipoConfirmacao = (String) request.getParameter("aceitar");
        boolean resultado = false;
        
        if (tipoConfirmacao.equals("true")){
            resultado = solicBo.aceitarSolicitacao(idSolicitante, usuarioSessao.getId());
        } else {
            resultado = solicBo.rejeitarSolicitacao(idSolicitante, usuarioSessao.getId());
        }
        
        if (resultado == true) {
            response.sendRedirect(request.getContextPath() + "/home#pessoas");
        }
    }
    
    
    
}
