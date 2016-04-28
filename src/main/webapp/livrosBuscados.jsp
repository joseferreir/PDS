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
<div class="container-fluid" style='padding: 30px;'>

    <div id="livrosSelecionados" class="well clearfix" >

        <form action='/livro/lista/' method='post'>

            <div class="form-group row">
                <div class="col-sm-2">
                    <h5>Busca por Atributos</h5>
                </div>
                <div class="col-sm-6">
                    <input id="parametro" name="parametro" type="text" class="form-control" placeholder="Buscar Livros" value="${parametro}">
                </div>
                <div class="col-sm-2">
                    <select class="form-control" name="campo" id="campo">
                        <option value="all"   
                                <c:if test="${campo eq 'Todos os campos'}">
                                    <c:out value="selected"/>
                                </c:if>        
                                >Todos os campos</option>
                        <option value="titulo"  
                                <c:if test="${campo eq 'titulo'}">
                                    <c:out value="selected"/>
                                </c:if>        
                                >Título</option>
                        <option value="autores" 
                                <c:if test="${campo eq 'autores'}">
                                    <c:out value="selected"/>
                                </c:if>        >Autores</option>
                        <option value="isbn"   
                                <c:if test="${campo eq 'isbn'}">
                                    <c:out value="selected"/>
                                </c:if>        >ISBN</option>
                        <option value="editora" 
                                <c:if test="${campo eq 'editora'}">
                                    <c:out value="selected"/>
                                </c:if>        >Editora</option>
                    </select>
                </div>	
                <div class="col-sm-2">
                    <button id="buscaLivros" type="button" class="btn btn-primary">Buscar por Atributos</button>			
                </div>

            </div>

            <div class="form-group row">
                <div class="col-sm-2">
                    <h5>Busca por Rating</h5>
                </div>

                <div class="col-sm-8">
                    <div class="form-inline">
                        <div class="form-group">
                            <label class="control-label">Mínimo de Estrelas</label>
                            <select class="form-control" name="min">
                                <option>-</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label"> Máximo de Estrelas</label>
                            <select class="form-control" name="min">
                                <option>-</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>

                    </div>

                </div>
                <div class="col-sm-2">
                    <button id="buscaLivros" type="button" class="btn btn-primary">Buscar por Rating</button>			
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-2">
                    <h5>Ordenar Por</h5>
                </div>

                <div class="btn-group col-sm-10" data-toggle="buttons">
                    <label class="btn btn-default active">
                        <input name="options" id="option1" type="radio"> Título
                    </label>
                    <label class="btn btn-default">
                        <input name="options" id="option2" type="radio"> Ano
                    </label>
                    <label class="btn btn-default">
                        <input name="options" id="option3" type="radio"> Rating
                    </label>
                </div>

            </div>


        </form>


    </div>

    <c:forEach var="livro" items="${livrosBuscados}">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="${livro.capa}" alt="capa do livro" style="width:200px; height: 280px;" class='avatar img-thumbnail'>
                <div class="caption text-center">
                    <h4>${livro.titulo}</h4>
                    <p>
                        <a href="/sislivros/logged/livro/pre/ver.do?isbn=${livro.isbn}" class="btn btn-primary" role="button">Ver livro</a>
                    </p>
                </div>
            </div>
        </div>    
    </c:forEach>
</div>
<script src="./dist/js/funcoesBusca.js"></script>