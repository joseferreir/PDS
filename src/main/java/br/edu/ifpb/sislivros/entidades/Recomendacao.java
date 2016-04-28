/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * @author José
 */
public class Recomendacao implements Comparable<Recomendacao>, Serializable{

    private int id;
    private Usuario remetente;
    private Usuario destinatario;
    private Livro livro;
    private LocalDateTime dataHora;

    public Recomendacao(){
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public int compareTo(Recomendacao o) {
        return this.getDataHora().compareTo(o.getDataHora());
    }
    
    public static class Comparators {
        public static final Comparator<Recomendacao> INVERT_SORT = (Recomendacao r1, Recomendacao r2) -> r2.compareTo(r1);
    }
}
