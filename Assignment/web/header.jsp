<%-- 
    Document   : header
    Created on : Jun 30, 2019, 1:56:08 AM
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
<%    if (role.equals("") || role.equals("user")) {
%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-right">
            <% if (username.equals("")) {%>
            <li><a data-toggle="modal" data-target="#loginModal">Login</a></li>
            <li><a href="/signup.jsp">Sign Up</a></li>
                <% } else { %>
            <li><a href="servletLogout">Logout</a></li>
            <% } %>
        </ul>
    </div>
</nav>

<div class="jumbotron">
    <div class="container text-center">
        <h1>Online Store</h1>      
        <p>Mission, Vision & Values</p>
    </div>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="Home">KoolStore</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="Home">Home</a></li>
            <li><a href="Product">Products</a></li>
            <li><a href="Contact">Contact</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="Profile"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
            <li><a href="Order"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
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
                <form id="frm_login" action="" method="">
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" required="required">
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

<%
    }else {
%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="Home">KoolStore</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="Order">Order</a></li>
            <li><a href="Product">Product</a></li>
            <li><a href="Payment">Payment</a></li>
            <li><a href="Customer">Customer</a></li>
            <li><a href="Employee">Employee</a></li>
            <li><a href="Office">Office</a></li>
            <li><a href="User">User</a></li>
            <li><a href="Logout">Logout</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a><span class="glyphicon glyphicon-user"></span> <%=username%></a></li>
            <li><a id="currentTime"></a></li>
        </ul>
    </div>
</nav>
<% }%>
<script type="text/javascript">
    $(function () {
        display_time();

        $("#frm_login").submit(event => {
            event.preventDefault();
            verifyLogin();
        });
    })
    function refresh_time() {
        setTimeout(display_time, 1000);
    }

    function display_time() {
        var x = new Date();
        $("#currentTime").text(x.toUTCString());
        refresh_time();
    }

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
                    window.location.href = "Account";
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