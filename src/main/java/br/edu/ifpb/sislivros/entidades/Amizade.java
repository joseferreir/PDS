/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.entidades;

/**
 *
 * @author José
 */
public class Amizade {
    private Usuario solicitante, solicitado = null;
    private boolean confirmada;

    public Amizade(Usuario usuario1, Usuario usuario2) {
        this.solicitante = usuario1;
        this.solicitado = usuario2;
        confirmada = false;
    }

    public Amizade() {
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public Usuario getSolicitado() {
        return solicitado;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public void setSolicitado(Usuario solicitado) {
        this.solicitado = solicitado;
    }
    
//    public boolean saoAmigos(Usuario user1, Usuario user2){
//        return (user1.equals(solicitante) && user2.equals(solicitado)) && isConfirmada() 
//                || (user1.equals(solicitado) && user2.equals(user1));
//    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }
    
    
    
}
