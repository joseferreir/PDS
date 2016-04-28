/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.tags;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Natarajan
 */
public class FormatadorDataHora extends SimpleTagSupport {

    private LocalDateTime dataHora;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String resultado = dtf.format(dataHora);
        this.getJspContext().getOut().print(resultado);
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
}
