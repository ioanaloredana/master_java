<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<body>
<h1>We are sorry but your request could not be completed successfully. <br>
The following occurred:</h1>
<br>
<p style="color: red">
<%= exception.getMessage() %>
</p>
</body>
</html>