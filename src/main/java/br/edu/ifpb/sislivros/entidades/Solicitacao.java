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
public class Solicitacao {

    private int id;
    private String emailDoUsuario; //quem recebe a solicitacao
    private String emailDosolicitante; //quem envia a solicitacao
    private boolean pendente;
    private boolean resposta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailDoUsuario() {
        return emailDoUsuario;
    }

    public void setEmailDoUsuario(String emailDoUsuario) {
        this.emailDoUsuario = emailDoUsuario;
    }

    public String getEmailDosolicitante() {
        return emailDosolicitante;
    }

    public void setEmailDsolicitante(String emailDsolicitante) {
        this.emailDosolicitante = emailDsolicitante;
    }

    public boolean isPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }

    public boolean isResposta() {
        return resposta;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }

}
