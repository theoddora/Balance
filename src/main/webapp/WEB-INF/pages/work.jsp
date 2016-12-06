<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <s:url value="/index" var="index"/>
        <div class="row">
            <div class="span12">
                <div class="navbar navbar_">
                    <div class="container">
                        <h1 class="brand brand_"><a href="${index}"><img alt="" src="img/logo.png" width="350px"> </a>
                        </h1>
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse_">Menu <span
                                class="icon-bar"></span> </a>

                        <div class="nav-collapse nav-collapse_  collapse">
                            <ul class="nav sf-menu">
                                <li>
                                    <a href="${index}"><s:message code="balance.home"/></a>
                                </li>
                                <li class="active">
                                    <s:url value="/product" var="product"/>
                                    <a href="${product}"><s:message code="balance.product"/></a>
                                </li>

                                <sec:authorize access="isAuthenticated()">
                                    <sec:authentication var="username" property="principal.username"/>
                                    <li class="sub-menu"><a><c:out value="${username}"/> </a>

                                        <ul>
                                            <s:url value="/${username}" var="profileUrl"/>
                                            <a href="${profileUrl}"><s:message code="balance.profile_page"/></a>
                                            <a href="<c:url value="/log_out"/>"><s:message code="balance.log_out"/></a>
                                        </ul>
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
    </div>
</header>


<!-- Content -->

<div class="bg-content">
    <!-- content -->
    <div id="content">
        <div class="ic"></div>
        <div class="container">
            <div class="row">
                <article class="span12">
                    <h4></h4>
                </article>
                <div class="clear"></div>
                <ul class="thumbnails thumbnails-1 list-services">
                    <c:forEach items="${products}" var="product">
                        <li class="span4">
                            <div class="thumbnail thumbnail-1"><img src="img/work/${product.name}.jpg" alt="" width="800" height="800">
                                <section><a class="link-1"><c:out value="${product.name}"/></a>
                                    <c:if test="${product.discount > 0}">

                                        <fmt:formatNumber  value="${product.discount * 100} " maxFractionDigits="0" var="discount"/>


                                      <p>Only today get <c:out value="${discount}"/>% discount for this product </p>

                                    </c:if>

                                    <p>
                                        <c:choose>
                                        <c:when test="${product.isForKilo}">

                                    <form action="${pageContext.request.contextPath}/product" method="POST">
                                        <input type="text" name="amount" placeholder="Amount...">
                                        <button type="submit" value="${product.id}" name="productId" class="btn btn-1">
                                            Buy
                                        </button>
                                    </form>
                                    </c:when>
                                    <c:when test="${not product.isForKilo}">
                                        <form action="${pageContext.request.contextPath}/product" method="POST">
                                            <input type="text" list="amount" name="amount" placeholder="Enter pieces">
                                            <datalist id="amount" name="amount">
                                                <option value="1">
                                                <option value="2">
                                                <option value="3">
                                                <option value="4">
                                            </datalist>
                                            <button type="submit" value="${product.id}" name="productId"
                                                    class="btn btn-1">Buy
                                            </button>
                                        </form>

                                    </c:when>
                                    </c:choose>

                                    <c:out value="Price:">Price:</c:out>
                                        <c:out value="${product.price} lv."></c:out></p>
                                </section>


                            </div>
                        </li>

                    </c:forEach>

                </ul>
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
</body>
</html>