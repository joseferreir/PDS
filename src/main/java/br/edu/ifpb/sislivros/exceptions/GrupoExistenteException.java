/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.exceptions;

/**
 *
 * @author José
 */
public class GrupoExistenteException extends Exception{

    public GrupoExistenteException() {
        super("Já existe um grupo com este nome");
    }
    
    
}
