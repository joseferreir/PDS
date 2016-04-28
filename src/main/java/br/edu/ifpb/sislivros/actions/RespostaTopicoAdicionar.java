/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.RespostaTopico;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.RespostaTopicoBo;
import br.edu.ifpb.sislivros.model.TopicoBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class RespostaTopicoAdicionar implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String idTopico = (String) request.getParameter("idTopico");
        String resposta = (String) request.getParameter("resposta");
        
        
        RespostaTopico rt = new RespostaTopico();
        rt.setResposta(resposta);
        rt.setTopico(new TopicoBo().buscarPorId(Integer.parseInt(idTopico)));
        rt.setUsuario(usuario);
        
        RespostaTopicoBo rtBo = new RespostaTopicoBo();
        
        
        if (rtBo.adicionarResposta(rt)) {
            response.sendRedirect("/sislivros/logged/topico/pre/ver.do?idTopico=" + idTopico);
        }
    }
    
}
