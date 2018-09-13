<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function alertLogout(){
	//var x = confirm("คุณต้องการออกจากโปรแกรมหรือไม่");
	//if(x==true){
		document.getElementsByName('criteria.criteriaKey')[0].value = '';
		document.forms[0].action = '<s:url value="/jsp/security/logoutLogin.action" />';
		document.forms[0].submit();
		
}
</script>
</head>
<table class="header">
	<tr>
		<td width="50%"></td>
		<td width="35%"></td>
		<td width="15%"></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td align="left"><s:text name="header.welcome"/>&nbsp;&nbsp;<s:property value="user.getFullName()"/></td>
	</tr>
	<tr>
		<td ><h1><b><s:text name="header.happy"/></b></h1></td>
		<td ></td>
		<td align="center" style="color: blue;"><b><a href='/shoppingonline/jsp/security/logoutLogin.action'><u>Logout</u></a></b></td>
		
	</tr>
</table>

</html>
