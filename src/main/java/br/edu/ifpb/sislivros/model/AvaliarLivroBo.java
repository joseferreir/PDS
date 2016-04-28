/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.dao.AvaliacaoDaoIF;
import br.edu.ifpb.sislivros.entidades.Avaliacao;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class AvaliarLivroBo {
    
    DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
    
    public boolean registrar(Avaliacao avaliacao){
        return factory.criaAvaliacao().adicionar(avaliacao);
    }
    
    public boolean alterar(Avaliacao avaliacao){
        return factory.criaAvaliacao().atualizar(avaliacao);
    }
    
    public Avaliacao buscar (Usuario usuario, Livro livro){
        return factory.criaAvaliacao().buscar(usuario, livro);
    }
    
    public Avaliacao buscarPorId(int idAvalicao){
        return factory.criaAvaliacao().buscarPorId(idAvalicao);
    }
    
    public List<Avaliacao> buscarPorLivro(int idLivro){
        return factory.criaAvaliacao().buscarPorLivro(idLivro);
    }
    
}
