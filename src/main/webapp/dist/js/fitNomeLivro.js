/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(".limit").each(function (i) {
    len = $(this).text().length;
    if (len > 20) {
        $(this).text($(this).text().substr(0, 20) + '...');
    }
});
