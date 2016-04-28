/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.RespostaTopico;
import br.edu.ifpb.sislivros.entidades.Topico;
import br.edu.ifpb.sislivros.model.ParticipanteGrupoBo;
import br.edu.ifpb.sislivros.model.RespostaTopicoBo;
import br.edu.ifpb.sislivros.model.TopicoBo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class TopicoVerPre implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTopico = request.getParameter("idTopico");
        if (idTopico == null) {
            request.getRequestDispatcher("home").forward(request, response);
        }
        Topico topico = new TopicoBo().buscarPorId(Integer.parseInt(idTopico));
        request.setAttribute("topico", topico);
        
        ParticipanteGrupoBo participanteBo = new ParticipanteGrupoBo();
        request.setAttribute("participanteBo", participanteBo);
        
        List<RespostaTopico> respostas = new RespostaTopicoBo().buscarRespostas(topico.getId());
        if (respostas.isEmpty()) 
            respostas = null;
        request.setAttribute("respostas", respostas);
        
        request.getRequestDispatcher("/paginas/topico/verTopico.jsp").forward(request, response);
    }
    
}
