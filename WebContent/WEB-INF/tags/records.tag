<%@ attribute name="category" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${sessionScope.locale eq 'ro'}">
		<fmt:setLocale value="ro" />
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="en" />
	</c:otherwise>
</c:choose>

<fmt:setBundle basename="ro.tirzuman.ioana.i18n.Messages" var="msgs" scope="page" />

<table style="width: 100%">
	<tr>
		<th><fmt:message key="record.category" bundle="${msgs}" /></th>
		<th><fmt:message key="record.key" bundle="${msgs}" /></th>
		<th><fmt:message key="record.value" bundle="${msgs}" /></th>
		<th><fmt:message key="record.date" bundle="${msgs}" /></th>
	</tr>
	<c:forEach var="record" items="${requestScope.records}">
		<c:if test="${empty category or record.category.name eq category}">
			<tr>
				<td><c:out value="${record.category.name}" /></td>
				<td><c:out value="${record.key}" /></td>
				<td><c:out value="${record.name}" /></td>
				<td><fmt:formatDate value="${record.date}" type="both" dateStyle="full" timeStyle="full" /></td>
			</tr>
		</c:if>
	</c:forEach>
</table>