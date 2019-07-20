<%-- 
    Document   : mtOrder
    Created on : Jul 5, 2019, 9:14:27 PM
    Author     : JK
--%>

<%@page import="utilities.Util"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="utilities.Util" %>
<%@page language="java" import="entity.Orders" %>
<%@page language="java" import="java.util.*" %>
<%@page language="java" import="java.text.SimpleDateFormat" %>
<%
    List<Orders> listOrders = null;
    if(request.getAttribute("listOrders") != null) {
        listOrders = (List<Orders>)request.getAttribute("listOrders");
    }
%>
<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<h1>Order List</h1>

<table id="table_orders" class="table table-striped table-hover table-bordered table-sm">
    <thead>
        <tr>
            <th class="text-center" width="50">No.</th>
            <th class="text-center" width="100">Order Number</th>
            <th class="text-center">Order Date</th>
            <th class="text-center">Required Date</th>
            <th class="text-center">Shipped Date</th>
            <th class="text-center" width="120">Status</th>
            <th class="text-center">Customer Number</th>
            <th class="text-center" width="50">Open</th>
        </tr>
    </thead>
    <tbody>
    <%
        int index = 1;
        Iterator i = listOrders.iterator();

        while(i.hasNext()) {
            Orders orders = (Orders)i.next();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
            Util util = new Util();

            out.println("<tr height='40' valign='middle'>");
                out.println("<td align='center'>" + index + ".</td>");
                out.println("<td align='center'>" + orders.getOrderNumber() + "</td>");
                out.println("<td align='center'>" + df.format(orders.getOrderDate()) + "</td>");
                out.println("<td align='center'>" + df.format(orders.getRequiredDate()) + "</td>");
                if(orders.getShippedDate() == null) {
                    out.println("<td align='center'> - </td>");
                }
                else {
                    out.println("<td align='center'>" + df.format(orders.getShippedDate()) + "</td>");
                }

                out.println("<td align='center'><span class='label " + util.getStatusClass(orders.getStatus()) + "'>" + orders.getStatus().toUpperCase() + "</span></td>");
                out.println("<td align='center'>" + orders.getCustomerNumber().getCustomerNumber() + "</td>");
                out.println("<td align='center' onclick='window.location.href=\"Order?orderNumber=" + orders.getOrderNumber() + "\";' class='pointer'><span class=\"glyphicon glyphicon-folder-open\"></span></td>");
            out.println("</tr>");

            index++;
        }
    %>
    </tbody>
</table>
<script type="text/javascript" src="js/datatables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#table_orders').DataTable();
    });
</script>