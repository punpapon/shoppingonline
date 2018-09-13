/* ************ function ในการควบคุุม user event ******************/

/**
 *  flag keep alert status [true=alert ได้ , false= ไม่ต้อง alert]
 *  เนื่องจากเวลา alert แล้ว สั่ง ให้ไป focus จะมองเป็น onblur อีก ทำให้ alert ซ้ำมากกว่า 1 ครั้ง
 */
var dateControlAlertFlag = true;

/**
 *
 */
var numAfterPoint = 2;
var isIE = document.all?true:false;
/**
 * ในขณะที่ user พิม ให้ ควบคุม event date ตาม format ที่ต้องการ
 * onblur ต้องทำการ validate ก่อน / กัน user copy วาง
 * work must include dateUtil.js before
 * use for : input type=text , css=input_date
 */
function DateControl(msg){
	var inputDate = jQuery(".input_date");

	inputDate.maxLength(10);
	inputDate.blur(
		function(){
			if(trim(this.value).length< 10 && trim(this.value)!='' &&!isDate(this.value)){
				if(dateControlAlertFlag){
					alert(msg);

					dateControlAlertFlag = false;
					/* clear value*/
					jQuery(this).attr("value",'');
					/* แก้ ปัญหา การ focus ใน  fire fox หลังจากการ alert */
					setTimeout('document.getElementById("'+this.id+'").focus();',100);
				}
			}
		}
	);

	inputDate.keyup(
		function (e){
			dateControlAlertFlag = true;
			dtval(this,e);
			if(this.value.length>=10){
				if(!isDate(this.value)){
					alert(msg);
					dateControlAlertFlag = false;
					/* clear value*/
					jQuery(this).attr("value",'');
				}
			}
		}
	);

	inputDate.keypress(
		function (e){
			dateControlAlertFlag = true;
			return keysdate(e);
		}
	);

	inputDate.click(
		function (){
			dateControlAlertFlag = true;
			jQuery(this).select();
		}
	);

	inputDate.bind('paste', null, function(e) {
    if(!e.keyCode){
	      return false;
	    }
	});

}
function DateControl_Bak(){
	jQuery(".input_date").mask("99/99/9999");
	jQuery(".input_date").blur(
			function (){
				if(trim(this.value)!='' &&!isDate(this.value)){
					alert('<s:text name="10006" />');

					/* clear value*/
					jQuery(this).attr("value",'');

					/* แก้ ปัญหา การ focus ใน  fire fox หลังจากการ alert */
					this.focus();
				}
			}
	);
}
function DateControl_Bak2(){
	jQuery(".input_date2").mask("99/99");
	jQuery(".input_date2").blur(
			function (){


					this.focus();

			}
	);
}


/**
 * ป้อนได้เฉพาะตัวเลข
 * onblur ต้องทำการ validate ก่อน / กัน user copy วาง
 */
function NumberControl(){
	jQuery("input.input_number").keypress(
			function(e){
				return onlyDigits2(e,this,0);
			}
		);

	jQuery("input.input_number").bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		    }
		});

}

/**
 * ป้อนได้เฉพาะตัวเลข  กำหนด maxlength เอง
 * onblur ต้องทำการ validate ก่อน / กัน user copy วาง
 */
function NumberControl2(){
	jQuery("input.input_number").keypress(
			function(e){
				return onlyDigits2(e,this);
			}
		);

}

/**
 * ป้อนจำนวนเงินได้ เป็นตัวเลบ และใส่ comma ให้ด้วย
 * onblur ต้องทำการ validate ก่อน / กัน user copy วาง
 */
function MoneyControl(){

	jQuery("input.input_money").keypress(
		function(e){
			return onlyDigits2(e,this,numAfterPoint);
		}
	);

	jQuery("input.input_money").blur(
		function(){
			if(trim(this.value)!=''){
				this.value = commas(parseFloat(this.value).toFixed(numAfterPoint));
			}
		}
	);

	jQuery("input.input_money").click(
			function(){
				jQuery(this).select();
			}
	);

	jQuery("input.input_money").bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		    }
		});
}

