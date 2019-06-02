<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=5,8,9" >
<title>Flinders POC App</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/formstyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jqueryui.dataTables.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.multiselect.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.multiselect.filter.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/prettify.css" />


<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jqueryui.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.multiselect.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.multiselect.filter.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/prettify.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app-ui.js"></script>

<style type="text/css">
div.container {
	min-width: 100%;
	min-height: 100%;
	margin: 0 auto;
}
</style>

<script type="text/javascript">
$(document).ready( function () {
	//  $('#listDT').DataTable();
	  
	  $("#resetBtn").on("click", function() {
			//alert("test reset");
			 $('#criteriaForm').clearForm();
		});
	  
	  $("#export").on("click", function() {
			//alert("test reset");
			 $('#exportForm').submit();
		});
	  
	  $(function() {
			var productGroupElement = $('[name="prodGrp"]');
			var productTypeElement = $('[name="prodType"]');
			var prodTypeValidation = null;
				
			if (typeof($('[name="prodTypeValidation"]').val()) != "undefined"){
				prodTypeValidation = $('[name="prodTypeValidation"]').val();
			} 	  			
				if (typeof($('[name="prodGrp"]').val()) != "undefined"){
					var instCode = "01";
					var prodGrp = productGroupElement.val();
					var prodTypeFullDesc = null;
					if(prodGrp==0){
						productTypeElement.val("");
					}else{
					$.ajax({
						url : '${pageContext.request.contextPath}/async/common/getProductTypeByProdGrp',
						type : 'POST',
						 data : {
							 instCode	  : instCode,
							 prodGrp : prodGrp
						}, 
						success : function(response) {	
							var productTypeOptions = productTypeElement.find('option');
							 if (productTypeOptions.length > 0) {
								productTypeOptions.remove();
								}  
								$.each(response, function(index,value) {
										if ((null == prodTypeValidation || "" == prodTypeValidation)&& prodGrp == "PIL" && value.prodType == "PIL") {
											$('<option>').val(value.prodType).text(value.prodType + " - " + value.description).attr("selected","selected").appendTo(productTypeElement);
										} else if ((null != prodTypeValidation || "" != prodTypeValidation) && prodTypeValidation == value.prodType) {
											$('<option>').val(value.prodType).text(value.prodType + " - " + value.description).attr("selected","selected").appendTo(productTypeElement);
										} else {
											$('<option>').val(value.prodType).text(value.prodType + " - " + value.description).appendTo(productTypeElement);
										} 										
									});
						},
						error : function(xhr) {
							alert("unfortunately failed Load Group IDs");
						}
						
						}); 
					}
				}			  
			});
	  
} );

$.fn.clearForm = function() {
    return this.each(function() {
    	//alert("clearForm");
        var type = this.type, tag = this.tagName.toLowerCase();
        if (tag == 'form')
            return $(':input',this).clearForm();
        if (type == 'text' || type == 'password' || tag == 'textarea')
            this.value = '';
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = 0;
    });
};

</script>

</head>
	<body>
		<spring:eval var="uiDateTimeFormat" expression="@environment.getProperty('ui.datetime.format')" scope="application"/>
		<spring:eval var="uiDateFormat" expression="@environment.getProperty('ui.date.format')" scope="application"/>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</body>
</html>

