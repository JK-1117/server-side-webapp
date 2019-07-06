<%-- 
    Document   : mtProduct
    Created on : Jul 5, 2019, 9:11:13 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Product" %>
<%@page language="java" import="java.util.*" %>
<%
    Product product = null;
    if(request.getAttribute("product") != null) {
        product = (Product)request.getAttribute("product");
    }
%>
<h1><%=product.getProductCode()%></h1>