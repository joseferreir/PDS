/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.factory;

import br.edu.ifpb.sislivros.dao.AmizadeDaoIF;
import br.edu.ifpb.sislivros.dao.AvaliacaoDaoIF;
import br.edu.ifpb.sislivros.dao.GrupoDaoIF;
import br.edu.ifpb.sislivros.dao.LivroDaoIF;
import br.edu.ifpb.sislivros.dao.ParticipaGrupoDaoIF;
import br.edu.ifpb.sislivros.dao.RecomendacaoDaoIF;
import br.edu.ifpb.sislivros.dao.RespostaTopicoDaoIF;
import br.edu.ifpb.sislivros.dao.TopicoDaoIF;
import br.edu.ifpb.sislivros.dao.UsuarioDaoIF;

/**
 *
 * @author José
 */
public interface DaoFactoryIF {

    public UsuarioDaoIF criaUsuarioDAO();

    public LivroDaoIF criaLivroDAO();

    public AmizadeDaoIF criaAmizadeDao();

    public AvaliacaoDaoIF criaAvaliacao();

    public TopicoDaoIF criaTopicoDao();

    public GrupoDaoIF criaGrupoDAO();

    public RecomendacaoDaoIF criaRecomendacao();

    public ParticipaGrupoDaoIF criaParticipaGrupoDao();
    
    public RespostaTopicoDaoIF criaRespostaTopicoDao();

}
