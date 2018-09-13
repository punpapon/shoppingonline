<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function sf(){
		frm = document.welcomeForm;
		frm.action="<s:url value='/jsp/security/initLogin.action' />";
		frm.submit();
	}
</script>
</head>
<body onload="sf()">
	<s:form name="welcomeForm" action="checkLogin" namespace="/jsp/security" method="POST" cssClass="margin-zero">
	</s:form>
</body>
</html>