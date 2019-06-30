<%-- 
    Document   : home
    Created on : Jun 30, 2019, 2:28:08 AM
    Author     : JK
--%>

<%@page import="java.util.*" %>
<%@page language="java" import="entity.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<ProductLine> listProductLine = (List<ProductLine>) request.getAttribute("listProductLine");

    Iterator i = listProductLine.iterator();
%>
<h1>HOME</h1>

<section class="row">
    <div class="col-sm-12">
        <h4>Product Line</h4>
    </div>
    <div class="col-sm-12">
        <div class="btn-group-justified">
            <%
                while (i.hasNext()) {
                    ProductLine proLine = (ProductLine) i.next();
                    out.println("<a href=\"#\" class=\"btn btn-default\">");
                        out.println(proLine.getProductLine());
                    out.println("</a>");
                }
            %>
        </div>
    </div>
</section>