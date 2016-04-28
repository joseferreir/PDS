/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import java.io.IOException;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author José
 */
public class ControleImagem {
     public void inserirImagemPerfil(FileItem item, String realPath, String nomeDaImagem) throws IOException{
         RequisicaoDeImg.inserirImagem(item, realPath, nomeDaImagem);
    }
    
}
