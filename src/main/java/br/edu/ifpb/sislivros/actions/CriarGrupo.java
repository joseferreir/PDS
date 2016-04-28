/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.exceptions.GrupoExistenteException;
import br.edu.ifpb.sislivros.model.CriarGrupoBo;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class CriarGrupo implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        String nomeGrupo = (String) request.getParameter("nomeGrupo");
        String descricaoGrupo = (String) request.getParameter("descricao");
        
        Grupo novoGrupo = new Grupo();
        novoGrupo.setNome(nomeGrupo);
        novoGrupo.setDescricao(descricaoGrupo);
        
        CriarGrupoBo cgBo = new CriarGrupoBo();
        
        Map<String,String> resultado = null;
        try {
            resultado = cgBo.criarGrupo(novoGrupo, usuario);
            
        } catch (GrupoExistenteException ex) {
            Logger.getLogger(CriarGrupo.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect(request.getContextPath() + "/errorPageGrupo.html");
        }
        
        if (resultado != null && resultado.get("passou").equalsIgnoreCase("true")) {
            response.sendRedirect("/sislivros/logged/grupo/pre/ver.do?idGrupo=" + resultado.get("idGrupo"));
        }
        
    
    }
    
}
