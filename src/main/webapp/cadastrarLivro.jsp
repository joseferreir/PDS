<%-- 
    Document   : editar_perfil
    Created on : 10/02/2016, 00:20:25
    Author     : Natarajan
--%>
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
<!DOCTYPE html>
<html>
    <head>
        <!--<meta charset="utf-8">-->
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
            if (usuario != null && usuario.getAdmin() == false) {
                response.sendRedirect("home");
            }
        %>
        <!--</header>-->
        <!-- Container da Edição de Perfil-->
        <div class="container" style="min-height: 650px; padding: 50px">

            <div class="row col-md-8 col-sm-10 col-sm-offset-1 col-md-offset-2" style='padding: 30px;'>

                <div class="panel panel-primary">
                    <div class="panel-heading text-center"><h3>Manutenção de Livros</h3></div>
                    <div class="panel-body">
                        <form action="/sislivros/logged/livro/cadastrar.do" id="formLivro" method="post" enctype="multipart/form-data">
                            <div class="form-group col-md-4 text-center">
                                <img id="blah" src='img/capa_default.png' style='width: 180px; height:220px; object-fit: cover' class='avatar img-thumbnail' alt="capa do livro">
                                <br><br>
                                <span class="btn btn-default btn-file"> Escolher capa <input type="file" id="imgInp" name="capa"/>
                                </span>
                            </div>

                            <div class="form-group col-md-8 has-feedback">
                                <label for="titulo" class="control-label">Titulo</label>
                                <input type="text" id="titulo" name="titulo" class="form-control" required>
                            </div>

                            <div class="form-group col-md-8 has-feedback">
                                <label for="isbn" class="control-label">ISBN</label>
                                <input type="text" id="isbn" name="isbn" class="form-control" pattern="[0-9]+([0-9-])+[0-9]+" required="">
                            </div>

                            <div class="form-group col-md-8 has-feedback">
                                <label for="anoPublicacao" class="control-label">Ano da publicação</label>
                                <div class="input-group col-md-5 col-sm-5">
                                    <input id="anoPublicacao" type="text" class="form-control" name="anoPublicacao" placeholder="AAAA" title="Informe somente o ano no formato AAAA" maxlength="4" pattern="[0-9]{4}" required>
                                </div>
                            </div>

                            <div class="form-group col-md-8 has-feedback">
                                <label for="editora" class="control-label">Editora</label>
                                <input type="text" id="editora" name="editora" class="form-control" required>
                            </div>

                            <div class="form-group col-md-8 col-md-push-4 has-feedback">
                                <label for="autores" class="control-label">Autores</label>
                                <input type="text" id="autores" name="autores" class="form-control" required>
                            </div>

                            <div class="form-group col-md-8 col-md-push-4 has-feedback">
                                <label for="areaTema" class="control-label">Tema/Área</label>
                                <input type="text" id="areaTema" name="areaTema" class="form-control" required>
                            </div>
                        </form>
                    </div>
                    <!--<input type="submit" form="formLivro" class="btn btn-primary" value="Salvar">-->
                    <!--<btn type="submit" form="formLivro" class="btn btn-primary">Salvar</btn>-->
                    
                    <div class="panel-footer clearfix">
                        
                        <div class="col-md-8 col-md-push-4">
                            
                            <input type="submit" form="formLivro" class="btn btn-primary" value="Salvar">
                            <a id="bntCancela" href="administrativo.jsp" class="btn btn-default ">Cancelar</a>
                        </div>
                        <!--<btn type="submit" form="formLivro" class="btn btn-primary">Salvar</btn>-->
                    </div>
                </div>

            </div>


        </div>
    </div>



    <!--FOOTER-->
    <%@ include file="footer.jsp"%>

    <!-- Latest compiled and minified JavaScript -->
    <script src="dist/js/jquery-2.1.4.min.js"></script>
    <script src="dist/js/bootstrap.js"></script>
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