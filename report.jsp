<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<script>
$(document).ready(function() {
	$('#generateBtn').click( function () {
		if ($('#reportType')[0].value == ""
				|| $('#reportPeriodFrom')[0].value == ""
				|| $('#reportPeriodTo')[0].value == "") {
			alert("Please select criteria");
			return false;
		}
		$('#report').submit();
    } );
} );
</script>
</head>
<body>
<section class="main-content">
	<div id="slimForm" class="main-container">
	<h3>Report</h3>
	<hr/>
	<div class="form-style">
		<c:url var="reportAction" value="/report/generateExcel"></c:url>
		<form:form id="report" action="${reportAction}" commandName="report" >
		<table style="width: 60%;">
			<tr>
				<td style="width: 40%;">
					<form:label path="reportType">
						<spring:message text="Report Type" />
					</form:label>
				</td>
				<td style="width: 30%;">
					<form:label path="reportPeriodFrom">
						<spring:message text="Period From" />
					</form:label>
				</td>
				<td style="width: 30%;">
					<form:label path="reportPeriodTo">
						<spring:message text="Period To" />
					</form:label>
				</td>
			</tr>
			<tr>
				<td>
					<form:select path="reportType" required="required">
						<form:option value="">--Select--</form:option>
						<form:option value="User Maintenance">Student Details</form:option>
						<form:option value="Group Maintenance">Student Grade</form:option>
						<form:option value="Team Maintenance">STudent Skills</form:option>
					</form:select>
				</td>
				<td>
					<input type="text"  id="reportPeriodFrom" name="reportPeriodFrom" class="datepicker"/>
				</td>
				<td>
					<input type="text"  id="reportPeriodTo" name="reportPeriodTo" class="datepicker"/>
				</td>
			</tr>
		</table>
		<button id="generateBtn" type="button" name="generate" class="btnStyle">Generate</button>
		</form:form>
	</div>
	</div>
	</section>
</body>
</html>