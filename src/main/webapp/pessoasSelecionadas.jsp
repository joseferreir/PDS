<%-- 
    Document   : livros
    Created on : 23/03/2016, 01:22:34
    Author     : Natarajan 
--%>

<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@page import="br.edu.ifpb.sislivros.model.AmizadeBo"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Livro"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.sislivros.model.LivroBuscarBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    AmizadeBo amizadeBo = new AmizadeBo();
    Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");

    pageContext.setAttribute("usuarioSessao", session.getAttribute("usuario"));
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

    </c:if>
</c:forEach>

<script src="./dist/js/funcoesBusca.js"></script>
<script src="./dist/js/fitNomeLivro.js"></script>