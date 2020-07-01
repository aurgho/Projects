<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Custom Login Page</title>
</head>

<body>

	<h3>My Custom Login Page</h3>

	<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
	
	<!-- Check for login error -->
	
	<c:if test="${param.error != null }">
		<i style="color: red;">Sorry! You entered invalid Username/Password. </i>
	</c:if>
	
	<p>
		User name: <input type="text" name="username">
	</p>

	<p>
		Password: <input type="password" name="password">
	</p>
	
	<input type="submit" value="Login">

	</form:form>

</body>

</html>