
// onclick="checkboxSelectAll('criteria.selectedIds', this.checked);
function checkboxSelectAll(elName, value){
	var el = document.getElementsByName(elName);
	var j = el.length - 1;
	for (var i = 0; i < el.length; i++) {

		if (i > j) {
			break;
		}

		if(!el[i].disabled){
			el[i].checked = value;

		}
		if(!el[j].disabled){
			el[j].checked = value;

		}

		j--;
	}
}

/**
 * ใช้ submit ไปยัง action ที่ต้องการ โดย default ที่ form 0
 * @param action คือ acion url
 */
function submitPage(form, action) {
	form.action = action;
	form.submit();
}

function clearAllRequireSelect(elId) {
	var elArray = elId.split(',');
	for (var index = 0; index < elArray.length; index++) {
		clearRequireSelect(trim(elArray[index]));
	}
}

function showMessageResponse(el) {
	var html = el.innerHTML;
	html = getMessageResponse(html);
	if (html.length > 0) {
		alert(html);
	}
}

function showErrorDetail(elName) {
	var html = document.getElementById(elName).innerHTML;
	html = getMessageResponse(html);
	if (html.length > 0) {
		alert(html);
	}
}

function getMessageResponse(text) {
	text = text.replace(/<br>|<BR>/g, '\n');
	text = text.replace(/^\s+|\s+$|\t|&nbsp;/g, '');
	return text;
}

/**
 * ทำการ focus ไปยัง elementId ที่ต้องการ
 */
function doTabFocus(elementNextId, elementBackId, event, isNext){
	var keynum = (event.which || event.keyCode);

	if (isNext && !event.shiftKey && keynum == 9){ //tab
		document.getElementById(elementNextId).focus();// for firefox 10 focus
		//setTimeout('doument.getElementById("'+elementNextId+'").focus();',100);
		return false;
	}
	else if(!isNext && (event.shiftKey && keynum==9)){ //shift + tab
		document.getElementById(elementBackId).focus();// for firefox 10 focus
		///setTimeout('document.getElementById("'+elementBackId+'").focus();',100);
		return false;
	}else if ((elementNextId.value == elementBackId.value ) && ((event.shiftKey && keynum==9) && isNext)){
		document.getElementById(elementBackId).focus();
		return false;
	}
	return true;
}

// elName:  name of checkbok
// elResult: name of result >> 1,2,3
function getIdsCheck(elName, elResult){
	var ids = "";
	var el = document.getElementsByName(elName);
	for (var i = 0; i < el.length; i++) {
		if(el[i].checked){
			ids += el[i].value + ",";
		}
	}

	if(ids != ""){
		ids = ids.substring(0, ids.length - 1);
	}
	document.getElementsByName(elResult)[0].value = ids;
}

/** Check browser IE,Firefox */
function checkBrowser() {
	var mybrowser = navigator.userAgent;
	mybs = 'ไม่สามารถเช็ค browser ได้';
	if (mybrowser.indexOf('MSIE') > 0) {
		mybs = "IE";
	}
	if (mybrowser.indexOf('Firefox') > 0) {
		mybs = "Firefox";
	}
	if (mybrowser.indexOf('Presto') > 0) {
		mybs = "Opera";
	}
	if (mybrowser.indexOf('Chrome') > 0) {
		mybs = "Chrome";
	}
	return mybs;
}