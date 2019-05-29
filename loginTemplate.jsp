<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Flinders POC - Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginFormStyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/formstyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/prettify.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginBody.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css" />

<!-- <style>
body {
    background-image: url("${pageContext.request.contextPath}/resources/images/flinder-background-image.jpg");
}
</style> -->

</head>
<body>
	<!-- <div id="wrapper">
		<div id="header"> -->
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<!-- </div> -->
		<!-- <div id="sectionLogin"> -->
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<!-- </div> -->
		<!-- <div id="footer"> -->
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		<!-- </div> -->
	</div>
</body>
</html>
