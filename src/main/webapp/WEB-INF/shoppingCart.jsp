<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="container desc">
		<h1>Shopping Cart</h1>
		<hr>
		<table class="table table-dark">
			<tbody>
				<c:forEach items="${allItems}" var="item">
					<tr>
						<td>${item.product.name}</td>
						<td>${item.product.price}</td>
						<%-- <td><form method="get" action="/delete/${item.id}">
						<input type="hidden" name="_method" value="Delete"/>
						<button class="btn btn-danger">Delete</button>
						</form></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h1> Your Total Is:</h1>
	</div>
</t:HeaderNavbarFooter>
</body>
</html>