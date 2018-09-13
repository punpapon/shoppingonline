function naviSubmit(start, range, formName, action, criteriaPrefix) {
	//alert(start + "," + range + "," + formName + "," + action);
	var form = document.getElementsByName(formName)[0];
	form[criteriaPrefix + ".start"].value = start;
	form.action = action;
	form.submit();
}