function TextControl(){
	jQuery("input.input_text").keypress(
			function(e){
				var keynum = (e.which || e.keyCode);
				if(keynum==13){
					return false;
				}
			}
		);
}
/**
 * Time event coutrol function
 * @return
 */
//function TimeControl_Bak(){
//	jQuery("input.input_time").mask("99:99");
//	jQuery("input.input_time").keyup(
//			function (){
//				if(this.value.length==5 && this.value.indexOf('_')<0){
//					if(trim(this.value)!='' &&!isTime(this.value)){
//						alert(MSG['_10002']);
//
//						/* clear value*/
//						jQuery(this).attr("value",'');
//
//						/* แก้ ปัญหา การ focus ใน  fire fox หลังจากการ alert */
//						this.focus();
//					}
//				}
//			}
//	);
//
//}

function TimeControl(msg){
	jQuery("input.input_time").keyup(
			function (e){
				var keynum = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
				if(this.value.length==2 && keynum != 8){
					this.value = this.value + ":";
				}
				if(this.value.length==5){
					if(trim(this.value)!='' && !checkTime(this.value)){
						alert(msg);
						/* แก้ ปัญหา การ focus ใน  fire fox หลังจากการ alert */
						this.value = '00:00';
						this.focus();
					}
				}
			}

	);

	jQuery("input.input_time").bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		}
	});

	//Check Time Format on lost focus
	jQuery("input.input_time").blur(function(event){
		var val = trim(this.value);
		if(val.length > 0 && val.length < 5) {
			if(val.length < 5 || !checkTime(val)){
				alert(msg);
				this.value = '00:00';
				var that = this;
				setTimeout(function() { jQuery(that).focus(); }, 0); //แก้ปัญหาไป focus ที่ element อื่น ใน  FireFox
				return false;
			}
		}
	});

	//Prevent Enter if enter time not complete And Prevent character
	jQuery("input.input_time").keypress(function(e){

		var keycode = e.which || e.keycode;
		var val = trim(this.value);
		if(keycode == 13 && val.length > 0 && val.length < 5){
			return false;
		}

		return onlyDigits2(e,this,0);
	});

}

/**
 * ใช้กรณี input invalid เมื่อ alert เสร็จให้แสดงค่า นั้นกลับมาด้วย  ในที่นี้คือ  23:59
 * @param msg
 * @param dufualtValue
 */
function TimeControlDefualtValue(msg,defaultValue){
	jQuery("input.input_time_default_value").keyup(
			function (e){
				var keynum = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
				if(this.value.length==2 && keynum != 8){
					this.value = this.value + ":";
				}
				if(this.value.length==5){
					if(trim(this.value)!='' && !checkTime(this.value)){
						alert(msg);
						/* แก้ ปัญหา การ focus ใน  fire fox หลังจากการ alert */
						this.value = defaultValue;
						this.focus();
					}
				}
			}

	);

	jQuery("input.input_time_default_value").bind('paste', null, function(e) {
		if(!e.keyCode){
			return false;
		}
	});

	//Check Time Format on lost focus
	jQuery("input.input_time_default_value").blur(function(event){
		var val = trim(this.value);
		if(val.length > 0 && val.length < 5) {
			if(val.length < 5 || !checkTime(val)){
				alert(msg);
				this.value = defaultValue;
				var that = this;
				setTimeout(function() { jQuery(that).focus(); }, 0); //แก้ปัญหาไป focus ที่ element อื่น ใน  FireFox
				return false;
			}
		}
	});

	//Prevent Enter if enter time not complete And Prevent character
	jQuery("input.input_time_default_value").keypress(function(e){

		var keycode = e.which || e.keycode;
		var val = trim(this.value);
		if(keycode == 13 && val.length > 0 && val.length < 5){
			return false;
		}

		return onlyDigits2(e,this,0);
	});

}

/**
 * สำหรับควบคุมการทำงานของปุ่ม
 * - ไม่ต้องทำ เพราะ ส่วนมาก กดปุ่มแต่ละคน จะมีการทำงานที่หลากหลาย
 */
function a4jControl(){

}
/* ************ End function ในการควบคุุม user event ******************/


