package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Grupo;
import java.util.List;

public interface GrupoDaoIF {

	public int adicionar(Grupo grupo,int idCridor);

	public boolean remover(int idGrupo);

	public boolean atualizar(Grupo grupo);

	public Grupo buscarPorId(int idGrupo);
        
        public Grupo buscarPorNome(String nome);

        public List<Grupo> buscarTodos();
        
}