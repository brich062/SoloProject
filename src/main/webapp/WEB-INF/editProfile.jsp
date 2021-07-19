<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
<t:HeaderNavbarFooter>
	<div class="container">
		<h3>Edit Your Information</h3>
		<div class="form-group">
			<form:form method="post" action="/edit/${user.id}" modelAttribute="user">
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