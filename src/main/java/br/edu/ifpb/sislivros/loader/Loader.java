/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.loader;

import br.edu.ifpb.sislivros.dao.AmizadeDao;
import br.edu.ifpb.sislivros.dao.GrupoDao;
import br.edu.ifpb.sislivros.dao.LivroDao;
import br.edu.ifpb.sislivros.dao.UsuarioDao;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.dao.UsuarioDaoIF;
import br.edu.ifpb.sislivros.entidades.Grupo;
import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.exceptions.LivroExistenteException;
import br.edu.ifpb.sislivros.model.AtualizarLivroBo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.postgresql.ssl.jdbc4.LibPQFactory;

/**
 *
 * @author susanneferraz
 */
public class Loader {
//    ControleUsuario controluser = new ControleUsuario();

    UsuarioDaoIF userdao = new UsuarioDao();
    AmizadeDao amizadeDao = new AmizadeDao();
    Usuario user1 = new Usuario();
    Usuario user2 = new Usuario();

    public Loader() throws SQLException, IOException, ClassNotFoundException, LivroExistenteException {
//        //cadastra um usuario
//        user1.setApelido("ellida");
//        user1.setCidade("Sousa");
//        user1.setDataNascimento(LocalDate.now());
//        user1.setEmail("ellida@gmail.com");
//        user1.setEstado("PB");
//        user1.setFoto("/../");
//        user1.setNome("Ellida Calixto");
//        user1.setSenha("123");
//        user1.setTipo(true);
//        userdao.adicionar(user1);
        
//        //cadastra um usuario
//        user1.setApelido("seu kreyner");
//        user1.setCidade("Pombal");
//        user1.setDataNascimento(LocalDate.now());
//        user1.setEmail("kleyner@gmail.com");
//        user1.setEstado("PB");
//        user1.setFoto("/../");
//        user1.setNome("Kleyner Lanterna Verde");
//        user1.setSenha("123");
//        user1.setTipo(false);
//        userdao.adicionar(user1);

//        //remover um usuario
//        userdao.remover("ellida@gmail.com");
//        System.out.println(userdao.consultarPorEmail("ellida@gmail.com").getApelido());
//        List<Usuario> l = userdao.buscarTodos();
//
//        for (Usuario u : l) {
//            System.out.println(u.getNome());
//        }

        

//        //adicionando amizade
//        boolean res = amizadeDao.adicionar(new Amizade(user1,user2));
//        if (res){
//            System.out.println("Adicionou a amizade");
//        }

//        if (amizadeDao.saoAmigos(1, 9)) {
//            System.out.println("SIMMMMM");
//        } else {
//            System.out.println("NAAAAOOO");
//        }
//        
//        if (amizadeDao.saoAmigos(5, 9)) {
//            System.out.println("SIMMMMM");
//        } else {
//            System.out.println("NAAAAOOO");
//        }
        
//        amizadeDao.remover(9, 1);
        
//        System.out.println("amigos de Natarajan");
//        List<Usuario> amigos1 = amizadeDao.carregarAmigos(1);
//        for (Usuario a : amigos1){
//            System.out.println(a.getNome());
//        }
//            
//        System.out.println("");
//        
//        System.out.println("Amigos de Jofran");
//        amigos1 = amizadeDao.carregarAmigos(9);
//        for (Usuario a : amigos1){
//            System.out.println(a.getNome());
//        }
//        
//        System.out.println("");
        
//        System.out.println("Amigos de Kleyner");
//        amigos1 = amizadeDao.carregarAmigos(10);
//        for (Usuario a : amigos1){
//            System.out.println(a.getNome());
//        }

        //consultado por email 
//        Usuario natarajan = userdao.consultarPorId(1);
//        Usuario ellida = userdao.consultarPorId(5);
//        Usuario jofran = userdao.consultarPorId(9);
//        Usuario kleyner = userdao.consultarPorId(10);
//        
//        
//        
//        if (amizadeDao.remover(9, 1)){
//            System.out.println("solicitou de 1 pra 9");
//        }
//        
//        if (amizadeDao.saoAmigos(1,9)){
//            //if (amizadeDao.confirmarAmizade(1,9)){
//            System.out.println("SIMM");
//        } else {
//            System.out.println("NAO");
//        }
//        
//        if (amizadeDao.adicionarSolicitacao(9, 1)){
//            System.out.println("solicitou de 5 pra 1");
//        } else {
//            System.out.println("nao solicitou");
//        }
//        
//     LivroDao livrodao = new LivroDao();
//       
//     Livro l = new Livro();
//     l= livrodao.buscarPorId(1);
//     l.setIsbn("as123");
//           // for(Livro a :l)
//       // System.out.println("lista "+a.getAutores());
//       livrodao.atualizar(l);
//        System.out.println( "ATUAL"+ livrodao.buscarPorId(1).getTitulo());
        GrupoDao gdao = new GrupoDao();
        Grupo g = new Grupo();
//        g.setNome("leirot 03");
//        g.setDescricao("grupo 3");
//        g.setId(2);
//        gdao.adicionar(g);
        AtualizarLivroBo b = new AtualizarLivroBo();
         LivroDao d= new LivroDao();
       AtualizarLivroBo z= new AtualizarLivroBo();
        Livro lc = d.buscarPorId(4);
        lc.setTitulo("Mulh");
        z.atualizar(lc);
        
        
      
             
        boolean xx = d.atualizar(lc);
        System.err.println("resur"+xx);
        
   //   System.out.println("busca "+gdao.buscarPorNome("leitores de quadrinhos").get(0).getNome());
//        
    

    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, LivroExistenteException {
        new Loader();
    }

}
