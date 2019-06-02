$(function() {
	$(".datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'mm/dd/yy'
	});
});

$(function() {
	$('#slimForm').slimscroll({
		height : '100%',
		width : '100%'
	});
});

$(function() {
	$('.scroll-it').slimscroll();
})

$(function() {
	$('.selectWithCheckbox').multiselect({
		placeholder : 'Select options'
	}).multiselectfilter({
		autoReset : true
	});
});

$(function() {
	$('.listDT').DataTable({
		bJQueryUI: true
	});
});

