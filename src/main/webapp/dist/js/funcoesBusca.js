/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#buscaLivros").click(processaBuscaLivros);

$("#buscaPessoas").click(processaBuscaPessoas);

//
//$("#buscaLivros2").click(function (e) {
//    e.preventDefault();
//
//    var parametro = $('#parametro').val();
//    var campo = $('#campo').val();
//    var actionName = "LivroBuscar";
//
//    $.ajax({
//        type: "POST",
//        url: "Controller",
//        data: {"parametro": parametro, "campo": campo, "action": actionName},
//        success: function (response) {
//            $("#livros").load("livrosBuscados.jsp");
//
//        }
//    });
//});

function processaBuscaLivros() {
    event.preventDefault();

    var dados = $("#formBusca").serialize();

    var dados = dados;
//    var dados = dados + "&action=LivroBuscar";
    
    var actionName = "LivroBuscar";

    $.ajax({
        type: "POST",
//        url: "Controller",
        url: "/sislivros/buscarlivro.do",
        data: dados,
//        beforeSend: function (){
//            $("#livrosSelecionados").fadeOut("fast");
//            $("#snipper").removeClass("hidden").fadeIn("slow");
//            
//        },
//        complete: function (){
//            $("#snipper").fadeOut("fast");
//            $("#livrosSelecionados").fadeIn("slow");
//        },
        success: function (response) {
//            $("#livros").load("livrosBuscados.jsp");
            $("#livrosSelecionados").load("pressSelectedBooks.jsp");
        }
    });

}


function processaBuscaPessoas() {
    event.preventDefault();

    var dados = $("#formBuscaPessoas").serialize();

    var dados = dados + "&action=UsuarioBuscar";
    
    $.ajax({
        type: "POST",
        url: "Controller",
        data: dados,

        success: function (response) {
            $("#pessoasContainer").load("pessoasSelecionadas.jsp");
        }
    });

}