function orderByHeaderWithEvent(myIndex, prifix, form, action, event) {
	if (event.ctrlKey == 1) {
	  multipleOrderByHeader(myIndex, prifix, form, action);
	} else {
	  singleOrderByHeader(myIndex, prifix, form, action);
	}
}

function multipleOrderByHeader(myIndex, prifix, form, action) {
	var elName = prifix;
	var size = document.getElementsByName(elName + 'Size')[0].value;
	var myTemp = document.getElementsByName(elName + 'Select')[0];

	myTemp.value = '';
	changeSeqOwner(elName + '[' + myIndex + '].seq');
	for (var i = 0; i < size; i++) {
		var seq = document.getElementsByName(elName + '[' + i + '].seq')[0].value;
		if (seq == '0') {
			if (myTemp.value.length == 0) {
				myTemp.value = i;
			} else {
				myTemp.value = myTemp.value + "," + i;
			}
		}
	}
	changeOrderOwner(elName + '[' + myIndex + '].order');

	form.action = action;
	form.submit();
}

function singleOrderByHeader(myIndex, prifix, form, action) {
	var elName = prifix;
	var size = document.getElementsByName(elName + 'Size')[0].value;
	var myTemp = document.getElementsByName(elName + 'Select')[0];
	myTemp.value = myIndex;

	for (var i = 0; i < size; i++) {
		if (myTemp.value != i) {
			clearOrderOwner(elName + '[' + i + '].order');
			clearSeqOwner(elName + '[' + i + '].seq');
		}
	}
	changeOrderOwner(elName + '[' + myTemp.value + '].order');
	changeSeqOwner(elName + '[' + myTemp.value + '].seq');

	form.action = action;
	form.submit();
}

function orderByHeader(myIndex, prifix, form, action) {
	var elName = prifix;
	var size = document.getElementsByName(elName + 'Size')[0].value;
	var myTemp = document.getElementsByName(elName + 'Select')[0];
	//alert(myTemp.value.indexOf(','));
	if (myTemp.value.length == 0) {
		myTemp.value = myIndex;
	} else if (myTemp.value.length == ((size * 2) - 1)) {
		var myTempArr = myTemp.value.split(',');
		var myTempArrIndex = -1;
		for (var i = 0; i < myTempArr.length; i++) {
			if (myTempArr[i] == myIndex) {
				myTempArrIndex = i;
				break;
			}
		}

		myTemp.value = myIndex;
		if (myTempArrIndex == -1) {
			for (var i = 0; i < size - 1; i++) {
				myTemp.value = myTemp.value + "," + myTempArr[i];
			}
		} else {
			for (var i = 0; i < myTempArr.length; i++) {
				if (i == myTempArrIndex) {
					continue;
				} else {
					myTemp.value = myTemp.value + "," + myTempArr[i];
				}
			}
		}
	} else {
		var index = myTemp.value.indexOf(',');
		if ((index == -1) && (myTemp.value == myIndex)) {
			myTemp.value = myIndex;
		} else {
			var myTempArr = myTemp.value.split(',');
			var myTempArrIndex = -1;
			for (var i = 0; i < myTempArr.length; i++) {
				if (myTempArr[i] == myIndex) {
					myTempArrIndex = i;
					break;
				}
			}

			if (myTempArrIndex == -1) {
				myTemp.value = myIndex + "," + myTemp.value;
			} else {
				myTemp.value = myIndex;
				for (var i = 0; i < myTempArr.length; i++) {
					if (i == myTempArrIndex) {
						continue;
					} else {
						myTemp.value = myTemp.value + "," + myTempArr[i];
					}
				}
			}
		}
	}

	for (var i = 0; i < size; i++) {
		document.getElementsByName(elName + '[' + i + '].seq')[0].value = '';
	}

	var index = myTemp.value.indexOf(',');
	if (index == -1) {
		document.getElementsByName(elName + '[' + myTemp.value + '].seq')[0].value = 0;
		if  (myTemp.value == myIndex) {
			changeOrderOwner(elName + '[' + myTemp.value + '].order');
		}
	} else {
		var myTempArr = myTemp.value.split(',');
		for (var i = 0; i < myTempArr.length; i++) {
			document.getElementsByName(elName + '[' + myTempArr[i] + '].seq')[0].value = i;
			if  (myTempArr[i] == myIndex) {
				changeOrderOwner(elName + '[' + myTempArr[i] + '].order');
			}
		}
	}
	form.action = action;
	form.submit();
}

function changeOrderOwner(elName) {
	var elHtml = document.getElementsByName(elName)[0];
	if (elHtml.value == '') {
		elHtml.value = 'ASC';
	} else if (elHtml.value == 'ASC') {
		elHtml.value = 'DESC';
	} else if (elHtml.value == 'DESC') {
		elHtml.value = 'ASC';
	}
}

function changeSeqOwner(elName) {
	var elHtml = document.getElementsByName(elName)[0];
	elHtml.value = '0';
}

function clearOrderOwner(elName) {
	var elHtml = document.getElementsByName(elName)[0];
	elHtml.value = 'ASC';
}

function clearSeqOwner(elName) {
	var elHtml = document.getElementsByName(elName)[0];
	elHtml.value = '';
}