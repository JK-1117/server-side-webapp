<%-- 
    Document   : product
    Created on : Jun 30, 2019, 2:30:47 AM
    Author     : JK
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>CONTACT</h1>

<section class="row">
    <c:forEach items="${requestScope.listOffice}" var="item">   
        <div class="col-sm-4 col-xs-12 card">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <h3>${item.city}</h3>
                </div>
                <div class="panel-body">
                    <p>${item.addressLine1}</p>
                    <c:if test="${empty item.addressLine2}">
                        <p>${item.addressLine2}</p>
                    </c:if>
                    <p>${item.postalCode} ${item.city}</p>
                    <p>${item.territory}, ${item.country}</p>
                </div>
                <div class="panel-footer">
                    <p>${item.phone}</p>
                </div>
            </div>      
        </div>  
    </c:forEach>
</section>

<div class="row">
    <img src="assets/map.jpg" width="100%" />
</div>
