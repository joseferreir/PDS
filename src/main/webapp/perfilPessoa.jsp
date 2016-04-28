<%-- 
    Document   : editar_perfil
    Created on : 10/02/2016, 00:20:25
    Author     : Natarajan
--%>
<%@page import="br.edu.ifpb.sislivros.model.AmizadeBo"%>
<%@page import="br.edu.ifpb.sislivros.model.ControleUsuario"%>
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
        <%
            Usuario usuarioPerfil = null;

            int id = Integer.parseInt(request.getParameter("id"));
            usuarioPerfil = (Usuario) new ControleUsuario().buscarPorId(id);
            pageContext.setAttribute("usuarioPerfil", usuarioPerfil);

        %>
        <!--Modal de upload de foto-->
        <div class="modal fade" id="modal-upload">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title">Upload de foto</h3>
                    </div>
                    <div class="modal-body">
                        <form enctype="multipart/form-data" method="post" action="uploadImagem">
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
                        <a href="excluirUsuario" class="btn btn-danger"><i class="fa fa-trash-o fa-lg fa-fx"></i> Excluir</a>
                        <!--<button id="btnConfirmaExclusao" class="btn btn-danger">Excluir</button>-->
                    </div>
                </div>
            </div>
        </div>

        <!--<header>-->
        <%@ include file="barraHome.jsp"%>

        <!--</header>-->
        <!-- Container da Edição de Perfil-->
        <div class="container" style="padding: 50px">

            <div class="row col-md-8 col-sm-10 col-sm-offset-1 col-md-offset-2">
                <div class="page-header">
                    <h3 class="text-center">Perfil Pessoal</h3>
                </div>

                <div class="col-md-4">
                    <div class="text-center">
                        <img src="${usuarioPerfil.foto}" style="width: 150px; height:150px; object-fit: cover" class="avatar img-circle img-thumbnail" alt="avatar">
                        <div>
                            <br>

                            <%                                AmizadeBo amizadeBo = new AmizadeBo();
                                if (amizadeBo.saoAmigos(usuario.getId(), usuarioPerfil.getId()) &&usuario.getId()!=usuarioPerfil.getId()) {
                            %>
                            <btn class="btn btn-success btn-sm"> Meu amigo</btn>
                                <%
                                } else if (amizadeBo.ehSolicitanteAmizade(usuarioPerfil.getId(), usuario.getId()) &&usuario.getId()!=usuarioPerfil.getId()) {
                                %>
                                    <h5>Deseja aceitar a amizade?</h5>
                                    <button type="button" class="btn btn-success">Aceitar</button>
                                    <button type="button" class="btn">Descartar</button>
                            <%
                            } else if (amizadeBo.ehSolicitanteAmizade(usuario.getId(), usuarioPerfil.getId()) &&usuario.getId()!=usuarioPerfil.getId()) {
                            %>
                                <btn class="btn btn-primary btn-sm"> Solicitacao Enviada</btn>
                                <%
                                } else  if(usuario.getId()!=usuarioPerfil.getId()){
                                %>
                                    <a href="/sislivros/logged/amizade/solicitar.do?idSolicitado=<%=usuarioPerfil.getId()%>" class="btn btn-primary btn-sm"> Solicitar</a>
                            <%
                                }
                            %>
                            
                            
                            

                        </div>
                    </div>

                </div>
                <br>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><h4>Informações Básicas</h4></div>
                        <div class="panel-body">
                            <form id="formulario_alteracao" action="" method="post">
                                <fieldset id="campos" disabled>
                                    <div class="form-group has-feedback">
                                        <label for="element-1" class="control-label">Nome Completo</label>
                                        <input type="text" id="nome" class="form-control" name="nome" placeholder="Digite seu nome" pattern="[A-Za-z0-9 ]+" title="Nome não pode conter caracteres especiais (% - $ _ # @, por exemplo)." required value="${usuarioPerfil.nome}">
                                        <p class="help-block hidden"></p>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label for="element-2" class="control-label">Apelido</label>
                                        <input type="text" id="apelido" class="form-control" name="apelido" placeholder="Escolha um apelido" required value="${usuarioPerfil.apelido}">
                                        <p class="help-block hidden"></p>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label for="element-3" class="control-label">Data de Nascimento</label>
                                        <%
                                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                            String dataNascimento = null;
                                            if (usuarioPerfil != null) {
                                                dataNascimento = usuarioPerfil.getDataNascimento().format(dtf);
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
                                        <input type="text" id="cidade" class="form-control" name="cidade" placeholder="Informe sua cidade" pattern="[A-Za-z0-9 ]+" title="Cidade não deve ter caracteres especiais" required value="${usuarioPerfil.cidade}">
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
                                                if (usuarioPerfil != null) {
                                                    estadoUsuario = usuarioPerfil.getEstado();
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
                                                   placeholder="Informe seu email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Digite um email válido" required value=${usuarioPerfil.email}>
                                            <p class="help-block hidden"></p>
                                        </div>

                                        <!--                            <div class="form-group has-feedback">
                                                                        <label for="element-8" class="control-label">Senha</label>
                                                                        <input type="password" id="element-8" class="form-control" name="senha" placeholder="Escolha sua senha" required value="******">
                                                                    </div>-->
                                    </div>

                                    <div id="alertaErroLogin" class="alert alert-danger alert-dismissible" role="alert" hidden>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                        <!--<div class="panel-footer">-->
                        <!--<button id="btnSalvar" type="submit" class="btn btn-default" form="formulario_cadastro">Salvar</button>-->
                        <!--<button id="btnSalvar" type="submit" form="formulario_cadastro" class="btn btn-default">Editar</button>-->
                        <!--<a id="btnSalvar" type="submit" form="formulario_cadastro" class="btn btn-default">Editar</a>-->
                        <!--                            <a id="btnEditar" class="btn btn-primary">Editar</a>
                                                    <a id="btnSalvar" form="formulario_alteracao" type="submit" class="btn btn-default disabled">Salvar</a>-->

                        <!--</div>-->
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

        </script>
    </body> 
</html>