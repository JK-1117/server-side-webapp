/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#table_user').DataTable();

    $(".error").hide();

    $("input").on("keyup", function() {
        $(".error").hide();
    });
});
function changePassword() {
    var newPassword = $("#textPassword").val();
    var newPassword2 = $("#textPassword2").val();

    if(newPassword != newPassword2) {
        $("#password2Error").show();
    }
    else {
        validation();
    }
}
function editUser(username,password,userRole) {
    $("#operation").val("Update");
    $("#textUsername").attr("readonly", true);
    $("#textUsername").val(username);
    $("#textPassword").val(password);
    $("#textUserRole").val(userRole);
}
function deleteUser(username,password,userRole) {
    $("#operation").val("Delete");
    $("#textUsername").attr("readonly", false);
    $("#textUsername").val(username);
    $("#textPassword").val(password);
    $("#textUserRole").val(userRole);
    validation();
}
function newUser() {
    $(".error").hide();
    $("#operation").val("New");
    $("#textUsername").attr("readonly", false);
    $("#textUsername").val("");
    $("#textPassword").val("");
    $("#textUserRole").val("");
}
function validation() {
    var valid = true;
    if($("#textUsername").val() != null) {
        if($("#textUsername").val().trim().length < 1 || $("#textUsername").val().length > 15) {
            $("#usernameError").show();
            valid = false;
        }
    }
    if($("#textPassword").val().trim().length < 1 || $("#textPassword").val().length > 15) {
        $("#passwordError").show();
        valid = false;
    }
    if(valid) {
        $( "#dialog-confirm" ).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
              "Yes": function() {
                  verifyPassword();
              },
              Cancel: function() {
                $( this ).dialog( "close" );
              }
            },
            open: function(event) {
                $("#password").on('keydown',function(event) {
                    if(event.keyCode == $.ui.keyCode.ENTER) {
                        event.preventDefault();
                        verifyPassword();
                    }
                });
            }
        });
    }
}
function verifyPassword() {
    var username = $('#username').val();
    var password = $('#password').val();
    var input = username + "," + password;
    $.ajax({
        url: 'servletLogin',
        method: 'POST',
        data: {input: input},
        success: function (result) {
            if (result == "true") {
                $("#frm_updateUser").submit();
            } else {
                $("#loginError").html("<div class=\"alert alert-danger\">Wrong password</div>");
            }
        },
        error: function (jqXHR, exception) {
            console.log('Error occured!!');
        }
    });
}
