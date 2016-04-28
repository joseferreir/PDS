/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import br.edu.ifpb.sislivros.factory.DaoFactory;
import br.edu.ifpb.sislivros.factory.DaoFactoryIF;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Verifica se o dados de um usário são válidos segundo regras de negócio.
 * <p>
 * Retorna um Map<String,String> cujas chaves representam os campos com problema
 * e os valores são mensagens de erro explicativas. Quando a verificação ocorre
 * sem erros, um Map vazio é devolvido. Se for informado um Usuário nulo, o
 * serviço também devolve um Map nulo.
 *
 * @author Natarajan
 */
public class VerificarAtualizarUsuarioBo {

    public static final Pattern REGEX_EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static Map<String, String> execute(Usuario usuario, String emailOriginal) {

        Map<String, String> resultado = new HashMap<>();

        if (usuario == null) {
            return null;
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            resultado.put("nome", "Informe seu nome.");
        } else if (!usuario.getNome().matches("[A-Za-zÀ-ú0-9 ]+")) {
            resultado.put("nome", "Nome não pode conter caracteres especiais (% - $ _ # @, por exemplo).");
        }

        if (usuario.getApelido() == null || usuario.getApelido().trim().isEmpty()) {
            resultado.put("apelido", "Informe um apelido.");
        }

        if (usuario.getDataNascimento() == null) {
            resultado.put("dataNascimento", "Informe sua data de nascimento.");
        }

        if (usuario.getCidade() == null || usuario.getCidade().trim().isEmpty()) {
            resultado.put("cidade", "Informe sua cidade de origem.");
        }

        if (usuario.getEstado() == null || usuario.getEstado().trim().isEmpty()) {
            resultado.put("estado", "Informe o Estado a que pertence a cidade.");
        }

        if (    usuario.getEmail() == null ||
                usuario.getEmail().trim().isEmpty() ||
                !REGEX_EMAIL_VALIDO.matcher(usuario.getEmail()).find())
        {
            resultado.put("email", "Informe um email válido.");
        } else {
            DaoFactoryIF factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
            Usuario usuarioEmail = factory.criaUsuarioDAO().buscarPorEmail(usuario.getEmail());
            if ((usuarioEmail != null) && (!usuario.getEmail().equals(emailOriginal))) {
                resultado.put("emailJaExiste", "Já existe um usuário cadastro com este email informado.");
            }
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            resultado.put("senha", "Informe sua senha.");
        }

        if (resultado.isEmpty()) {
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }

        return resultado;
    }
}
