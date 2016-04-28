package br.edu.ifpb.sislivros.dao;

import java.util.List;

import br.edu.ifpb.sislivros.entidades.Livro;
import java.util.Map;

public interface LivroDaoIF {

//    public Integer adicionar2(Livro livro);
    
    public boolean adicionar(Livro livro);
    
    public boolean mudarCapa(int idLivro, String capa);

    public boolean remover(int id);

    public boolean atualizar(Livro livro);

    public Livro buscarPorId(int id);
    
    public Livro buscarPorIsbn(String isbn);
//    
//    public Livro buscarPorTitulo(String titulo);
//    
//    public List<Livro> buscarPorAutor(String autor);
    
    public List<Livro> buscarExclusivaPorAtributos(Map<String, String> atributos);
    
    public List<Livro> buscarInclusivaPorAtributos(Map<String, String> atributos);

    public List<Livro> retornarTodos();

}
