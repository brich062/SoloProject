<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="jumbotron">
		<h2 class="display-3">Hello <c:out item="${user.firstName}"/>!</h2>
		<p class="lead">Last Name: <c:out item="${user.lastName}"/></p>
		<p class="lead">Email: <c:out item="${user.email}"/></p>
		<hr class="my-5">
		<a class="btn btn-primary" href="/edit/${user.id}">Edit Profile</a>
		<a class="btn btn-warning" href="/delete/${user.id}">Delete Profile</a>
	</div>
</t:HeaderNavbarFooter>
</body>
</html>