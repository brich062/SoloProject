<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Products</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="container">
		<h3><a href="/products/${1}">Ultimate Full Body</a></h3>
		<img src="<c:url value="WEB-INF/Resources/Images/fullBody"/>"/>
		<p>Price: 19.99</p>	
	</div>
	<div class="container">
		<h3><a href="/products/${2}">Chest Builder</a></h3>
		<img src="<c:url value="WEB-INF/Resources/Images/chest"/>"/>
		<p>Price: 19.99</p>	
	</div>
	<div class="container">
		<h3><a href="/products/${3}">Titan Legs</a></h3>
		<img src="<c:url value="WEB-INF/Resources/Images/squat"/>"/>
		<p>Price: 19.99</p>	
	</div>
	<div class="container">
		<h3><a href="/products/${4}">Cobra Back</a></h3>
		<img src="<c:url value="WEB-INF/Resources/Images/back"/>"/>
		<p>Price: 19.99</p>	
	</div>
	<div class="container">
		<h3><a href="/products/${5}">Nutrition Guide</a></h3>
		<img src="<c:url value="WEB-INF/Resources/Images/nutrition"/>"/>
		<p>Price: 9.99</p>	
	</div>
</t:HeaderNavbarFooter>
</body>
</html>