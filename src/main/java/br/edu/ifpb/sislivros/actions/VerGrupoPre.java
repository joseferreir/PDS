/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.dao.ParticipaGrupoDao;
import br.edu.ifpb.sislivros.dao.ParticipaGrupoDaoIF;
import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Topico;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.BuscarGrupoBo;
import br.edu.ifpb.sislivros.model.LivroBuscarBo;
import br.edu.ifpb.sislivros.model.ParticipanteGrupoBo;
import br.edu.ifpb.sislivros.model.TopicoBo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José
 */
public class VerGrupoPre implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idGrupo = request.getParameter("idGrupo");
        if (idGrupo == null) {
            request.getRequestDispatcher("home").forward(request, response);
        }
        Grupo grupo = new BuscarGrupoBo().buscarPorId(Integer.parseInt(idGrupo));
        request.setAttribute("grupo", grupo);
        
        ParticipanteGrupoBo participanteBo = new ParticipanteGrupoBo();
        request.setAttribute("participanteBo", participanteBo);
        
        LivroBuscarBo livroBuscarBo = new LivroBuscarBo();
        List<Livro> livros = livroBuscarBo.buscarTodos();
        request.setAttribute("livros", livros);

        List<Topico> topicos = new TopicoBo().buscarPorGrupo(grupo.getId());
        request.setAttribute("topicos", topicos);
         ParticipaGrupoDaoIF daoParticipa = new ParticipaGrupoDao();
        List<Usuario> participantes = daoParticipa.buscarParticipantes(Integer.parseInt(idGrupo));
        request.setAttribute("participantes", participantes);
        
        //
        request.getRequestDispatcher("/paginas/grupo/verGrupo.jsp").forward(request, response);
//        response.sendRedirect(request.getContextPath() + "/paginas/grupo/verGrupo.jsp");
        
    }

}
