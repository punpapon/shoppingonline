<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function search() {
		document.forms[0].action = '<s:url value="/jsp/shopping/searchAdminSale.action"/>';
		document.forms[0].submit();
	}
	function clear() {
		document.forms[0].action = '<s:url value="/jsp/shopping/initAdminSale.action"/>';
		document.forms[0].submit();
	}
	function gotoView() {
		document.forms[0].action = '<s:url value="/jsp/shopping/gotoViewAdminSale.action"/>';
		document.forms[0].submit();
	}
</script>
<title>Insert title here</title>
</head>
<body>

<s:form id="searchForm" name="searchForm" method="post" namespace="/jsp/shopping" action="initAdminSale">
	 <div id="div_criteria" class="RESULT_BOX BORDER_COLOR">
	
				<table class="FORM">
							<tr style="display: none;">
								<td class="BORDER"></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="BORDER"></td>
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"><s:text name="order.orderId"/></td>
								<td class="VALUE"><s:textfield name="criteria.orderId" /></td>
								<td class="LABEL"><s:text name="order.status"/></td>
								<td class="VALUE"><s:select name="criteria.statusProduct"
									list="#{'Y':'จัดส่งแล้ว','N':'ยังไม่ได้จัดส่ง'}" 
									headerKey=""
									headerValue="ทั้งหมด" 									
									cssClass = "combox"
									/></td>
								<td class="BORDER"></td>
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"><s:text name="order.countPage"/></td>
								<td class="VALUE"><s:select id="criteria_linePerPage" cssClass="lpp-style clearform" name="criteria.linePerPage"  list="LPP" /></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="BORDER"></td>
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="LABEL"><s:submit value="Search" onclick="search()"/></td>
								<td class="VALUE"><s:submit value="Clear" onclick="clear()"/></td>
								<td class="BORDER"></td>
							</tr>
										
				</table>
					</div>
					
					<!------------------------- Result ------------------------- -->
					
					 
					<div id="div_datatable" class="table table-striped">
					<b>จำนวนรายการสั่งซื้อ <s:property value="criteria.totalResult"/> รายการ</b>
						<table>
							<thead>
								<tr>
									<th style="width: 75px;"><s:text name="order.no"/></th>						
									<th style="width: 200px;"><s:text name="order.orderId"/></th>
									<th style="width: 200px;"><s:text name="order.date"/></th>
									<th style="width: 200px;"><s:text name="order.fullName"/></th>
									<th style="width: 200px;"><s:text name="order.total"/></th>
									<th style="width: 200px;"><s:text name="order.status"/></th>
									<th style="width: 100px;"><s:text name="order.show"/></th>
									<th style="width: 100px;"><s:text name="order.ship"/></th>
									<th style="width: 100px;"><s:text name="order.cancel"/></th>
								</tr>
							</thead>
							<tbody>
							<s:if test="%{lstResultO != null and lstResultO.size() > 0}">
								<s:iterator value="lstResultO" var="result" status="listResultStatus" >
									<tr>
										<td align="center"><s:property value="#listResultStatus.index + 1"/></td>
										<td align="center"><s:property value="#result.orderId" /></td>
										<td align="center"><s:property value="#result.orderName" /></td>
										<td align="center"><s:property value="#result.fullName" /></td>
										<td align="center"><s:property value="#result.totalValue" /></td>
										<td align="center">							
											<s:if test='%{#result.statusShip eq "Y"}'>
												<s:text name="ส่งแล้ว" />											
											</s:if>
											<s:else>
												<s:text name="ยังไม่ได้ส่ง" />	
											</s:else>
											</td>
										<td align="center"><img src="<s:url value='/images/icon/i_search.png' />" width="30px" height="30px" onclick="gotoView()"/></td>
										<td align="center">
											<s:if test='%{#result.statusShip eq "Y"}'>
												<img src="<s:url value='/images/icon/plane_off.png' />" width="30px" height="30px"/>						
											</s:if>
											<s:else>
												<img src="<s:url value='/images/icon/plane.png' />" width="30px" height="30px"/>
											</s:else>
										</td>
										<td align="center"><img src="<s:url value='/images/icon/idea_off.png' />" width="30px" height="30px"/></td>
									</tr>
								</s:iterator>
								</s:if> 
								<s:else>
					    			<tr><td colspan="9">EMPTY</td>
					    			</tr>
					    		</s:else>
							</tbody>
						</table>
					</div>
				 
				<!------------------------------------- BUTTON ------------------------------------->
	   	<%-- <div style="display: ;"><s:include value="/jsp/template/hiddenSearchForDatatable.jsp" /></div> --%>
	    <s:hidden name="adminSale.id" />
	    <s:token/>
				</s:form> 
</body>


</html>