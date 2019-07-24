<%-- 
    Document   : mtUser
    Created on : Jul 9, 2019, 9:29:06 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.User" %>
<%@page language="java" import="entity.UserRole" %>
<%@page language="java" import="sessionBean.sessionbeanUserRole" %>
<%@page language="java" import="java.util.*" %>
<%
    String username = "";
    String role = "";
    String errorUser = "";
    sessionbeanUserRole sessionbeanUserRole = null;
    List<User> listUser = null;
    
    if(session.getAttribute("username") != null) {
        username = (String)session.getAttribute("username");
    }
    if(session.getAttribute("role") != null) {
        role = (String)session.getAttribute("role");
    }
    if(request.getAttribute("sessionbeanUserRole") != null) {
        sessionbeanUserRole = (sessionbeanUserRole)request.getAttribute("sessionbeanUserRole");
    }
    if(request.getAttribute("listUser") != null) {
        listUser = (List<User>)request.getAttribute("listUser");
    }
    if(request.getAttribute("errorUser") != null) {
        errorUser = (String)request.getAttribute("errorUser");
    }
    
    if(username.equals("")) {
        out.println("<script>window.location.href='./accessDenied.jsp';</script>");
    }
%>
<link rel="stylesheet" type="text/css" href="css/datatables.min.css"/>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>
<h1>User</h1>

<% if(role.equals("staff")) { %>
<form id="frm_updateUser" action="servletUserUpdate" method="POST">
    <input type="hidden" name="operation" id="operation" value="ChangePassword" />
    <div class="form-group">
        <label for="textPassword">New Password: </label>
        <input type="text" class="form-control" placeholder="password" id="textPassword" name="textPassword">
        <small id="passwordError" class="error">*password length must within 1 to 15</small>
    </div>
    <div class="form-group">
        <label for="textPassword2">Re-enter Password: </label>
        <input type="text" class="form-control" placeholder="re-enter password" id="textPassword2" name="textPassword2">
        <small id="password2Error" class="error">*please enter the same password</small>
    </div>
    <div class="form-group">
        <button type="button" onclick="changePassword()" class="btn btn-success">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
        <button type="reset" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove"></span></button>
    </div>
</form>

<% } else if(role.equals("admin")) { %>
<div class="row">
    <div class="col-md-6">
        <table id="table_user" class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th width="50">No.</th>
                    <th width="150">Username</th>
                    <th width="150">Password</th>
                    <th width="100">User Role</th>
                    <th width="50">Edit</th>
                    <th width="50">Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Iterator i = listUser.iterator();
                    int index = 1;
                    
                    while(i.hasNext()) {
                        User user = (User)i.next();
                        String currUser = user.getUsername();
                        String password = user.getPassword();
                        String userRole = sessionbeanUserRole.getUserRole(user.getUsername());
                        if(!userRole.equals("")) {
                            out.println("<tr><td class=\"text-center\">" + index + ".</td>");
                            out.println("<td>" + currUser + "</td>");
                            out.println("<td>" + password + "</td>");
                            out.println("<td>" + userRole + "</td>");
                            out.println("<td class='pointer text-center' onclick=\"editUser('" + currUser + "','" + password + "','" + userRole + "')\"><span class=\"glyphicon glyphicon-pencil\"></span></td>");
                            out.println("<td class='pointer text-center' onclick=\"deleteUser('" + currUser + "','" + password + "','" + userRole + "')\"><span class=\"glyphicon glyphicon-trash\"></span></td></tr>");
                            index++;
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    <div class="col-md-6">
        <form id="frm_updateUser" action="servletUserUpdate" method="POST">
            <input type="hidden" name="operation" id="operation" value="New" />
            <div class="form-group">
                <label for="textUsername">Username: </label>
                <input type="text" class="form-control" placeholder="username" id="textUsername" name="textUsername">
                <small id="usernameError" class="error">*username length must within 1 to 15</small>
            </div>
            <div class="form-group">
                <label for="textPassword">Password: </label>
                <input type="text" class="form-control" placeholder="password" id="textPassword" name="textPassword">
                <small id="passwordError" class="error">*password length must within 1 to 15</small>
            </div>
            <div class="form-group">
                <label for="textUserRole">User Role: </label>
                <select id="textUserRole" name="textUserRole" class="form-control">
                    <option value="admin">admin</option>
                    <option value="staff">staff</option>
                    <option value="user">user</option>
                </select>
            </div>
            <%
                if(!errorUser.equals("")) {
                    out.println("<div id=\"errorUser\" class=\"alert alert-danger\"><strong>ERROR: </strong>" + errorUser + "</div>");
                }
            %>
            <div class="form-group">
                <button type="button" onclick="validation()" class="btn btn-success">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
                <button type="reset" onclick="newUser()" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove"></span></button>
            </div>
        </form>
    </div>
</div>
<% } %>
            
<div id="dialog-confirm" title="Delete Comfirmation" style="display:none;">
    <p><span class="ui-icon ui-icon-alert"></span>Please enter your password to confirm operation.</p>
    <form id="frm_login" action="" method="">
        <input type="hidden" id="username" name="username" value="<%=username%>" />
        <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">	
        </div>
        <div id="loginError" class="form-group"></div>
    </form>
</div>
            
<script type="text/javascript" src="js/datatables.min.js"></script>
<script src="js/mtUser.js"></script>
