/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.loader;

import br.edu.ifpb.sislivros.dao.AmizadeDao;
import br.edu.ifpb.sislivros.dao.LivroDao;
import br.edu.ifpb.sislivros.dao.ParticipaGrupoDao;
import br.edu.ifpb.sislivros.dao.ParticipaGrupoDaoIF;
import br.edu.ifpb.sislivros.dao.UsuarioDao;
import br.edu.ifpb.sislivros.dao.UsuarioDaoIF;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.exceptions.GrupoExistenteException;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import br.edu.ifpb.sislivros.model.ControleLivro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author susanneferraz
 */
public class LoginLoader {
    
    public static void main(String[] args) throws GrupoExistenteException {
        UsuarioDaoIF userdao = new UsuarioDao();
        Usuario u = userdao.login("joao34@gmai.com", "1234567");
        if (u != null) 
            System.out.println(u.getNome());

        LivroDao livrodao = new LivroDao();
//        Livro livro = new Livro();
//        livro.setAnoPublicacao(2000);
//        livro.setAutores("Carlos Castanheda");
//        livro.setCapa("/");
//        livro.setEditora("Editora Fronteira");
//        livro.setIsbn("123");
//        livro.setTitulo("A erva do diabo");
        
        
//        Livro livro = livrodao.buscarPorId(1);
//        livro.setIsbn("123455");
//        
//        if (livrodao.atualizar(livro)){
//            System.out.println(livro.getAnoPublicacao());
//            System.out.println(livro.getAutores());
//            System.out.println(livro.getCapa());
//            System.out.println(livro.getEditora());
//            System.out.println(livro.getIsbn());
//            System.out.println(livro.getTitulo());
//        }
        AmizadeDao dao = new AmizadeDao();
        //Amizade a = new Amizade();
        // boolean a = dao.saoAmigos(3, 2);
        //  System.out.println(a);
//        SolicitacoesAmizadeBo solicBo = new SolicitacoesAmizadeBo();
//        boolean ss = solicBo.aceitarSolicitacao(2,3 );
//        System.err.println("acc" +ss);
        DaoFactoryIF factoy = DaoFactory.createFactory(DaoFactory.DAO_BD);
//        u = factoy.criaUsuarioDAO().consultarPorId(1);
//        Livro l = factoy.criaLivroDAO().buscarPorId(1);
//        Avaliacao a = new Avaliacao();
//        a.setUsuario(u);
//        a.setLivro(l);
//        a.setComentario("otimo");
//        a.setRating(12);
//        a.setId(2);
//        boolean re = factoy.criaAvaliacao().atualizar(a);
//        List<Topico> re = factoy.criaTopicoDao().buscarPorGrupo(2);
//        System.out.println("cadastrou "+re.get(1).getDescricao());
//        CriarGrupoBo b = new CriarGrupoBo();
//        Grupo g = new Grupo();
//        g.setNome("meu grupo 4");
//        g.setDescricao("descrição 22");
//        g.setId(1);
//        Map c = b.criarGrupo(g);
        //  System.err.println("grupo "+c.get("passou"));
       
                
        ParticipaGrupoDao d = new ParticipaGrupoDao();
//        System.err.println("dd "+d.retornaUsuariosDeUmGrupo(8));
//        

    }
    
}
