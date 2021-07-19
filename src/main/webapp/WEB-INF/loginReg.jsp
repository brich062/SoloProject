<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Login or Register</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="container desc">
		<div class="form-group">
			<h3>Login</h3>
			<p><c:out value="${errors}"/></p>
			<form method="post" action="/login">
				<p>
					<label>Email:</label>
					<input type="email" name="email"/>
				</p>
				<p>
					<label>Password:</label>
					<input type="password" name="password"/>
				</p>
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
		<hr>
		<div class="form-group">
			<h3>New User Registration</h3>
			<form:form method="post" action="/register" modelAttribute="user">
				<p>
					<form:label path="firstName">First Name: </form:label>
					<form:errors path="firstName"/>
					<form:input path="firstName"/>
				</p>
				<p>
					<form:label path="lastName">Last Name: </form:label>
					<form:errors path="lastName"/>
					<form:input path="lastName"/>
				</p>
				<p>
					<form:label path="email">Email: </form:label>
					<form:errors path="email"/>
					<form:input path="email"/>
				</p>
				<p>
					<form:label path="password">Password: </form:label>
					<form:errors path="password"/>
					<form:input type="password" path="password"/>
				</p>
				<p>
					<form:label path="confirmPassword">Confirm Password: </form:label>
					<form:errors path="confirmPassword"/>
					<form:input type="password" path="confirmPassword"/>
				</p>
				<button class="btn btn-success">Register</button>
			</form:form>
		</div>
	</div>
</t:HeaderNavbarFooter>
</body>
</html>