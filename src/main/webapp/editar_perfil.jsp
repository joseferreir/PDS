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

        <!--Modal de upload de foto-->
        <div class="modal fade" id="modal-upload">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title">Upload de foto</h3>
                    </div>
                    <div class="modal-body">
                        <form enctype="multipart/form-data" method="post" action="/sislivros/uploadfoto.do">
                            <input name="imagem" id="imagem" class="file" type="file" multiple data-min-file-count="1">
                            <br>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!--Modal Exclusão de Perfil-->
        <div class="modal fade" id="modal-exclusao">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title">Exclusão de Perfil</h3>
                    </div>
                    <div class="modal-body">
                        <p><strong>Tem certeza que deseja excluir o seu perfil?</strong></p>
                        <p>A operação de exclusão é irreversível.</p>
                    </div>
                    <div class="modal-footer">
                        <a href="" class="btn btn-default" data-dismiss="modal">Cancelar</a>
                        <!--<input form="formulario" type="submit" class="btn btn-danger" value="Excluir">-->
                        <a href="/sislivros/logged/user/excluir.do" class="btn btn-danger"><i class="fa fa-trash-o fa-lg fa-fx"></i> Excluir</a>
                        <!--<button id="btnConfirmaExclusao" class="btn btn-danger">Excluir</button>-->
                    </div>
                </div>
            </div>
        </div>

        <!--<header>-->
        <%@ include file="barraHome.jsp"%>
        
        <!--</header>-->
        <!-- Container da Edição de Perfil-->
        <div class="container" style="padding: 30px">

            <div class="row col-md-8 col-sm-10 col-sm-offset-1 col-md-offset-2">
                <div class="page-header">
                    <h2 class="text-center">Editar Perfil</h2>
                </div>

                <div class="col-md-4">
                    <div class="text-center">
                        <img src="${usuario.foto}" style="width:150px; height:150px; object-fit:cover" class="avatar img-circle img-thumbnail" alt="avatar">
                             <div>
                            <br><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-upload">Alterar Foto</button>
                        </div>
                    </div>

                </div><br>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><h4>Informações Básicas</h4></div>
                        <div class="panel-body">
                            <form id="formulario_alteracao" action="/sislivros/logged/user/atualizar.do" method="post">
                                <fieldset id="campos" disabled>
                                    <div class="form-group has-feedback">
                                        <label for="element-1" class="control-label">Nome Completo</label>
                                        <input type="text" id="nome" class="form-control" name="nome" placeholder="Digite seu nome" pattern="[A-Za-z0-9 ]+" title="Nome não pode conter caracteres especiais (% - $ _ # @, por exemplo)." required value="${usuario.nome}">
                                        <p class="help-block hidden"></p>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label for="element-2" class="control-label">Apelido</label>
                                        <input type="text" id="apelido" class="form-control" name="apelido" placeholder="Escolha um apelido" required value="${usuario.apelido}">
                                        <p class="help-block hidden"></p>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label for="element-3" class="control-label">Data de Nascimento</label>
                                        <%                                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                            String dataNascimento = null;
                                            if (usuario != null) {
                                                dataNascimento = usuario.getDataNascimento().format(dtf);
                                            }
                                        %>

                                        <div class="input-group date col-md-5 col-sm-5" id="datepicker1" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                            <input id="dataNascimento" type="text" class="form-control" name="dataNascimento" placeholder="dd/mm/aaaa" pattern="\d{2}/\d{2}/\d{4}" title="Data deve estar no formato dd/mm/aaaa" required value="<%=dataNascimento%>">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        </div>
                                        <p class="help-block hidden"></p>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label for="element-4" class="control-label">Cidade</label>
                                        <input type="text" id="cidade" class="form-control" name="cidade" placeholder="Informe sua cidade" pattern="[A-Za-z0-9 ]+" title="Cidade não deve ter caracteres especiais" required value="${usuario.cidade}">
                                        <p class="help-block hidden"></p>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label for="estado" class="control-label">Estado</label>
                                        <select class="form-control" id="estado" name="estado"> 
                                            <%
                                                Map<String, String> estados = new TreeMap<>();
                                                estados.put("ac", "Acre");
                                                estados.put("al", "Alagoas");
                                                estados.put("am", "Amazonas");
                                                estados.put("ap", "Amapá");
                                                estados.put("ba", "Bahia");
                                                estados.put("ce", "Ceará");
                                                estados.put("df", "Distrito Federal");
                                                estados.put("es", "Espírito Santo");
                                                estados.put("go", "Goiás");
                                                estados.put("ma", "Maranhão");
                                                estados.put("mt", "Mato Grosso");
                                                estados.put("ms", "Mato Grosso do Sul");
                                                estados.put("mg", "Minas Gerais");
                                                estados.put("pa", "Pará");
                                                estados.put("pb", "Paraíba");
                                                estados.put("pr", "Paraná");
                                                estados.put("pe", "Pernambuco");
                                                estados.put("pi", "Piauí");
                                                estados.put("rj", "Rio de Janeiro");
                                                estados.put("rn", "Rio Grande do Norte");
                                                estados.put("ro", "Rondônia");
                                                estados.put("rs", "Rio Grande do Sul");
                                                estados.put("rr", "Roraima");
                                                estados.put("sc", "Santa Catarina");
                                                estados.put("se", "Sergipe");
                                                estados.put("sp", "São Paulo");
                                                estados.put("to", "Tocantins");
                                                String estadoUsuario = null;
                                                if (usuario != null) {
                                                    estadoUsuario = usuario.getEstado();
                                                }

                                                String estadoSelecionado = "";

                                                try {

                                                    for (String sigla : estados.keySet()) {
                                                        out.print("<option value=\"" + sigla + "\"");
                                                        if (sigla.equals(estadoUsuario)) {
                                                            out.print("selected");
                                                        }
                                                        out.print(">" + estados.get(sigla) + "</option>\n");
                                                    }
                                                } catch (Exception e) {
                                                }


                                            %>
                                        </select>

                                    </div>

                                    <div class="container row" style="width: 80%">
                                        <div class="form-group has-feedback">
                                            <label for="element-7" class="control-label">Email</label>
                                            <input type="text" id="email" class="form-control" name="email" 
                                                   placeholder="Informe seu email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Digite um email válido" required value=${usuario.email}>
                                            <p class="help-block hidden"></p>
                                        </div>

                                    </div>

                                    <div id="alertaErroLogin" class="alert alert-danger alert-dismissible" role="alert" hidden>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                        <div class="panel-footer">
                            <!--<button id="btnSalvar" type="submit" class="btn btn-default" form="formulario_cadastro">Salvar</button>-->
                            <!--<button id="btnSalvar" type="submit" form="formulario_cadastro" class="btn btn-default">Editar</button>-->
                            <!--<a id="btnSalvar" type="submit" form="formulario_cadastro" class="btn btn-default">Editar</a>-->
                            <a id="btnEditar" class="btn btn-primary">Editar</a>
                            <a id="btnSalvar" form="formulario_alteracao" type="submit" class="btn btn-default disabled">Salvar</a>

                        </div>
                    </div>

                    <!-- Painel Alteração de Senha -->
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Alteração de Senha</h4></div>
                        <div class="panel-body">
                            <p>Para alterar sua senha de acesso ao SisLivros, entre com a senha atual e informe uma nova senha.</p>

                            <form class="form-horizontal" id="form_altera_senha" action="/sislivros/logged/user/alterarsenha.do" method="post">
                                <div class="form-group">
                                    <label for="elementoSenhaAntiga" class="control-label col-sm-3">Senha Atual</label>
                                    <div class="col-sm-8">
                                        <input type="password" id="elementoSenhaAntiga" class="form-control" name="senhaAntiga" placeholder="Informe sua senha atual" required >
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label for="elementoSenhaAntiga" class="control-label col-sm-3">Nova Senha</label>
                                    <div class="col-sm-8">
                                        <input type="password" id="elementoSenhaNova" class="form-control" name="novaSenha" placeholder="Informe sua nova senha" title="Faltou informar a sua nova senha" required>

                                    </div>
                                </div>

                                <div id="alertaErroSenha" class="alert alert-danger alert-dismissible" role="alert" hidden></div>
                                <div id="alertaSucessoSenha" class="alert alert-info alert-dismissible" role="alert" hidden></div>
                            </form>
                        </div>
                        <div class="panel-footer">
                            <!--<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-alterar-senha" id="btn-excluir">Alterar Senha</button>-->
                            <!--<button form="form_altera_senha" id="btnAlterarSenha" type="submit" class="btn btn-default">Alterar Senha</button>-->
                            <!--<button id="btnAlterarSenha" class="btn btn-default">Alterar Senha</button>-->
                            <a id="btnAlterarSenha" form="form_altera_senha" type="submit" class="btn btn-default">Alterar Senha</a>
                        </div>
                    </div>

                    <!-- Painel Exclusão de Perfil-->
                    <div class="panel panel-danger">
                        <div class="panel-heading"><h4>Exclusão de Perfil</h4></div>
                        <div class="panel-body">
                            Essa operação é irreversível. Caso confirme a exclusão, todos os seus dados dentro do SisLivros serão perdidos. </div>
                        <div class="panel-footer">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-exclusao" id="btn-excluir">Excluir Perfil</button>
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
            $(document).ready(function () {
                $('#datepicker1').datepicker({
                    pickTime: false,
                    format: 'dd/mm/yyyy',
                    language: "pt-BR"
                });


            });


            //PROCESSAMENTO DO PAINEL DE ALTERAÇÃO DE SENHA
            $("#btnAlterarSenha").click(function () {
                event.preventDefault();

                $('#alertaErroSenha').hide();

//                var dados = $("#form_altera_senha").serialize();
                var senhaAntiga = $('#elementoSenhaAntiga').val();
                var novaSenha = $('#elementoSenhaNova').val();
//                var actionName = "AlterarSenha";
                
//                var data = {"senhaAntiga": senhaAntiga, "novaSenha": novaSenha, "action": actionName};
                var data = {"senhaAntiga": senhaAntiga, "novaSenha": novaSenha};


                if ($('#elementoSenhaAntiga').val() === "" || $('#elementoSenhaNova').val() === "" || $('#elementoSenhaAntiga') === null || $('#elementoSenhaNova') === null) {
                    $('#alertaErroSenha').html("Por favor insira a senha antiga e a nova senha .").show(250);
                } else {
                    $.post("/sislivros/logged/user/alterarsenha.do", data, function (response) {
//                        $.post("Controller", data, function (response) {

                        if (response === "OK") {
                            $('#alertaSucessoSenha').html("Senha alterada com sucesso").show(250).delay(5000).hide(250);
                        } else {
                            $('#alertaErroSenha').html("A senha atual informada não confere.").show(250);
                        }
                    });
                }


            });


            //PROCESSAMENTO DO PAINEL DE EDIÇÃO DE DADOS DO PERFIL
            //ações quando aperta botão de Editar
            $('#btnEditar').click(function () {
                $('#campos').prop('disabled', false);
                $('#btnEditar').addClass("disabled");
                $('#btnSalvar').removeClass('disabled');
            });

            function processaRequest() {
                event.preventDefault();

                $('#alertaErroLogin').hide();

                var dados = $("#formulario_alteracao").serialize();
                
//                var data = dados+ "&action=AtualizarUsuario";

                $.post("/sislivros/logged/user/atualizar.do", dados, function (responseJson) {
//                    $.post("Controller", data, function (responseJson) {

                    var resultado = responseJson.passou;
                    $('p').addClass("hidden");
                    $("div").removeClass("has-error");

                    if (resultado === "true") {
//                        $(location).attr('href', 'editarPerfil');
                        location.reload();
                    } else {
                        $.each(responseJson, function (key, value) {

                            if (key === "dataNascimento") {
                                $("#" + key).parent("div").next("p").html(value).removeClass("hidden");
                            } else if (key === "emailJaExiste") {
                                $('#alertaErroLogin').show(250).text(value);
                            } else {
                                $("#" + key).next("p").html(value).removeClass("hidden");
                            }

                            $("#" + key).parent("div").addClass("has-error");

                        });
                    }
                });

            }
            $('#btnSalvar').click(processaRequest);

        </script>
    </body> 
</html>