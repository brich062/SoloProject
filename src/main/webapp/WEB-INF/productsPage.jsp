<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="container desc">
		<c:forEach items="${allProd}" var="prods">
			<h1>${prods.name}</h1>
		<h3>Description</h3>
		<div>
			<p>${prods.description}</p>
		</div>
		<div class="cartAdding">
		<c:choose>
			<c:when test="${userLoggedIn != null}">
				<c:choose >
					<c:when test="${prods.likes.contains(userLoggedIn)}">
						<a href="/unlike/${prods.id}">Unlike</a>
					</c:when>
					<c:otherwise>
						<a href="/like/${prods.id}">Like</a>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<!-- Left Blank to eliminate choice if not logged in -->
			</c:otherwise>
		</c:choose>
	<h4><c:out value="${prods.price}"/></h4>
	<c:choose>
		<c:when test="${userLoggedIn != null}">
			<form method="post" action="/additem/${prods.id}">
			<button class="btn btn-warning">Add To Cart</button>
			</form>
		</c:when>
		<c:otherwise>
			<h3>Log In or Register to Purchase</h3>
		</c:otherwise>
	</c:choose>
	</div>
	</c:forEach>
	</div>
</t:HeaderNavbarFooter>
</body>
</html>