<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="ro.tirzuman.ioana.model.Record"%>
<html>
<body>

	<table style="width: 100%">
		<tr>
			<th>Category</th>
			<th>Key</th>
			<th>Name</th>
		</tr>
		<%
			List<Record> recordList = (List<Record>) request.getAttribute("records");
			for (Record record : recordList) {
				out.println("<tr>");
				out.println("<td>" + record.getCategory().getName()+ "</td>");
				out.println("<td>" + record.getKey()+ "</td>");
				out.println("<td>" + record.getName()+ "</td>");
				out.println("</tr>");
			}
		%>
	</table>

</body>
</html>