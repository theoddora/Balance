<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kantar | Register</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
    <script src="js/jquery.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script>
        jQuery(window).load(function () {
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
                                    <li>
                                        <s:url value="/cart" var="cart"/>
                                        <a href="${cart}"><i class="fa fa-shopping-cart fa-lg" aria-hidden="true"></i></a>
                                    </li>
                                </sec:authorize>

                                <sec:authorize access="isAnonymous()">
                                    <li class="active"><s:url value="/registration" var="registration"/>
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
    <!-- content -->
    <div id="content">
        <div class="ic"></div>
        <div class="container">
            <div class="row">

                <!-- REGISTER FORM -->
                <article class="span8">
                    <h3>Register</h3>

                    <div class="inner-1">
                        <form:form method="POST" id="contact-form" action="registration" commandName="user">
                            <div class="success"> You have registered successfully!</div>
                            <c:if test="${errorMessage != null}">
                                <div class="error" style="display:block;"><c:out value="${errorMessage}"/></div>
                            </c:if>
                            <form:errors path="*" element="div" cssClass="errors"/>
                            <fieldset>
                                <div>
                                    <form:input path="username" cssErrorClass="error" placeholder="Username: "/>
                                    <br/><br/>
                                </div>
                                <div>
                                    <form:input path="email" type="email" cssErrorClass="error" placeholder="Email: "/>
                                    <br/><br/>
                                </div>
                                <div>
                                    <form:password path="password" cssErrorClass="error" placeholder="Password:"/>
                                    <br/><br/>
                                </div>
                                <div>
                                    <form:password path="passwordRepeat" cssErrorClass="error"
                                                   placeholder="Repeat Password:"/>
                                    <br/><br/>
                                </div>
                                <div>
                                    <form:input path="name" cssErrorClass="error" placeholder="Your name: "/>
                                    <br/><br/>
                                </div>
                                <br><br>

                                <div class="buttons-wrapper">
                                    <input class="btn btn-1" type="submit" value="REGISTER"/>
                                </div>
                            </fieldset>

                        </form:form>
                    </div>
                </article>
                <!--/ END REGISTER FORM -->

                <article class="span4"><br/>

                    <h3>Want to buy some fruits/vegetables?</h3>

                    <div class="map"><img src="img/map.jpg" alt=""></div>
                </article>
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