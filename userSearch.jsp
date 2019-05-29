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
		<h3>User - Search</h3>
	    <hr/>
		<div class="form-style">
		<c:url var="userSearchAction" value="/user/searchByCriteria"></c:url>
		<c:url var="userAddAction" value="/user/addUser"></c:url>
		<form:form id="criteriaForm" action="${userSearchAction}" commandName="criteria" >
		<table>
				<tr>
					<td>
						<form:label path="userId">
							<spring:message text="User ID" />
						</form:label>
					</td>
					<td><form:input path="userId" maxlength="35" /></td>
				</tr>
				<tr>
					<td>
						<form:label path="userName">
							<spring:message text="Name" />
						</form:label>
					</td>
					<td><form:input path="userName" maxlength="100"/></td>
				</tr>
				<tr>
					<td><button type="submit" name="search" class="btnStyle">Search</button></td>
					<td><button type="button" name="add" onclick="location.href='${pageContext.request.contextPath}/user/addUser';" class="btnStyle">Add User</button></td>
				</tr>
		</table>
		</form:form>
		</div>
		
		<br />
		
		<div class="container">
			<table class="listDT" width="100%">
			<thead>
				<tr>
					<th>User ID</th>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td><a href="${pageContext.request.contextPath}/user/view?id=${user.userId}&name=${user.userName}">${user.userId}</a></td>
						<td>${user.userName}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</div>
</section>
</body>
</html>