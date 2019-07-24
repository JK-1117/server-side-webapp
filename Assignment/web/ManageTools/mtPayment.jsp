<%-- 
    Document   : mtPayment
    Created on : Jul 24, 2019, 9:07:54 PM
    Author     : User
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Date"%>
<%@page import="entity.Payment"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.UserRole" %>
<%@page language="java" import="sessionBean.sessionbeanUserRole" %>
<!DOCTYPE html>
<%

    String username = "";
    String role = "";
    sessionbeanUserRole sessionbeanUserRole = null;
    List<Payment> listPayment = null;

    if (session.getAttribute("username") != null) {
        username = (String) session.getAttribute("username");
    }

    if (session.getAttribute("role") != null) {
        role = (String) session.getAttribute("role");
    }
    if (request.getAttribute("sessionbeanUserRole") != null) {
        sessionbeanUserRole = (sessionbeanUserRole) request.getAttribute("sessionbeanUserRole");
    }
    if (request.getAttribute("listPayment") != null) {
        listPayment = (List<Payment>) request.getAttribute("listPayment");
    }

%>
<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>

<div>
    <h1 style="display: inline-block">Payment</h1>
</div>
<%if (role.equals("admin") || role.equals("staff")) { %>
<div class="row">
    <div class="col-md-12">
        <table id="table_user" class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th width="50">Customer Number</th>
                    <th width="150">Check Number</th>
                    <th width="150">Payment Date</th>
                    <th width="100">Amount</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Iterator i = listPayment.iterator();
                    int index = 1;

                    while (i.hasNext()) {
                        Payment py = (Payment) i.next();
                        int currcn = py.getPaymentPK().getCustomerNumber();
                        String checkno = py.getPaymentPK().getCheckNumber();
                        Date dt = py.getPaymentDate();
//                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
//                        String strDate = dateFormat.format(dt); 
//                        Date dt2= new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
//                        DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(dt);
                        BigDecimal am = py.getAmount();
                        if (currcn != 0) {
                            out.println("<tr><td class=\"text-center\">" + currcn + ".</td>");
                            out.println("<td>" + checkno + "</td>");
                            out.println("<td>" + dt + "</td>");
                            out.println("<td>" + am + "</td>");
                            index++;
                        }
                    }
                %>
            </tbody>
        </table>
    </div>

    <% }%>


    <script type="text/javascript" src="js/datatables.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#table_user').DataTable();

            $(".error").hide();

            $("input").on("keyup", function () {
                $(".error").hide();
            });
        });

        function editCustomer(custno, fname, lname, companyname, phone, addr1, addr2, city, state, poscode, country, salesrepno, crdlimit) {
            $("#custno").attr("readonly", true);
            $("#custno").val(custno);
            $("#contfname").val(fname);
            $("#contlname").val(lname);
            $("#companyname").val(companyname);
            $("#phone").val(phone);
            $("#addr1").val(addr1);
            $("#addr2").val(addr2);
            $("#city").val(city);
            $("#state").val(state);
            $("#poscode").val(poscode);
            $("#country").val(country);
            $("#salesrepno").val(salesrepno);
            $("#crdlimit").val(crdlimit);
        }
        function deleteCustomer(custno) {
            $("#custno").attr("readonly", true);
            $("#custno").val(custno);
        }
    </script>
