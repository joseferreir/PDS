/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItem;
import java.io.IOException;

/**
 *
 * @author José
 */
public class RequisicaoDeImg {

    public static void inserirImagem(FileItem item, String realPath, String nomeDaImagem) throws IOException {

        //Pegar o diretorio /imagensPerfil dentro do diretorio atual
        String diretorio = realPath + "/";

        //Criar diretorio caso não exista;
        File f = new File(diretorio);

        if (!f.exists()) {
            f.mkdir();
        }

        //Mandar o arquivo para o diretorio informado
        f = new File(diretorio + nomeDaImagem + ".jpg");

        try {
            FileOutputStream output = new FileOutputStream(f);
            InputStream is = item.getInputStream();

            byte[] buffer = new byte[2048];

            int nLidos;

            while ((nLidos = is.read(buffer)) >= 0) {
                output.write(buffer, 0, nLidos);
            }

            output.flush();
        } finally {

        }

    }

}
