/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(window).resize(function () {
    winWidth = $(window).width();

    if (winWidth > 992) {
        $("#productLine")
                .removeClass("row")
                .addClass("btn-group-justified");
    } else {
        $("#productLine")
                .removeClass("btn-group-justified")
                .addClass("row");
    }
});