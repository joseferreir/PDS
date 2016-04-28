<%-- 
    Document   : home
    Created on : 08/02/2016, 01:08:56
    Author     : susanneferraz
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="br.edu.ifpb.sislivros.model.RecomendacaoBuscarBo"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Recomendacao"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Livro"%>
<%@page import="br.edu.ifpb.sislivros.model.LivroBuscarBo"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ct" uri="/WEB-INF/MyTags.tld" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisLivros</title>
        <meta name="description" content="Hello World">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="./dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="dist/css/another.css">
        <!--Font Awsome-->
        <link rel="stylesheet" href="./dist/css/font-awesome.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="./dist/css/bootstrap-theme.min.css">
    </head>

    <body>
        <header style="padding-bottom: 70px">
            <%@ include file="barraHome.jsp"%>
        </header>

        <div class="container">
            <ul class="nav nav-tabs" id="navs_opcoes">
                <li role="presentation" class="active"><a href="#livros" aria-controls="livros" >Livros</a></li>
                <li role="presentation"><a href="#pessoas" aria-controls="pessoas" >Pessoas</a></li>
                <li role="presentation"><a href="#grupos" aria-controls="grupos" >Grupos</a></li>
                <li role="presentation"><a href="#recomendacoes" aria-controls="recomendacoes">Recomendações</a></li>
            </ul>

            <div class="tab-content" id="tab_opcoes">

                <!-- LIVROS -->
                <div role="tabpanel" class="tab-pane active" id="livros" style="min-height:450px">
                    <%@ include file="livros.jsp"%>
                </div>

                <div role="tabpanel" class="tab-pane" id="pessoas" style="min-height:550px;">
                    <%@ include file="pessoas.jsp"%>
                </div>
                <div role="tabpanel" class="tab-pane" id="grupos" style="min-height:550px">
                    <%@ include file="grupos.jsp"%>
                </div>
                <div role="tabpanel" class="tab-pane col-md-8 col-md-offset-2" id="recomendacoes" style="min-height:550px">
                    <div class="container-fluid" style="padding: 30px">
                        <%                            if (usuario != null) {
                                List<Recomendacao> recomParaMim = new RecomendacaoBuscarBo().buscarDestinario(usuario.getId());
                                List<Recomendacao> recomMinhas = new RecomendacaoBuscarBo().buscarRemetente(usuario.getId());

                                Collections.sort(recomParaMim, Recomendacao.Comparators.INVERT_SORT);
                                Collections.sort(recomMinhas, Recomendacao.Comparators.INVERT_SORT);

                                pageContext.setAttribute("recomendacoes", recomParaMim);
                                pageContext.setAttribute("minhasRecomendacoes", recomMinhas);
                            }

                        %>
                        <c:if test="${!empty recomendacoes}">
                            <h3>Recomendados Para Mim</h3>
                        </c:if> 
                        <c:forEach var="r" items="${recomendacoes}">
                            <div class="media">
                                <div class="media-left media-middle">
                                    <a href="/sislivros/logged/livro/pre/ver.do?isbn=${r.livro.isbn}"><img class="media-object" src="${r.livro.capa}" alt="capa do livro relacionado ao grupo" style="width:80px; height: 112px;"></a>
                                </div>
                                <div class="media-body media-middle">
                                    <strong><a href='verPerfil?id=${r.remetente.id}'>${r.remetente.nome}</a></strong> recomendou o livro <a href="/sislivros/logged/livro/pre/ver.do?isbn=${r.livro.isbn}">"${r.livro.titulo}"</a> para você, em <ct:FormatadorDataHora dataHora="${r.dataHora}" />.
                                </div>
                            </div> 

                        </c:forEach>
                            <hr>
                        <c:if test="${!empty minhasRecomendacoes}">
                            <h3 class="text-right">Minhas Recomendações</h3>
                        </c:if> 
                        <c:forEach var="r" items="${minhasRecomendacoes}">
                            <div class="media">
                                <div class="media-body media-middle media-right">
                                    <p class="text-right">Você recomendou o livro <a href="/sislivros/logged/livro/pre/ver.do?isbn=${r.livro.isbn}">"${r.livro.titulo}"</a> para <strong><a href='verPerfil?id=${r.destinatario.id}'>${r.destinatario.nome}</a></strong>, em <ct:FormatadorDataHora dataHora="${r.dataHora}" />.</p>
                                </div>
                                <div class="media-right media-middle">
                                    <a href="/sislivros/logged/livro/pre/ver.do?isbn=${r.livro.isbn}"><img class="media-object" src="${r.livro.capa}" alt="capa do livro relacionado ao grupo" style="width:80px; height: 112px;"></a>
                                </div>

                            </div> 

                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>


        <!--FOOTER-->
        <%@ include file="footer.jsp"%>

        <!-- Latest compiled and minified JavaScript -->
        <script src="./dist/js/jquery-2.1.4.min.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>
        <script src="./dist/js/funcoesBusca.js"></script>
        <script src="./dist/js/fitNomeLivro.js"></script>
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

            $('.nav-tabs a[href="#pessoas"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#pessoas"]').tab('show');
            });
            $('.nav-tabs a[href="#grupos"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#grupos"]').tab('show');
            });
            $('.nav-tabs a[href="#livros"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#livros"]').tab('show');
            });
            $('.nav-tabs a[href="#recomendacoes"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#recomendacoes"]').tab('show');
            });


            $('.breadcrumb a[href="home#pessoas"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#pessoas"]').tab('show');
            });
            $('.breadcrumb a[href="home#recomendacoes"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#recomendacoes"]').tab('show');
            });
            $('.breadcrumb a[href="home#grupos"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#grupos"]').tab('show');
            });
            $('.breadcrumb a[href="home#livros"]').click(function (e) {
                e.preventDefault();
                $('.nav-tabs a[href="#livros"]').tab('show');
            });

        </script>

    </body> 
</html>