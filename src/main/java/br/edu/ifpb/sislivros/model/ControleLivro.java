/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.exceptions.LivroExistenteException;
import java.util.List;

/**
 *
 * @author José
 */
public class ControleLivro {

    public boolean adicionar(Livro livro) throws LivroExistenteException {
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        Livro buscarExistente = factory.criaLivroDAO().buscarPorIsbn(livro.getIsbn());
        if (buscarExistente != null){
            throw new LivroExistenteException();
        }
        return factory.criaLivroDAO().adicionar(livro);
    }

    public boolean remover(int id) {
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        return factory.criaLivroDAO().remover(id);
    }

    public boolean atualizar(Livro livro) {
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        return factory.criaLivroDAO().atualizar(livro);

    }

    public Livro buscarPorId(int id) {
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        return factory.criaLivroDAO().buscarPorId(id);
    }
    
    public Livro buscarPorIsbn(String isbn) {
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        return factory.criaLivroDAO().buscarPorIsbn(isbn);
    }


    public List<Livro> retornarTodos() {
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        return factory.criaLivroDAO().retornarTodos();
    }

}
