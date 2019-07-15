<%-- 
    Document   : cart
    Created on : Jul 14, 2019, 5:07:42 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<h1>Cart</h1>
<section>
    <table class="table table-hover table-striped">
        <thead>
            <tr>
                <th width="50">No.</th>
                <th>Product</th>
                <th>Qty</th>
                <th>Price</th>
                <th>Amount</th>
                <th width="50">Remove</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty sessionScope.cart}">
                <tr>
                    <td colspan="6" class="text-center">No item added...</td>
                </tr>
            </c:if>
            <c:forEach var="item" items="${sessionScope.cart.contents}" varStatus="loop">
                <tr id="item${loop.index}">
                    <td class="text-center">${loop.index + 1}.</td>
                    <td>${item.key.productName}</td>
                    <td class="text-right">
                        <input id="qty${loop.index}" onchange="changeQty('${item.key.productCode}','${loop.index}',this)" type="number" value="${item.value}" min="0" max="${item.key.quantityInStock}" />
                    </td>
                    <td class="text-right" id="price${loop.index}">RM ${item.key.msrp}</td>
                    <td class="text-right" id="amt${loop.index}">RM ${item.key.msrp * item.value}</td>
                    <td class="text-center pointer" onclick="removeItem('${item.key.productCode}','${loop.index}')"><span class="glyphicon glyphicon-trash"></span></td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <th colspan="2" class="text-right">Total Qty:</th>
            <th class="text-right" id="totalQty">${sessionScope.cart.totalQty}</th>
            <th class="text-right">Total Amount:</th>
            <th class="text-right" id="totalAmount">RM ${sessionScope.cart.totalAmount}</th>
            <th></th>
        </tfoot>
    </table>
</section>
            
<section>
    <div class="form-group">
        <c:choose>
            <c:when test="${empty sessionScope.username}">
                <button type="button" class="btn btn-lg btn-danger pull-right" id="btnCheckout" data-toggle="modal" data-target="#loginModal">Checkout</button>
            </c:when>
            <c:otherwise>
                <button type="button" class="btn btn-lg btn-danger pull-right" id="btnCheckout">Checkout</button>
            </c:otherwise>
        </c:choose>
    </div>
</section>
            
<script>
    function removeItem(productCode, id) {
        $("#qty" + id).val(0);
        changeQty(productCode, id, $("#qty" + id));
    }
    function changeQty(productCode, id, inputControl){
        var qty = $(inputControl).val();
        var input = productCode + "," + qty;
        var price = $("#price" + id).text().slice(3);
        var amt = price * qty;
        $.ajax({
            url: 'servletCart',
            method: 'GET',
            data: {input: input},
            success: function (result) {
                var totalQty = result.split(",")[0];
                var totalAmount = result.split(",")[1];

                $("#amt" + id).text("RM " + amt.toFixed(2));
                $("#totalQty").html(totalQty);
                $("#totalAmount").html("RM " + totalAmount);
                if(qty == 0) {
                    $("#item" + id).remove();
                }
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
</script>
