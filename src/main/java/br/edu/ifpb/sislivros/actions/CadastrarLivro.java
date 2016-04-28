/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.exceptions.LivroExistenteException;
import br.edu.ifpb.sislivros.model.CadastrarLivroBo;
import br.edu.ifpb.sislivros.model.ProcessadorFotos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author Natarajan
 */
//@MultipartConfig(maxFileSize = 10*1024*1024,maxRequestSize = 20*1024*1024,fileSizeThreshold = 5*1024*1024)
public class CadastrarLivro implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Livro livro = null;
        try {
            livro = montarLivro(request);
        } catch (FileUploadException ex) {
            Logger.getLogger(CadastrarLivro.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (livro != null) {
            CadastrarLivroBo cadastarBo = new CadastrarLivroBo();

            try {
                boolean cadastrou = cadastarBo.cadastrar(livro);
                if (cadastrou){
                    response.sendRedirect(request.getContextPath() + "/administrativo.jsp");
                }
            } catch (LivroExistenteException ex) {
                Logger.getLogger(CadastrarLivro.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("errorPageISBN.html");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    private Livro montarLivro(HttpServletRequest request) throws FileUploadException, IOException, ServletException {
        Livro livro = new Livro();
        
        //processando e salvando a capa do livro
        Part capaPart = request.getPart("capa");
        String nomeArquivo = capaPart.getSubmittedFileName();
        if (nomeArquivo == null || nomeArquivo == "")
            nomeArquivo = "img/livros/capa_default.png";
        String isbn = request.getParameter("isbn");
        String capa = new ProcessadorFotos("img/livros").processarArquivoCapa(request, capaPart, "capa" + isbn + nomeArquivo);
        livro.setCapa(capa);
        
        int ano = Integer.parseInt(request.getParameter("anoPublicacao"));
        livro.setAnoPublicacao(ano);
        livro.setAreaTema(request.getParameter("areaTema"));
        livro.setAutores(request.getParameter("autores"));
        livro.setEditora(request.getParameter("editora"));
        livro.setIsbn(isbn);
        livro.setTitulo(request.getParameter("titulo"));

        return livro;
    }

    

}
