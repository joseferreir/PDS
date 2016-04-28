/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.exceptions.LivroExistenteException;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;

/**
 *
 * @author Natarajan
 */
public class CadastrarLivroBo {

    public boolean cadastrar(Livro livro) throws LivroExistenteException{
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        if (livro.getCapa() == null || livro.getCapa().trim().equals(""))
            livro.setCapa("img/livros/capa_default.png");
        
        Livro buscarExistente = factory.criaLivroDAO().buscarPorIsbn(livro.getIsbn());
        if (buscarExistente != null){
            throw new LivroExistenteException();
        }
        return factory.criaLivroDAO().adicionar(livro);
    }
    
    public boolean mudarCapa(int idLivro, String capa){
        return DaoFactory.createFactory(DaoFactory.DAO_BD).criaLivroDAO().mudarCapa(idLivro, capa);
    }
    

}
