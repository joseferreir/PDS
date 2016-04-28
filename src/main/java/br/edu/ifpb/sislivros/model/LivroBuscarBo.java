/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.dao.LivroDao;
import br.edu.ifpb.sislivros.dao.LivroDaoIF;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
public class LivroBuscarBo {
    
    private LivroDaoIF livroDao = DaoFactory.createFactory(DaoFactory.DAO_BD).criaLivroDAO();
    
    
    //alterar aqui para buscar uma amostra com número limitado a X livros, podendo ser
    // os x mais bem ranqueados, ou x mais recentes
    public List<Livro> buscarAmostra(){
        return livroDao.retornarTodos();
    }
    
    public List<Livro> buscarTodos(){
        return livroDao.retornarTodos();
    }
    
    public List<Livro> buscaAvancada(Map<String, String> atributos){
        int min = Integer.parseInt(atributos.get("min"));
        int max = Integer.parseInt(atributos.get("max"));
        
        if (max == 0) {
            atributos.replace("max", "5");
        }
        
        if (min > max) {
            int aux = min;
            min = max;
            max = aux;
        }
        
        
        
        return livroDao.buscarInclusivaPorAtributos(atributos);
    }
}
