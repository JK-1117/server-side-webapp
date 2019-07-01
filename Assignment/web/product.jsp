<%-- 
    Document   : product
    Created on : Jun 30, 2019, 2:30:47 AM
    Author     : JK
--%>

<%@page import="java.util.*" %>
<%@page import="entity.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String productLine = "";
    if(request.getAttribute("productLine") != null) {
        productLine = ((String)request.getAttribute("productLine")).toUpperCase() + " > ";
    }
    List<Product> listProducts = (List<Product>) request.getAttribute("listProducts");

    Iterator i = listProducts.iterator();
%>
<h3><%=productLine%>PRODUCT</h3>
<small>Showing 10 results</small>

<div class="tab-content">
<%  
    int index = 0;
    int productPage = 1;
    out.println("<div id=\"page1\" class=\"tab-pane list-group fade in active\" >");
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
        if(index == productPage*10) {
            out.println("</div>");
            if(i.hasNext()) {
                productPage++;
                out.println("<div id=\"page" + productPage +"\" class=\"tab-pane list-group fade\" >");
            }
        }
    }
    if(index%10 > 0) {
        out.println("</div>");
    }
    out.println("</div>");
    out.println("<ul class=\"pagination pull-right\">");
        out.println("<li class=\"active\"><a onclick=\"changePage(this)\" href=\"#\" >1</a></li>");
    for(int j = 2; j <= productPage; j++) {
        out.println("<li><a onclick=\"changePage(this)\" href=\"#\" >" + j + "</a></li>");
    }
    out.println("</ul>");
%>
<script>
    function changePage(page) {
        $(".pagination > li").removeClass("active");
        console.log(page);
        $(page).parent().addClass("active");
        $(".tab-pane").removeClass("in active");
        $("#page" + $(page).text()).addClass("in active");
    }
</script>
