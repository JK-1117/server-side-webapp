<%-- 
    Document   : header
    Created on : Jun 30, 2019, 1:56:08 AM
    Author     : JK
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${empty sessionScope.role || sessionScope.role == 'user'}">
    <jsp:include page="loginForm.jsp" flush="true" />

    <div class="jumbotron banner text-center">
        <!--<div class="container">-->
            <h1>Kool Store</h1>      
            <h3>Accelerating the future.</h3>
        <!--</div>-->
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
                <c:choose>
                    <c:when test="${empty sessionScope.username}">
                        <li><a data-toggle="modal" data-target="#loginModal">
                                <span class="glyphicon glyphicon-user"></span> My Account
                            </a></li>
                    </c:when>
                            
                    <c:otherwise>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-user"></span> My Account<span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="Order">Order History</a></li>
                                <li><a href="Payment">Payment History</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li><a href="Cart"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
            </ul>
        </div>
    </nav>
</c:if>

<c:if test="${sessionScope.role == 'staff' || sessionScope.role == 'admin'}">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="Order">KoolStore</a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.username}</a></li>
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
</c:if>

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