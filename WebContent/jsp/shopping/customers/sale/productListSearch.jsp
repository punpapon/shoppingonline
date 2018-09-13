<%@page import="com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearch"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://util.paginationtagiii" prefix="pagination-tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style> 
#product {
  
    border: 1px solid black;
    float: left;
}

#example2 {
    box-sizing: border-box;
    width: 300px;
    height: 100px;
    padding: 30px;    
    border: 10px solid blue;
}
#borderblack {
    border: 1px solid black;
    } 
h4 {
	text-align: center;
	color : red;
}
p.textred {
	border: 1px solid black;
	text-align: center;
		color : red;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
function sf(){
	
}
function addchart(product) {
	alert(product);
	var productId = product;
	window.location="/shoppingonline/jsp/shopping/searchProductListCustomerSale.action?product="+productId;
    <%
	 	 String text = (String)request.getParameter("product"); 
		// session.setAttribute("ProductId", text); 
		 System.out.println("PRODUCT:" + text);
	%>  
	
}
</script>
</head>
<body>

	Product List Search
	<div style="width: 100%">
	
	 <s:iterator value="lstResult" var="user">
	 <table id="borderblack" width="300px">	    	
	    	<tr>
	    	
	    	<td id="borderblack" width="30%"><img src="<s:url value='/images/%{#user.type_id}/%{#user.code}.jpg' />" width="80px" height="120px" /></td>
		    	
		    	<td align="center" id="borderblack" width="70%">
			    	<s:text name="view.no"/>&nbsp;&nbsp;<s:property value="#user.code" /><br>
			    	<s:property value="#user.product_desc" /><br>
			    	<s:text name="view.total"/>&nbsp;&nbsp;<s:property  value="#user.price" />&nbsp;&nbsp;<s:text name="view.bath"/><br>
			    	<s:text name="view.count"/>&nbsp;&nbsp;<s:property value="#user.stock_num" />&nbsp;&nbsp;<s:text name="view.a"/><br><br>
			    		<s:if test="#user.stock_num != 0" >
			    			<s:submit name="%{#user.code}" value="Add to Cart" onclick="addchart(name)"> </s:submit>    			
			    		</s:if>
			    		<s:else>
			    			<h4><s:text name="view.productOut" /></h4>
			    		</s:else>
	
		    	</td> 
		   		
		    </tr>
    	</table>
    	
 	</s:iterator>
 	</div>
 	<!-- Page Navigator -->
   <table class="total-record">
   <thead>
    <tr>
     <th class="left"><s:text name="view.count" />&nbsp;<s:property value="criteria.totalResult" />&nbsp;<s:text name="view.a"/></th>
     <th class="right">
      <%String param ="criteriaKey="+request.getAttribute("criteriaKey")+"&functioncode="+request.getAttribute("functioncode");%>
     <%--  <pagination-tag:pager 
       start=
       range='<%=String.valueOf(request.getAttribute("linePerPage"))%>'
       results=
       parameter="<%=param %>"
       title="yes"
       action="searchOffBalance.action"
       styleClass="navigate" 
      /> 
      <pagination-tag:pager results='<%=String.valueOf(request.getAttribute("totalResult")) %>' form="" action="searchOffBalance.action" start='<%=String.valueOf(request.getAttribute("start"))%>' range='<%=String.valueOf(request.getAttribute("linePerPage"))%>'/> --%>
   
      </th>
     </tr>
    </thead>
   </table>
 	<s:debug />
</body>
</html>