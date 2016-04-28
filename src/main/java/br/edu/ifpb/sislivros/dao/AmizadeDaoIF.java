
package br.edu.ifpb.sislivros.dao;

import br.edu.ifpb.sislivros.entidades.Amizade;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.List;

/**
 *
 * @author José
 */
public interface AmizadeDaoIF {
    
    public boolean confirmarAmizade(int idUser1, int idUser2);
    
    public Amizade localizar(int idUser1, int idUser2);
    
    public boolean remover(int idUser1, int idUser2);
    
    public List<Amizade> localizarAmizades(int idUser);
    
    public List<Usuario> carregarAmigos (int idUser);
    
    public void excluiTodasAmizades (int idUsuario);
    
    public boolean saoAmigos(int idUser1, int idUser2);
    
    public boolean adicionarSolicitacao(int idSolicitante, int idSolicitado);
    
    public boolean removerSolicitacao(int idSolicitante, int idSolicitado);
    
    public Amizade buscarSolicitacao(int idSolicitante, int idSolicitado);
    
    public List<Amizade> buscarSolicitacoes(int idUsuarioSolicitado);
}
