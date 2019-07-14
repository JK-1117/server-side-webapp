<%-- 
    Document   : loginForm
    Created on : Jul 5, 2019, 5:14:50 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = "";
    String username = "";

    if (session.getAttribute("role") != null) {
        role = (String) session.getAttribute("role");
    } else {
        role = "";
    }

    if (session.getAttribute("username") != null) {
        username = (String) session.getAttribute("username");
    } else {
        username = "";
    }

%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-right">
            <% if (username.equals("")) {%>
            <li><a class="pointer" data-toggle="modal" data-target="#loginModal">Login</a></li>
            <li><a href="/signup.jsp">Sign Up</a></li>
                <% } else { %>
            <li><a href="servletLogout">Logout</a></li>
                <% }%>
        </ul>
    </div>
</nav>

<!-- Modal -->
<div id="loginModal" class="modal fade in" role="dialog">
    <div class="modal-dialog modal-login">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div class="avatar">
                    <img src="assets/avatar.png" alt="Avatar">
                </div>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Member Login</h4>
            </div>
            <div class="modal-body">
                <form id="frm_login" action="" method="" autocomplete="on">
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" required="required" autofocus="autofocus">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">	
                    </div>
                    <div id="loginError" class="form-group"></div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-btn">Login</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="./signup.jsp">Don't have an account?</a>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_login").submit(event => {
            event.preventDefault();
            verifyLogin();
        });
    })

    function verifyLogin() {
        var username = $('#username').val();
        var password = $('#password').val();
        var input = username + "," + password;
        $.ajax({
            url: 'servletLogin',
            method: 'POST',
            data: {input: input},
            success: function (result) {
                if (result == "true") {
                    window.location.href = "index.jsp";
                } else {
                    $("#loginError").html("<div class=\"alert alert-danger\">Wrong username/password</div>");
                }
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
</script>