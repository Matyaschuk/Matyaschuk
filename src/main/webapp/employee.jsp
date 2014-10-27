
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet task</title>
<script src="js/jquery.min.js"></script>
<script src="js/script.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<form action="MyServlet.html" method="post">
		<!-- Create fields for new employee -->
		<txt> <b>Enter your Name, Surname and E-mail.</b></txt>
		<fieldset>
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="fname"></td>
				</tr>
				<tr>
					<td>Surname:</td>
					<td><input type="text" name="lname"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"></td>
				</tr>
			</table>
			<input type="submit" name="addEmp" value="Confirm">
	</form>
	<div class="errMsg">
		<c:out value="${errMsg}" />
	</div>
	<div class="empTable">
		<c:choose>
			<c:when test="${empty applicationScope.employees}">
				<!-- Inform user that there's no employee yet -->
				<c:out value="Employee list is empty"></c:out>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>Id</td>
						<td>Name</td>
						<td>Surname</td>
						<td>E-Mail</td>
					</tr>
					<c:forEach var="employee" items="${applicationScope.employees}">
						<tr>
							<td><c:out value="${employee.id}"></c:out></td>
							<td><c:out value="${employee.name}"></c:out></td>
							<td><c:out value="${employee.surName}"></c:out></td>
							<td><c:out value="${employee.email}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>