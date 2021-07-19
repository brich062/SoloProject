<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>${product.name}</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="container">
		<h1>${product.name}</h1>
		<h3>Description</h3>
		<div>
			<p><c:out value="${product.description}"/></p>
		</div>
		<div class="cartAdding">
		<c:choose >
			<c:when test="${!product.likes.contains(user)}">
				<a href="/like/${product.id}">Like</a>
			</c:when>
			<c:otherwise>
				<a href="/unlike/${product.id}">Unlike</a>
			</c:otherwise>
		</c:choose>

	<h4><c:out value="${product.price}"/></h4>
	<form method="post" action="/additem">
		<button class="btn btn-warning">Add To Cart</button>
	</form>
	</div>
	</div>
</t:HeaderNavbarFooter>
</body>
</html>