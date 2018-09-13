function checkboxValidateSelect(elName){
	var chk = false;
	var el = document.getElementsByName(elName);
	for (var i = 0; i < el.length; i++) {
		if (el[i].checked){
			chk = true;
			break;
		}
	}
	return chk;
}

function validate(formId, msg){

	var eles = jQuery("#"+formId+" .requireSelect");
	for(var i=0;i < eles.length;i++){
		if ((eles[i].tagName == 'SPAN') || (eles[i].tagName == 'span')) {
			continue;
		}
		if ((eles[i].type == 'radio') || (eles[i].type == 'checkbox')) {
			var status = checkboxValidateSelect(eles[i].name);
			if (status == false){
				alert(msg);
				document.getElementsByName(eles[i].name)[0].focus();
				return false;
			} else {
				document.getElementsByName(eles[i].name)[0].className = document.getElementsByName(eles[i].name)[0].className.replace("requireSelect", "requireInput");
				document.getElementById(eles[i].name + 'SpanId').className = document.getElementById(eles[i].name + 'SpanId').className.replace("requireSelect", "requireSpanRadio");
			}
		} else {
			if(!eles[i].disabled && trim(eles[i].value)==''){
				alert(msg);
				eles[i].focus();
				return false;
			} else {
				eles[i].className = eles[i].className.replace("requireSelect", "requireInput");
			}
		}
	}

	eles = jQuery("#"+formId+" .requireInput");
	for(var i=0;i < eles.length;i++){
		if ((eles[i].type == 'radio') || (eles[i].type == 'checkbox')) {
			var status = checkboxValidateSelect(eles[i].name);
			if (status == false){
				alert(msg);
				alert(jQuery(eles).attr('name'));
				document.getElementsByName(eles[i].name)[0].focus();
				document.getElementsByName(eles[i].name)[0].className = document.getElementsByName(eles[i].name)[0].className.replace("requireInput", "requireSelect");

				document.getElementById(eles[i].name + 'SpanId').className = document.getElementById(eles[i].name + 'SpanId').className.replace("requireSpanRadio","requireSelect");
				return false;
			}
		} else {
			if(!eles[i].disabled && trim(eles[i].value)==''){
				alert(msg);
				eles[i].focus();
				eles[i].className = eles[i].className.replace("requireInput", "requireSelect");
				return false;
			}
		}
	}
	return true;
}

function LTrim(str) {
	/*
	 * PURPOSE: Remove leading blanks from our string. IN: str - the string we
	 * want to LTrim
	 */

	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		// We have a string with leading blank(s)...
		var j = 0, i = s.length;

		// Iterate from the far left of string until we
		// don't have any more whitespace...
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
			j++;

		// Get the substring from the first non-whitespace
		// character to the end of the string...
		s = s.substring(j, i);
	}
	return s;
}

function RTrim(str) {
	/*
	 * PURPOSE: Remove trailing blanks from our string. IN: str - the string we
	 * want to RTrim
	 *
	 */

	// We don't want to trip JUST spaces, but also tabs,
	// line feeds, etc. Add anything else you want to
	// "trim" here in Whitespace
	var whitespace = new String(" \t\n\r");
	var s = new String(str);

	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		// We have a string with trailing blank(s)...
		var i = s.length - 1; // Get length of string

		// Iterate from the far right of string until we
		// don't have any more whitespace...
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
			i--;

		// Get the substring from the front of the string to
		// where the last non-whitespace character is...
		s = s.substring(0, i + 1);
	}

	return s;
}

function Trim(str) {
	/*
	 * PURPOSE: Remove trailing and leading blanks from our string. IN: str -
	 * the string we want to Trim
	 *
	 * RETVAL: A Trimmed string!
	 */
	return RTrim(LTrim(str));
}

function ReplaceAll(inText, inFindStr, inReplStr, inCaseSensitive) {
	var searchFrom = 0;
	var offset = 0;
	var outText = "";
	var searchText = "";
	if (inCaseSensitive == null) {
		inCaseSensitive = false;
	}

	if (inCaseSensitive) {
		searchText = inText.toLowerCase();
		inFindStr = inFindStr.toLowerCase();
	} else {
		searchText = inText;
	}

	offset = searchText.indexOf(inFindStr, searchFrom);
	while (offset != -1) {
		outText += inText.substring(searchFrom, offset);
		outText += inReplStr;
		searchFrom = offset + inFindStr.length;
		offset = searchText.indexOf(inFindStr, searchFrom);
	}
	outText += inText.substring(searchFrom, inText.length);

	return (outText);
}

function replaceurl(inText) {
	inText = ReplaceAll(inText, '%', '%25');
	inText = ReplaceAll(inText, '&', '%26');
	inText = ReplaceAll(inText, '<', '%3C');
	inText = ReplaceAll(inText, '>', '%3E');
	inText = ReplaceAll(inText, '=', '%3D');
	inText = ReplaceAll(inText, '+', '%2B');
	inText = ReplaceAll(inText, '#', '%23');
	inText = ReplaceAll(inText, '?', '%40');
	outText = inText;
	return (outText);
}

function trim(str) {
	return str.replace(/^\s+|\s+$/g, "");
}

/*
 * ตรวจสอบรูปแบบ email
 */
function validateEmail(elementValue){
	  var emailPattern = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return emailPattern.test(elementValue);
}

$(document).ready(function() {

    $('textarea[maxlength]').keyup(function(){
        //get the limit from maxlength attribute
        var limit = parseInt($(this).attr('maxlength'));
        //get the current text inside the textarea
        var text = $(this).val();
        //count the number of characters in the text
        var chars = text.length;

        //check if there are more characters then allowed
        if(chars > limit){
            //and if there are use substr to get the text before the limit
            var new_text = text.substr(0, limit);

            //and change the current text with the new text
            $(this).val(new_text);
        }
    });

});

//*** ตรวจสอบหมายเลขบัตรประชาชน  กรณีที่เลือกประเภทเอกสารอ้างอิง เป็นบัตรประชาชน ***
function checkID(id){
	if(id.length != 13) return false;
    for(i=0, sum=0; i < 12; i++)
        sum += parseFloat(id.charAt(i))*(13-i);
    if((11-sum%11)%10!=parseFloat(id.charAt(12))) return false;
    return true;
}


//*** รับ id วันที่เริ่มต้น ถึง สิ้นสุด จำนวนวันที่จะไม่ให้เกิน และข้อความที่แจ้งเตือน
function validateDaily(startDate , endDate , day , msg , msg2){

	// Set the unit values in milliseconds.
    var msecPerMinute = 1000 * 60;
    var msecPerHour = msecPerMinute * 60;
    var msecPerDay = msecPerHour * 24;

	// Set a date and get the milliseconds: mm/dd/yyyy
    var sdate = new Date(startDate);

    var edate = new Date(endDate);

	var diff = edate.getTime() - sdate.getTime();
	var diffDay = diff / msecPerDay;

	if(edate.getTime()<sdate.getTime()){
		alert(msg2);
		return false;
	}

    if(diffDay > day){
    	alert(msg);
		return false;
	} else {
		return true;
	}
}
