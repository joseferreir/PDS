package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Topico;
import java.util.List;

public interface TopicoDaoIF {

    public int adicionar(Topico t);
    
    public int adicionar2(Topico t);

    public boolean remover(int id);

    public boolean atualizar(Topico t, int idTopico);

    public List<Topico> buscarPorGrupo(int idGrupo);

    public Topico buscarPorId(int idTopico);
}
