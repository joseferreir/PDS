<%-- 
    Document   : pessoas
    Created on : 01/03/2016, 02:23:29
    Author     : Natarajan 
--%>
<%@page import="br.edu.ifpb.sislivros.model.AmizadeBo"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@page import="br.edu.ifpb.sislivros.model.BuscaUsuarioBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid" style="padding: 30px">

    <div class="well clearfix">

        <form action='' method='post' id="formBuscaPessoas" class="container-fluid row">
            <div class="form-group">
                <div class="col-sm-4">
                    <input type="text" class="form-control" placeholder="Digite aqui um nome a ser buscado" name="nomeBuscado" id="nomePessoaBuscada">
                </div>
                <!--                <div class="col-sm-2">
                                    <select class="form-control">
                                        <option>Todos os campos</option>
                                        <option>Nome</option>
                                        <option>Email</option>
                                    </select>
                                </div>	-->

            </div>
            <!--<button type="button" class="btn btn-primary" id="buscaPessoas">Buscar</button>-->			
        </form>


    </div>

    <div id="pessoas" class="container-fluid">
        <%
            List<Usuario> todosUsuarios = new BuscaUsuarioBo().buscarTodos();
            pageContext.setAttribute("usuarios", todosUsuarios);
        %>

        <div id="pessoasContainer" class="col-md-3">
            <%
                Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");
                pageContext.setAttribute("usuarioSessao", session.getAttribute("usuario"));
                AmizadeBo amizadeBo = new AmizadeBo();
                pageContext.setAttribute("amizadesBo", amizadeBo);
            %>
            <c:forEach var="user" items="${usuarios}">
                <c:if test="${usuario.id ne user.id}">
                    <div class="media">
                        <div class="media-left media-middle">        
                            <img src="${user.foto}" class="media-object" style="width: 30px; height:30px; object-fit: cover">
                        </div>
                        <div class="media-body">
                            <a href="verPerfil?id=${user.id}"><h5 class="media-heading"><strong>${user.nome}</strong></h5></a>
                                        <c:choose>
                                            <c:when test="${amizadesBo.saoAmigos(user.getId(), usuarioSessao.getId()) eq true}">
                                    <btn class="btn btn-success btn-sm"> Meu amigo</btn>
                                    </c:when>
                                    <c:when test="${amizadesBo.ehSolicitanteAmizade(user.getId(), usuarioSessao.getId()) eq true}">
                                        <!--<button id="aceitar" class="btn btn-primary btn-sm" onclick="aceitarAmizade(${user.id})"> Aceitar</button>--> 
                                    <a href="/sislivros/logged/amizade/processarsolicitacao.do?idSolicitante=${user.id}&aceitar=true" class="btn btn-primary btn-sm"> Aceitar</a>
                                    <a href="/sislivros/logged/amizade/processarsolicitacao.do?idSolicitante=${user.id}&aceitar=false" class="btn btn-default btn-sm"> Rejeitar</a>
                                </c:when>
                                <c:when test="${amizadesBo.ehSolicitanteAmizade(usuarioSessao.getId(), user.getId()) eq true}">
                                    <btn class="btn btn-primary btn-sm"> Solicitacao Enviada</btn>
                                    </c:when>
                                    <c:otherwise>
                                    <a href="/sislivros/logged/amizade/solicitar.do?idSolicitado=${user.id}" class="btn btn-primary btn-sm"> Solicitar</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <hr>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>