<%-- 
    Document   : mtProduct
    Created on : Jul 5, 2019, 9:11:13 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<form id="frm_cart" name="frm_cart" action="servletCart" method="POST">
    <input type="hidden" id="operation" name="operation" value="AddToCart" />
    <input type="hidden" id="productCode" name="productCode" value="${requestScope.product.productCode}" />
    <input type="hidden" id="productName" name="productName" value="${requestScope.product.productName}" />
    <input type="hidden" id="qty" name="qty" value="1" />
</form>

<div class="row">
    <a href="Product?productLine=${requestScope.product.productLine.productLine}">${requestScope.product.productLine.productLine.toUpperCase()}</a> > ${requestScope.product.productName.toUpperCase()}
</div>
<h2>${requestScope.product.productName}</h2>

<section>
    <div class="form-group row">
        <label class="col-xs-3 text-right">Product Scale: </label>
        <div class="col-xs-9">${requestScope.product.productScale}</div>
    </div>

    <div class="form-group row">
        <label class="col-xs-3 text-right">Product Vendor: </label>
        <div class="col-xs-9">${requestScope.product.productVendor}</div>
    </div>

    <div class="form-group row">        
        <label class="col-xs-3 text-right">Price:  </label>
        <div class="col-xs-9">
            RM${requestScope.product.msrp}
        </div>
    </div>

    <div class="form-group row">
        <label class="col-xs-3 text-right">Quantity: </label>

        <c:choose>
            <c:when test="${requestScope.product.quantityInStock < 1}">
                <div class="col-xs-2">
                    <strong class="text-danger">Out of Stock</strong>
                </div>
            </c:when>
            
            <c:otherwise>
                <div class="col-xs-2">
                    <input class="form-control" type="number" id="orderQty" name="orderQty" value="1" min="1" max="${requestScope.product.quantityInStock}" />
                </div>
                <small class="col-xs-7">
                    ${requestScope.product.quantityInStock} pieces available
                </small>
            </c:otherwise>
        </c:choose>
    </div>
        
    <c:if test='${param.itemAdded == "true"}'>
        <div id="successAlert" class="alert alert-success">
            <strong>Items Added Successfully!</strong>
        </div>
    </c:if>
        
    <div class="form-group row">        
        <div class="col-xs-offset-3 col-xs-9">
            <button onclick="order('AddToCart')" class="btn btn-lg btn-primary ${requestScope.product.quantityInStock < 1? 'disabled':''}">
                <span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart
            </button>
            <button onclick="order('BuyNow')" class="btn btn-lg btn-danger ${requestScope.product.quantityInStock < 1? 'disabled':''}">
                <span class="glyphicon glyphicon-usd"></span> Buy Now
            </button>
        </div>
    </div>

    <div class="row form-group">
        <label class="col-xs-3 text-right">Description: </label>
        <div class="col-xs-9">${requestScope.product.productDescription}</div>
    </div>
</section>
        
<script>
    $(function() {
        window.setTimeout(function() {
            $("#successAlert").fadeOut('slow');
        }, 600);
        
        $("#orderQty").change(event => {
            $("#qty").val($("#orderQty").val());
        });
    });
    
    function order(operation) {
        $("#operation").val(operation);
        $("#frm_cart").submit();
    }
</script>