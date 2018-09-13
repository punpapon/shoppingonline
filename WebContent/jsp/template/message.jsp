<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<div style="padding-bottom: 7px; padding-top: 7px;">
<s:if test='%{getAlertMaxExceed().equals("C"}'>
	<table class="FORM">
		<tr>
			<td class="MESSAGE">&nbsp;</td>
		</tr>
	</table>
</s:if> 
<s:else>
	<table class="FORM" style="table-layout: fixed;" id="tblMessage">
		<s:if test='%{ActionMessages[0] == "E"}'>
			<tr>
				<td class="MESSAGE ERROR" style="width: 40%; text-align: right;">
					<img src="<s:url value='/images/message/i_error.png' />" class="MESSAGE"/>
				</td>
				<td class="MESSAGE ERROR" style="width: 60%; text-align: left;">
	
					<s:property value="ActionMessages[1]" escapeHtml="false"/>
					<s:if test='%{ActionMessages[2] != null}'>
						<a class="LINK" href="javascript:void(0);" onclick="showErrorDetail('messageDetail')">detail</a>	
							<div id='messageDetail' style="display: none;"><s:property  value="ActionMessages[2]" escapeHtml="false"/></div>
					</s:if>
				</td>
			</tr>
		</s:if>
		<s:elseif test='%{ActionMessages[0] == "W"}'>
			<tr>
				<td class="MESSAGE WARING" style="width: 40%; text-align: right;">
					<img src="<s:url value='/images/message/i_waring.png' />" class="MESSAGE" />
				</td>
				<td class="MESSAGE WARING" style="width: 60%; text-align: left;">
					<s:property value="ActionMessages[1]" escapeHtml="false"/>
					<s:if test='%{ActionMessages[2] != null}'>
						<a class="LINK" href="javascript:void(0);" onclick="showErrorDetail('messageDetail')">detail</a>	
							<div id='messageDetail' style="display: none;"><s:property  value="ActionMessages[2]" escapeHtml="false"/></div>
					</s:if>
				</td>
			</tr>
		</s:elseif>
		<s:elseif test='%{ActionMessages[0] == "S"}'>
			<tr>
				<td class="MESSAGE SUCCESS" style="width: 40%; text-align: right;">
					<img src="<s:url value='/images/message/i_success.png' />" class="MESSAGE"/>
				</td>
				<td class="MESSAGE SUCCESS" style="width: 60%; text-align: left;">
					<s:property value="ActionMessages[1]" escapeHtml="false"/>
					<s:if test='%{ActionMessages[2] != null}'>
						<a class="LINK" href="javascript:void(0);" onclick="showErrorDetail('messageDetail')">detail</a>	
							<div id='messageDetail' style="display: none;"><s:property  value="ActionMessages[2]" escapeHtml="false"/></div>
					</s:if>
				</td>
			</tr>
		</s:elseif>
		<s:else>
			<tr>
				<td class="MESSAGE">&nbsp;</td>
			</tr>
		</s:else>
	</table>
	
	<table class="FORM" style="table-layout: fixed; display: none" id="tblMessageEmpty">
		<tr>
			<td class="MESSAGE">&nbsp;</td>
		</tr>
	</table>
</s:else>
</div>