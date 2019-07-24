<%-- 
    Document   : mtOrder
    Created on : Jul 5, 2019, 9:14:27 PM
    Author     : JK
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${empty username}">
    <c:redirect url="accessDenied.jsp" />
</c:if>

<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<h1>Product List</h1>
        
<c:if test="${sessionScope.role == 'admin'}">
    <div class="row">
        <div class="col-xs-12" style="padding: 0px 0px 25px 15px;">
            <a href="Product?operation=New" class="btn btn-success">New <span class="glyphicon glyphicon-plus-sign"></span></a>
        </div>
    </div>
</c:if>

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
        <c:if test="${not empty requestScope.listProducts}">
            <c:forEach items="${requestScope.listProducts}" var="item" varStatus="loop">
                <tr height='40' valign='middle'>
                    <td align='center'>${loop.index + 1}</td>
                    <td align='center'>${item.productCode}</td>
                    <td>${item.productName}</td>
                    <td>${item.productLine.productLine}</td>
                    <td>${item.productVendor}</td>
                    <td align='right'>${item.quantityInStock}</td>
                    <td align='right'>RM${item.buyPrice}</td>
                    <td align='right'>RM${item.msrp}</td>
                    <td onclick="window.location.href='Product?productCode=${item.productCode}';" class='pointer text-center'><span class="glyphicon glyphicon-folder-open"></span></td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>
<script type="text/javascript" src="js/datatables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#table_product').DataTable();
    });
</script>