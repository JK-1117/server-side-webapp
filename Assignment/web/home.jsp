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
    <div class="col-sm-12 container">
        <div class="row" id="productLine">
            <%
                while (i.hasNext()) {
                    ProductLine proLine = (ProductLine) i.next();
                    out.println("<a href=\"Product?productLine=" + proLine.getProductLine() + "\" class=\"btn btn-default col-sm-3 col-xs-4\">");
                    out.println(proLine.getProductLine());
                    out.println("</a>");
                }
            %>
        </div>
    </div>
</section>

<script>
    $(function () {
    })
    $(window).resize(function () {
        winWidth = $(window).width();

        if (winWidth > 992) {
            $("#productLine")
                    .removeClass("row")
                    .addClass("btn-group-justified");
        }
        else {
            $("#productLine")
                    .removeClass("btn-group-justified")
                    .addClass("row");
        }
    });
</script>