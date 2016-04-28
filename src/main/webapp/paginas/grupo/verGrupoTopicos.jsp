<%-- 
    Document   : criarGrupo
    Created on : 25/03/2016, 23:47:40
    Author     : Natarajan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisLivros - Criar Grupo</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="dist/css/another.css">
        <!--Font Awsome-->
        <link rel="stylesheet" href="dist/css/font-awesome.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="dist/css/bootstrap-theme.min.css">
    </head>
    <body>
        <header style="padding-bottom: 70px">
            <%@ include file="/barraHome.jsp"%>
        </header>

        <div class="container" style="min-height: 550px">
            <div class="row col-md-8 col-sm-10 col-sm-offset-1 col-md-offset-2" style='padding-top: 30px;'>
                <h3 class="text-center">Criar Grupo</h3>

                <form action="/sislivros/logged/grupo/criar.do" id="criarGrupo" method="post">
                    <div class="form-group">
                        <label for="nomeGrupo" class="control-label">Nome do Grupo</label>
                        <input type="text" id="nomeGrupo" class="form-control" name="nomeGrupo" maxlength="200" placeholder="Digite aqui o nome do Grupo" title="Insira um nome que você quer dar ao grupo." required>
                    </div>
                    <div class="form-group">
                        <label for="amigo" class="control-label">Escolha um livro</label>
                        <!--<input type="text" id="tituloBuscar" class="form-control" name="tituloBuscar">-->
                        <select class="form-control" id="livro" name="livro"> 
                            <c:forEach  var="livro" items="${livros}">
                                <option value="${livro.id}">
                                    ${livro.titulo}
                                </option> 
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="descricao" class="control-label">Descrição</label>
                        <textarea id="descricao" name="descricao" class="form-control" rows="5" maxlength="1000" required></textarea>
                    </div>
                </form>   
                <div>
                    <input id="btnCriar" type="submit" form="criarGrupo" class="btn btn-primary" value="Criar">
                </div>

            </div>



        </div>

        <!--FOOTER-->
        <footer>
            <%@ include file="/footer.jsp"%>
        </footer>
        <!-- Latest compiled and minified JavaScript -->
        <script src="dist/js/jquery-2.1.4.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>

    </body>
</html>
