<%-- 
    Document   : editar_perfil
    Created on : 10/02/2016, 00:20:25
    Author     : Natarajan
--%>
<%@page import="br.edu.ifpb.sislivros.entidades.Livro"%>
<%@page import="br.edu.ifpb.sislivros.model.ControleLivro"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>SisLivros</title>
        <meta name="description" content="Hello World">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/another.css">
        <!-- Datepicker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/datepicker/bootstrap-datepicker.min.css">
        <!-- Uploader-->
        <link href="${pageContext.request.contextPath}/dist/css/uploader/fileinput.css" media="all" rel="stylesheet" type="text/css" >
        <!--Font Awsome-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/font-awesome.min.css">

        <!--Stars-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/stars/css/star-rating.min.css" media="all" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/stars/css/theme-krajee-fa.css" media="all" type="text/css"/>
    </head>

    <body>
        <!--Modal realizar avaliação-->
        <div class="modal fade" id="modal-avaliar">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title modal-title-center form-singin-heading text-center">Avaliar Livro</h3>
                    </div>
                    <div class="modal-body" id="corpoModalAvaliar">
                        <form id="avaliacao" action="/sislivros/logged/avaliacaolivro/cadastrar.do" method="post">
                            <div class="form-group">
                                <label for="email" class="control-label">Sua Avaliação</label>
                                <input id="novaAvaliacao" name="novaAvaliacao" type="text" class="rating rating-loading" value="" data-size="xs" title="">
                            </div>
                            <div class="form-group">
                                <label for="comentario" class="control-label">Comentário</label>
                                <textarea id="comentario" name="comentario" class="form-control" rows="5" required></textarea>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                        <!--                        <a id="btnAvaliar" type="submit" class="btn btn-primary">Salvar</a>-->
                        <input id="btnAvaliar" type="submit" form="avaliacao" class="btn btn-primary" value="Salvar">
                    </div>
                </div>
            </div>
        </div>

        <!--Modal alterar avaliação-->
        <div class="modal fade" id="modal-alterar-avaliacao">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title modal-title-center form-singin-heading text-center">Alterar Avaliação</h3>
                    </div>
                    <div class="modal-body" id="corpoModalAvaliar">
                        <form id="avaliacaoAlterar" action="/sislivros/logged/avaliacaolivro/alterar.do" method="post">
                            <div class="form-group">
                                <label for="email" class="control-label">Sua Avaliação</label>
                                <input id="alterarAvaliacao" name="alterarAvaliacao" type="text" class="rating rating-loading" value="${avaliacao.rating}" data-size="xs" title="">
                            </div>
                            <div class="form-group">
                                <label for="comentario" class="control-label">Comentário</label>
                                <textarea id="comentario" name="comentario" class="form-control" rows="5" required>${avaliacao.comentario}</textarea>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                        <input id="btnAvaliar" type="submit" form="avaliacaoAlterar" class="btn btn-primary" value="Salvar Alteração">
                    </div>
                </div>
            </div>
        </div>

        <!--Modal recomendar-->
        <div class="modal fade" id="modal-recomendacao">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title modal-title-center form-singin-heading text-center">Recomendar Livro</h3>
                    </div>
                    <div class="modal-body" id="corpoModalRecomendar">
                        <form id="recomendar" action="/sislivros/logged/livro/recomendar.do" method="post">
                            <div class="form-group ">
                                <label for="amigo" class="control-label">Escolha o seu amigo</label>

                                <select class="form-control" id="amigo" name="amigo"> 
                                    <c:forEach var="amigo" items="${amigos}">
                                        <option value="${amigo.id}">
                                            ${amigo.nome}
                                        </option> 
                                    </c:forEach>
                                </select>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                        <input id="btnRecomendar" type="submit" form="recomendar" class="btn btn-primary" value="Enviar">
                    </div>
                </div>
            </div>
        </div>

        <!--<header>-->
        <c:if test="${usuario ne null}">
            <%@ include file="barraHome.jsp"%>
        </c:if>
        <c:if test="${usuario eq null}">
            <%@ include file="barraHomeNullUser.jsp"%>
        </c:if>

        <!--</header>-->
        <!-- Container da Edição de Perfil-->
        <div class="container" style="min-height: 650px; padding: 50px">

            <div class="row col-md-8 col-sm-10 col-sm-offset-1 col-md-offset-2" style='padding-top: 30px;'>

                <div class="panel panel-primary">
                    <div class="panel-heading text-center">                        
                        <h3>${livro.titulo}</h3> 
                    </div>

                    <div class="panel-body">
                        <div class="col-md-4 text-center">
                            <img id="capa" src="${pageContext.request.contextPath}/${livro.capa}" style='width: 200px; height:290px; object-fit: cover' class='avatar img-thumbnail' alt="capa do livro">
                            <input id="avaliacaoTotal" name="avaliacaoTotal" type="text" class="rating" value="${livro.rating}" data-size="xs">
                            <div>
                                <c:if test="${usuario ne nu && usuario.admin eq true}">
                                    <div>
                                        <a href="editarlivro?isbn=${livro.isbn}" id="editalivro" type="button" class="btn btn-primary navbar-btn btn-block" ><span class="glyphicon glyphicon-edit"></span> Editar Livro</a>
                                    </div>
                                </c:if>
                                <c:if test="${usuario ne null && avaliacao eq null}">
                                    <div>
                                        <button type="button" class="btn btn-primary navbar-btn btn-block" data-toggle="modal" data-target="#modal-avaliar"><span class="glyphicon glyphicon-star"></span> Avaliar Livro</button>
                                    </div>
                                </c:if>
                                <c:if test="${usuario ne null && avaliacao ne null}">
                                    <div>
                                        <button type="button" class="btn btn-primary navbar-btn btn-block" data-toggle="modal" data-target="#modal-alterar-avaliacao"><span class="glyphicon glyphicon-star"></span> Alterar Avaliação</button>
                                    </div>
                                </c:if>
                                <c:if test="${usuario ne null}">
                                    <div>
                                        <button type="button" class="btn btn-primary navbar-btn btn-block" data-toggle="modal" data-target="#modal-recomendacao"><span class="glyphicon glyphicon-share-alt"></span> Recomendar</button>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="col-md-8">
                            <br>
                            <dl class="dl-horizontal">
                                <dt>Autor(es):</dt>
                                <dd>${livro.autores}</dd>
                            </dl>

                            <dl class="dl-horizontal">
                                <dt>ISBN:</dt>
                                <dd>${livro.isbn}</dd>
                            </dl>

                            <dl class="dl-horizontal">
                                <dt>Editora:</dt>
                                <dd>${livro.editora}</dd>
                            </dl>

                            <dl class="dl-horizontal">
                                <dt>Ano de Publicação:</dt>
                                <dd>${livro.anoPublicacao}</dd>
                            </dl>

                            <dl class="dl-horizontal">
                                <dt>Área / Tema:</dt>
                                <dd>${livro.areaTema}</dd>
                            </dl>

                            <c:if test="${usuario ne null && avaliacao ne null}">
                                <dl class="dl-horizontal">
                                    <dt>Sua avaliação:</dt>
                                    <div>
                                        <dd>
                                            ${avaliacao.comentario}
                                        </dd>
                                        <dd><input id="minhaAvaliacao" name="minhaAvaliacao" type="text" class="rating" value="${avaliacao.rating}" data-size="xs"></dd>
                                    </div>

                                </dl>
                            </c:if>
                            <!--                            <dl class="dl-horizontal">
                                                            <dt>Avaliação total:</dt>
                                                            <div>
                                                                <dd><input id="avaliacaoTotal" name="avaliacaoTotal" type="text" class="rating" value="${livro.rating}" data-size="xs"></dd>
                                                            </div>
                            
                                                        </dl>-->



                            <!--                            <span>
                                                            <h4><strong>Autor(es):</strong></h4><h5>${livro.autores}</h5> 
                                                        </span>
                                                        <span>
                                                            <h4><strong>ISBN:</strong></h4><h5>${livro.isbn}</h5> 
                                                        </span>
                                                        <span>
                                                            <h4><strong>Editora:</strong></h4><h5>${livro.editora}</h5> 
                                                        </span>
                                                        <span>
                                                            <h4><strong>Ano de Publicação: </strong></h4><h5>${livro.anoPublicacao}</h5> 
                                                        </span>
                                                        <span>
                                                            <h4><strong>Área / Tema: </strong></h4><h5>${livro.areaTema}</h5> 
                                                        </span>-->
                            <%--<c:if test="${avaliacao ne null}">--%>
                            <!--                                <span>
                                                                <h4><strong>Sua avaliação: </strong></h4>
                                                                <input id="minhaAvaliacao" name="minhaAvaliacao" type="text" class="rating rating-loading" value="${avaliacao.rating}" data-size="xs" title="">
                                                            </span>-->
                            <%--</c:if>--%>
                        </div>
                    </div>
                    <c:if test="${!empty avaliacoesLivro}">
                        <div class="panel-footer">

                            <h4 class="text-center">Avaliações feitas por usuários</h4>
                            <table class="table table-striped">
                                <c:forEach var="av" items="${avaliacoesLivro}">
                                    <tr><td>
                                    <div class="media">
                                        <div class="media-body media-right media-middle">
                                            <p class="text-right">"${av.comentario}"</p>
                                            <input class="avUsuarios" dir="rtl" type="text" class="rating rating-loading" value="${av.rating}" data-size="xs" title="">
                                            <!--<input id="avaliacaoTotal" name="avaliacaoTotal" type="text" class="rating" value="${livro.rating}" data-size="xs">-->
                                        </div>
                                        <div class="media-right media-middle">
                                            <img src="${av.usuario.foto}" class="media-object" style="width: 30px; height:30px; object-fit: cover">
                                        </div>

                                    </div> 
                                    <td></tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:if>

                </div>
                <div>
                    <!--<a href="administrativo.jsp" id="volta" type="button" class="btn btn-default" ><span class="glyphicon glyphicon-backward"></span> volta</a>-->





                </div>

            </div>


        </div>
    </div>



    <!--FOOTER-->
    <c:if test="${usuario ne null}">
        <%@ include file="footer.jsp"%>
    </c:if>


    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/dist/js/jquery-2.1.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/datepicker/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/datepicker/bootstrap-datepicker.pt-BR.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/uploader/fileinput.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/uploader/fileinput_locale_pt-BR.js"></script>
    <script src="${pageContext.request.contextPath}/dist/stars/js/star-rating.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dist/stars/js/star-rating_locale_pt-br.js" type="text/javascript"></script>
    <script>
        
        
        $('#novaAvaliacao').rating({
            min: 0,
            max: 5,
            step: 1,
            size: 'xs',
            showClear: true,
            language: "pt-BR"
        });

        $('#alterarAvaliacao').rating({
            min: 0,
            max: 5,
            step: 1,
            size: 'xs',
            showClear: true,
            language: "pt-BR"
        });

        $('#minhaAvaliacao').rating({
            min: 0,
            max: 5,
            step: 1,
            size: 'xs',
            showClear: false,
            displayOnly: true,
            language: "pt-BR"
        });
        
        $('#avaliacaoTotal').rating({
            min: 0,
            max: 5,
            step: 0.1,
            size: 'xs',
            showClear: false,
            displayOnly: true,
            language: "pt-BR"
        });
        
        $('.avUsuarios').rating({
            min: 0,
            max: 5,
            step: 1,
            size: 'xs',
            showClear: false,
            displayOnly: true,
            language: "pt-BR"
        });
        

        $("#editalivro").click(function () {
            var isbn = "${livro.isbn}";  // $(this).closest(${livro.isbn}).children(${livro.isbn}).text();

            location.href = "/sislivros/logged/livro/atualizar.do?isbn=" + isbn;
        });
    </script>
</body> 
</html>