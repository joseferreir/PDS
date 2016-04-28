/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author José
 */
public class VerificaAtualizacaoLivro {

    public static Map<String, String> execute(String isbnOriginal, Livro livro) {

        Map<String, String> resultado = new HashMap<>();

        if (livro == null) {
            return null;
        }

        if (livro.getAnoPublicacao() <= 0) {
            resultado.put("AnoPublicaca", "Ano de publicação inválido");
        }

        if (livro.getAreaTema() == null || livro.getAreaTema().trim().isEmpty()) {
            resultado.put("tema", "Informe o tema");
        }

        if (livro.getAutores() == null) {
            resultado.put("autores", "Informe autor/autores.");
        }

        if (livro.getEditora() == null || livro.getEditora().trim().isEmpty()) {
            resultado.put("editora", "Informe a editora.");
        }

        if (livro.getIsbn() == null || livro.getIsbn().trim().isEmpty()) {
            resultado.put("isbn", "Informe o isbn.");
        }
                
        Livro livroIsbnInformado = DaoFactory.createFactory(DaoFactory.DAO_BD).criaLivroDAO().buscarPorIsbn(livro.getIsbn());
        
        if (livroIsbnInformado != null && (!livroIsbnInformado.getIsbn().equals(isbnOriginal))){
            resultado.put("isbnJaExiste", "Já existe um livro com o ISBN informado.");
        }
        
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            resultado.put("isbn", "Informe o título.");
        }
        
        if (resultado.isEmpty()) {
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }

        return resultado;
    }
}
