<%-- 
    Document   : index
    Created on : Jun 29, 2019, 7:47:43 PM
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
<% if(role.equals("") || role.equals("user")) { %>
<script>
    window.location.href = "Home";
</script>
<%
    }
    else if(role.equals("staff") || role.equals("admin")) { 
%>
<script>
    window.location.href = "Order";
</script>
<% } %>
