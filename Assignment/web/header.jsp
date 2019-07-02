<%-- 
    Document   : header
    Created on : Jun 30, 2019, 1:56:08 AM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userRole = "";
    String userName = "";

    if (session.getAttribute("userRole") != null) {
        userRole = (String) session.getAttribute("userRole");
    } else {
        userRole = "";
    }

    if (session.getAttribute("userName") != null) {
        userName = (String) session.getAttribute("userName");
    } else {
        userName = "";
    }

    userRole = "";
    userName = "testing";
%>
<%
    if (userRole.equals("") || userRole.equals("user")) {
%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/login.jsp">Login</a></li>
            <li><a href="/signup.jsp">Sign Up</a></li>
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
            <button type="button" class="navbar-toggle">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
            <a class="navbar-brand" href="Home">KoolStore</a>
        </div>
        <div class="collapse navbar-collapse">
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
    </div>
</nav>
<%
} else {
%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
            <a class="navbar-brand" href="Home">KoolStore</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="Order">Order</a></li>
                <li><a href="Product">Product</a></li>
                <li><a href="Payment">Payment</a></li>
                <li><a href="Customer">Customer</a></li>
                <li><a href="Employee">Employee</a></li>
                <li><a href="Office">Office</a></li>
                <li><a href="Password">Change Password</a></li>
                <li><a href="Logout">Logout</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a><span class="glyphicon glyphicon-user"></span> <%=userName%></a></li>
                <li><a id="currentTime"></a></li>
            </ul>
        </div>
    </div>
</nav>
<script type="text/javascript">
    $(function(){
        display_time();
    })
        function refresh_time() {
            setTimeout(display_time,1000);
        }
        
        function display_time() {
            var x = new Date();
            $("#currentTime").text(x.toUTCString());
            refresh_time();
        }
</script>
<% } %>