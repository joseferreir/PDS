/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.model.LivroBuscarBo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
public class LivroBuscar implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String campo = (String) request.getParameter("campo");
        String parametro = (String) request.getParameter("parametro");
        
        String minRating = (String) request.getParameter("min");
        String maxRating = (String) request.getParameter("max");
        
        String ordem = request.getParameter("ordem");
        String sentido = request.getParameter("sentido");
        
        //salvando na sessao
//        request.getSession().setAttribute("campo", campo);
//        request.getSession().setAttribute("parametro", parametro);
//        
//        request.getSession().setAttribute("ordem", ordem);
//        request.getSession().setAttribute("min", minRating);
//        request.getSession().setAttribute("max", maxRating);
               
        
        //passando para o mapa
        Map<String,String> mapaBusca = new HashMap<>();
        if (campo.equals("all")){
            mapaBusca.put("titulo", parametro);
            mapaBusca.put("editora", parametro);
            mapaBusca.put("autores", parametro);
            mapaBusca.put("isbn", parametro);
            
        } else {
            mapaBusca.put(campo, parametro);
        }
        
        mapaBusca.put("min", minRating);
        mapaBusca.put("max", maxRating);
        mapaBusca.put("ordem", ordem);
        mapaBusca.put("sentido", sentido);
        
        //buscando e retornando a busca
        List<Livro> livros = new LivroBuscarBo().buscaAvancada(mapaBusca);
        request.getSession().setAttribute("livrosBuscados", livros);
        
    }
    
}
