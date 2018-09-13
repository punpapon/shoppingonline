<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript">
function sf(){ 
	var sum = document.getElementById("sum").innerText;
	alert("sum :" + sum);
	//document.getElementById("sum").innerHTML = "sum";
}
function order(){ 
	if(confirm('คุณต้องการสั่งซื้อสินค้า ?') == false){ 
		return false;
	}
	
	frm = document.cartAndOrderAddForm;
	submitPage(frm, "<s:url value='/jsp/shopping/orderCompleteCustomerSale.action' />");
}
function backtoSearch(){ 
	
	frm = document.cartAndOrderAddForm;
	submitPage(frm, "<s:url value='/jsp/shopping/searchProductListCustomerSale.action' />");
}
function openPopUp(img)
{
	//alert("XX :" + img);
	window.open("thumbnailCustomerSale.action",null,
	"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no"); 
}

</script>
</head>
<body>
	<s:form name="cartAndOrderAddForm" method="post" cssClass="margin-zero">
		My Cart
		
		
					<div style="width: 100%;">
						<table class="table table-striped">
							<thead>
								<tr>
									<td align="center" style="width: 150px;"><s:text name="chart.productId"/></td>						
									<td align="center" style="width: 50px;"></td>
									<td align="center" style="width: 200px;"><s:text name="chart.productDesc"/></td>
									<td align="center" style="width: 100px;"><s:text name="chart.value"/></td>
									<td align="center" style="width: 100px;"><s:text name="chart.count"/><em>*</em></td>
									<td align="center" style="width: 100px;"><s:text name="chart.sumValue"/></td>
									<td align="center" style="width: 100px;"><s:text name="chart.delete"/></td>			
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listResult" var="result" status="listResultStatus" > 
									<s:url action="thumbnailCustomerSale.action" var="episodio">
								        <s:param name="id">
								            <s:property value="#result.productId" /> 
								        </s:param>
								    </s:url>
								    <s:set var="picId" >
								    	 <s:property value="#result.productId" />
								    </s:set>
								    <s:set var="total" value="0"/>
									<tr>									
										<td align="center"><s:property value="#result.productId" /></td>
										<td align="center"><img src="<s:url value='/images/%{#result.typeId}/%{#result.productId}.jpg' />" width="50px" height="50px" onclick="openPopUp()" /></td>
										<td align="center"><s:property value="#result.productDesc" /></td>
										<td align="center"><s:property value="#result.value" /></td>
										<td align="center"><s:textfield name="criteria.countProduct" value="%{#result.count}"/></td>
										<td align="center"><p id="listResultStatus" ><s:property value="#result.sumValue" /></p></td>
										<td align="center"><a href="%{#result.productId}" ><img src="<s:url value='/images/icon/i_del.png' />" width="20px" height="20px" /></a></td>
										<s:set var="total" value="%{#total = total+#result.sumValue}" />
									</tr>
									<s:set id="sum" var="sumValue" value="#result.sumValue"/>
								</s:iterator>
							</tbody>
						</table>
					</div>				
					<s:label name="total" value="%{#total}" label="Total"/>				
		<center><button id="btnBack" class="submitBtn" type="button"
			onclick="return order();">
			<span> <img src="<s:url value='/images/icon/i_add.png'/>">
					<br /> &nbsp;<s:text name="Order" />&nbsp; </span>
		</button>
		<button id="btnBack" class="submitBtn" type="button"
			onclick="return backtoSearch();">
			<span> <img src="<s:url value='/images/icon/i_back.png'/>">
					<br /> &nbsp;<s:text name="Back" />&nbsp; </span>
		</button></center>		
		<s:hidden name="criteria.criteriaKey" />
		<s:token />
	</s:form>
</body>
</html>