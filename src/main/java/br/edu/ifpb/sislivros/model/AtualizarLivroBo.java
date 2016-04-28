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
 * @author José
 */
public class AtualizarLivroBo {

    public boolean atualizar(Livro livro) throws LivroExistenteException {
//        if (verificarLivro(livro)) {
            return DaoFactory.createFactory(DaoFactory.DAO_BD).criaLivroDAO().atualizar(livro);
//        }
//        return false;
    }

    private boolean verificarLivro(Livro livro) throws  LivroExistenteException {
        boolean result = true;

        if (livro == null) {
            return false;
        }

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            return false;
        }

        if (livro.getIsbn() == null || livro.getIsbn().trim().isEmpty()) {
            return false;
        }
        DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        Livro livroBuscado = factory.criaLivroDAO().buscarPorIsbn(livro.getIsbn());
        if (livroBuscado != null) {
            if (!livroBuscado.getIsbn().equalsIgnoreCase(livro.getIsbn())) {
                throw new LivroExistenteException();
            }
        }
        return result;
    }
}
