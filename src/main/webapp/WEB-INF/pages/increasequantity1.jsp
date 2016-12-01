<%--
  Created by IntelliJ IDEA.
  User: pgenev
  Date: 28/11/2016
  Time: 08:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page errorPage="404.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <style>
        #fruitsSelect {
            display: none;
        }

        #vegetablesSelect {
            display: none;
        }
    </style>
</head>
<body>
<div class="main">





        <form:form method="POST" id="addproducts" action="increasequantity" commandName="product">

            Category : <form:select path="productType" id="products">
            <form:option disabled="true" selected="true" value="">Choose product</form:option>
            <form:option value="vegetable">Vegetables</form:option>
            <form:option value="fruit">Fruits</form:option>

        </form:select>


            <form:select path="id" id="fruitsSelect">
                <form:option disabled="true" selected="true" value="">Choose fruit</form:option>
                <c:forEach var="fruit" items="${fruits}">
                    <form:option value="${fruit.id}"> ${fruit.name} </form:option>
                </c:forEach>
            </form:select>


            <form:select path="id" id="vegetablesSelect">
                <form:option disabled="true" selected="true" value="">Choose vegetable</form:option>
                <c:forEach var="vegetable" items="${vegetables}">
                    <form:option value="${vegetable.id}"> ${vegetable.name} </form:option>
                </c:forEach>
            </form:select>

            <br><br>

            Increase quantity by :
            <form:input path="amountKilo" name="amouneKilo"/> kgs <br>
            <form:input path="amountPiece" name="amountPiece"/> pieces
            <br>
            <input type="submit" value="Submit"/>

        </form:form>

</div>
<script type="text/javascript" src="js/main.js"></script>

</body>

</html>
