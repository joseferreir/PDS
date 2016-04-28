<%-- 
    Document   : barraHome
    Created on : 10/02/2016, 18:45:46
    Author     : Natarajan 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.edu.ifpb.sislivros.model.SolicitacoesAmizadeBo"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Amizade"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = usuario = (Usuario) session.getAttribute("usuario");;
    List<Amizade> solicitacoes = new SolicitacoesAmizadeBo().buscarSolicitacoes(usuario.getId());
    pageContext.setAttribute("solicitacoes", solicitacoes);  
%>
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header my-navbar">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="${pageContext.request.contextPath}/home" class="navbar-brand">SisLivros</a>
        </div>
        <div class="navbar-collapse collapse" id="example">

            <form action="" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Busque livros aqui">
                </div>
                <button type="submit" class="btn btn-primary">Buscar</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><img style="min-height: 25px"></img>
                        <%                            String numSolic = null;
                            if (solicitacoes != null && !solicitacoes.isEmpty()) {
                                numSolic = Integer.toString(solicitacoes.size());
                            } else {
                                numSolic = "";
                            }
                        %>
                        <i class="fa fa-user-plus"></i> <span class="label label-primary"><%= numSolic%></span>
                    </a>
                    
                    
                    <c:if test="${!empty solicitacoes}">
                    <ul class="dropdown-menu">
                        <c:forEach var="amizade" items="${solicitacoes}">
                            <li style="padding: 10px 10px; min-width: 250px">
                            <div class="media">
                                <div class="media-left media-middle">
                                    <img src="${pageContext.request.contextPath}/${amizade.solicitante.foto}" class="media-object" style="max-width: 30px; height:30px; object-fit: cover">
                                </div>
                                <div class="media-body">
                                    <a href="verPerfil?id=${amizade.solicitante.id}"><h5 class="media-heading"><strong>${amizade.solicitante.nome}</strong>
                                    </h5></a>
                                    <a href="/sislivros/logged/amizade/processarsolicitacao.do?idSolicitante=${amizade.solicitante.id}&aceitar=true" class="btn btn-primary btn-sm"> Aceitar</a>
                                    <a href="/sislivros/logged/amizade/processarsolicitacao.do?idSolicitante=${amizade.solicitante.id}&aceitar=false" class="btn btn-default btn-sm"> Rejeitar</a>
                                </div>
                            </div>
                            </li>
                        </c:forEach>
                    </ul>    
                    </c:if>
                    
                    
                </li>



                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/${usuario.foto}" class="profile-image img-circle"style="width: 25px; height:25px; object-fit: cover">  ${usuario.apelido}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="editarPerfil"><i class="fa fa-user fa-fw"></i> Editar Perfil</a></li>
                        <c:if test="${usuario.admin}">
                            <li><a href="administrativo.jsp"><i class="fa fa-cog fa-fw"></i> Painel Administrativo</a></li>
                        </c:if>
                        <li class="divider"></li>
                        <li><a href="/sislivros/logged/user/logout.do"><i class="fa fa-sign-out fa-fw"></i> Sair</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>