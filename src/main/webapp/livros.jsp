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
<link rel="stylesheet" href="dist/css/another.css">
<div class="container-fluid" style='padding: 30px;'>

    <div id="barraBuscaLivros" class="well clearfix" >

        <form action='' method='post' id="formBusca" >

            <div class="row">
                <div class="form-group col-sm-6">
                    <div class="col-sm-7">
                        <input id="parametro" name="parametro" type="text" class="form-control" placeholder="Buscar Livros por Atributos">
                    </div>
                    <div class="col-sm-5">
                        <select class="form-control" name="campo" id="campo">
                            <option value="all"     >Todos os campos</option>
                            <option value="titulo"  >Título</option>
                            <option value="autores" >Autores</option>
                            <option value="isbn"    >ISBN</option>
                            <option value="editora" >Editora</option>
                        </select>
                    </div>	
                </div>

                <div class="form-group col-sm-3">

                    <div class="col-sm-12">
                        <div class="form-inline">
                            <div class="form-group">
                                Avaliação:
                                <select class="form-control" name="min" id='min'>
                                    <option value="0">MIN</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                            <div class="form-group"> 
                                <select class="form-control" name="max" id='max'>
                                    <option value='0'>MAX</option>
                                    <option value='1'>1</option>
                                    <option value='2'>2</option>
                                    <option value='3'>3</option>
                                    <option value='4'>4</option>
                                    <option value='5'>5</option>
                                </select>
                            </div>
                        </div>                        
                    </div>

                </div>
                <div class="form-group col-sm-1">
                    <button id="buscaLivros" type="button" class="btn btn-primary">Buscar</button>
                </div>
            </div>

            <div class="container-fluid">
                
                <div class="row form-group col-sm-6">
                    Ordenar Por:
                    <div class="btn-group" data-toggle="buttons">

                        <label class="btn btn-default active">
                            <input name="ordem" id="tituloOrder" type="radio" value="titulo" checked> Título
                        </label>
                        <label class="btn btn-default">
                            <input name="ordem" id="anoOrder" type="radio" value="ano"> Ano
                        </label>
                        <label class="btn btn-default">
                            <input name="ordem" id="ratingOrder" type="radio" value="rating"> Rating
                        </label>
                    </div>

                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active"><i class="glyphicon glyphicon glyphicon-sort-by-attributes">
                                <input name="sentido" id="" type="radio" value="asc" checked></i> Cresc
                        </label>
                        <label class="btn btn-default"><i class="glyphicon glyphicon glyphicon-sort-by-attributes-alt">
                                <input name="sentido" id="" type="radio" value="desc"></i> Decres
                        </label>
                    </div>
                    <!--                    <div class="btn-group pull-right">
                                            <button id="buscaLivros" type="button" class="btn btn-primary">Buscar</button>
                                        </div>-->
                </div>
<!--                <div class="form-group col-sm-6">
                    <button id="buscaLivros" type="button" class="btn btn-primary">Buscar</button>
                </div>-->

            </div>



        </form>


    </div>

    <%                            LivroBuscarBo livroBuscarBo = new LivroBuscarBo();
        List<Livro> livrosAmostra = livroBuscarBo.buscarAmostra();
        request.setAttribute("livrosAmostra", livrosAmostra);
    %>

    <!--    <div id="snipper" class="clearfix hidden" style="padding: 30px">
            <img src="img/Spinner.gif" class="center-block">
        </div>-->

    <div id="livrosSelecionados">
        <c:forEach var="livro" items="${livrosAmostra}" begin="0" end="3">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <img src="${livro.capa}" alt="capa do livro" style="width:200px; height: 280px;" class='avatar img-thumbnail'>
                    <div class="caption text-center">
                        <h4 class="limit">${livro.titulo}</h4>
                        <!--<p>...</p>-->
                        <p>
                            <a href="/sislivros/logged/livro/pre/ver.do?isbn=${livro.isbn}" class="btn btn-primary" role="button">Ver livro</a>
                            <!--                                        <a href="#" class="btn btn-default" role="button">Button</a>-->
                        </p>
                    </div>
                </div>
            </div>    
        </c:forEach>
    </div>

</div>
