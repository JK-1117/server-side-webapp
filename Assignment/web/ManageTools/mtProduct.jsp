<%-- 
    Document   : mtProduct
    Created on : Jul 5, 2019, 9:11:13 PM
    Author     : JK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Product" %>
<%@page language="java" import="entity.ProductLine" %>
<%@page language="java" import="java.util.*" %>
<%
    String username = "";
    String role = "";
    
    if(session.getAttribute("username") != null) {
        username = (String)session.getAttribute("username");
    }
    if(session.getAttribute("role") != null) {
        role = (String)session.getAttribute("role");
    }
    
    String readonly = "readonly";
    if(role.equals("admin")) {
        readonly = "";
    }
    
    String operation = "";
    Product product = null;
    List<ProductLine> listProductLine = null;
    if(request.getAttribute("operation") != null) {
        operation = (String)request.getAttribute("operation");
    }
    if(request.getAttribute("product") != null) {
        product = (Product)request.getAttribute("product");
    }
    if(request.getAttribute("listProductLine") != null) {
        listProductLine = (List<ProductLine>)request.getAttribute("listProductLine");
    }
%>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-ui.min.js" ></script>

<form id="frm_updateProduct" action="servletProductUpdate" method="POST">
    <input type="hidden" name="operation" id="operation" value="<%=operation%>" />
    <input type="hidden" name="productCode" id="productCode" value="<%=product.getProductCode()%>" />
    <input type="hidden" name="productName" id="productName" value="<%=product.getProductName()%>" />
    <input type="hidden" name="productLine" id="productLine" value="<%=product.getProductLine().getProductLine()%>" />
    <input type="hidden" name="productScale" id="productScale" value="<%=product.getProductScale()%>" />
    <input type="hidden" name="productVendor" id="productVendor" value="<%=product.getProductVendor()%>" />
    <input type="hidden" name="quantityInStock" id="quantityInStock" value="<%=product.getQuantityInStock()%>" />
    <input type="hidden" name="buyPrice" id="buyPrice" value="<%=product.getBuyPrice()%>" />
    <input type="hidden" name="MSRP" id="MSRP" value="<%=product.getMsrp()%>" />
    <input type="hidden" name="productDescription" id="productDescription" value="<%=product.getProductDescription()%>" />
</form>

<h1>Product</h1>
        
<% if(role.equals("admin")) {%>
    <div class="row">
        <div class="col-xs-12" style="padding: 0px 0px 25px 15px;">
            <button onclick="updateProduct()" class="btn btn-success">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
            <% 
                if(operation.equals("Update")) {
                    out.println("<button onclick=\"deleteProduct()\" class=\"btn btn-danger\">Delete <span class=\"glyphicon glyphicon-remove\"></span></button>");
                }
                else if(operation.equals("New")) {
                    out.println("<a href=\"Product\" class=\"btn btn-danger\">Cancel <span class=\"glyphicon glyphicon-remove\"></span></a>");
                }
            %>
        </div>
    </div>
<% } %>

