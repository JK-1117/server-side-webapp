<%-- 
    Document   : customer
    Created on : Jul 17, 2019, 9:34:33 PM
    Author     : User
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entity.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Customer" %>
<%@page language="java" import="entity.UserRole" %>
<%@page language="java" import="sessionBean.sessionbeanUserRole" %>
<%@page language="java" import="sessionBean.sessionbeanCustomer" %>
<%@page language="java" import="java.util.*" %>
<%
    String customername = "";
    int custno = 0;
    String username = "";
    String role = "";
    sessionbeanUserRole sessionbeanUserRole = null;
    List<Customer> listCustomer = null;

    if (session.getAttribute("username") != null) {
        username = (String) session.getAttribute("username");
    }
    if (session.getAttribute("customername") != null) {
        customername = (String) session.getAttribute("customername");
    }
    if (session.getAttribute("role") != null) {
        role = (String) session.getAttribute("role");
    }
    if (request.getAttribute("sessionbeanUserRole") != null) {
        sessionbeanUserRole = (sessionbeanUserRole) request.getAttribute("sessionbeanUserRole");
    }
    if (request.getAttribute("listCustomer") != null) {
        listCustomer = (List<Customer>) request.getAttribute("listCustomer");
    }
%>
<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>

<div>
    <h1 style="display: inline-block">Customer</h1>
    <button type="submit" class="btn btn-primary btn-lg btn-block login-btn" data-toggle='modal' data-target='#deleteModal' style="float:right;width: 150px;height: 50px;margin-top: 10px">Delete</button>
    <button type="submit" class="btn btn-primary btn-lg btn-block login-btn" data-toggle='modal' data-target='#createModal' style="float:right;width: 150px;height: 50px;margin-top: 10px;margin-right: 5px">Create</button>
