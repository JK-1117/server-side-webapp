<%-- 
    Document   : product
    Created on : Jul 4, 2019, 1:06:36 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Product" %>
<%@page language="java" import="java.util.*" %>
<%
    String productLine = "";
    List<Product> listProducts = null;
    
    if(request.getAttribute("productLine") != null) {
        productLine = (String)request.getAttribute("productLine");
    }
    if(request.getAttribute("listProducts") != null) {
        listProducts = (List<Product>)request.getAttribute("listProducts");
    }
%>
<h3><%=productLine.toUpperCase()%> > PRODUCT</h3>
<small>Showing 10 results</small>
<div class="tab-content">
    <div id="page1" class="tab-pane list-group fade in active" >
        <%
            int index = 0;
            int productPage = 1;
        //TODO: sorting & select productline & probably search
            Iterator i = listProducts.iterator();
            while (i.hasNext()) {
                Product pro = (Product) i.next();
                out.println("<a href=\"#\" class=\"list-group-item\" >");
                out.println("<h4 class=\"list-group-item-heading\" >");
                out.println(pro.getProductName());
                out.println("<span class=\"label label-info\">RM" + pro.getMsrp() + "</span>");
                out.println("</h4><p class=\"list-group-item-text\" >");
                out.println(pro.getProductDescription());
                out.println("</p></a>");
                index++;
                if (index == productPage * 10) {
                    out.println("</div>");
                    if (i.hasNext()) {
                        productPage++;
                        out.println("<div id=\"page" + productPage + "\" class=\"tab-pane list-group fade\" >");
                    }
                }
            }
            if (index % 10 > 0) {
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<ul class=\"pagination pull-right\">");
            out.println("<li class=\"active\"><a onclick=\"changePage(this)\" href=\"#\" >1</a></li>");
            for (int j = 2; j <= productPage; j++) {
                out.println("<li><a onclick=\"changePage(this)\" href=\"#\" >" + j + "</a></li>");
            }
            out.println("</ul>");
        %>
        <script src=\"js/productjs.js\"></script>