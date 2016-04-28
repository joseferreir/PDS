/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.loader;

import br.edu.ifpb.sislivros.model.VerificarCadastroBo;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
public class LoaderVerificarUsuario {
    public static void main(String[] args) {
         //cadastra um usuario
        Usuario user = new Usuario();
        user.setApelido(" ");
        user.setCidade("");
        user.setDataNascimento(null);
        user.setEmail("natarajan@gmail.com");
        user.setEstado("");
        user.setFoto("/../");
        user.setNome("");
        user.setSenha("");
        user.setAdmin(true);
        
        Map<String, String> verificacao = VerificarCadastroBo.execute(user);
        
        verificacao.keySet().stream().forEach((chave) -> {
            System.out.println(chave + " - " + verificacao.get(chave));
        });
        
        
        
        
    }
}
