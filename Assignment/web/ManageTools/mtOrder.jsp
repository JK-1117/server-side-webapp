<%-- 
    Document   : mtOrder
    Created on : Jul 6, 2019, 2:27:49 AM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Orders" %>
<%@page language="java" import="java.util.*" %>
<%@page language="java" import="java.text.DateFormat" %>
<%
    Orders orders = null;
    if(request.getAttribute("orders") != null) {
        orders = (Orders)request.getAttribute("orders");
    }
%>

<%=orders.getOrderNumber()%>