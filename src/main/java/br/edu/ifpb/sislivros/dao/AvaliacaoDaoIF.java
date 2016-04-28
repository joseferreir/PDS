package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Avaliacao;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.List;

public interface AvaliacaoDaoIF {

    public boolean adicionar(Avaliacao avaliacao);

    public boolean atualizar(Avaliacao avaliacao);

    public boolean remover(int idAvaliacao);

    public Avaliacao buscarPorId(int id);
    
    public Avaliacao buscar(Usuario usuario, Livro livro);

    public List<Avaliacao> buscarPorLivro(int idLivro);
    

}
