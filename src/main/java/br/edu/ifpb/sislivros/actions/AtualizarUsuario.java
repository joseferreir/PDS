/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.model.AtualizarUsuarioBo;
import br.edu.ifpb.sislivros.model.VerificarAtualizarUsuarioBo;
import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.exceptions.EmailExistenteException;
import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Natarajan
 */
public class AtualizarUsuario implements Action{
   
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        String emailOriginal = usuario.getEmail();

        Usuario userComNovasInfos = DaoFactory.createFactory(DaoFactory.DAO_BD).criaUsuarioDAO().buscarPorId(usuario.getId());

        String erro = "";
        boolean resultado = false;
        if (userComNovasInfos != null) {

            atualizarInformacoes(request, userComNovasInfos);

            Map<String, String> resultadoVerificacao = VerificarAtualizarUsuarioBo.execute(userComNovasInfos, emailOriginal);

            if (resultadoVerificacao.get("passou").equals("true")) {
                AtualizarUsuarioBo boAtualizarCadastro = new AtualizarUsuarioBo();

                try {
                    resultado = boAtualizarCadastro.atualizar(userComNovasInfos);
                } catch (EmailExistenteException ex) {
                    Logger.getLogger(CadastrarUser.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println(ex.getMessage());
                } finally {
                    if (resultado) {
                        sessao.setAttribute("usuario", userComNovasInfos);
                    }
                }
            }

            String json = new Gson().toJson(resultadoVerificacao);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
    }
    
    
    private void atualizarInformacoes(HttpServletRequest request, Usuario user) {
        user.setNome(request.getParameter("nome"));
        user.setApelido(request.getParameter("apelido"));
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = request.getParameter("dataNascimento");
        if (data != null && data != ""){
            user.setDataNascimento(LocalDate.parse(request.getParameter("dataNascimento"), DTF));
        }
        user.setCidade(request.getParameter("cidade"));
        user.setEstado(request.getParameter("estado"));
        user.setEmail(request.getParameter("email"));

    }


}
