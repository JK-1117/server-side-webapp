<%-- 
    Document   : mtOrder
    Created on : Jul 6, 2019, 2:27:49 AM
    Author     : JK
--%>

<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Orders" %>
<%@page language="java" import="entity.Orderdetail" %>
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
    
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
    Orders orders = null;
    if(request.getAttribute("orders") != null) {
        orders = (Orders)request.getAttribute("orders");
    }
    
    if(username.equals("")) {
        out.println("<script>window.location.href='./accessDenied.jsp';</script>");
    }
%>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>

<form id="frm_updateOrder" action="servletOrdersUpdate" method="POST">
    <input type="hidden" name="operation" id="operation" value="Update" />
    <input type="hidden" name="orderNumber" id="orderNumber" value="<%=orders.getOrderNumber()%>" />
    <input type="hidden" name="shippedDate" id="shippedDate" value="<%=df.format(orders.getShippedDate())%>" />
    <input type="hidden" name="status" id="status" value="<%=orders.getStatus()%>" />
    <input type="hidden" name="comments" id="comments" value="<%=orders.getComments()%>" />
</form>

<h1>Order</h1>
        
<div class="row">
    <div class="col-xs-12" style="padding: 0px 0px 25px 15px;">
        <button onclick="updateOrder()" class="btn btn-success">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
        <a href="Order" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove"></span></a>
    </div>
</div>

<div class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2">Order Number: </label>
        <div class="col-sm-3">
            <input type="text" readonly value="<%=orders.getOrderNumber()%>" class="form-control" />
        </div>

        <label class="control-label col-sm-2">Customer Number: </label>
        <div class="col-sm-3">
            <input type="text" readonly value="<%=orders.getCustomerNumber().getCustomerNumber()%>" class="form-control" />
        </div>
    </div>
        
    <div class="form-group">
        <label class="control-label col-sm-2">Order Date: </label>
        <div class="col-sm-3">
            <input type="text" readonly value="<%=df.format(orders.getOrderDate())%>" class="form-control" />
        </div>

        <label class="control-label col-sm-2">Required Date: </label>
        <div class="col-sm-3">
            <input type="text" readonly value="<%=df.format(orders.getRequiredDate())%>" class="form-control" />
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Shipped Date: </label>
        <div class="col-sm-3">
            <input id="inputShippedDate" type="text" value="<%
                if(orders.getShippedDate() == null) {
                    out.println(" - ");
                }
                else {
                    out.println(df.format(orders.getShippedDate()));
                }%>
                " class="form-control datepicker" />
        </div>
                
        <label for="optionStatus" class="control-label col-sm-2">Status: </label>
        <div class="col-sm-3">
            <select class="form-control" id="optionStatus">
                <option value="Shipped"
                    <%if(orders.getStatus().equals("Shipped")) {
                        out.print("selected");
                    }%>
                    >Shipped</option>
                <option value="Resolved"
                    <%if(orders.getStatus().equals("Resolved")) {
                        out.print("selected");
                    }%>
                    >Resolved</option>
                <option value="Cancelled"
                    <%if(orders.getStatus().equals("Cancelled")) {
                        out.print("selected");
                    }%>
                    >Cancelled</option>
                <option value="On Hold"
                    <%if(orders.getStatus().equals("On Hold")) {
                        out.print("selected");
                    }%>
                    >On Hold</option>
                <option value="Disputed"
                    <%if(orders.getStatus().equals("Disputed")) {
                        out.print("selected");
                    }%>
                    >Disputed</option>
                <option value="In Process"
                    <%if(orders.getStatus().equals("In Process")) {
                        out.print("selected");
                    }%>
                    >In Process</option>
            </select>
        </div>
    </div>
</div>
                    
<h3>Items:</h3>

<table class="table table-hover table-bordered table-striped">
    <thead>
        <tr valign="middle">
            <th class="text-center" width="50">No.</th>
            <th class="text-center" width="150">Product Code</th>
            <th class="text-center">Product Name</th>
            <th class="text-center" width="70">Qty</th>
            <th class="text-center" width="100">Price</th>
            <th class="text-center" width="120">Amount</th>
        </tr>
    </thead>
    <tbody>
        <%
            Iterator i = orders.getOrderdetailList().iterator();
            int index = 1;
            int totalQty = 0;
            BigDecimal totalAmt = BigDecimal.ZERO;
            
            while(i.hasNext()) {
                Orderdetail orderdetail = (Orderdetail)i.next();
                Product product = orderdetail.getProduct();
                BigDecimal subAmt = BigDecimal.valueOf(orderdetail.getQuantityOrdered()).multiply(orderdetail.getPriceEach());
                
                out.println("<tr>");
                out.println("<td>" + index + ".</td>");
                out.println("<td class=\"text-center\">" + product.getProductCode() + "</td>");
                out.println("<td>" + product.getProductName() + "</td>");
                out.println("<td class=\"text-right\">" + orderdetail.getQuantityOrdered() + "</td>");
                out.println("<td class=\"text-right\">RM" + orderdetail.getPriceEach() + "</td>");
                out.println("<td class=\"text-right\">RM" + subAmt + "</td>");
                out.println("</tr>");
                
                index++;
                totalQty += orderdetail.getQuantityOrdered();
                totalAmt = totalAmt.add(subAmt);
            }
        %>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="3" class="text-right">total Qty:</td>
            <td class="text-right"><%=totalQty%></td>
            <td class="text-right">total Amt:</td>
            <td class="text-right">RM<%=totalAmt%></td>
        </tr>
    </tfoot>
</table>

<div class="form-group">
    <label>Comments:</label>
    <textarea id="inputComments" class="form-control" rows="5">
        <%
            if(orders.getComments() == null) {
                out.println("");
            }
            else {
                out.println(orders.getComments());
            }
        %>
    </textarea>
</div>
    
<div id="dialog-confirm" title="Save Comfirmation" style="display:none;">
    <p><span class="ui-icon ui-icon-alert"></span>Are you sure you want to make changes to this item?</p>
</div>
<script>
    $(function() {
        $(".datepicker").datepicker({ dateFormat: 'dd/mm/yy' });
        
        $("#inputShippedDate").change(event => {
            $("#shippedDate").val($("#inputShippedDate").val());
        });
        $("#optionStatus").change(event => {
            $("#status").val($("#optionStatus").val());
        });
        $("#inputComments").keyup(event => {
            $("#comments").val($("#inputComments").val());
        });
        
    })
    
    function updateOrder() {
        $( "#dialog-confirm" ).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
              "Yes": function() {
                $("#frm_updateOrder").submit();
              },
              Cancel: function() {
                $( this ).dialog( "close" );
              }
            }
        });
    }
</script>