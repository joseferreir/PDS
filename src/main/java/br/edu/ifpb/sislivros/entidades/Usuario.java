/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author José
 */
public class Usuario implements Comparable<Usuario>, Serializable{
    private Integer id;
    private String email;
    private String senha;
    private String nome;
    private String apelido;
    private LocalDate dataNascimento;
    private String cidade;
    private String estado;
    private boolean admin;
    private String foto;

    public Usuario() {
    }

    public Usuario(Integer id, String email, String senha, String nome, String apelido, LocalDate dataNascimento, String cidade, String estado, boolean tipo, String foto) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.cidade = cidade;
        this.estado = estado;
        this.admin = tipo;
        this.foto = foto;
    }

    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNAscimento) {
        this.dataNascimento = dataNAscimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Usuario o) {
        return this.getNome().compareTo(o.getNome());
    }
    
    public static class Comparators {
        public static final Comparator<Usuario> ID = (Usuario u1, Usuario u2) -> u1.getId().compareTo(u2.getId());
        public static final Comparator<Usuario> NAME = (Usuario u1, Usuario u2) -> u1.getNome().compareTo(u2.getNome());
    }

}
