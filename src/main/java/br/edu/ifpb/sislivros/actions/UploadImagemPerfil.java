/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.actions;

import br.edu.ifpb.sislivros.model.ControleUsuario;
import br.edu.ifpb.sislivros.model.ProcessadorFotos;
import br.edu.ifpb.sislivros.entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Natarajan
 */
public class UploadImagemPerfil implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        Part fotoPart = request.getPart("imagem");
        
        String foto = new ProcessadorFotos("img/profiles").processarFotoPerfil(request, fotoPart, "imagemPerfil"+usuario.getId());
        if (foto != null){

            usuario.setFoto(foto);
            ControleUsuario control = new ControleUsuario();
            if (!control.atualizar(usuario, usuario.getId())){
                return;
            }
        }

//        request.getRequestDispatcher("/editarPerfil").forward(request, response);
        response.sendRedirect("editarPerfil");
    }
    
}
