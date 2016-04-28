


function aceitarAmizade(idAmigo)
{
    var user = idAmigo;

    $.ajax({
        type: "POST",
        url: "ServletAceitarAmizade",
        data: {"idSolicitado": idAmigo},
        success: function (data) {
            if (data == 'True') {
               // $(location).attr('href', 'home#pessoas');
               
            }
        }
    });
}

