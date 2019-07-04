<%-- 
    Document   : home
    Created on : Jul 4, 2019, 1:36:29 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.Iterator" %>
<%@page language="java" import="java.util.List" %>
<%@page language="java" import="entity.Product" %>
<%@page language="java" import="entity.ProductLine" %>
<%@page language="java" import="sessionBean.sessionbeanOrderDetail" %>
<%
    List<ProductLine> listProductLine = null;
    List<Product> listTopSalesProduct = null;
    sessionbeanOrderDetail sessionbeanOrderDetail = null;

    if (request.getAttribute("listProductLine") != null) {
        listProductLine = (List<ProductLine>) request.getAttribute("listProductLine");
    }
    if (request.getAttribute("listTopSalesProduct") != null) {
        listTopSalesProduct = (List<Product>) request.getAttribute("listTopSalesProduct");
    }
    if (request.getAttribute("sessionbeanOrderDetail") != null) {
        sessionbeanOrderDetail = (sessionbeanOrderDetail) request.getAttribute("sessionbeanOrderDetail");
    }

    Iterator i = listProductLine.iterator();
    Iterator j = listTopSalesProduct.iterator();
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

<section class="row">
    <div class="col-xs-12">
        <h4>Top Sales <span class="label label-danger">HOT</span></h4>
    </div>
    <div class="col-xs-12 list-group">
        <%
            while (j.hasNext()) {
                Product pro = (Product) j.next();
                Long quantitySold = sessionbeanOrderDetail.getQuantitySold(pro);
                out.println("<a href=\"#\" class=\"list-group-item list-group-item-danger\" >");
                out.println("<h4 class=\"list-group-item-heading\" >");
                out.println(pro.getProductName());
                out.println("<span class=\"label label-danger\">" + quantitySold + " Sold</span>");
                out.println("</h4><p class=\"list-group-item-text\" >");
                out.println(pro.getProductDescription());
                out.println("</p></a>");
            }
        %>
    </div>
</section>

<script src="js/homejs.js"></script>