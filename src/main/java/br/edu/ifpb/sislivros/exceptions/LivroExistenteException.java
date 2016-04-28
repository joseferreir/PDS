/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.exceptions;

public class LivroExistenteException extends Exception{

    public LivroExistenteException() {
        super("Já existe um livro com o ISBN informado.");
    }

    public LivroExistenteException(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
