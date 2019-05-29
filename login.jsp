<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
</head>
<body>
	<section class="main-content">
    <div class="main-container">
		<c:url var="loginAction" value="/auth/login"></c:url>
		<section class="mainLogin">
		<form:form action="${loginAction}" commandName="loginBean" class="form-2">
		<h1><span class="log-in">User Login</span></h1>
			<p class="float">
			<form:label path="userId"><spring:message text="User ID" /></form:label>
			<form:input path="userId" />
			<form:errors path="userId" class="required"/>
			<form:label path="password"><spring:message text="Password" /></form:label>
			<form:password path="password" />
			<form:errors path="password" class="required"/>
			</p>
			<center>
			<p class="clearfixLogin"><input type="submit" value="<spring:message text="Login"/>"/></p>
			</center>
			<c:if test="${not empty authErrMsg}">
				<span class="required">
						<spring:message text="${authErrMsg}" ></spring:message>
				</span>
			</c:if> 
		</form:form>
		</section>
	</div>
	</section>
</body>
</html>


