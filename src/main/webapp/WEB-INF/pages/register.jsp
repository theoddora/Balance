<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page errorPage="404.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Codester | Contact</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
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
<div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div>  
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
    <!-- NAV-BAR FORM -->
    <div class="row">
      <div class="span12">
        <div class="navbar navbar_">
          <div class="container">
            <h1 class="brand brand_"><a href="index.jsp"><img alt="" src="img/logo.png"> </a></h1>
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse_">Menu <span class="icon-bar"></span> </a>
            <div class="nav-collapse nav-collapse_  collapse">
              <ul class="nav sf-menu">
                <li><a href="/index">Home</a></li>
                <li><a href="work.jsp">Work</a></li>
                <li><a href="blog.html">Blog</a></li>
                <li class="sub-menu"><a href="process.html">Process</a>
                  <ul>
                    <li><a href="#">Process 01</a></li>
                    <li><a href="#">Process 02</a></li>
                    <li><a href="#">Process 03</a></li>
                  </ul>
                </li>
                <li class="active"><a href="/register">Register</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div><!-- / END NAV-BAR FORM -->
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
					<form:form method="POST" id="contact-form" action="/register" commandName="user">
						<div class="success"> You have registered successfully!</div>
						  
						  <fieldset>
							<div>
							<label class="name">
								<form:input path="userName" placeholder="Username" required=""/>
								<br>
								<form:errors>*This is not a valid username.</form:errors> <span class="empty">*This field is required.</span>
							</label>
							</div>
							<div>
							  <label class="email">
								<form:input path="email" placeholder="Email" required=""/>
								<br>
								<form:errors>*This is not a valid email address.</form:errors> <span class="empty">*This field is required.</span> </label>
							</div>
							<div>
							  <label>
								<form:password path="password" placeholder="Password" required=""/>
								<br>
								<form:errors>*This is not a valid password.</form:errors> <span class="empty">*This field is required.</span> </label>
							</div>
							
							<div>
							  <label>
								<form:input path="name" placeholder="Your name" required=""/>
								<br>
								<form:errors>*The message is too short.</form:errors> <span class="empty">*This field is required.</span> </label>
							</div>
							
							<div class="buttons-wrapper">
								<a class="btn btn-1" data-type="reset">Clear</a>
								<input class="btn btn-1" type="submit" value="Register"/>
							</div>
						  </fieldset>
						  
				</form:form>
			  </div>
			</article> <!--/ END REGISTER FORM -->
		
        <article class="span4">
          <h3>Contact info</h3>
          <div class="map"> <a href="#"><img src="img/map.jpg" alt=""></a> </div>
          <address class="address-1">
          <strong>Inbetwin Networks,<br>
          Paud Phata, Road,<br>
          Kothrud, Pune-38.</strong>
          <div class="overflow"> <span>Mobile:</span>+91 12345 67890<br>
            <span>Telephone:</span>+91 12345 67890<br>
            <span>FAX:</span>+91 12345 67890 <br>
            <span>E-mail:</span> <a href="#" class="mail-1">you@domain.com</a><br>
            <span>Skype:</span> <a href="#" class="mail-1">@woohooo</a></div>
          </address>
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
    <div class="privacy pull-left">&copy; 2016 | Best Java Junior Developers | </div>
  </div>
</footer>
<script src="js/bootstrap.js"></script>
</body>
</html>