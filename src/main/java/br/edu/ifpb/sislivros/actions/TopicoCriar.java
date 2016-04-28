/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.dao.GrupoDao;
import br.edu.ifpb.sislivros.dao.GrupoDaoIF;
import br.edu.ifpb.sislivros.dao.LivroDao;
import br.edu.ifpb.sislivros.dao.LivroDaoIF;
import br.edu.ifpb.sislivros.entidades.Topico;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.TopicoBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class TopicoCriar implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        GrupoDaoIF grupoDao = new GrupoDao();
        LivroDaoIF livroDao = new LivroDao();
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        String idGrupo = (String) request.getParameter("idGrupo");
        String idLivro = (String) request.getParameter("livro");
        String descricaoTopico = (String) request.getParameter("descricao");
        
        Topico topico = new Topico();
        topico.setDescricao(descricaoTopico);
        topico.setGrupo(grupoDao.buscarPorId(Integer.parseInt(idGrupo)));
        
        topico.setLivro(livroDao.buscarPorId(Integer.parseInt(idLivro)));
        
        TopicoBo topicoBo = new TopicoBo();
        
        int idTopico = topicoBo.adicionaTopico(topico, usuario);
        
        if (idTopico > 0) {
            response.sendRedirect("/sislivros/logged/topico/pre/ver.do?idTopico=" + idTopico);
        }
    }
    
}
