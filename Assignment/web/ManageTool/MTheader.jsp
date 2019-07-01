<%-- 
    Document   : header
    Created on : Jun 30, 2019, 1:56:08 AM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = "admin"; //For testing
%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
            <a class="navbar-brand" href="MTOrder">KoolStore</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="MTOrder">Order</a></li>
                <li><a href="MTProduct">Product</a></li>
                <li><a href="MTPayment">Payment</a></li>
                <li><a href="MTCustomer">Customer</a></li>
                <li><a href="MTPassword">Change Password</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%=username%></a></li>
                <li><a href="#" id="currentTime"></a></li>
            </ul>
        </div>
    </div>
</nav>
<script>
    $(function() {
        display_time();
    })
    function refresh_time() {
        setTimeout('display_time()', 1000)
    }

    function display_time() {
        var x = new Date();
        $("#currentTime").text(x.toUTCString());
        console.log(x);
        console.log($('#currentTime'))
        refresh_time();
    }
</script>