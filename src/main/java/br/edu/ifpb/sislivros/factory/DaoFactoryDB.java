/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.factory;

import br.edu.ifpb.sislivros.dao.AmizadeDao;
import br.edu.ifpb.sislivros.dao.AmizadeDaoIF;
import br.edu.ifpb.sislivros.dao.AvaliacaoDAO;
import br.edu.ifpb.sislivros.dao.AvaliacaoDaoIF;
import br.edu.ifpb.sislivros.dao.GrupoDao;
import br.edu.ifpb.sislivros.dao.GrupoDaoIF;
import br.edu.ifpb.sislivros.dao.LivroDao;
import br.edu.ifpb.sislivros.dao.UsuarioDao;
import br.edu.ifpb.sislivros.dao.LivroDaoIF;
import br.edu.ifpb.sislivros.dao.ParticipaGrupoDao;
import br.edu.ifpb.sislivros.dao.ParticipaGrupoDaoIF;
import br.edu.ifpb.sislivros.dao.RecomendacaoDaoIF;
import br.edu.ifpb.sislivros.dao.RecomendacaoDao;
import br.edu.ifpb.sislivros.dao.RespostaTopicoDao;
import br.edu.ifpb.sislivros.dao.RespostaTopicoDaoIF;
import br.edu.ifpb.sislivros.dao.TopicoDao;
import br.edu.ifpb.sislivros.dao.TopicoDaoIF;
import br.edu.ifpb.sislivros.dao.UsuarioDaoIF;

/**
 *
 * @author José
 */
public class DaoFactoryDB implements DaoFactoryIF{

    @Override
    public UsuarioDaoIF criaUsuarioDAO() {
        return new UsuarioDao();
    }
    @Override
      public LivroDaoIF criaLivroDAO(){
          return new LivroDao();
     }

    @Override
    public AmizadeDaoIF criaAmizadeDao() {
        return new AmizadeDao();
    }

    @Override
    public AvaliacaoDaoIF criaAvaliacao() {
        return  new AvaliacaoDAO();
    }

    @Override
    public TopicoDaoIF criaTopicoDao() {
        return new TopicoDao();
    }

    @Override
    public GrupoDaoIF  criaGrupoDAO() {
        return new GrupoDao();
    }

    @Override
    public RecomendacaoDaoIF criaRecomendacao() {
        return  new RecomendacaoDao();
    }

    @Override
    public ParticipaGrupoDaoIF criaParticipaGrupoDao() {
        return new ParticipaGrupoDao();
    }
    
    @Override
    public RespostaTopicoDaoIF criaRespostaTopicoDao(){
        return new RespostaTopicoDao();
    }
    
}
