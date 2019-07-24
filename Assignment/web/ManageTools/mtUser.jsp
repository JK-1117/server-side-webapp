<%-- 
    Document   : mtUser
    Created on : Jul 9, 2019, 9:29:06 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.User" %>
<%@page language="java" import="entity.UserRole" %>
<%@page language="java" import="sessionBean.sessionbeanUserRole" %>
<%@page language="java" import="java.util.*" %>
<%
    String username = "";
    String role = "";
    sessionbeanUserRole sessionbeanUserRole = null;
    List<User> listUser = null;
    
    if(session.getAttribute("username") != null) {
        username = (String)session.getAttribute("username");
    }
    if(session.getAttribute("role") != null) {
        role = (String)session.getAttribute("role");
    }
    if(request.getAttribute("sessionbeanUserRole") != null) {
        sessionbeanUserRole = (sessionbeanUserRole)request.getAttribute("sessionbeanUserRole");
    }
    if(request.getAttribute("listUser") != null) {
        listUser = (List<User>)request.getAttribute("listUser");
    }
    
    if(username.equals("")) {
        out.println("<script>window.location.href='./accessDenied.jsp';</script>");
    }
%>
<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>
<h1>User</h1>

<% if(role.equals("staff")) { %>
<form id="frm_updateUser" action="servletUserUpdate" method="POST">
    <input type="hidden" name="operation" id="operation" value="ChangePassword" />
    <div class="form-group">
        <label for="textPassword">New Password: </label>
        <input type="text" class="form-control" placeholder="password" id="textPassword" name="textPassword">
        <small id="passwordError" class="error">*password length must within 1 to 15</small>
    </div>
    <div class="form-group">
        <label for="textPassword2">Re-enter Password: </label>
        <input type="text" class="form-control" placeholder="re-enter password" id="textPassword2" name="textPassword2">
        <small id="password2Error" class="error">*please enter the same password</small>
    </div>
    <div class="form-group">
        <button type="button" onclick="changePassword()" class="btn btn-success">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
        <button type="reset" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove"></span></button>
    </div>
</form>

<% } else if(role.equals("admin")) { %>
<div class="row">
    <div class="col-md-6">
        <table id="table_user" class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th width="50">No.</th>
                    <th width="150">Username</th>
                    <th width="150">Password</th>
                    <th width="100">User Role</th>
                    <th width="50">Edit</th>
                    <th width="50">Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Iterator i = listUser.iterator();
                    int index = 1;
                    
                    while(i.hasNext()) {
                        User user = (User)i.next();
                        String currUser = user.getUsername();
                        String password = user.getPassword();
                        String userRole = sessionbeanUserRole.getUserRole(user.getUsername());
                        if(!userRole.equals("")) {
                            out.println("<tr><td class=\"text-center\">" + index + ".</td>");
                            out.println("<td>" + currUser + "</td>");
                            out.println("<td>" + password + "</td>");
                            out.println("<td>" + userRole + "</td>");
                            out.println("<td class='pointer text-center' onclick=\"editUser('" + currUser + "','" + password + "','" + userRole + "')\"><span class=\"glyphicon glyphicon-pencil\"></span></td>");
                            out.println("<td class='pointer text-center' onclick=\"deleteUser('" + currUser + "','" + password + "','" + userRole + "')\"><span class=\"glyphicon glyphicon-trash\"></span></td></tr>");
                            index++;
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    <div class="col-md-6">
        <form id="frm_updateUser" action="servletUserUpdate" method="POST">
            <input type="hidden" name="operation" id="operation" value="New" />
            <div class="form-group">
                <label for="textUsername">Username: </label>
                <input type="text" class="form-control" placeholder="username" id="textUsername" name="textUsername">
                <small id="usernameError" class="error">*username length must within 1 to 15</small>
            </div>
            <div class="form-group">
                <label for="textPassword">Password: </label>
                <input type="text" class="form-control" placeholder="password" id="textPassword" name="textPassword">
                <small id="passwordError" class="error">*password length must within 1 to 15</small>
            </div>
            <div class="form-group">
                <label for="textUserRole">User Role: </label>
                <select id="textUserRole" name="textUserRole" class="form-control">
                    <option value="admin">admin</option>
                    <option value="staff">staff</option>
                    <option value="user">user</option>
                </select>
            </div>
            <div class="form-group">
                <button type="button" onclick="validation()" class="btn btn-success">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
                <button type="reset" onclick="newUser()" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove"></span></button>
            </div>
        </form>
    </div>
</div>
<% } %>
            
<div id="dialog-confirm" title="Delete Comfirmation" style="display:none;">
    <p><span class="ui-icon ui-icon-alert"></span>Please enter your password to confirm operation.</p>
    <form id="frm_login" action="" method="">
        <input type="hidden" id="username" name="username" value="<%=username%>" />
        <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">	
        </div>
        <div id="loginError" class="form-group"></div>
    </form>
</div>
            
<script type="text/javascript" src="js/datatables.min.js"></script>
<script>
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
</script>
