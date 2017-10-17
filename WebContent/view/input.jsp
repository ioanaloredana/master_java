<!DOCTYPE html>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="records" method="post">
		Key: <input type="text" name="key"> <br> 
		Name: <input type="text" name="name"> <br>
		Category: <select name="category">
		<%
		String preselectCategory = (String) request.getAttribute("selectedCategory");
		List<String> categoryList = (List<String>) request.getAttribute("categories");
		for (String category : categoryList) {
			String selected = "";
			if (category.equals(preselectCategory)) {
				selected = "selected";
			}
			out.println("<option value=\"" + category + "\" " + selected + ">" + category + "</option>");
		}
		%>
		</select><br>
		Check for session records only: <input type="checkbox" name="recordsFromSession"><br>
		Captcha: <input type="text" name="captcha"> <br> 
		<input type="submit" value="Send">
	</form> <br>
	<a href="captcha" target="iframe_captcha">New captcha</a> <br>
	<iframe name="iframe_captcha" src="captcha" width="100" height="50"></iframe>
</body>
</html>