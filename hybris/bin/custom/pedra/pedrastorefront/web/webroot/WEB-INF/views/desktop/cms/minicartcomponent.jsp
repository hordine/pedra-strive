<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>

<c:url value="/cart/miniCart/${totalDisplay}" var="refreshMiniCartUrl"/>
<c:url value="/cart/rollover/${component.uid}" var="rolloverPopupUrl"/>

<script id="miniCartTemplate" type="text/x-jquery-tmpl">
/*<![CDATA[*/
	<dt><ycommerce:testId code="miniCart_items_label"><spring:theme text="items" code="cart.items" arguments="{{= totalItems}}"/></ycommerce:testId> - </dt>
	<dd><ycommerce:testId code="miniCart_total_label">
			<c:if test="${totalDisplay == 'TOTAL'}">{{= totalPrice.formattedValue}}</c:if>
			<c:if test="${totalDisplay == 'SUBTOTAL'}">{{= subTotal.formattedValue}}</c:if>
			<c:if test="${totalDisplay == 'TOTAL_WITHOUT_DELIVERY'}">{{= totalNoDelivery.formattedValue}}</c:if>
	</ycommerce:testId></dd>
/*]]>*/
</script>

<script type="text/javascript">
var rolloverPopupUrl = '${rolloverPopupUrl}';
var refreshMiniCartUrl = '${refreshMiniCartUrl}/?';
</script>


<div id="cart_header">
	<div id="cart_content">
		<c:url value="/cart" var="cartUrl"/>
		<a href="${cartUrl}">
			<h2>${component.title}</h2>
			<theme:image code="img.iconCart" />
		</a>
		<dl id="minicart_data">
			<dt>
				<ycommerce:testId code="miniCart_items_label">
					<spring:theme text="items" code="cart.items" arguments="${totalItems}"/>
				</ycommerce:testId>
				-
			</dt>
			<dd>
				<ycommerce:testId code="miniCart_total_label">
					<c:if test="${totalDisplay == 'TOTAL'}">
						<format:price priceData="${totalPrice}"/>
					</c:if>
					<c:if test="${totalDisplay == 'SUBTOTAL'}">
						<format:price priceData="${subTotal}"/>
					</c:if>
					<c:if test="${totalDisplay == 'TOTAL_WITHOUT_DELIVERY'}">
						<format:price priceData="${totalNoDelivery}"/>
					</c:if>
				</ycommerce:testId>
			</dd>
		</dl>
	</div>
	<ul>
		<li class="active">
			<ycommerce:testId code="miniCart_viewCart_link">
				<a href="${cartUrl}" class="doCheckoutBut"><spring:theme text="VIEW CART" code="cart.checkout"/></a>
			</ycommerce:testId>
		</li>
	</ul>
</div>
<cart:rolloverCartPopup/>