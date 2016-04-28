/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

/**
 *
 * @author Natarajan
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author João
 */
public class ProcessadorFotos {

    private String folder;

    public ProcessadorFotos(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public boolean salvarImagem(String path, FileItemStream item, String nameToSave) {
        try {
            File f = new File(path);
//            File f = new File(path+File.separator+folder);            
//            File parent = new File(f.getParent());
//            
//            if(!parent.exists())
//                parent.mkdir();
            if (!f.exists()) {
                f.mkdir();
            }

            File savedFile = new File(f.getAbsoluteFile() + File.separator + nameToSave);
            FileOutputStream fos = new FileOutputStream(savedFile);

            InputStream is = item.openStream();

            int x = 0;
            byte[] b = new byte[1024];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.flush();
            fos.close();

            return true;

        } catch (Exception ex) {
        }
        return false;
    }
    
    
    public boolean salvarImagemCapa(String path, InputStream item, String nameToSave) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }

            File savedFile = new File(f.getAbsoluteFile() + File.separator + nameToSave);
            FileOutputStream fos = new FileOutputStream(savedFile);

//            InputStream is = item.openStream();
            InputStream is = item;

            int x = 0;
            byte[] b = new byte[1024];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.flush();
            fos.close();

            return true;

        } catch (Exception ex) {
        }
        return false;
    }

    public String processarArquivo(HttpServletRequest request, String nameToSave)
            throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            ServletFileUpload upload = new ServletFileUpload();
            try {
                FileItemIterator itr = upload.getItemIterator(request);

                while (itr.hasNext()) {
                    FileItemStream item = itr.next();
                    if (!item.isFormField()) {
//                        pode ser tb sem a barra ????
//                        String path = request.getServletContext().getRealPath("");
                        String contextPath = request.getServletContext().getRealPath("/");

                        //refatorar aqui apenas para salvarimagem receber um pasta, inputStream e o nome
                        //aqui, criar um inputStream através do arquivo item antes de enviar
                        //diminuir 1 método, deixando salvarImagem mais genérico
                        if (salvarImagem(contextPath + File.separator + folder, item, nameToSave)) {
                            return folder + "/" + nameToSave;
                        }
                    }
                }

            } catch (FileUploadException ex) {
                System.out.println("erro ao obter informaçoes sobre o arquivo");
            }

        } else {
            System.out.println("Erro no formulario!");
        }

        return null;
    }

    public String processarFotoPerfil(HttpServletRequest request, Part fotoPart, String nameToSave)
            throws ServletException, IOException {

        
        if (fotoPart != null & nameToSave != null) {
            InputStream fileContent = fotoPart.getInputStream();
            String contextPath = request.getServletContext().getRealPath("/");
            
            if (salvarImagemCapa(contextPath + File.separator + folder, fileContent, nameToSave)) {
                return folder + "/" + nameToSave;
            }
        }

        return null;
    }
    
    public String processarArquivoCapa(HttpServletRequest request, Part partCapa, String nameToSave) throws IOException {

        if (partCapa != null & nameToSave != null) {
            InputStream fileContent = partCapa.getInputStream();
            String contextPath = request.getServletContext().getRealPath("/");
            
            if (salvarImagemCapa(contextPath + File.separator + folder, fileContent, nameToSave)) {
                return folder + "/" + nameToSave;
            }
        }

        return null;
    }

}
