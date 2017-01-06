<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <title><s:message code="balance.title"/></title>
    <meta charset="utf-8">

    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/main.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
    <script src="js/jquery.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.min.js"></script>
    <script src="js/smart-table.js"></script>

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
            jQuery(".list-blog li:last-child").addClass("last");
            jQuery(".list li:last-child").addClass("last");
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
<body ng-controller="mainCtrl">
<div class="spinner"></div>
<header>
    <s:url value="/index" var="index"/>
    <div class="container clearfix">
        <!-- NAV-BAR FORM -->
        <div class="row">
            <div class="span12">
                <div class="navbar navbar_">
                    <div class="container">

                        <h1 class="brand brand_">
                            <a href="${index}">
                                <img alt="" src="img/logo.png" width="350px">
                            </a>
                        </h1>
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse_">
                            <s:message code="balance.menu"/>
                        </a>

                        <div class="nav-collapse nav-collapse_  collapse">
                            <ul class="nav sf-menu">
                                <li>
                                    <a href="${index}"><s:message code="balance.home"/></a>
                                </li>
                                <li>
                                    <s:url value="/product" var="product"/>
                                    <a href="${product}"><s:message code="balance.product"/></a>
                                </li>
                                <!-- ADMIN STUFF -->
                                <sec:authorize access="isAuthenticated() and hasRole('ADMIN')">
                                    <li>
                                        <s:url value="/addproducts" var="addproduct"/>
                                        <a href="${addproduct}"><s:message code="balance.addproduct"/></a>
                                    </li>
                                    <li>
                                        <s:url value="/manageproducts" var="manageproduct"/>
                                        <a href="${manageproduct}"><s:message code="balance.manageproducts"/></a>
                                    </li>
                                </sec:authorize>
                                <!-- END ADMIN STUFF -->

                                <sec:authorize access="isAuthenticated()">
                                    <sec:authentication var="username" property="principal.username"/>
                                    <li class="sub-menu">
                                        <a><c:out value="${username}"/></a>
                                        <s:url value="/${username}" var="profileUrl"/>
                                        <ul>
                                            <li>
                                                <a href="${profileUrl}">
                                                    <s:message code="balance.profile_page"/>
                                                </a>
                                            </li>
                                            <li>
                                                <s:url value="/log_out" var="log_out"/>
                                                <a href="${log_out}"><s:message code="balance.log_out"/></a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="active">
                                        <s:url value="/cart" var="cart"/>
                                        <a href="${cart}"><i class="fa fa-shopping-cart fa-lg"
                                                             aria-hidden="true"></i></a>
                                    </li>
                                </sec:authorize>

                                <sec:authorize access="isAnonymous()">
                                    <li><s:url value="/registration" var="registration"/>
                                        <a href="${registration}"><s:message code="balance.register"/></a>
                                    </li>
                                    <li><s:url value="/log_in" var="logIn"/>
                                        <a href="${logIn}"><s:message code="balance.log_in"/></a>
                                    </li>
                                </sec:authorize>


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
    <!--  content  -->
    <div class="container">
        <article class="span8">

            <br/>
            <h3><c:out value="${message}"/></h3>
            <c:set value="${currentCart}" var="product"/>
            <br/>
            <c:choose>
                <c:when test="${currentCart.size() > 0}">
                    <br/>
                    <table st-table="displayedCollection" st-safe-src="rowCollection" class="table-fill">
                        <thead>
                        <tr>
                            <th class="text-left">Products</th>
                            <th class="text-left">Type</th>
                            <th class="text-left">Price</th>
                            <th class="text-left">Amount</th>
                            <th class="text-left">Discount</th>
                            <th class="text-left">Remove</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="row in rowCollection">
                            <td class="text-left">{{row.name}}</td>
                            <td class="text-left">{{row.type}}</td>
                            <td class="text-left">{{row.price | currency}}</td>
                            <td class="text-left">{{row.amount | number : 2}}</td>
                            <td class="text-left">{{row.discount * 100 | number : 0}} %</td>
                            <td class="text-left">
                                <button type="button" ng-click="removeRow(row)" class="btn btn-sm btn-danger"
                                        value="{{row.id}}">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <article class="span8">
                        <h3>
                            Total price:
                            <span id="totalPrice">{{${priceToShow} | number : 2}}</span> levs.
                            <form action="addToBuy"  method="POST"><input class="btn btn-1" type="submit" value="BUY ITEMS"/></form>
                        </h3>
                    </article>

                </c:when>
                <c:otherwise>
                    <h3>
                        Your cart is empty.
                    </h3>
                </c:otherwise>

            </c:choose>

            <br/>
        </article>
        <article class="span3"><br/>
            <img src="img/map.jpg">
        </article>
    </div>
</div>
<!--  footer  -->
<footer>
    <div class="container clearfix">
        <ul class="list-social pull-right">
            <li><a class="icon-1" href="#"></a></li>
            <li><a class="icon-2" href="#"></a></li>
            <li><a class="icon-3" href="#"></a></li>
            <li><a class="icon-4" href="#"></a></li>
        </ul>
        <div class="privacy pull-left">&copy; 2017 | Best Java Junior Developers |</div>
    </div>
</footer>
<script src="js/bootstrap.js"></script>
<script>

    var app = angular.module('myApp', ['smart-table']);
    app.controller('mainCtrl', ['$scope', function (scope) {
        scope.rowCollection = [
            <c:forEach var="product" items="${currentCart}" varStatus="stat">
            {
                name: '${product.key.name}',
                type: '${product.key.productType}',
                price: '${product.key.price}',
                amount: '${product.value}',
                discount: '${product.key.discount}',
                id: '${product.key.id}'
            }
            <c:if test="${!stat.last}">
            ,
            </c:if>
            </c:forEach>
        ];

        scope.removeRow = function removeRow(row) {
            var index = scope.rowCollection.indexOf(row);
            if (index !== -1) {
                $.ajax(
                        {
                            dataType: 'text',
                            type: "POST",
                            url: 'removeFromCart',
                            data: {
                                id: scope.rowCollection[index].id
                            },
                            success: function (successData) {
                                if (successData < 0) {
                                    successData = 0;
                                }
                                document.getElementById('totalPrice').innerHTML = successData;
                            }
                        }
                );
                scope.rowCollection.splice(index, 1);
            }
        }

    }]);

</script>

</body>
</html>