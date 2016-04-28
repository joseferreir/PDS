<%-- 
    Document   : livros
    Created on : 23/03/2016, 01:22:34
    Author     : Natarajan 
--%>

<%@page import="br.edu.ifpb.sislivros.entidades.Livro"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.sislivros.model.LivroBuscarBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach var="livro" items="${livrosBuscados}">
    <div class="col-sm-6 col-md-3">
        <div class="thumbnail">
            <img src="${livro.capa}" alt="capa do livro" style="width:200px; height: 280px;" class='avatar img-thumbnail'>
            <div class="caption text-center">
                <h4 class="limit">${livro.titulo}</h4>
                <p>
                    <a href="/sislivros/logged/livro/pre/ver.do?isbn=${livro.isbn}" class="btn btn-primary" role="button">Ver livro</a>
                </p>
            </div>
        </div>
    </div>    
</c:forEach>

<script src="./dist/js/funcoesBusca.js"></script>
<script src="./dist/js/fitNomeLivro.js"></script>