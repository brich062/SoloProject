<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/CSS/homenavbarfooterSTYLE.css"/>">
</head>
<body class="container hnf">
	<div class="container">
		<div class="slogan">
			<img src="<c:url value="/images/Vulk.png"/>" width= 75px;/>
			<h1 class="main">Conquer Your Goals!</h1>
		</div>
			<div>
				<div class="navbar navbar-dar bg-dark">
  					<a href="/">Home</a>
  					
  							<c:choose>
    							<c:when test="${userLoggedIn != null}">
      								<a href="/profile/${userLoggedIn}">My Profile</a>
      								<a href="/logout">Log Out</a>
      							</c:when>
      							<c:otherwise>
      								<a href="/loginregister">Login/Register</a>
      							</c:otherwise>
      						</c:choose>
      						<a href="/products">Shop</a>
      						<c:choose>
      							<c:when test="${userLoggedIn != null}">
      								<a href="/cart"><img src="<c:url value="images/Cart.png"/>" width= 25px/></a>
      							</c:when>
      							<c:otherwise>
      							</c:otherwise>
      						</c:choose>
				</div>
  			</div>
  			</div>
	
	<div class="jBody">
	
	<jsp:doBody/>
	
	</div>
	<div class="footer" class="container">
		<div class="container" id="content">
			<a class="footItem" href="/about">About Us</a>
			<a class="footItem" href="https://www.instagram.com/bronson_the_odinson/"><img src="<c:url value="images/instaBlack.png"/>" width= 50px/></a>
		</div>
	</div>
	
</body>

</html>