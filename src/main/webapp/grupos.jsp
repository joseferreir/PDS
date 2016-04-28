<%-- 
    Document   : grupos
    Created on : 25/03/2016, 15:56:29
    Author     : Natarajan 
--%>
<%@page import="br.edu.ifpb.sislivros.model.ParticipanteGrupoBo"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Grupo"%>
<%@page import="br.edu.ifpb.sislivros.model.BuscarGrupoBo"%>
<%@page import="br.edu.ifpb.sislivros.model.AmizadeBo"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@page import="br.edu.ifpb.sislivros.model.BuscaUsuarioBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Modal Login-->
<div class="modal fade" id="modal-criar-grupo">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title modal-title-center form-singin-heading">Login</h3>
            </div>
            <div class="modal-body" id="corpoModalLogin">
                <form id="formulario" class="form-signin" method="post">
                    <div class="form-group">
                        <label for="nome" class="control-label">Nome</label>
                        <input type="text" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Senha</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                <!--<input form="formulario" type="submit" class="btn btn-primary" value="Entrar">-->
                <a id="btnEntrar" type="submit" class="btn btn-primary">Entrar</a>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid" style="padding: 30px">

    <div class="well" >

<!--        <form action='' method='post' class="">
            <div class="form-group">
                <div class="col-sm-4">
                    <input type="text" class="form-control" placeholder="Buscar Grupos">
                </div>
                <div class="col-sm-2">
                    <select class="form-control">
                        <option>Todos os campos</option>
                        <option>Nome do grupo</option>
                        <option>Título do livro</option>
                    </select>
                </div>	

            </div>

            <button type="button" class="btn btn-primary">Buscar</button>
            
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-criar-grupo">Criar Grupo</button>
        </form>-->
        
        <div class="container-fluid">
            <a href="paginas/grupo/criarGrupo.jsp" id="btnCriarGrupo" type="submit" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-plus-sign"></i> Criar Grupo</a>
        </div>
    </div>

    <div id="gruposArea" class="container-fluid">

        <%
            List<Grupo> grupos = new BuscarGrupoBo().buscarTodos();
            ParticipanteGrupoBo participanteBo = new ParticipanteGrupoBo();

            pageContext.setAttribute("grupos", grupos);
            pageContext.setAttribute("participanteBo", participanteBo);


        %>

        <c:forEach var="grupo" items="${grupos}">
            <div class="media">

                <div class="media-left media-middle">        
                    <!--<img src="
                    
                    " class="media-object" style="width:80px; height: 130px; object-fit: cover">-->
                </div>
                <div class="media-body media-middle">
                    <a href="/sislivros/logged/grupo/pre/ver.do?idGrupo=${grupo.id}" style="text-decoration: none"><h5 class="media-heading"><strong>${grupo.nome}</strong></h5></a>
                    <p>${grupo.descricao}</p>
                    <c:if test="${participanteBo.ehParticipante(usuario.getId(), grupo.getId()) eq false}">
                        <a href="/sislivros/logged/grupo/entrar.do?idGrupo=${grupo.id}" class="btn btn-primary btn-sm"> Participar do Grupo</a>
                    </c:if>
                </div>

            </div>


        </c:forEach>
    </div>
</div>