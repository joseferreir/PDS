<%-- 
    Document   : criarGrupo
    Created on : 25/03/2016, 23:47:40
    Author     : Natarajan 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisLivros - Grupos</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/another.css">
        <!--Font Awsome-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/font-awesome.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap-theme.min.css">
    </head>
    <body>

        <!--Modal Sair do Grupo-->
        <div class="modal fade" id="modal-confirmar-sair">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title modal-title-center form-singin-heading">Sair do Grupo</h3>
                    </div>
                    <div class="modal-body" id="corpoModalLogin">
                        <h4>Confirmar sua saída do grupo?</h4>
                        <p>Suas contribuições não serão removidas(!), mas você ainda pode retornar ao grupo.</p>
                    </div>

                    <div class="modal-footer">
                        <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                        <!--<a id="btnEntrar" type="submit" class="btn btn-primary">Entrar</a>-->
                        <a href="/sislivros/logged/grupo.sair.do?idGrupo=${grupo.id}" class="btn btn-danger pull-right"><i class="glyphicon glyphicon-remove"></i>  Sair</a>
                    </div>
                </div>
            </div>
        </div>

        <!--Modal Criar Tópico-->
        <div class="modal fade" id="modal-criar-topico">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title modal-title-center form-singin-heading">Criar Tópico</h3>
                    </div>
                    <div class="modal-body" id="corpoModalLogin">
                        <form action="/sislivros/logged/topico/criar.do?idGrupo=${grupo.id}" id="criarTopico" method="post">
                            <div class="form-group">
                                <label for="livro" class="control-label">Escolha um livro para o tópico</label>
                                <!--<input type="text" id="tituloBuscar" class="form-control" name="tituloBuscar">-->
                                <select class="form-control" id="livro" name="livro" required> 
                                    <c:forEach  var="livro" items="${livros}">
                                        <option value="${livro.id}">
                                            ${livro.titulo} - Autor(es): ${livro.autores}
                                        </option> 
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="descricao" class="control-label">Descrição</label>
                                <textarea id="descricao" name="descricao" class="form-control" rows="5" maxlength="1000" required></textarea>
                            </div>
                        </form>   
                    </div>
                    <div class="modal-footer">
                        <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                        <input id="btnCriarTopico" type="submit" form="criarTopico" class="btn btn-primary" value="Criar">
                    </div>
                </div>
            </div>
        </div>
        <!--FIM MODAL-->

        <header style="padding-bottom: 40px">
            <%@ include file="/barraHome.jsp"%>
        </header>

        <div class="jumbotron">
             <!-- style="background-image: url('${grupo}'); -->

            <div class="container well well-sm">
                <h3>${grupo.nome}</h3>
                <p>${grupo.descricao}</p>

                <c:if var="ehDoGrupo" test="${participanteBo.ehParticipante(usuario.getId(), grupo.getId()) eq false}">
                    <a href="/sislivros/logged/grupo/entrar.do?idGrupo=${grupo.id}" class="btn btn-primary btn-sm pull-right"> Entrar no Grupo</a>
                </c:if>
                <c:if var="ehDoGrupo" test="${participanteBo.ehParticipante(usuario.getId(), grupo.getId()) eq true}">
                    <button type="button" class="btn btn-danger btn-sm pull-right" data-toggle="modal" data-target="#modal-confirmar-sair">Sair do Grupo</button>
                </c:if>
            </div> 
        </div>

        <div class="container" style="min-height: 550px;">

            <ul class="nav nav-tabs" id="navs_opcoes">
                <li role="presentation" class="active"><a href="#topicos" aria-controls="topicos" >Tópicos</a></li>
                <li role="presentation"><a href="#participantes" aria-controls="participantes" >Participantes</a></li>
            </ul>

            <div class="tab-content" id="tab_opcoes">

                <!-- TOPICOS -->
                <div role="tabpanel" class="tab-pane active" id="topicos" style="min-height:450px">
                    <div class="container-fluid" style='padding: 30px;'>

                        <c:if var="ehDoGrupo" test="${participanteBo.ehParticipante(usuario.getId(), grupo.getId()) eq true}">
                            <span class="container">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-criar-topico">Criar Tópico</button>
                            </span>
                        </c:if>


                        <span class="container">
                            <c:forEach var="topico" items="${topicos}">
                                <div class="media">
                                    <div class="media-left media-middle">
                                        <img class="media-object" src="${pageContext.request.contextPath}/${topico.livro.capa}" alt="capa do livro relacionado ao grupo" style="width:80px; height: 112px;">
                                    </div>
                                    <div class="media-body media-middle">
                                        <h4 class="media-heading"><a href="/sislivros/logged/topico/pre/ver.do?idTopico=${topico.id}"> ${topico.descricao}</a></h4>
                                        <p>Sobre o livro: "${topico.livro.titulo}" de "${topico.livro.autores}" </p>

                                    </div>

                                </div>
                            </c:forEach>
                        </span>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="participantes" style="min-height:450px">
                    <div class="container-fluid" style='padding: 30px;'>


                        <c:forEach var="participante" items="${participantes}">
                            <style type="text/css">
                                a {text-decoration: none;}
                                a:hover {text-decoration: none;}
                            </style>
                            <a href="${pageContext.request.contextPath}/verPerfil?id=${participante.id}"><h5 class="media-heading"><strong> <img src="${pageContext.request.contextPath}/${participante.foto}" style="width: 30px; height:30px; object-fit: cover">  ${participante.nome}
                                    </strong></h5></a>
                            <br>

                        </c:forEach>
                    </div>
                </div>

            </div>

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
