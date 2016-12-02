<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><s:message code="balance.title"/></title>
    <meta charset="utf-8">
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
        <!-- / END NAV-BAR FORM -->
    </div>

</header>

<div class="bg-content">
    <!--  content  -->
    <div id="content">
        <div class="container">
            <div class="row ">
                <div class="span12">
                    <div class="block-404"><img class="img-404" src="img/email.jpg" alt="">

                        <div class="box-404">
                            <h3><s:message code="balance.emailSent"/></h3>

                        </div>
                    </div>
                </div>
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