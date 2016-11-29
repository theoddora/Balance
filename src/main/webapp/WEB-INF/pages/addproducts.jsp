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
    <script type="text/javascript" src="js/main.js"></script>


</head>
<body>
<div class="center">
    <p>Add product</p>
    <form:form method="POST" id="addproducts" action="addproducts" commandName="product">

        Category : <form:select path="productType" name="parent_selection" id="parent_selection">
        <option value="">-- Please Select --</option>
        <option value="vegetables">Vegetables</option>
        <option value="fruits">Fruits</option>
    </form:select>
        <form:select path="name" name="child_selection" id="child_selection">
        </form:select>

        <br><br>

        Quantity :
        <form:input path="amountKilo" name="amouneKilo" />
        <form:input path="amountPiece" name="amountPiece" />


        <br><br>

        Price kg/piece : <form:input path="price"/>

        <br><br>
        Discount % : <form:input path="discount"/>
        <br><br>
        <input type="submit" value="Submit" />

    </form:form>
    </div>

    </body>
    </html>
