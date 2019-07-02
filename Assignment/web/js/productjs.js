/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function changePage(btn) {
    $(".pagination > li").removeClass("active");
    $(btn).addClass("active");
    $("[id^=page]").removeClass("in active");
    $("#page" + $(btn).text()).addClass("in active");
}