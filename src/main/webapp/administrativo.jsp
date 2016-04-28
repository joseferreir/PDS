<%-- 
    Document   : index
    Created on : 08/02/2016, 01:05:52
    Author     : Natarajan
--%>

<%@page import="br.edu.ifpb.sislivros.entidades.Livro"%>
<%@page import="br.edu.ifpb.sislivros.model.ControleLivro"%>
<%@page import="br.edu.ifpb.sislivros.model.BuscaUsuarioBo"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="br.edu.ifpb.sislivros.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt_BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisLivros</title>
        <meta name="description" content="Hello World">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">

        <!--Font Awsome-->
        <link rel="stylesheet" href="./dist/css/font-awesome.min.css">
        <link rel="stylesheet" href="dist/css/another.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="dist/css/bootstrap-theme.min.css">
        


    </head>

    <body>

        <header>
            <%@ include file="barraHome.jsp"%>
        </header>

        <%            
            if (usuario != null && usuario.getAdmin() == false) {
                response.sendRedirect("home");
            }
        %> 
        <div class="container" style="margin-top: 100px">

            <!--Usuarios-->
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Administração de <strong>Usuários</strong></h3></div>
                <div class="panel-body">
                    <form class="form-inline float-right col-xs-8 no-margin-right">
                        <div class="form-group col-xs-12 no-margin-right">
                            <div class="input-group col-xs-12 float-right">
                                <input type="text" id="filter" class="form-control" placeholder="Nome ou Email">
                                <div class="btn input-group-addon glyphicon glyphicon-search"></div>
                            </div>
                        </div>
                    </form>

                </div>
                <!-- Table Usuários-->
                <table class="table table-bordered table-hover table-selectable">
                    <thead>
                        <tr class="alert-info text-center">
                            <th id="tableHeadId" class="text-center">Id</th>
                            <th id="tableHeadNome"class="text-center">Nome</th>
                            <th id="tableHeadEmail" class="text-center">Email</th>
                            <th class="text-center">Status</th>
                            <!--<th class="text-center">Funções</th>-->
                        </tr>
                    </thead>
                    <tbody id="usersTable" class="searchable">
                        <%                            BuscaUsuarioBo busca = new BuscaUsuarioBo();
                            List<Usuario> allUser = busca.buscarTodos();
                            Collections.sort(allUser, Usuario.Comparators.ID);
                            for (Usuario u : allUser) {
                                out.print("<tr>");
                                out.print("<th class=\"text-center\" \"id\">" + u.getId() + "</th>");
                                out.print("<th>" + u.getNome() + "</th>");
                                out.print("<th class=\"text-center\">" + u.getEmail() + "</th>");
                                if (u.getAdmin() == true) {
                                    out.print("<th class=\"text-center\">" + "ADMIN" + "</th>");
                                } else {
                                    out.print("<th>" + "</th>");
                                }
                                out.print("</tr>");
                            }

                        %>
                    </tbody>
                </table>
                <div id="botoesAcoesUsarios" class="container-fluid panel-footer">

                    <!--<button id="btnTornarAdmin" class="btn btn-warning glyphicon glyphicon-pencil" disabled></button>-->
                    <button id="btnTornarAdmin" type="button" class="btn btn-success" disabled><span class="glyphicon glyphicon-ok"></span> Habilitar</button>
                    <button id="btnDesabilitarAdmin" type="button" class="btn btn-danger" disabled><span class="glyphicon glyphicon-remove"></span> Desabilitar</button>


                    <!--antigos e pequenos -->
                    <!--<button class="btn btn-info glyphicon glyphicon-plus"></button>-->
                    <!--<button id="btn-deletar" class="btn btn-danger glyphicon glyphicon-trash" disabled></button>-->
                </div>
            </div> <!-- fim painel -->

            <hr>

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Administração de <strong>Livros</strong></h3></div>
                <div class="panel-body">
                    <form class="form-inline float-right col-xs-8 no-margin-right">
                        <div class="form-group col-xs-12 no-margin-right">
                            <div class="input-group col-xs-12 float-right">
                                <input type="text" id="filter" class="form-control" placeholder="Informações sobre o livro">
                                <div class="btn input-group-addon glyphicon glyphicon-search"></div>
                            </div>
                        </div>
                    </form>

                </div>
                <!-- Table Livros-->
                <table class="table table-bordered table-hover table-selectable">
                    <thead>
                        <tr class="alert-info text-center">
                            <th id="tableHeadIdLivro" class="text-center">Id</th>
                            <th id="tableHeadTituloLivro"class="text-center">Título</th>
                            <th id="tableHeadISBN" class="text-center">ISBN</th>
                            <th id="tableHeadAutor" class="text-center">Autor(es)</th>
                            <th id="tableHeadEditar" class="text-center">Operações</th>
                            <!--<th class="text-center">Funções</th>-->
                        </tr>
                        
                        
                        
                        
                    </thead>
                    
                    
                    <tbody id="livrosTable" class="searchable">
                        <%                            ControleLivro clivros = new ControleLivro();

                            List<Livro> todosLivros = clivros.retornarTodos();
