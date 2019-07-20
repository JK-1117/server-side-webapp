<%-- 
    Document   : Order
    Created on : Jul 20, 2019, 3:58:31 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>
<h1>Order Detail</h1>

<fmt:formatDate value="${orders.orderDate}" var="orderDate" type="date" pattern="dd/MM/yyyy" />
<fmt:formatDate value="${orders.requiredDate}" var="requiredDate" type="date" pattern="dd/MM/yyyy" />
<fmt:formatDate value="${orders.shippedDate}" var="shippedDate" type="date" pattern="dd/MM/yyyy" />
<div class="row form-group">
    <div class="col-xs-3 text-right">
        <strong>Order Date: </strong>
    </div>
    <div class="col-xs-3 text-center">
        ${orderDate}
    </div>
    <div class="col-xs-3 text-right">
        <strong>Required Date: </strong>
    </div>
    <div class="col-xs-3 text-center">
        ${requiredDate}
    </div>
</div>
    
<div class="row form-group">
    <div class="col-xs-3 text-right">
        <strong>Status: </strong>
    </div>
    <div class="col-xs-3 text-center">
        <jsp:useBean id="Util" class="utilities.Util"/>
        <span class='label ${Util.getStatusClass(orders.status)}'>${orders.status.toUpperCase()}</span>
    </div>
    <div class="col-xs-3 text-right">
        <strong>Shipped Date: </strong>
    </div>
    <div class="col-xs-3 text-center">
        ${shippedDate}
    </div>
</div>

<table class="table table-hover table-bordered table-striped">
    <thead>
        <tr valign="middle">
            <th class="text-center" width="50">No.</th>
            <th class="text-center">Product Name</th>
            <th class="text-center" width="70">Qty</th>
            <th class="text-center" width="100">Price</th>
            <th class="text-center" width="120">Amount</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="totalQty" value="${0}"/>
        <c:set var="totalAmt" value="${0}"/>
        <c:forEach var="item" items="${requestScope.orders.orderdetailList}" varStatus="loop">
            <c:set var="totalQty" value="${totalQty + item.quantityOrdered}" />
            <c:set var="totalAmt" value="${totalAmt + (item.quantityOrdered * item.priceEach)}" />
            <tr>
                <td>${loop.index + 1}.</td>
                <td>${item.product.productName}</td>
                <td class="text-right">${item.quantityOrdered}</td>
                <td class="text-right">RM ${item.priceEach}</td>
                <td class="text-right">RM ${item.quantityOrdered * item.priceEach}</td>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="2" class="text-right">total Qty:</td>
            <td class="text-right">${totalQty}</td>
            <td class="text-right">total Amt:</td>
            <td class="text-right">RM${totalAmt}</td>
        </tr>
    </tfoot>
</table>