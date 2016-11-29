<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Codester | Work</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/touchTouch.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
    <script src="js/jquery.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="js/touchTouch.jquery.js"></script>
    <script>if ($(window).width() > 1024) {
        document.write("<" + "script src='js/jquery.preloader.js'></" + "script>");
    }    </script>
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
<!-- header -->
<header>
    <div class="container clearfix">
        <div class="row">
            <div class="span12">
                <div class="navbar navbar_">
                    <div class="container">
                        <h1 class="brand brand_"><a href="index.jsp"><img alt="" src="img/logo.png"> </a></h1>
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse_">Menu <span
                                class="icon-bar"></span> </a>

                        <div class="nav-collapse nav-collapse_  collapse">
                            <ul class="nav sf-menu">
                                <li><a href="index">Home</a></li>
                                <li class="active"><a href="work.jsp">Work</a></li>
                                <li><a href="cart">Cart</a></li>
                                <li class="sub-menu"><a href="process.html">Process</a>
                                    <ul>
                                        <li><a href="#">Process 01</a></li>
                                        <li><a href="#">Process 02</a></li>
                                        <li><a href="#">Process 03</a></li>
                                    </ul>
                                </li>
                                <li><a href="register.jsp">Contact</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>


<!-- Content -->


<div class="container">


    <c:forEach items="${products}" var="product">

        <div class="box floating-box"><c:out value="${product.name}"></c:out><img alt=""
                                                                                  src="img/work/${product.name}.jpg"
                                                                                  width="100" height="100">


            <c:choose>
                <c:when test="${product.isForKilo}">
                    <form action="${pageContext.request.contextPath}/product" method="POST">
                    <input type="text" name="amount" placeholder="Amount...">
                    <button type="submit" value="${product.id}" name = "productId" class="btn btn-1">Buy</button>
                    </form>
                </c:when>
                <c:when test="${not product.isForKilo}">
                    <form action="${pageContext.request.contextPath}/product" method="POST">
                        <input type="text" list = "amount" name="amount" placeholder="Enter pieces">
                        <datalist id = "amount" name="amount">
                            <option value="1">
                            <option value="2">
                            <option value="3">
                            <option value="4">
                        </datalist>
                        <button type="submit" value="${product.id}" name = "productId" class="btn btn-1">Buy</button>
                    </form>

                </c:when>
            </c:choose>

            <c:out value="Price:">Price:</c:out>
            <c:out value="${product.price} lv."></c:out>
        </div>


    </c:forEach>


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
        <div class="privacy pull-left">&copy; 2013 | <a href="http://www.dzyngiri.com">Dzyngiri</a> | Demo Illustrations
            by <a href="http://justinmezzell.com">Justin Mezzell</a></div>
    </div>
</footer>
<script src="js/bootstrap.js"></script>
</body>
</html>