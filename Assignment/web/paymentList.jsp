<%-- 
    Document   : orderList
    Created on : Jul 20, 2019, 4:06:06 PM
    Author     : JK
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Payment History</h1>

<section>
    <div class="form-group">
        <label for="cmbCompany">Company Name: </label>
        <input onchange="checkCompany()" value="${requestScope.customerName}" class="form-control" list="listCompany" id="cmbCompany" name="cmbCompany">
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

<div id="errorVerify" class="alert alert-danger">
    <strong>Contact Name Not Correct!</strong>!
</div>
        
<section>
    <form id="frm_paymentInsert" action="servletPayment" method="POST" class="form-inline">
        <div class="form-group">
            <input type="hidden" value="${requestScope.customerName}" id="customerName" name="customerName">
            <input type="text" id="checkNumber" name="checkNumber" class="form-control" placeholder="Check Number" />
            <input type="text" id="amount" name="amount" class="form-control" placeholder="Amount" />
            <button id="btnMakePayment" type="button" class="btn btn-success">Make Payment</button>
        </div>
    </form>
</section>
            
<table id="table_orders" class="table table-striped table-hover table-bordered table-sm">
    <thead>
        <tr>
            <th class="text-center" width="50">No.</th>
            <th class="text-center">Check Number</th>
            <th class="text-center">Payment Date</th>
            <th class="text-center" width="105">Amount</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty requestScope.listPayment}">
            <tr>
                <td colspan="8" class="text-center">No payment...</td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.listPayment}">
            <jsp:useBean id="Util" class="utilities.Util"/>
            <c:forEach items="${requestScope.listPayment}" var="item" varStatus="loop">

                <fmt:formatDate value="${item.paymentDate}" var="paymentDate" type="date" pattern="dd/MM/yyyy" />

                <tr height='40' valign='middle'>
                    <td align='center'>${loop.index + 1}</td>
                    <td align='center'>${item.paymentPK.checkNumber}</td>
                    <td align='center'>${paymentDate}</td>
                    <td align='right'>RM ${item.amount}</td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>
            
<script>
    $(function() {
        $("#verificationSection").hide()
        $("#errorCompany").hide();
        $("#errorVerify").hide();
        
        $("#amount").on("change", event => {
            validateAmountFormat();
        })
        $("#btnMakePayment").click(event => {
            event.preventDefault();
            validateAmountFormat();
            
            if($("#customerName").val() == "") {
                alert("Please select your company and verify you identity.");
            }
            else if($("#amount").val() == "0.00") {
                alert("Paymetn amount cannot be ZERO.");
            }
            else {
                $("#frm_paymentInsert").submit();
            }
        })
    })
    
    function validateAmountFormat() {
        var amountValue = $("#amount").val();
        amountValue = +amountValue;
        if(isNaN(amountValue)) {
            amountValue = 0;
        }
        amountValue = amountValue.toFixed(2);
        $("#amount").val(amountValue);
    }
    
    function checkCompany() {
        $("#cmbCompany").parent().removeClass("has-error");
        $("#verificationSection").hide()
        $("#errorCompany").hide();
        $("#errorVerify").hide();
        
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
                    $("#errorVerify").hide();
                    window.location.href = "Payment?customerName=" + cmbCompany;
                }
                else {
                    $("#errorVerify").show(200);
                }
            },
            error: function (jqXHR, exception) {
                console.log('Error occured!!');
            }
        });
    }
</script>
