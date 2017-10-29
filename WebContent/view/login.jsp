<!DOCTYPE html>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%
		String errorMessage = (String) request.getAttribute("errorMessage");
		if (errorMessage != null && !errorMessage.isEmpty()) {
			out.println("<h3>" + errorMessage + "</h3>");
		}
	%>
	<form action="login" method="post">
		User: <input type="text" name="username" value="<%
		String username = (String) request.getAttribute("username");
		if (username != null && !username.isEmpty()) {
			out.println(username);
		}
	%>"> <br> 
		Password: <input type="password" name="password"> <br> 
		Remember me <input type="checkbox" name="rememberLogin"><br>
	</form>
	<br>
</body>
</html>