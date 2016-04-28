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

<!--Font Awsome-->
<!--<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/font-awesome.min.css">

<script src="dist/js/jquery-2.1.4.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/stars/css/star-rating.min.css" media="all" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/stars/css/theme-krajee-fa.css" media="all" type="text/css"/>

<script src="${pageContext.request.contextPath}/dist/stars/js/star-rating.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/dist/stars/js/star-rating_locale_pt-br.js" type="text/javascript"></script>-->


<c:forEach var="livro" items="${livrosBuscados}">
    <div class="col-sm-6 col-md-3">
        <div class="thumbnail">
            <a href="/sislivros/logged/livro/pre/ver.do?isbn=${livro.isbn}"><img src="${livro.capa}" alt="capa do livro" style="width:200px; height: 290px;" class='avatar img-thumbnail'></a>
            <div class="caption text-center">
                <h4 class="limit">${livro.titulo}</h4>
<!--                <p>
                    <a href="/sislivros/logged/livro/pre/ver.do?isbn=${livro.isbn}" class="btn btn-primary" role="button">Ver livro</a>
                </p>-->
                
                <input class="avLivro" type="text" class="rating rating-loading" value="${livro.rating}" data-size="xs" title="">
                
            </div>
        </div>
    </div>    
</c:forEach>

<script src="./dist/js/funcoesBusca.js"></script>
<script src="./dist/js/fitNomeLivro.js"></script>
<script>

    $('.avLivros').rating({
        min: 0,
        max: 5,
        step: 1,
        size: 'xs',
        showClear: false,
        displayOnly: true,
        language: "pt-BR"
    });
    
    $('.avLivro').rating('refresh', {disabled: true, showClear: false, displayOnly: true});

</script>