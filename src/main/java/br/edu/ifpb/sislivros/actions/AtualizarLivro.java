/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.entidades.Livro;
import br.edu.ifpb.sislivros.exceptions.LivroExistenteException;
import br.edu.ifpb.sislivros.model.AtualizarLivroBo;
import br.edu.ifpb.sislivros.model.ProcessadorFotos;
import br.edu.ifpb.sislivros.model.VerificaAtualizacaoLivro;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author José
 */
public class AtualizarLivro implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Livro livroComNovasInfo = null;

        Livro livroOriginal = (Livro) request.getSession().getAttribute("livro");
        String isbnOriginal = livroOriginal.getIsbn();

        if (livroOriginal != null) {

            livroComNovasInfo = atualizarInformacoes(request, livroOriginal);

            Map<String, String> resultadoVerificacao = VerificaAtualizacaoLivro.execute(isbnOriginal, livroComNovasInfo);

            if (resultadoVerificacao.get("passou").equals("true")) {
                boolean resultadoAlteracao = false;
                try {
                    resultadoAlteracao = new AtualizarLivroBo().atualizar(livroComNovasInfo);
                } catch (LivroExistenteException ex) {
                    Logger.getLogger(AtualizarLivro.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (resultadoAlteracao) {
                    request.setAttribute("livro", livroComNovasInfo);
                    response.sendRedirect(request.getContextPath() + "/administrativo.jsp");
                }
            } else if (resultadoVerificacao.containsKey("isbnJaExiste")) {
                response.sendRedirect(request.getContextPath() + "/errorPageISBN.html");
            }
        } else {
//            request.getRequestDispatcher("administrativo.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/administrativo.jsp");
        }
    }


    private Livro atualizarInformacoes(HttpServletRequest request, Livro livro) throws IOException, ServletException {

        //processando e salvando a capa do livro
        Part capaPart = request.getPart("capa");
        String isbn = request.getParameter("isbn");

        String nomeArquivo = capaPart.getSubmittedFileName();

        if (nomeArquivo != null && nomeArquivo != "") {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyhhss");
            StringBuilder sb = new StringBuilder();
            sb.append("newCapa").append(isbn).append(dateTime.format(dtf)).append(nomeArquivo);

            String capa = new ProcessadorFotos("img/livros").processarArquivoCapa(request, capaPart, sb.toString());
            livro.setCapa(capa);
        }

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
