<%-- 
    Document   : mtOrder
    Created on : Jul 5, 2019, 9:14:27 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Product" %>
<%@page language="java" import="java.util.*" %>
<%@page language="java" import="java.text.SimpleDateFormat" %>
<%
    String username = "";
    String role = "";
    
    if(session.getAttribute("username") != null) {
        username = (String)session.getAttribute("username");
    }
    if(session.getAttribute("role") != null) {
        role = (String)session.getAttribute("role");
    }
    
    List<Product> listProducts = null;
    if(request.getAttribute("listProducts") != null) {
        listProducts = (List<Product>)request.getAttribute("listProducts");
    }
%>
<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<h1>Product List</h1>
        
<% if(role.equals("admin")) {%>
    <div class="row">
        <div class="col-xs-12" style="padding: 0px 0px 25px 15px;">
            <a href="Product?operation=New" class="btn btn-success">New <span class="glyphicon glyphicon-plus-sign"></span></a>
        </div>
    </div>
<% } %>

<table id="table_product" class="table table-striped table-hover table-bordered table-sm">
    <thead>
        <tr>
            <th class="text-center">No.</th>
            <th class="text-center">Product Code</th>
            <th class="text-center">Product Name</th>
            <th class="text-center">Product Line</th>
            <th class="text-center">Vendor</th>
            <th class="text-center">Qty</th>
            <th class="text-center">Buy Price</th>
            <th class="text-center">MSRP</th>
            <th class="text-center" width="50">Open</th>
        </tr>
    </thead>
    <tbody>
    <%
        int index = 1;
        Iterator i = listProducts.iterator();

        while(i.hasNext()) {
            Product product = (Product)i.next();

            out.println("<tr height='40' valign='middle'>");
                out.println("<td>" + index + ".</td>");
                out.println("<td class=\"text-center\">" + product.getProductCode()+ "</td>");
                out.println("<td>" + product.getProductName() + "</td>");
                out.println("<td>" + product.getProductLine().getProductLine() + "</td>");
                out.println("<td>" + product.getProductVendor() + "</td>");
                out.println("<td>" + product.getQuantityInStock()+ "</td>");
                out.println("<td>RM" + product.getBuyPrice()+ "</td>");
                out.println("<td>RM" + product.getMsrp()+ "</td>");
                out.println("<td onclick='window.location.href=\"Product?productCode=" + product.getProductCode() + "\";' class='pointer text-center'><span class=\"glyphicon glyphicon-folder-open\"></span></td>");
            out.println("</tr>");

            index++;
        }
    %>
    </tbody>
</table>
<script type="text/javascript" src="js/datatables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#table_product').DataTable();
    });
</script>