/* ************  function ในการควบคุุม  จุดทศนิยม ******************/
function DoublepointControl(blurOpt){
	var input = jQuery(".input_quantity");
	var input_b = jQuery(".input_balance");
	var input_m = jQuery(".input_moistuer");
	var input_p = jQuery(".input_percent");

	input.keypress(
		function(e){
			return onlyDigits2(e,this,pointQuantity);
		}
	);


	input.blur(
		function(){
			if(trim(this.value)!=''){
				this.value = this.value.replace(/,/g,'');
				this.value = commas(parseFloat(this.value).toFixed(pointQuantity));
			}
		}
	);
	input.click(
			function(){
				jQuery(this).select();
			}
	);
	input.bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		    }
		});

	input_b.keypress(
			function(e){
				return onlyDigits2(e,this,pointMoney);
			}
		);


	input_b.blur(
		function(){
			if(trim(this.value)!=''){
				this.value = this.value.replace(/,/g,'');
				this.value = commas(parseFloat(this.value).toFixed(pointMoney));
			}
		}
	);
	input_b.click(
			function(){
				jQuery(this).select();
			}
	);

	input_b.bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		    }
		});

	input_m.keypress(
			function(e){
				return onlyDigits2(e,this,pointMoisture);
			}
		);


	input_m.blur(
		function(){
			if(trim(this.value)!=''){
				this.value = this.value.replace(/,/g,'');
				this.value = commas(parseFloat(this.value).toFixed(pointMoisture));
			}
		}
	);
	input_m.click(
			function(){
				jQuery(this).select();
			}
	);
	input_m.bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		    }
		});

	input_p.keyup(
			function(e){
				var keyValue = parseFloat(this.value);
				var maxNo = parseFloat('100.00');
				if(keyValue > maxNo){
					this.value = '100.0';
					return false;
				}
				return true;
			}
	);


	input_p.blur(
		function(){
			var keyValue = parseFloat(this.value);
			var maxNo = parseFloat('100.00');
			if(keyValue > maxNo){
				this.value = '100.0';
			}
		}
	);
	input_p.click(
			function(){
				jQuery(this).select();
			}
	);
	input_p.bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		    }
		});


}

function onlyDigits2(event,element,numAfterPoint) {
	var num = new Array();
	var index;
	var keynum = (event.which || event.keyCode);
	if(keynum==37){// pointer left
		if(String.fromCharCode(event.which) != '%'){
			return true;
		}
	 }
	 else if(keynum==39){// pointer right
		if(String.fromCharCode(event.which) != '\''){
			return true;
		}
	 }

	if(numAfterPoint == 0 && keynum == 46){
		return false;
	}

	if ((keynum >= 48 && keynum <= 57) || keynum == 46){
		if(keynum == 46){
			return true;
		}
		else{
			index = element.value.indexOf('.', 0);
			if(index > 0){
				num = element.value.split('.');

				if(num[1].length > numAfterPoint-1){
					if(isIE){
						var selectTxt = document.selection.createRange().text;
						if(selectTxt!=null && selectTxt!=""){
							element.value="";
							return true;
						}
					}else{
					    var startPos = element.selectionStart;
					    var endPos = element.selectionEnd;
					    var selectedText = element.value.substring(startPos, endPos)
						if(selectedText.length>0){
							element.value="";
							return true;
						}
					}

					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
	 }else if(keynum == 0){
		 return true;
	 }else if(keynum == 9){
		 return true;
	 }else if(keynum == 8){
		 return true;
//	 }else if(keynum == 37 || keynum == 39){ // because 39 = '
	 }else if(keynum == 13 ){
		 return true;
	 }else if(keynum == 46 ){ // Delete button
		 return true;
	 }else{
	    return false;
	 }
	return false;
}
function commas(nStr){
    nStr = nStr.replace(/,/g,"");
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;

	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	var result = x1 + x2;
	if(isNaN(result.replace(/,/g,""))){
		result = '';
	}
	return result;
}

function checkTime(s){
	  if(s == null) return false;
	  if(s.indexOf(":") == -1) return false;
	  var spl = s.split(":");
	  return parseInt(spl[0], 10) < 24 && parseInt(spl[1], 10) < 60;
}





