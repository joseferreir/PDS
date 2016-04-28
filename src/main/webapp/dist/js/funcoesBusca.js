/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#buscaLivros").click(processaBuscaLivros);

$("#nomePessoaBuscada").keyup(processaBuscaPessoas);

//$("#buscaPessoas").click(processaBuscaPessoas);


function processaBuscaLivros() {
    event.preventDefault();

    var dados = $("#formBusca").serialize();

    var dados = dados;
    
    var actionName = "LivroBuscar";

    $.ajax({
        type: "POST",
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
            $("#livrosSelecionados").load("pressSelectedBooks.jsp");
            $('.avLivro').rating('refresh', {disabled: true, showClear: false, showCaption: true});
        }
    });

}


function processaBuscaPessoas() {
    event.preventDefault();

    var dados = $("#formBuscaPessoas").serialize();
    
    $.ajax({
        type: "POST",
        url: "/sislivros/logged/user/buscar.do",
        data: dados,
        success: function () {
            $("#pessoasContainer").load("pessoasSelecionadas.jsp");
        }
    });

}