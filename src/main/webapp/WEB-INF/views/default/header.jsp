<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<body>
	 <header class="header-area">
			<div class="head-top-area">
			<div class="logo col-width-10 pull-left">
				<img src="${pageContext.request.contextPath}/resources/images/flinder-logo.png" />
			</div>
			<div class="col-width-33 pull-left">
				<br><br>
				<h2>Flinders POC<h2>
			</div>
            <div class="col-width-33 pull-right text-right">
                <label class="usernameLbl">${sessionScope['loginUser'].username}</label> 
				<a href="${pageContext.request.contextPath}/auth/logout" class="btn-logout">Logout</a>
            </div>
            <p class="clearfix"></p>
        </div>
        
        <c:if test="${sessionScope['loginUser'].userBUFlag eq true 
        	|| sessionScope['loginUser'].studentBUFlag eq true}">
        	<!-- || sessionScope['loginUser'].reportBUFlag eq true}"> -->
        	
        <div class="head-bottom-area">
			<div class="main-menu">
			<ul>
					<c:if test="${sessionScope['loginUser'].userBUFlag eq true}">
				    <li class="dropdown">
                    	<a href="javascript:void(0)" class="dropbtn">User Maintenance</a>
                    	<div class="dropdown-content">
				    		<a id="link1" href="${pageContext.request.contextPath}/user">User Maintenance</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/group">Group Maintenance</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/function">Function Maintenance</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/userReport">Report</a>
				    	</div>
                    </li>
                    </c:if>
                    
					<c:if test="${sessionScope['loginUser'].studentBUFlag eq true}">
				    <li class="dropdown">
                    	<a href="javascript:void(0)" class="dropbtn">Student Maintenance</a>
                    	<div class="dropdown-content">
				    		<a id="link1" href="${pageContext.request.contextPath}/student">Student detail</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentCourse">Student course</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentGrade">Student grade</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentSKill">Student skill</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentReport">Report</a>
				    	</div>
                    </li>
                    </c:if>
                    
                    <%-- <c:if test="${sessionScope['loginUser'].reportBUFlag eq true}">
                    <li class="dropdown">
                        <a href="javascript:void(0)" class="dropbtn">Reports</a>
                        <div class="dropdown-content">
				    		<a id="link1" href="${pageContext.request.contextPath}/studentReport">Student detail report</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentCourseReport">Student course report</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentGradeReport">Student grade report</a>
				    		<a id="link1" href="${pageContext.request.contextPath}/studentSKillReport">Student skill report</a>
                        </div>
                    </li>
                    </c:if> --%>
                   
			</ul>
			</div>
		</div>
		
		</c:if>
    </header>
</body>
</html>