</div>
<%if (role.equals("admin")||role.equals("staff")) { %>
<div class="row">
    <div class="col-md-12">
        <table id="table_user" class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th width="50">No.</th>
                    <th width="150">Company Name</th>
                    <th width="150">Customer Name</th>
                    <th width="100">Phone</th>
                    <th width="100">Address</th>
                    <th width="100">City</th>
                    <th width="100">State</th>
                    <th width="100">Post Code</th>
                    <th width="100">Country</th>
                    <th width="100">Sales Rep No</th>
                    <th width="100">Credit Limit</th>
                    <th width="30">Edit</th>
                    <!--<th width="30">Delete</th>-->
                </tr>
            </thead>
            <tbody>
                <%
                    Iterator i = listCustomer.iterator();
                    int index = 1;

                    while (i.hasNext()) {
                        Customer customer = (Customer) i.next();
                        int currcustno = customer.getCustomerNumber();
                        String currUser = customer.getContactFirstName() + " " + customer.getContactLastName();
                        String fname = customer.getContactFirstName();
                        String lname = customer.getContactLastName();
                        String currcomname = customer.getCustomerName();
                        String currphone = customer.getPhone();
                        String curraddr = customer.getAddressLine1() + " " + customer.getAddressLine2();
                        String addr1 = customer.getAddressLine1();
                        String addr2 = customer.getAddressLine2();
                        String currcity = customer.getCity();
                        String currstate = customer.getState();
                        String currposcode = customer.getPostalCode();
                        String currcontry = customer.getCountry();
                        Employee currsalesrepno = customer.getSalesRepEmployeeNumber();
                        BigDecimal currcrdlimit = customer.getCreditLimit();
                        if (!currUser.equals("")) {
                            out.println("<tr><td class=\"text-center\">" + currcustno + ".</td>");
                            out.println("<td>" + currcomname + "</td>");
                            out.println("<td>" + currUser + "</td>");
                            out.println("<td>" + currphone + "</td>");
                            out.println("<td>" + curraddr + "</td>");
                            out.println("<td>" + currcity + "</td>");
                            out.println("<td>" + currstate + "</td>");
                            out.println("<td>" + currposcode + "</td>");
                            out.println("<td>" + currcontry + "</td>");
                            if (currsalesrepno != null) {
                                out.println("<td>" + currsalesrepno.getEmployeeNumber() + "</td>");
                            } else {
                                out.println("<td> - </td>");
                            }
                            out.println("<td>" + currcrdlimit + "</td>");
                            out.println("<td class='pointer text-center' data-toggle='modal' data-target='#updateModal'onclick=\"editCustomer('" + currcustno + "','" + fname + "','" + lname + "','" + currcomname + "','" + currphone + "','" + addr1 + "','" + addr2 + "','" + currcity + "','" + currstate + "','" + currposcode + "','" + currcontry + "','" + currsalesrepno + "','" + currcrdlimit + "')\" ><span class=\"glyphicon glyphicon-pencil\"></span></td>");
//                            out.println("<td class='pointer text-center' data-toggle='modal' data-target='#deleteModal'onclick=\"deleteCustomer('" + currcustno + "')\" ><span class=\"glyphicon glyphicon-trash\"></span></td></tr>");
                            index++;
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    <div id="createModal" class="modal fade in" role="dialog">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="avatar">
                        <img src="assets/avatar.png" alt="Avatar">
                    </div>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Customer Create</h4>
                </div>
                <div class="modal-body">
                    <form id="frm_login" action="servletCreateCustomer" method="post" autocomplete="on">
                        <div class="form-group">
                            <input type="text" class="form-control" id="custno" name="custno" placeholder="Customer Number" required="required" autofocus="autofocus">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="companyname" name="companyname" placeholder="Company Name" required="required" >
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="contfname"  name="contfname" placeholder="First Name" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="contlname" name="contlname" placeholder="Last Name" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="phone"  name="phone" placeholder="Phone" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addr1" name="addr1" placeholder="Address Line1" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addr2"  name="addr2" placeholder="Address Line 2" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="city" name="city" placeholder="City" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="state" name="state" placeholder="State" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="poscode"  name="poscode" placeholder="Pos Code" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="country" name="country" placeholder="Country" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="salesrepno" name="salesrepno" placeholder="Sales Rep Number" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="crdlimit" name="crdlimit" placeholder="Credit Limit" required="required">	
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg btn-block login-btn">Add</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
    <div id="updateModal" class="modal fade in" role="dialog">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="avatar">
                        <img src="assets/avatar.png" alt="Avatar">
                    </div>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Customer Update</h4>
                </div>
                <div class="modal-body">
                    <form id="frm_login" action="servletUpdateCustomer" method="post" autocomplete="on">
                        <div class="form-group">
                            <input type="text" class="form-control" id="custno" name="custno" placeholder="Customer Number" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="companyname" name="companyname" placeholder="Company Name" required="required" autofocus="autofocus">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="contfname"  name="contfname" placeholder="First Name" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="contlname" name="contlname" placeholder="Last Name" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="phone"  name="phone" placeholder="Phone" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addr1" name="addr1" placeholder="Address Line1" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addr2"  name="addr2" placeholder="Address Line 2" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="city" name="city" placeholder="City" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="state" name="state" placeholder="State" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="poscode"  name="poscode" placeholder="Pos Code" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="country" name="country" placeholder="Country" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="salesrepno" name="salesrepno" placeholder="Sales Rep Number" required="required">	
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="crdlimit" name="crdlimit" placeholder="Credit Limit" required="required">	
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg btn-block login-btn">Save</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
    <div id="deleteModal" class="modal fade in" role="dialog" >
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="avatar">
                        <img src="./assets/avatar.png" alt="Avatar">
                    </div>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Customer Delete</h4>
                </div>
                <form id="frm_login" action="servletDeleteCustomer" method="post">
                    <div class="form-group">
                        <lable>Please Insert the Customer No:<input type="text" id="custno" name="custno" value="" /></lable>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-btn" style="width: 80px;display: inline-block;margin-top: 5px">Yes</button>
                        <button type="reset" class="btn btn-primary btn-lg btn-block login-btn" style="width: 80px;display: inline-block;">No</button>
                    </div>
                </form>
            </div>
        </div>
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