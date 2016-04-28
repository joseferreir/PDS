<%-- 
    Document   : barraHomeNullUser
    Created on : 23/03/2016, 01:43:52
    Author     : Natarajan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="dist/css/bootstrap.min.css">
<link rel="stylesheet" href="dist/css/another.css">

<!-- Optional theme -->
<link rel="stylesheet" href="dist/css/bootstrap-theme.min.css">
<!DOCTYPE html>
<div class="modal fade" id="modal-login">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title modal-title-center form-singin-heading">Login</h3>
            </div>
            <div class="modal-body" id="corpoModalLogin">
                <form id="formulario" class="form-signin" method="post">
                    <div class="form-group">
                        <label for="email" class="control-label">Email</label>
                        <input type="text" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Senha</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                    <div id="alertaErroLogin" class="alert alert-danger alert-dismissible" role="alert" hidden>
                        <strong>Falha no login!</strong> Informações de email e senha incorretas.
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

<header>
    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="index" class="navbar-brand">SisLivros</a>
            </div>
            <div class="collpase navbar-collapse" id="example">
                <form action="" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Busque livros aqui">
                    </div>
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </form>

                <button type="button" class="btn btn-primary navbar-btn navbar-right" data-toggle="modal" data-target="#modal-login">Login</button>


            </div>

        </div>
    </nav>    
</header>
<script src="dist/js/jquery-2.1.4.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $("#modal-login").on("shown.bs.modal", function () {
            $("#email").focus();
        });

        $("#modal-login").on("hidden.bs.modal", function () {
            $('#alertaErroLogin').hide();
        });

//                $('#corpoModalLogin').click(function(){
//                    if $('#alertaErroLogin').is(":visible"){
//                        $('#alertaErroLogin').hide(250);
//                    }
//                });

        //funções para quando o botão entrar é clicado 
        $('#btnEntrar').click(function ()
        {

            var email = $('#email').val();
            var pwd = $('#password').val();
//            var actionName = "LoginUser";

            $.ajax({
                type: "POST",
                url: "/sislivros/login.do",
//                        dataType: "json",
//                        contentType: 'application/json',
//                        data: {email: email, password: pwd, action: actionName},

//                data: {"email": email, "password": pwd, "action": actionName},
                data: {"email": email, "password": pwd},
                success: function (response) {
                    if (response === "TRUE") {
                        $(location).attr('href', 'home');
                    } else {
                        $('#alertaErroLogin').show(250);
                    }
                }
            });
        });


        //função para quando pressiona ENTER dentro do input da senha ou login 
        $('#email').keypress(function (e) {
            if (e.which === 13) { // se digitar um enter nesse componente (password)
                $('#btnEntrar').click();
            }
        });

        $('#password').keypress(function (e) {
            if (e.which === 13) { // se digitar um enter nesse componente (password)
                $('#btnEntrar').click();
            }
        });

    });

</script>