<%-- 
    Document   : product
    Created on : Jul 4, 2019, 1:06:36 PM
    Author     : JK
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page language="java" import="entity.Product" %>
<%@page language="java" import="entity.ProductLine" %>
<%@page language="java" import="java.util.*" %>
<%
    String txtSearch = "";
    String productLine = "";
    String sortBy = "";
    List<Product> listProducts = null;
    List<ProductLine> listProductLine = null;
    
    if(request.getAttribute("txtSearch") != null) {
        txtSearch = (String)request.getAttribute("txtSearch");
    }
    if(request.getAttribute("productLine") != null) {
        productLine = (String)request.getAttribute("productLine");
    }
    if(request.getAttribute("sortBy") != null) {
        sortBy = (String)request.getAttribute("sortBy");
    }
    if(request.getAttribute("listProducts") != null) {
        listProducts = (List<Product>)request.getAttribute("listProducts");
    }
    if (request.getAttribute("listProductLine") != null) {
        listProductLine = (List<ProductLine>) request.getAttribute("listProductLine");
    }
%>
<h3><%=productLine.toUpperCase()%> > PRODUCT</h3>

<form action="Product" method="GET">
    <div class="input-group">
        <input id="txtSearch" name="txtSearch" type="text" class="form-control" placeholder="Search" value="<%=txtSearch%>">
        <div class="input-group-btn">
          <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-search"></span> Search
          </button>
        </div>
    </div>
</form>

<section class="row">
    <div class="col-sm-12">
        <h4>Product Line</h4>
    </div>
    <div class="col-sm-12 container">
        <div class="row" id="productLine">
            <%
                Iterator k = listProductLine.iterator();
                while (k.hasNext()) {
                    ProductLine proLine = (ProductLine) k.next();
                    out.println("<a href=\"Product?productLine=" + proLine.getProductLine() + "\" class=\"btn btn-default col-sm-3 col-xs-4\">");
                    out.println(proLine.getProductLine());
                    out.println("</a>");
                }
            %>
        </div>
    </div>
</section>
        
<div class="row">
    <div class="col-lg-9 col-xs-8">
        <small>Showing 10 results</small>
    </div>
    <div class="col-lg-3 col-xs-4 form-group form-inline">
        <label class="text-right">Sort by&nbsp;</label>
        <select id="sortBy" class="form-control">
            <option value="PriceLH"
                    <%if(sortBy.equals("PriceLH")) out.println("selected");%>
            >Price: Low to High</option>
            <option value="PriceHL"
                    <%if(sortBy.equals("PriceHL")) out.println("selected");%>
            >Price: High to Low</option>
        </select>
    </div>
</div>
        
<div class="tab-content">
    <div id="page1" class="tab-pane list-group fade in active" >
        <%
            int index = 0;
            int productPage = 1;
        //TODO: search
            Iterator i = listProducts.iterator();
            while (i.hasNext()) {
                Product pro = (Product) i.next();
                out.println("<a href=\"Product?productCode=" + pro.getProductCode() + "\" class=\"list-group-item\" >");
                out.println("<h4 class=\"list-group-item-heading\" >");
                out.println(pro.getProductName());
                out.println("<span class=\"label label-info\">RM" + pro.getMsrp() + "</span>");
                out.println("</h4><p class=\"list-group-item-text\" >");
                out.println(pro.getProductDescription());
                out.println("</p></a>");
                index++;
                if (index == productPage * 10) {
                    out.println("</div>");
                    if (i.hasNext()) {
                        productPage++;
                        out.println("<div id=\"page" + productPage + "\" class=\"tab-pane list-group fade\" >");
                    }
                }
            }
            if (index % 10 > 0) {
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<ul class=\"pagination pull-right\">");
            out.println("<li class=\"active\"><a onclick=\"changePage(this)\" href=\"#\" >1</a></li>");
            for (int j = 2; j <= productPage; j++) {
                out.println("<li><a onclick=\"changePage(this)\" href=\"#\" >" + j + "</a></li>");
            }
            out.println("</ul>");
        %>
<script type="text/javascript">
    $(function() {      
        $("#sortBy").change(event => {
            var str = "Product?sortBy=" + $("#sortBy").val();
            <%
                if(!productLine.equals("")) {
                    out.println("str += '&productLine=" + productLine + "';");
                }
            %>
            window.location.href = str;
        });
    })
function changePage(btn) {
    $(".pagination > li").removeClass("active");
    $(btn).parent().addClass("active");
    $("[id^=page]").removeClass("in active");
    $("#page" + $(btn).text()).addClass("in active");
}
</script>