<%-- 
    Document   : product
    Created on : Jun 30, 2019, 2:30:47 AM
    Author     : JK
--%>

<%@page import="java.util.*" %>
<%@page import="entity.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Product> listProducts = (List<Product>) request.getAttribute("listProducts");

    Iterator i = listProducts.iterator();
%>
<h1>PRODUCT</h1>
<small>Showing 10 results</small>

<div class="row">
    <table class="table col-sm-12">
        <thead>
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Scale</th>
                <th>Vendor</th>
                <th width="500px">Description</th>
                <th>In Stock</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <%
                int index = 1;
                while (i.hasNext() && index < 11) {
                    Product pro = (Product) i.next();
                    out.println("<tr>");
                        out.println("<td>" + (index++) + "</td>");
                        out.println("<td>" + pro.getProductName() + "</td>");
                        out.println("<td>" + pro.getProductScale() + "</td>");
                        out.println("<td>" + pro.getProductVendor() + "</td>");
                        out.println("<td width=\"500px\">" + pro.getProductDescription() + "</td>");
                        if (pro.getQuantityInStock() > 0) {
                            out.println("<td>YES!!</td>");
                        } else {
                            out.println("<td>Out of Stock...</td>");
                        }
                        out.println("<td>" + pro.getMsrp() + "</td>");
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
</div>
