/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.model.ConfigurarUsuarioAdminBo;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Natarajan
 */
public class ConfigurarUsuarioAdmin implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessao = request.getSession();

        if (sessao != null) {
            Usuario userRequisicao = (Usuario) sessao.getAttribute("usuario");

            boolean resultado = false;
            if (userRequisicao.getAdmin() == true) {
                Usuario userTarget = DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
                boolean habilitar = request.getParameter("habilitar").equalsIgnoreCase("true");
                
                ConfigurarUsuarioAdminBo comando = new ConfigurarUsuarioAdminBo();
                resultado = comando.execute(userTarget, habilitar);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            
        }
    }

}