//                            Collections.sort(allUser, Usuario.Comparators.ID);
                            for (Livro l : todosLivros) {
                                out.print("<tr>");
                                out.print("<td class=\"text-center\" \"id\">" + l.getId() + "</td>");
                                out.print("<td>" + l.getTitulo() + "</th>");
                                out.print("<td class=\"isbn text-center\">" + l.getIsbn() + "</td>");
                                out.print("<td class=\"text-center\">" + l.getAutores() + "</td>");
                                out.print("<td class=\"text-center\"> <a title=\"Editar este livro\" href=\"editarlivro?isbn=" + l.getIsbn() + " \" class=\"btn btn-xs \" ><span class=\"glyphicon glyphicon-edit\"></span></a> </td>");
                                out.print("</tr>");
                            }

                        %>
                    </tbody>
                </table>

                <div class="container-fluid panel-footer">

                    <!--<button id="btnTornarAdmin" class="btn btn-warning glyphicon glyphicon-pencil" disabled></button>-->
                    <a href="admLivro"><button id="btnAddLivro" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar</button></a>
                    <!--<button id="btnDesabilitarAdmin" type="button" class="btn btn-danger" disabled><span class="glyphicon glyphicon-trash"></span> Desabilitar</button>-->

                    <!--antigos e pequenos -->
                    <!--<button class="btn btn-info glyphicon glyphicon-plus"></button>-->
                    <!--<button id="btn-deletar" class="btn btn-danger glyphicon glyphicon-trash" disabled></button>-->
                </div>


            </div> <!-- fim painel -->


        </div>


        <!--FOOTER-->
        <%@ include file="footer.jsp"%>

        <!-- Latest compiled and minified JavaScript -->
        <script src="dist/js/jquery-2.1.4.min.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>
        
        <script type="text/javascript">

            //FUNÇÃO PARA HABILITAR OS BOTÕES
            $('.table-selectable > #usersTable > tr').click(function (e) {
                e.preventDefault();
                if ($(this).hasClass('active')) {
                    $(this).removeClass('active');
                } else {
                    $(".table-selectable > #usersTable > tr.active").removeClass('active');
                    $(this).addClass('active');
                }

                //HABILITANDO BOTÕES
                if (($(".table-selectable > #usersTable > tr.active").length)) {

                    //pega a célula da tabela que diz se o usuário selecionado é ADMIN
                    var isAdmin = $('.table-selectable > #usersTable > tr.active').find('th:last-child').text();

                    //habilita os botões no caso de ser ou não admin
                    if (isAdmin === "ADMIN") {
                        $("#btnTornarAdmin").prop("disabled", true);
                        $("#btnDesabilitarAdmin").prop("disabled", false);

                    } else {
                        $("#btnTornarAdmin").prop("disabled", false);
                        $("#btnDesabilitarAdmin").prop("disabled", true);
                    }


                } else { //se nenhuma linha de usuário está selecionada, desabilita os botões
                    $("#btnTornarAdmin").prop("disabled", true);
                    $("#btnDesabilitarAdmin").prop("disabled", true);
                }

            });

            //função que faz o filtro digitando no formulário de busca dos usuários
            $(document).ready(function () {
                (function ($) {
                    $('#filter').keyup(function () {
                        var rex = new RegExp($(this).val(), 'i');
                        $('.searchable tr').hide();
                        $('.searchable tr').filter(function () {
                            return rex.test($(this).text());
                        }).show();
                    });
                }(jQuery));
            });

            //ações nos cliques dos botões de habilitar e desabilitar
            $("#btnTornarAdmin").click(function () {
                var idUsuario = $('.table-selectable > #usersTable > tr.active').find('th:first-child').text();
                changeToAdmin(idUsuario, "true");
            });

            $("#btnDesabilitarAdmin").click(function () {
                var idUsuario = $('.table-selectable > #usersTable > tr.active').find('th:first-child').text();
                changeToAdmin(idUsuario, "false");
            });
            //chama servlet que muda Status de Admin
            function changeToAdmin(id, habilitar) {
                $.ajax({
                    url: "/sislivros/logged/user/addadmin.do",
                    type: "POST",
                    data: {id: id, habilitar: habilitar},
                    success: function (data) {
                        location.reload();
                    }
                });
            }

            //ACOES LIVROS
            $(".table-selectable > #livrosTable > tr").dblclick(function () {
                var isbn = $(this).closest('tr').children('td.isbn').text();
//                alert(isbn);
//                location.replace("verLivro.jsp?isbn="+isbn);
                location.href = "/sislivros/logged/livro/pre/ver.do?isbn=" + isbn;
            });



            //SORTS
            $("#tableHeadId").click(function (e) {
                e.preventDefault();
            <%                StringBuilder sb = new StringBuilder();
                Collections.sort(allUser, Usuario.Comparators.ID);
                for (Usuario u : allUser) {
                    sb.append("<tr>");
                    sb.append("<th class=\"text-center\">" + u.getId() + "</th>");
                    sb.append("<th>" + u.getNome() + "</th>");
                    sb.append("<th class=\"text-center\">" + u.getEmail() + "</th>");
                    if (u.getAdmin() == true) {
                        sb.append("<th class=\"text-center\">" + "ADMIN" + "</th>");
                    } else {
                        sb.append("<th>" + "</th>");
                    }
                    sb.append("</tr>");
                }

                String resultado = sb.toString();

            %>
                var conteudo = '<%=resultado%>';
                $("#usersTable").html(conteudo);

            });

            $("#tableHeadNome").click(function (e) {

            <%

                StringBuilder sb2 = new StringBuilder();
                Collections.sort(allUser, Usuario.Comparators.NAME);
                for (Usuario u : allUser) {
                    sb2.append("<tr>");
                    sb2.append("<th class=\"text-center\">" + u.getId() + "</th>");
                    sb2.append("<th>" + u.getNome() + "</th>");
                    sb2.append("<th class=\"text-center\">" + u.getEmail() + "</th>");
                    if (u.getAdmin() == true) {
                        sb2.append("<th class=\"text-center\">" + "ADMIN" + "</th>");
                    } else {
                        sb2.append("<th>" + "</th>");
                    }
                    sb2.append("</tr>");
                }

                String resultado2 = sb2.toString();
            %>
                e.preventDefault();
                $("#usersTable").html('<%=resultado2%>');
//                location.replace();
                //$('.table-selectable > tbody > tr').first().addClass('active');
            });


        </script>
    </body> 
</html>

