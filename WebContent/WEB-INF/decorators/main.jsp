<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig"%>
<%
	String contextPath = request.getContextPath();
	String ua = request.getHeader( "User-Agent" );
%>
<html>
	<head>
		<title><%=ParameterConfig.getApplication().getTitle()%></title>
		<script type="text/javascript" src="<s:url value='/js/jquery-1.10.2.min.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/jquery-ui-1.10.3.custom.min.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/pagination.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/headersorts.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/inputvalidate.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/inputmethod.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/control.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/autocomplete.js' />"></script>
		<decorator:head/>
		<script type="text/javascript">
			jQuery(document).on("keydown",function(event){
				var keyCode = event.which || event.keyCode;
				if (document.getElementsByName('page').length > 0) {
					var page = document.getElementsByName('page')[0].value;
					if (page == 'SEARCH') {
						if (keyCode == 13) {
							var frominput = event.target.type;
							if (frominput == 'undefined') {
								return false;
							}
							//alert('frominput: ' + frominput);
							if ((frominput == 'text') || (frominput == 'textarea') || (frominput == 'select-one')
									|| (frominput == 'checkbox') || (frominput == 'radio') || (frominput == 'password') || (frominput == 'button')
									|| (frominput == 'submit')) {

								if (checkBrowser() != 'IE') {
									if (frominput == 'select-one') {
										searchPage();
										return false;
									} else {
										//alert('cacel submit');	
										return;
									}	
								} else {
									if (frominput == 'button') {
										//alert('cacel submit');	
										return;
									}
								}
							}
							searchPage();		
						} 
					}
				}
			});
		
			function profile() {
				alert('profile');
			}
					
		</script>
		<link href="<s:url value='/css/pagination.css' />" rel="stylesheet" type="text/css"/>
		<link href="<s:url value='/css/headersorts.css' />" rel="stylesheet" type="text/css"/>
		<link href="<s:url value='/css/autocomplete.css' />" rel="stylesheet" type="text/css"/>
		<!--[if IE]>
			<link href="<s:url value='/css/default-ie.css' />" rel="stylesheet" type="text/css"/>
			<link href="<s:url value='/css/default-ie-purple.css' />" rel="stylesheet" type="text/css"/>
		<![endif]-->
		<!--[if !IE]><!-->
			<link href="<s:url value='/css/default-not-ie.css' />" rel="stylesheet" type="text/css"/>
			<link href="<s:url value='/css/default-not-ie-purple.css' />" rel="stylesheet" type="text/css"/>
		<!--<![endif]-->
	</head>
	
	<body onload="sf();" class="margin-zero">
	
		<table class="body" style="margin-left: auto; margin-right: auto;">
			<tr>
				<td class="content"><s:include value="/jsp/template/banner.jsp"/></td>
			</tr>					
			<tr>
				<td class="content" style="border-top: none;">
					<s:include value="/jsp/template/header.jsp"/>
				</td>
			</tr>
			<tr>
				<td class="content">
					<div id="container">
						<div id="leftPanel" class="leftPanel">
							<UL><LI><a href='/shoppingonline/jsp/shopping/searchProductListCustomerSale.action'>Product</a></LI>
							<UL><LI><a href='/shoppingonline/jsp/shopping/searchProductListCustomerSale.action?pagee=1'>Dress</a></LI></UL>
							<UL><LI><a href='/shoppingonline/jsp/shopping/searchProductListCustomerSale.action?pagee=2'>Shoes</a></LI></UL>
							<UL><LI><a href='/shoppingonline/jsp/shopping/searchProductListCustomerSale.action?pagee=3'>Bags</a></LI></UL>
							<UL><LI><a href='/shoppingonline/jsp/shopping/gotoMyCartAddCustomerSale.action'>My Cart</a></LI></UL>
							</LI></UL>
							<s:if test='user.getActive() == "Y" '>
								<UL><LI><a href="#">Admin</a></LI>
								<UL><LI><a href='/shoppingonline/jsp/shopping/initAdminSale.action'>Order List</a></LI></UL>
								</UL>
							</s:if>
						</div>
						<div id="rightPanel" class="rightPanel">
							<decorator:body></decorator:body>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="content FOOTER_COLOR">
					<s:include value="/jsp/template/footer.jsp"/>
				</td>
			</tr>
		</table>
		<s:set name="cssClassYear" value="%{'YYYYDISPLAY w4em split-date statusformat-l-cc-sp-d-sp-F-sp-Y'}" scope="application"> </s:set>
		<script>
			new NumberControl();
			new MoneyControl();
			new TimeControl('<s:text name="10033" />');
			new TimeControlDefualtValue('<s:text name="10033" />','23:59');
		</script>
		
	</body>
	
</html>