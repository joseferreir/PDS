/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.exceptions;

public class EmailExistenteException extends Exception{

    public EmailExistenteException() {
        super("Já existe um usuário com o email informado.");
    }

    public EmailExistenteException(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
