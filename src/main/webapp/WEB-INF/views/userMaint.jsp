<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<!-- <script>
$(document).ready(function() {
  
    $('#groupCode').change( function () {
    	var groupCode = $('#groupCode')[0].value;
    	if (groupCode != '') {
    	alert("${user.groupList}");
    	alert("${fn:length(user.groupList)}");
    	var grpLstSize = "${fn:length(user.groupList)}";
    	alert(grpLstSize); 	
    	var grpLst = "${user.groupList}";
    	alert(grpLst);
    	alert(groupCode);
    	}
   	} );  
    
} );
</script> -->
</head>
<body>
<section class="main-content">
	<div id="slimForm" class="main-container">
    <h3>User - Profile</h3>
	<hr/>
	<div class="form-style">
	<c:url var="submitAction" value="/user/edit"></c:url>
	<form:form action="${submitAction}" commandName="user" >

	<form:hidden path="creationDate"/>
	<form:hidden path="createdBy"/>
	<form:hidden path="modificationDate"/>
	<form:hidden path="modifiedBy"/>
	<form:hidden path="versionNum"/>
	<form:hidden path="add" value="${user.isAdd()}"/>

   	<table width="50%">
   	<thead>
   		<tr>
   			<th style="text-align: left;">User Id</th>
   		</tr>
	</thead>
   	<tbody>
   		<tr>
   			<c:if test="${user.isEditable() eq false}">
   				<td><form:input path="userId" maxlength="30" size="35" value="${user.userId}" STYLE="background-color: #D3D3D3;" readonly="true" /></td>
   			</c:if>
   			<c:if test="${user.isEditable() eq true}">
   				<td><form:input path="userId" maxlength="30" size="35" value="${user.userId}" /></td>
   			</c:if>
   		</tr>
	</tbody>
   	<thead>
   		<tr>
   			<th style="text-align: left;">Name</th>
			<th style="text-align: left;">Password</th>
			<th style="text-align: left;">Email Address</th>
   		</tr>
	</thead>
   	<tbody>
   		<tr>
   			<td><form:input path="userName" maxlength="30" size="35" value="${user.userName}" required="true"/></td>
   			<td><form:input path="password" maxlength="30" size="35" value="${user.password}" required="true"/></td>
   			<td><form:input path="emailAddress" maxlength="35" size="35" value="${user.emailAddress}" required="true"/></td>
   		</tr>
	</tbody>
	<thead>
   		<tr>
   			<th style="text-align: left;">Group Code</th>
   			<th style="text-align: left;">Full Group Description</th>
   		</tr>
	</thead>
   	<tbody>
   		<tr>
   			<td>
				<form:select path="groupCode">
				<c:forEach items="${user.groupList}" var="group">
				<form:option value="${group}">${group}</form:option>
				</c:forEach>
				</form:select>
			</td>
			<td><form:input path="fullGroupDesc" maxlength="14" size="35" value="${user.fullGroupDesc}" STYLE="background-color: #D3D3D3;" readonly="true" /></td>
   		</tr>
	</tbody>
   	</table>
    <br />
	<button type="button" name="back" onclick="location.href='${pageContext.request.contextPath}/user';" class="btnStyle">Back</button>
	<button type="submit" name="save" class="btnStyle">Save</button>
	<br />
	<Label style="color: Red;">${actionMsg}</Label>
	</form:form>
	</div>
	<br>

	</div>
	</section>
</body>
</html>