<div class="form-horizontal">
    <div class="form-group">
        <label for="textProductCode" class="control-label col-sm-2">Product Code: </label>
        <div class="col-sm-4">
            <input value="<%=product.getProductCode()%>" type="text" class="form-control" id="textProductCode" required="required"
                   <%
                       if(!operation.equals("New")) {
                           out.println("readonly");
                       }
                   %>
                   />
        </div>
        <% 
            if(readonly.equals("readonly")) {
                out.println("<label for=\"textProductLine\" class=\"control-label col-sm-2\">Product Line: </label>");
                out.println("<div class=\"col-sm-4\">");
                out.println("<input value=\"" + product.getProductLine().getProductLine() + 
                        "\" type=\"text\" class=\"form-control\" id=\"textProductLine\" readonly />");
                out.println("</div>");
            }
            else if(readonly.equals("")) {
                out.println("<label for=\"textProductLine\" class=\"control-label col-sm-2\">Product Line: </label>");
                out.println("<div class=\"col-sm-4\">");
                out.println("<select class=\"form-control\" id=\"textProductLine\">");

                for(ProductLine productLine : listProductLine) {
                    out.println("<option value=\"" + productLine.getProductLine() + "\"");
                    if(productLine.equals(product.getProductLine())) {
                        out.println(" selected ");
                    }
                    out.println(">" + productLine.getProductLine() + "</option>");
                }

                out.println("</select></div>");
            }
        %>
    </div>

    <div class="form-group">
        <label for="textProductName" class="control-label col-sm-2">Product Name: </label>
        <div class="col-sm-4">
            <input value="<%=product.getProductName()%>" type="text" class="form-control" id="textProductName" <%=readonly%> required="required" />
        </div>

    </div>

    <div class="form-group">
        <label for="textProductScale" class="control-label col-sm-2">Product Scale: </label>
        <div class="col-sm-4">
            <input value="<%=product.getProductScale()%>" type="text" class="form-control" id="textProductScale" <%=readonly%> required="required" />
        </div>

    </div>

    <div class="form-group">
        <label for="textProductVendor" class="control-label col-sm-2">Product Vendor: </label>
        <div class="col-sm-4">
            <input value="<%=product.getProductVendor()%>" type="text" class="form-control" id="textProductVendor" <%=readonly%> required="required" />
        </div>

    </div>

    <div class="form-group">
        <label for="textQuantityInStock" class="control-label col-sm-2">Qty: </label>
        <div class="col-sm-2">
            <input value="<%=product.getQuantityInStock()%>" type="text" class="form-control" id="textQuantityInStock" <%=readonly%> required="required" />
        </div>
        
        <label for="textBuyPrice" class="control-label col-sm-2">Buy Price: </label>
        <div class="col-sm-2">
            <input value="<%=product.getBuyPrice()%>" type="text" class="form-control" id="textBuyPrice" <%=readonly%> required="required" />
        </div>
        
        <label for="textMSRP" class="control-label col-sm-2">MSRP:  </label>
        <div class="col-sm-2">
            <input value="<%=product.getMsrp()%>" type="text" class="form-control" id="textMSRP" <%=readonly%> required="required" />
        </div>
    </div>

    <div class="form-group">
        <label for="textDescription" class="control-label col-sm-2">Description: </label>
        <div class="col-sm-10">
            <textarea id="textDescription" class="form-control" <%=readonly%> rows="5" required="required" ><%=product.getProductDescription()%></textarea>
        </div>
    </div>
</div>
    
<div id="dialog-confirm-update" title="Save Comfirmation" style="display:none;">
    <p><span class="ui-icon ui-icon-alert"></span>Are you sure you want to make changes to this item?</p>
</div>
    
<div id="dialog-confirm-delete" title="Delete Comfirmation" style="display:none;">
    <p><span class="ui-icon ui-icon-alert"></span>This item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<script>
    $(function() {
        $("#textProductCode").change(event => {
            $("#productCode").val($("#textProductCode").val());
        });
        $("#textProductLine").change(event => {
            $("#productLine").val($("#textProductLine").val());
        });
        $("#textProductName").change(event => {
            $("#productName").val($("#textProductName").val());
        });
        $("#textProductScale").change(event => {
            $("#productScale").val($("#textProductScale").val());
        });
        $("#textProductVendor").change(event => {
            $("#productVendor").val($("#textProductVendor").val());
        });
        $("#textQuantityInStock").change(event => {
            $("#quantityInStock").val($("#textQuantityInStock").val());
        });
        $("#textBuyPrice").change(event => {
            $("#buyPrice").val($("#textBuyPrice").val());
        });
        $("#textMSRP").change(event => {
            $("#MSRP").val($("#textMSRP").val());
        });
        $("#textDescription").keyup(event => {
            $("#productDescription").val($("#textDescription").val());
        });
        
    })
    
    function updateProduct() {
        $( "#dialog-confirm-update" ).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
              "Yes": function() {
                $("#frm_updateProduct").submit();
              },
              Cancel: function() {
                $( this ).dialog( "close" );
              }
            }
        });
    }
    
    function deleteProduct() {
        $("#operation").val("Delete");
        $( "#dialog-confirm-delete" ).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
              "Yes": function() {
                $("#frm_updateProduct").submit();
              },
              Cancel: function() {
                $( this ).dialog( "close" );
              }
            }
        });
    }
</script>