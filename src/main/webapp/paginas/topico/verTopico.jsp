<%-- 
    Document   : criarGrupo
    Created on : 25/03/2016, 23:47:40
    Author     : Natarajan 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ct" uri="/WEB-INF/MyTags.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisLivros - Tópico</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/another.css">
        <!--Font Awsome-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/font-awesome.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap-theme.min.css">

        <!-- CSS Especial dos Comentários -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/comentarios.css">
    </head>
    <body>

        <header style="padding-bottom: 40px">
            <%@ include file="/barraHome.jsp"%>
        </header>

        <!--jumbotron-->
        <div class="jumbotron">
             <!-- style="background-image: url('${grupo}'); -->

            <div class="container">
                <div class="media">
                    <div class="media-body media-middle">
                        <h2 class="media-heading">Grupo: ${topico.grupo.nome}</h2>
                        <p>Tópico: ${topico.descricao}</p>
                        <p><small>Sobre o livro: "${topico.livro.titulo}" de "${topico.livro.autores}"</small></p>
                    </div>
                    <div class="media-right media-middle">
                        <img class="media-object" src="${pageContext.request.contextPath}/${topico.livro.capa}" alt="capa do livro relacionado ao grupo" style="width:80px; height: 112px;">
                    </div>
                </div>
            </div>

        </div>

        <!--body-->
        <div class="container" style="min-height: 550px;">
            <!--se tiver reposta mostra esse span-->

            <c:if test="${respostas ne null}">
                <span class="container">
                    <div class="timeline-messages col-md-10 col-md-push-1">
                        <div class="msg-time-chat">
                            <ul class="messages">

                                <c:forEach var="resposta" items="${respostas}">
                                    <li class="user clearfix">
                                        <a href="#" class="avatar">
                                            <img src="${pageContext.request.contextPath}/${resposta.usuario.foto}" alt="" />
                                        </a>
                                        <div class="message">
                                            <div class="head clearfix">
                                                <span class="name"><strong>${resposta.usuario.nome}</strong> disse:</span>
                                                <span class="time"><ct:FormatadorDataHora dataHora="${resposta.dataHora}" /></span>
                                            </div>
                                            <p>
                                                ${resposta.resposta}
                                            </p>
                                        </div>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                    </div>
                </span >

            </c:if>




            <c:if var="ehDoGrupo" test="${participanteBo.ehParticipante(usuario.getId(), topico.grupo.getId()) eq true}">
                <div class="container-fluid well col-md-10 col-md-push-1">
                    <form id="minhaResposta" action='/sislivros/logged/respostatopico/adicionar.do' method='post'>
                        <div class="form-group">
                            <!--<label for="resposta" class="control-label"> Resposta</label>-->
                            <input name="idTopico" value="${topico.id}" hidden="true">
                            <textarea id="resposta" name="resposta" class="form-control" rows="5" maxlength="5000" required placeholder="Digite aqui uma resposta."></textarea>
                        </div>
                    </form>
                    <input id="btnEnviarResposta" type="submit" form="minhaResposta" class="btn btn-primary" value="Enviar Resposta">
                </div>
            </c:if>







        </div>

        <!--FOOTER-->
        <footer>
            <%@ include file="/footer.jsp"%>
        </footer>
        <!-- Latest compiled and minified JavaScript -->
        <script src="${pageContext.request.contextPath}/dist/js/jquery-2.1.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/dist/js/bootstrap.min.js"></script>
        <script>
            var url = document.location.toString();
            if (url.match('#')) {
                $('.nav-tabs a[href=#' + url.split('#')[1] + ']').tab('show');
            }

            // Change hash for page-reload
            $('.nav-tabs a').on('shown.bs.tab', function (e) {
                window.location.hash = e.target.hash;
                window.scrollTo(0, 0);
            });

            $('.nav-tabs a[href="#topicos"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#topicos"]').tab('show');
            });
            $('.nav-tabs a[href="#participantes"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#participantes"]').tab('show');
            });


        </script>

    </body>
</html>
