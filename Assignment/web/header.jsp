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

<%    if (role.equals("") || role.equals("user")) { %>

<jsp:include page="loginForm.jsp" flush="true" />

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
            <%
                if(username.equals("")) {
                    out.println("<li><a data-toggle=\"modal\" data-target=\"#loginModal\">" 
                            + "<span class=\"glyphicon glyphicon-user\"></span> My Account</a></li>");
                }
                else {
                    out.println("<li class=\"dropdown\">");
                    out.println("<a class=\"dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"glyphicon glyphicon-user\"></span> My Account<span class=\"caret\"></span></a>");
                    out.println("<ul class=\"dropdown-menu\">");
                    out.println("<li><a href=\"Profile\">Profile</a></li>");
                    out.println("<li><a href=\"Order\">Order History</a></li>");
                    out.println("<li><a href=\"Payment\">Payment History</a></li>");
                    out.println("</ul></li>");
                }
            %>
            <li><a href="Order"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
        </ul>
    </div>
</nav>
<%
    }else {
%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="Order">KoolStore</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a><span class="glyphicon glyphicon-user"></span> <%=username%></a></li>
            <li><a id="currentTime"></a></li>
        </ul>
    </div>
</nav>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="Order">Order</a></li>
            <li><a href="Product">Product</a></li>
            <li><a href="Payment">Payment</a></li>
            <li><a href="Customer">Customer</a></li>
            <li><a href="Employee">Employee</a></li>
            <li><a href="Office">Office</a></li>
            <li><a href="User">User</a></li>
            <li><a href="servletLogout">Logout</a></li>
        </ul>
    </div>
</nav>
<script type="text/javascript">
    $(function () {
        display_time();
    })
    function refresh_time() {
        setTimeout(display_time, 1000);
    }

    function display_time() {
        var x = new Date();
        $("#currentTime").text(x.toUTCString());
        refresh_time();
    }
</script>
<% }%>