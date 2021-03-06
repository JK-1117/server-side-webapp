<%-- 
    Document   : orderList
    Created on : Jul 20, 2019, 4:06:06 PM
    Author     : JK
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Order History</h1>

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
            
<table id="table_orders" class="table table-striped table-hover table-bordered table-sm">
    <thead>
        <tr>
            <th class="text-center" width="50">No.</th>
            <th class="text-center" width="80">Order Number</th>
            <th class="text-center">Order Date</th>
            <th class="text-center">Required Date</th>
            <th class="text-center">Shipped Date</th>
            <th class="text-center" width="105">Status</th>
            <th class="text-center">Order Total</th>
            <th class="text-center" width="50">Open</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty requestScope.listOrders}">
            <tr>
                <td colspan="8" class="text-center">No orders placed...</td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.listOrders}">
            <jsp:useBean id="Util" class="utilities.Util"/>
            <c:forEach items="${requestScope.listOrders}" var="item" varStatus="loop">

                <fmt:formatDate value="${item.orderDate}" var="orderDate" type="date" pattern="dd/MM/yyyy" />
                <fmt:formatDate value="${item.requiredDate}" var="requiredDate" type="date" pattern="dd/MM/yyyy" />
                <fmt:formatDate value="${item.shippedDate}" var="shippedDate" type="date" pattern="dd/MM/yyyy" />

                <tr height='40' valign='middle'>
                    <td align='center'>${loop.index + 1}</td>
                    <td align='center'>${item.orderNumber}</td>
                    <td align='center'>${orderDate}</td>
                    <td align='center'>${requiredDate}</td>
                    <td align='center'>${shippedDate}</td>
                    <td align='center'><span class='label ${Util.getStatusClass(item.status)}'>${item.status.toUpperCase()}</span></td>
                    <td align='right'>RM ${requestScope.listOrderTotal.get(loop.index)}</td>
                    <td align='center' onclick='window.location.href="Order?orderNumber=${item.orderNumber}";' class='pointer'><span class="glyphicon glyphicon-folder-open"></span></td>
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
    })
    
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
                    window.location.href = "Order?customerName=" + cmbCompany;
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
