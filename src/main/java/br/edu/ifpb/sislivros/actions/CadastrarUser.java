/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.model.CadastrarUsuarioBo;
import br.edu.ifpb.sislivros.model.VerificarCadastroBo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import br.edu.ifpb.sislivros.exceptions.EmailExistenteException;
import br.edu.ifpb.sislivros.model.BuscaUsuarioBo;
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
 * @author José
 */
public class CadastrarUser implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuario = montarUsuario(request);

        Map<String, String> resultadoVerificacao = VerificarCadastroBo.execute(usuario);

        boolean resultadoCadastro = false;

        if (resultadoVerificacao.get("passou").equals("true")) {

            CadastrarUsuarioBo boCadastro = new CadastrarUsuarioBo();
            try {
                resultadoCadastro = boCadastro.cadastrar(usuario);
            } catch (EmailExistenteException ex) {
                Logger.getLogger(CadastrarUser.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(ex.getMessage());
            } finally {
                if (resultadoCadastro) {
                    Integer idCriado = new BuscaUsuarioBo().buscarPorEmail(usuario.getEmail()).getId();
                    usuario.setId(idCriado);
                    sessao.setAttribute("usuario", usuario);
                }
            }
        }

        String json = new Gson().toJson(resultadoVerificacao);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private Usuario montarUsuario(HttpServletRequest request) {
        Usuario user = new Usuario();

        if (request.getParameter("nome") != null) {
            user.setNome(request.getParameter("nome"));
        }
        if (request.getParameter("apelido") != null) {
            user.setApelido(request.getParameter("apelido"));
        }

        user.setFoto("img/profiles/reader-default.png");

        if (request.getParameter("email") != null) {
            user.setEmail(request.getParameter("email"));
        }

        if (request.getParameter("senha") != null) {
            user.setSenha(request.getParameter("senha"));
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = request.getParameter("dataNascimento");
        if (data != null && data != "") {
            user.setDataNascimento(LocalDate.parse(request.getParameter("dataNascimento"), dtf));
        }
        if (request.getParameter("cidade") != null) {
            user.setCidade(request.getParameter("cidade"));
        }

        if (request.getParameter("estado") != null) {
            user.setEstado(request.getParameter("estado"));
        }

        user.setAdmin(false);

        return user;
    }

}
