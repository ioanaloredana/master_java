<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="ro.tirzuman.ioana.model.Record"%>
<%@ taglib prefix="mylib" uri="/WEB-INF/tlds/mylibrary.tld"%>
<html>
<body>

	<table style="width: 100%" border="1">
		<tr>
			<th>Category</th>
			<th>Key</th>
			<th>Name</th>
		</tr>
		<%
			List<Record> recordList = (List<Record>) request.getAttribute("records");
			for (Record record : recordList) {
				out.print("<tr>");
				out.print("<td>" + record.getCategory().getName() + "</td>");
				out.print("<td>" + record.getKey() + "</td>");
		%>
		<td><mylib:record key='<%=record.getKey()%>' category='<%=record.getCategory().getName()%>' /></td>
		<%
			/* out.print("<td>" + record.getName() + "</td>"); */
				out.print("</tr>");
			}
		%>
	</table>

</body>
</html>