<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
  <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Discovered Nodes</title>
        <link href="<c:url value='/static/css/font.css' />" rel='stylesheet' type='text/css'>
        <link href="<c:url value='/static/css/table.css' />" rel="stylesheet"></link>
</head>
 		<h1>Discovered Nodes</h1>
		<form:form>
			<table class="container">
		<thead>
			<tr>
				<th>IDP</th>
				<th>Node Name</th>
			</tr>
		</thead>
		<c:forEach items="${nodesList}" var="node">
				<tbody>
					<tr>
						<td><c:out value="${node.ip}" /></td>
						<td><c:out value="${node.nodename}" /></td>
					</tr>
				</tbody>
		</c:forEach>
			</table>
		</form:form>
</html>