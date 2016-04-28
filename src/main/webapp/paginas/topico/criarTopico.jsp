<%-- 
    Document   : criarTopico
    Created on : 27/03/2016, 22:09:20
    Author     : Natarajan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


    
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
    tstest
