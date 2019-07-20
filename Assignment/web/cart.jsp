<%-- 
    Document   : cart
    Created on : Jul 14, 2019, 5:07:42 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>
<h1>Cart</h1>

<form id="frm_insertOrder" action="servletOrdersUpdate" method="POST">
    <input type="hidden" id="operation" name="operation" value="New" />
    <input type="hidden" id="customerName" name="customerName" value="" />
    <input type="hidden" id="requiredDate" name="requiredDate" value="" />
</form>

<c:if test="${not empty sessionScope.username}">
    <section>
        <div class="form-group">
            <label for="cmbCompany">Company Name: </label>
            <input onchange="checkCompany()" class="form-control" list="listCompany" id="cmbCompany" name="cmbCompany">
            <datalist id="listCompany">
                <c:forEach items="${requestScope.listCustomer}" var="item">
                    <option value="${item.customerName}">
                </c:forEach>
            </datalist>
        </div>
        <div id="errorCompany" class="alert alert-danger">
            Company Not Exist!
        </div>
    
        <div id="verificationSection" class="panel panel-warning">
            <div class="panel-heading">To verify your identity, please enter the questions below:</div>
            <div class="form-inline panel-body">
                <div class="form-group">
                    <label for="txtContactFirstName">Contact First Name: </label>
                    <input type="text" id="txtContactFirstName" name="txtContactFirstName" class="form-control" value="" />
                    <label for="txtContactLastName">Last Name: </label>
                    <input type="text" id="txtContactLastName" name="txtContactLastName" class="form-control" value="" />
                </div>
                <div class="form-group-lg">
                    <button onclick="verifyIdentity()" type="button" class="btn btn-success pull-right">Check</button>
                </div>
            </div>
        </div>
    </section>
    
    <div id="alertVerify" class="alert alert-success">
        <strong>Successfully Verified!</strong>
    </div>
    <div id="errorVerify" class="alert alert-danger">
        <strong>Contact Name Not Correct!</strong>!
    </div>
</c:if>

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
            
<c:if test="${not empty requestScope.errorInsertOrders}">
    <div id="errorInsertOrders" class="alert alert-danger">
        <strong>${requestScope.errorInsertOrders}</strong>!
    </div>
</c:if>
            
<section>
    <div class="form-group form-inline">
        <div class="col-sm-10">
        <label for="txtRequiredDate" class="control-label col-sm-7 text-right">Required Date: </label>
            <input id="txtRequiredDate" name="txtRequiredDate" type="text" value="" class="form-control datepicker" />
        </div>
        
        <c:choose>
            <c:when test="${empty sessionScope.username}">
                <button type="button" class="btn btn-lg btn-danger pull-right col-sm-2" id="btnCheckout" data-toggle="modal" data-target="#loginModal">Checkout</button>
            </c:when>
            <c:otherwise>
                <button onclick="checkout()" type="button" class="btn btn-lg btn-danger pull-right col-sm-2" id="btnCheckout">Checkout</button>
            </c:otherwise>
        </c:choose>
    </div>
</section>
            
<script>
    var verified = false;
    $(function() {
        $(".datepicker").datepicker({ dateFormat: 'dd/mm/yy' });
        
        $("#verificationSection").hide()
        $("#errorCompany").hide();
        $("#alertVerify").hide();
        $("#errorVerify").hide();
    })
    
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
    
    function checkCompany() {
        $("#cmbCompany").parent().removeClass("has-error");
        $("#verificationSection").hide()
        $("#errorCompany").hide();
        $("#alertVerify").hide();
        $("#errorVerify").hide();
        
        verified = false;
        var input = $("#cmbCompany");
	var option = $('#listCompany option');
	input.on('keyup click',function(){
            var val = this.value;
            if($(option).filter(function(){
                    return this.value === val;
            }).length) {
                $("#verificationSection").show()
                $("#errorCompany").hide();
            }
            else{
                $("#cmbCompany").parent().addClass("has-error");
                $("#verificationSection").hide()
                $("#errorCompany").show(200);
            }    
        });
    }
    
    function verifyIdentity() {
        var cmbCompany = $('#cmbCompany').val();
        var firstName = $('#txtContactFirstName').val();
        var lastName = $('#txtContactLastName').val();
        var input = cmbCompany + "," + firstName + "," + lastName;
        $.ajax({
            url: 'servletAjaxCustomer',
            method: 'POST',
            data: {input: input},
            success: function (result) {
                if (result == "true") {
                    verified = true;
                    $("#errorVerify").hide();
                    $("#alertVerify").show(400);
                    $("#customerName").val(cmbCompany);
                    
                    window.setTimeout(function() {
                        $("#alertVerify").fadeOut('slow');
                    }, 1000);
                }
                else {
                    verified = false;
                    $("#errorVerify").show(200);
                }
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
    
    function checkout() {
        if(${empty sessionScope.cart}) {
            console.log("empty cart");
            alert("No item added...");
        }
        else if(!verified) {
            alert("Please Select Your Company and verify");
            $("#cmbCompany").parent().addClass("has-error");
            $('html, body').animate({
                scrollTop: $("#cmbCompany").offset().top
            }, 600);
        }
        else if($("#txtRequiredDate").val() == "") {
            alert("Please enter your required date");
            $("#txtRequiredDate").parent().addClass("has-error");
            $('html, body').animate({
                scrollTop: $("#txtRequiredDate").offset().top
            }, 600);
        }
        else {
            $("#cmbCompany").parent().removeClass("has-error");
            $("#requiredDate").val($("#txtRequiredDate").val());
            $("#frm_insertOrder").submit();
        }
    }
</script>
