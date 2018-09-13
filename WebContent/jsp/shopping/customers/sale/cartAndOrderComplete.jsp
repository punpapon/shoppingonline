<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
function sf(){ 
	
}

</script>
</head>
<body>
	<table class="FORM">
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;"></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;text-align: center;font-size: 20px;"><s:text name="orderComplete.complete" /></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;"><center><img src="<s:url value='/images/icon/qrcode.png' />" width="300px" height="250px" /></center></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;text-align: center;font-size: 20px;"><s:text name="orderComplete.orderId"/></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;text-align: center;font-size: 15px;"><s:text name="orderComplete.customerTran"/></td></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;text-left: center;font-size: 15px;"><s:text name="orderComplete.KBank"/></td></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;text-left: center;font-size: 15px;""><s:text name="orderComplete.SCB"/></td></td>
			<td style="width: 15%;"></td>
		</tr>
		<tr>
			<td style="width: 15%;"></td>
			<td style="width: 70%;text-align: center;font-size: 15px;""><s:text name="orderComplete.detail"/></td></td>
			<td style="width: 15%;"></td>
		</tr>
	</table>
</body>
</html>