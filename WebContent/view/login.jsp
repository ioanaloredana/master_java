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
		if (errorMessage == null || errorMessage.isEmpty()) {
			errorMessage = "Please enter your credentials";
		}
		String username = (String) request.getAttribute("username");
		if (username == null || username.isEmpty()) {
			username = "";
		}
	%>
	<h3><%=errorMessage%></h3>
	<form action="login" method="post">
		User: <input type="text" name="username" value="<%=username%>">
		<br> Password: <input type="password" name="password"> <br>
		Remember me <input type="checkbox" name="rememberLogin" checked><br>
		Language:<select name="locale">
			<option value="en" selected>English</option>
			<option value="ro">Romana</option>
		</select><br>
		 <input type="submit" value="Send">
	</form>
	<br>
</body>
</html>