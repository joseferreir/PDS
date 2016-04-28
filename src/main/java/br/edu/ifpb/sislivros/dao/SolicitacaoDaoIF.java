package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Amizade;
import java.util.List;

public interface SolicitacaoDaoIF {

    public boolean adicionar(int idSolicitante, int idSolicitado);

    public List<Amizade> buscarPorId(int idSolicitado);

    public boolean remover(int idSolicitante, int idSolicitado);
    
    public boolean existeSolicitacao(int idSolicitante, int idSolicitado);
}
