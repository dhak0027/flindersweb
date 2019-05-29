<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
</head>
<body>
<section class="main-content">
	<div id="slimForm" class="main-container">
		<h3>Student - Search</h3>
	    <hr/>
		<div class="form-style">
		<c:url var="studentSearchAction" value="/student/searchByCriteria"></c:url>
		<form:form id="criteriaForm" action="${studentSearchAction}" commandName="criteria" >
		<table>
				<tr>
					<td>
						<form:label path="studentId">
							<spring:message text="Student ID" />
						</form:label>
					</td>
					<td><form:input path="studentId" maxlength="35" /></td>
				</tr>
				<tr>
					<td>
						<form:label path="studentName">
							<spring:message text="Student Name" />
						</form:label>
					</td>
					<td><form:input path="studentName" maxlength="100"/></td>
				</tr>
				<tr>
					<td>
						<button type="submit" name="search" class="btnStyle">Search</button>
					</td>
				</tr>
		</table>
		</form:form>
		</div>
		
		<br />
		
		<div class="container">
			<table class="listDT" width="100%">
			<thead>
				<tr>
					<th>Student ID</th>
					<th>Student Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentList}" var="user">
					<tr>
						<td><a href="${pageContext.request.contextPath}/user/view?id=${user.studentId}&name=${user.studentName}">${user.studentId}</a></td>
						<td>${user.studentName}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</div>
</section>
</body>
</html>