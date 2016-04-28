/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.model.ParticipanteGrupoBo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class SairGrupo implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));

        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ParticipanteGrupoBo bo = new ParticipanteGrupoBo();
        if (!bo.sairGrupo(u.getId(), idGrupo)) {
            request.getRequestDispatcher("/sislivros/logged/grupo/pre/ver.do?idGrupo=" + idGrupo).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home#grupos");
        }
    }

}
