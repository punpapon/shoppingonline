<%@ page contentType="text/html; charset=UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function closeView(){
		alert("SSS");
		document.forms[0].action = '<s:url value="/jsp/shopping/initAdminSale.action"/>';
		document.forms[0].submit();
	}
</script>
<title>Insert title here</title>
</head>
<body>
<div id="div_criteria"  >
			<table class="FORM" >
							<tr >
								<td class="BORDER"></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="BORDER"></td>
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL" ><s:text name="view.orderId"/></td>
								<td class="VALUE" style="color: blue;"><s:text name="adminSale.orderId" /></td>
								<td class="LABEL"><s:text name="view.fullName"/></td>
								<td class="VALUE" style="color: blue;"><s:text name="adminSale.fullName" /></td>
								<td class="BORDER"></td>
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"><s:text name="view.statusProduct"/></td>
								<td class="VALUE">
									<s:if test='adminSale.statusProduct eq "N" '>
										<s:textfield name="ยังไม่ได้จัดส่ง" value="ยังไม่ได้ส่ง" disabled="true"/>
									</s:if>
									<s:else>
										<s:textfield name="ส่งแล้ว" value="ส่งแล้ว" disabled="true"/>
									</s:else>
								</td>
								<td class="LABEL"><s:text name="view.shipDate"/><em>*</em></td>
								<td class="VALUE"><s:textfield name="adminSale.date" disabled="true" /></td>
								<td class="BORDER"></td>
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"><s:text name="view.shipNo"/><em>*</em></td>
								<td class="VALUE"><s:textfield name="adminSale.shipNo" disabled="true"/></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="BORDER"></td>
							</tr>							
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"><s:text name="view.remark"/></td>
								<td class="VALUE"><s:textarea name="adminSale.remark" disabled="true"/></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="BORDER"></td>							
							</tr>
							<tr>
								<td class="BORDER"></td>
								<td class="LABEL"></td>
								<td class="VALUE"></td>
								<td class="LABEL"></td>
								<td class="VALUE"><s:submit align="center" value="Close" onclick="closeView()"></s:submit></td>
								<td class="BORDER"></td>							
							</tr>
					</table>	
				</div> 			
								<!------------------------------------- Result --------------------------------->
							<div id="div_datatable" class="table table-striped">
					<b><s:text name="order.orderId"></s:text><s:property value="criteria.totalResult"/><s:text name="order.list" /></b>
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
							<s:if test="%{listOrder != null and listOrder.size() > 0}">
								<s:iterator value="listOrder" var="result" status="listResultStatus" >
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
			 				
</body>
</html>