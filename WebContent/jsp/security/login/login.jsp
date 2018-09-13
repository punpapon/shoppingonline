<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig"%>
<html>
<head>
<style type="text/css">
	td.textGreen {
		color : #00cc00;
	}
</style>
<title><%=ParameterConfig.getApplication().getTitle()%></title>
<!--[if IE]>
	<link href="<s:url value='/css/login-ie.css' />" rel="stylesheet" type="text/css"/>
<![endif]-->
<!--[if !IE]><!-->
	<link href="<s:url value='/css/login-not-ie.css' />" rel="stylesheet" type="text/css"/>
<!--<![endif]-->
<script type="text/javascript" src="<s:url value='/js/jquery-1.10.2.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/inputvalidate.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/inputmethod.js' />"></script>
<script type="text/javascript">
	function sf() {
		
	}

	function login() {
		frm = document.loginForm;
		if (validate(frm.id, '<s:text name="10002"/>') == false) {
			//$("p").text("passs");
			return false;
		}
		submitPage(frm, "<s:url value='/jsp/security/checkLogin.action' />");
	}
	
	function refreshCaptCha() {	
		alert('szx');
		submitPage("<s:url value='/captchaImageServlet' />");
		
	}
</script>
</head>
<body onload="sf();">
<s:form id="loginForm" name="loginForm" action="checkLogin" namespace="/jsp/security" method="post" cssClass="margin-zero" onsubmit="return false;">
	<br/>
	<br/>
	<br/>
	<br/>
	<table class="FORM">
		<tr>
			<td style="width: 40%">&nbsp;</td>
			<td>
			<div id="flipbox" class="flipbox" style="height: 400px; width: 450px; margin: 0 auto;">
				<div id="login" class="login_form">
					<div class="login_title"><s:text name='login.sign_in'/></div>
						<div style="padding-bottom: 7px; padding-top: 7px;">
							<table class="FORM">
								<tr>
									<td class="MESSAGE">&nbsp;</td>
								</tr>
							</table>
						</div>
					<div class="login_body" style="margin-top: -5px;">
						<table class="FORM">
							<tr>
								<td style="width : 25%;"></td>
								<td style="width : 50%;"><p id="failId"></p></td>
								<td style="width : 24%;"></td>
							</tr>
							<tr>
								<td style="width: 25%;" class="LABEL"><s:text name="login.username"/><em>*</em></td>
								<td style="width: 50%;" class="">
									<s:textfield cssClass="requireInput" cssStyle="width: 95%;" name="username" id="username" value = "arunya.k" autocomplete="off" maxlength="20"
									/>
								</td>
								<td style="width: 24%;" class="LABEL">&nbsp;</td>
							</tr>
							<tr>
								<td style="width: 25%;" class="LABEL"><s:text name="login.password"/><em>*</em></td>
								<td style="width: 50%;" class=""><s:password cssClass="requireInput"  cssStyle="width: 95%;" name="password" id="password" value = "Fl,k4k" autocomplete="off" maxlength="20"/></td>
								<td style="width: 24%;" class="LABEL">&nbsp;</td>
							</tr>
							<s:if test='%{captchaEnable == "Y"}'>
							<tr>
								<td style="width : 25%"></td>
								<td class="textGreen" style="width : 50%"><s:text name="login.captcha"/></td>
								<td style="width : 24%"></td>
							</tr>
							<tr>
								<td style="width: 25%;" class="LABEL"></td>
								<td style="width: 50%;" class=""><s:textfield cssClass="requireInput" cssStyle="width: 95%;" name="captcha" id="captcha" autocomplete="off" maxlength="6"/></td>
								<td style="width: 24%;"></td>
							</tr>
							<tr>
								<td style="width: 25%;" class="LABEL"></td>
								<td style="width: 50%;" class=""><img id="captchaImg" src="<s:url value='/captchaImageServlet'/>" style="width: 100%;" /></td>
								<td style="width: 24%; vertical-align:top; padding-left: 10px;"><img onclick="refreshCaptCha();" src="<s:url value='/images/icon/i_refresh.png' />" /></td>
							</tr>
							
							<tr>
								<td style="width: 25%;" class="LABEL"></td>
								<td style="width: 50%;text-align: right;">
									<button id="btnRegister" class="btnLoginUser" onclick="login();">
										&nbsp;<s:text name="login.sign_in"/>&nbsp;
									</button>
								</td>
								<td style="width: 24%; padding-left: 10px;"><a class="LINK" id="forgotUserEnp" href="javascript:void(0);" onclick="flippy();"><s:text name="login.forgotPassword"/></a></td>
							</tr>
							</s:if>
							<s:else>
							<tr>
								<td style="width: 25%;" class="LABEL"></td>
								<td style="width: 50%;text-align: right;">
									<button id="btnRegister" class="btnLoginUser" onclick="login();">
										&nbsp;<s:text name="login.sign_in"/>&nbsp;
									</button>
								</td>
								<td style="width: 24%; padding-left: 10px;"><a class="LINK" id="forgotUserEnp" href="javascript:void(0);" onclick="flippy();"><s:text name="login.forgotPassword"/></a></td>
							</tr>
							</s:else>
						</table>
					</div>
				</div>
				</div>
			</td>
		<td style="width: 40%">&nbsp;</td>
		</tr>
	</table>
<s:hidden name="page" />   <!-- เปลี่ยนจาก pagestatus เป็น page -->
<s:hidden name="captchaEnable"/>
</s:form>
<s:token />
<s:debug />
</body>
</html>