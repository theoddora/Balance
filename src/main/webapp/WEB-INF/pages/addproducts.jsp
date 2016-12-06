<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!--%@-- page errorPage="404.jsp" --%>-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><s:message code="balance.title"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <style>
        #amountKilo {
            display: none;
        }

        #price {
            display: none;
        }



        #amountPiece {
            display: none;
        }
    </style>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/touchTouch.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/kwicks-slider.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
    <script src="js/jquery.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.flexslider-min.js"></script>
    <script src="js/jquery.kwicks-1.5.1.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="js/touchTouch.jquery.js"></script>
    <script>
        if ($(window).width() > 1024) {
            document.write("<" + "script src='js/jquery.preloader.js'></" + "script>");
        }
    </script>
    <script>
        jQuery(window).load(function () {
            $x = $(window).width();
            if ($x > 1024) {
                jQuery("#content .row").preloader();
            }
            jQuery('.magnifier').touchTouch();
            jQuery('.spinner').animate({
                'opacity': 0
            }, 1000, 'easeOutCubic', function () {
                jQuery(this).css('display', 'none')
            });
        });
    </script>
    <!--[if lt IE 8]>
    <div style='text-align:center'><a
            href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img
            src="http://www.theie6countdown.com/img/upgrade.jpg" border="0" alt=""/></a></div>
    <![endif]-->
    <!--[if (gt IE 9)|!(IE)]><!-->
    <!--<![endif]-->
    <!--[if lt IE 9]>
    <script src="js/html5.js"></script>
    <link rel="stylesheet" href="css/docs.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet' type='text/css'>
    <![endif]-->
</head>


<body>
<div class="spinner"></div>
<!-- header start -->
<header>
    <div class="container clearfix">
        <!-- NAV-BAR FORM -->
        <div class="row">
            <div class="span12">
                <div class="navbar navbar_">
                    <div class="container">
                        <h1 class="brand brand_"><a href="index"><img alt="" src="img/logo.png" width="350px"> </a></h1>
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse_"><s:message
                                code="balance.menu"/><span class="icon-bar"></span> </a>

                        <div class="nav-collapse nav-collapse_  collapse">
                            <ul class="nav sf-menu">
                                <li>
                                    <s:url value="/index" var="index"/>
                                    <a href="${index}"><s:message code="balance.home"/></a>
                                </li>
                                <li>
                                    <s:url value="/product" var="product"/>
                                    <a href="${product}"><s:message code="balance.product"/></a>
                                </li>
                                <li><a href="blog.html">Blog</a></li>
                                <c:choose>
                                    <c:when test="${!empty sessionScope.email}">
                                        <li class="sub-menu"><a href="process.html">Process</a>
                                            <ul>
                                                <li><a href="#">Process 01</a></li>
                                                <li><a href="#">Process 02</a></li>
                                                <li><a href="#">Process 03</a></li>
                                            </ul>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><s:url value="/registration" var="registration"/>
                                            <a href="${registration}"><s:message code="balance.register"/></a>
                                        </li>
                                        <li class="active"><s:url value="/log_in" var="logIn"/>
                                            <a href="${logIn}"><s:message code="balance.log_in"/></a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- / END NAV-BAR FORM -->
    </div>

</header>
<div class="bg-content">
    <!-- content -->
    <div id="content">
        <div class="ic"></div>
        <div class="container">
            <div class="row">

                <!-- Log In FORM -->
                <article class="span8">
                    <h3>Add new product</h3>

                    <div class="inner-1">
                        <table>

                            <form:form method="POST" id="addproducts" action="addproducts"
                                       commandName="product" name="productForm" onsubmit="validateAddProducts()">
                                <tr>
                                    <td><span class="textcolor">Category :</span></td>

                                    <td>
                                        <form:select path="productType" id="products" name="productType">
                                            <form:option disabled="true" selected="true"
                                                         value="">Choose product</form:option>
                                            <form:option value="vegetable">Vegetable</form:option>
                                            <form:option value="fruit">Fruit</form:option>

                                        </form:select>
                                    </td>

                                    <td>
                                        <form:input path="name" placeholder="Product name" name="productName"/>
                                    </td>
                                </tr>
                                <tr>

                                    <td><span class="textcolor">Selling unit : </span></td>
                                    <td><form:radiobutton path="isForKilo" name="isForKilo" value="true"
                                                          id="forKilo"/><span class="textcolor"> Kg </span>
                                        <form:radiobutton path="isForKilo" name="isForKilo" value="false"
                                                          id="forPiece"/><span class="textcolor">Piece </span>
                                    </td>
                                    <td><form:input path="amountKilo" id="amountKilo" placeholder="Add kilos" name="amountKilo"/>
                                        <form:input path="amountPiece" id="amountPiece" placeholder="Add pieces" name="amountPiece"/>
                                        </td>
                                    <td>
                                        <form:input path="price" id="price" name="price" placeholder=""/>

                                    </td>

                                </tr>

                                <tr>
                                    <td><b><input type="submit" value="Submit" class="textcolor"/></b></td>
                                </tr>


                            </form:form>
                        </table>
                    </div>
                </article>
                <!--/ END Log IN FORM -->
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<footer>
    <div class="container clearfix">
        <ul class="list-social pull-right">
            <li><a class="icon-1" href="#"></a></li>
            <li><a class="icon-2" href="#"></a></li>
            <li><a class="icon-3" href="#"></a></li>
            <li><a class="icon-4" href="#"></a></li>
        </ul>
        <div class="privacy pull-left">&copy; 2016 | Best Java Junior Developers |</div>
    </div>
</footer>

<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
