<%-- 
    Document   : EditarLivro
    Created on : 01/03/2016, 08:39:10
    Author     : José
--%>

<%@page import="br.edu.ifpb.sislivros.model.ControleLivro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Livro"%>>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>SisLivros</title>
        <meta name="description" content="Hello World">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="dist/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="dist/css/another.css">
        <!-- Datepicker -->
        <link rel="stylesheet" href="dist/css/datepicker/bootstrap-datepicker.min.css">
        <!-- Uploader-->
        <link href="dist/css/uploader/fileinput.css" media="all" rel="stylesheet" type="text/css" >
        <!--Font Awsome-->
        <link rel="stylesheet" href="./dist/css/font-awesome.min.css">
    </head>

    <body>
        <!--<header>-->
        <%@ include file="barraHome.jsp"%>
        <%    
            String isbn = request.getParameter("isbn");
            if (isbn == null) {
                request.getRequestDispatcher("home").forward(request, response);
            }

            Livro livro = new ControleLivro().buscarPorIsbn(isbn);
            session.setAttribute("livro", livro);
            
            if (usuario.getAdmin() != true) {
                response.sendRedirect("home");
            }
        
        %>
        <!--</header>-->
        <!-- Container da Edição de Perfil-->
        <div class="container" style="min-height: 650px; padding: 30px">

            <div class="row col-md-8 col-sm-10 col-sm-offset-1 col-md-offset-2" style='padding: 30px;'>

                <div class="panel panel-primary">
                    <div class="panel-heading text-center"><h3>Atualizar Livro</h3></div>
                    <div class="panel-body">
                        <form id="formularioAlteraLivro" action="/sislivros/logged/livro/atualizar.do" method="post" enctype="multipart/form-data">

                            <div class="form-group col-md-4 text-center">
                                <img id="blah" src="${livro.capa}" style='width: 200px; height:290px; object-fit: cover' class='avatar img-thumbnail' alt="capa do livro">
                                <br><br>
                                <span class="btn btn-default btn-file"> Nova capa <input type="file" id="imgInp" name="capa"/>
                                </span>
                            </div>

                            <div class="col-md-8">
                                <div class="form-group has-feedback">
                                    <label for="titulo" class="control-label">Titulo</label>
                                    <input type="text" id="titulo" name="titulo" class="form-control" value="${livro.titulo}">
                                </div>

                                <div class="form-group has-feedback">
                                    <label for="isbn" class="control-label">ISBN</label>
                                    <input type="text" id="isbn" name="isbn" class="form-control" pattern="[0-9]+([0-9-])+[0-9]+" required value="${livro.isbn}">
                                </div>

                                <div class="form-group has-feedback">
                                    <label for="anoPublicacao" class="control-label">Ano da publicação</label>
                                    <div class="input-group col-md-5 col-sm-5">
                                        <input id="anoPublicacao" type="text" class="form-control" name="anoPublicacao" placeholder="AAAA" title="Informe somente o ano no formato AAAA" maxlength="4" pattern="[0-9]{4}" required value="${livro.anoPublicacao}">
                                    </div>
                                </div>

                                <div class="form-group  has-feedback">
                                    <label for="editora" class="control-label">Editora</label>
                                    <input type="text" id="editora" name="editora" class="form-control" required value="${livro.editora}">
                                </div>

                                <div class="form-group has-feedback">
                                    <label for="autores" class="control-label">Autores</label>
                                    <input type="text" id="autores" name="autores" class="form-control" required value="${livro.autores}">
                                </div>

                                <div class="form-group has-feedback">
                                    <label for="areaTema" class="control-label">Tema/Área</label>
                                    <input type="text" id="areaTema" name="areaTema" class="form-control" required value="${livro.areaTema}">
                                </div>
                            </div>

                        </form>
                    </div>

                    <div class="panel-footer clearfix">
                        <div class="col-md-8 col-md-push-4">
                            <input type="submit" form="formularioAlteraLivro" id="btnSalvar" class="btn btn-primary" value="Salvar">
                            <a id="bntCancela" href="administrativo.jsp" class="btn btn-default ">Cancelar</a>
                        </div>
                    </div>
                </div>

            </div>


        </div>
    </div>



    <!--FOOTER-->
    <%@ include file="footer.jsp"%>

    <!-- Latest compiled and minified JavaScript -->
    <script src="dist/js/jquery-2.1.4.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="dist/js/moment.js"></script>
    <script type="text/javascript" src="dist/js/datepicker/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="dist/js/datepicker/bootstrap-datepicker.pt-BR.min.js"></script>
    <script type="text/javascript" src="dist/js/uploader/fileinput.min.js"></script>
    <script type="text/javascript" src="dist/js/uploader/fileinput_locale_pt-BR.js"></script>
    <script>

        function readURL(input) {

            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#blah').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imgInp").change(function () {
            readURL(this);
        });

    </script>
</body>
</html>
