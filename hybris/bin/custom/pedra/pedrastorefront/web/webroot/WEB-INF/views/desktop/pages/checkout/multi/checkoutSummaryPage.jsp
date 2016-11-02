<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<spring:url value="/checkout/multi/placeOrder" var="placeOrderUrl" />
<spring:url value="/checkout/multi/termsAndConditions" var="getTermsAndConditionsUrl" />

<script type="text/javascript">
	var getTermsAndConditionsUrl = "${getTermsAndConditionsUrl}";
</script>

<template:page pageTitle="${pageTitle}">


	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>

	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>

	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="4" stepName="confirmOrder"/>
	
	<div class="span-20 last">

		<div class="span-20 last">
			<multi-checkout:summaryFlow deliveryAddress="${cartData.deliveryAddress}" deliveryMode="${deliveryMode}" paymentInfo="${paymentInfo}" requestSecurityCode="${requestSecurityCode}"  cartData="${cartData}"/>
		</div>

		<div class="span-20 last place-order-top">
			<form:form action="${placeOrderUrl}" id="placeOrderForm1" commandName="placeOrderForm">
				<c:if test="${requestSecurityCode}">
					<form:input type="hidden" class="securityCodeClass" path="securityCode"/>
				</c:if>
				<button type="button" class="positive right pad_right place-order placeOrderWithSecurityCode">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
				<dl class="terms right">
					<dt class="left">
						<form:checkbox id="Terms1" path="termsCheck" />
						<label for="Terms1"><spring:theme code="checkout.summary.placeOrder.readTermsAndConditions"/></label>
					</dt>
					<dd></dd>
				</dl>
			</form:form>
		</div>

		<div class="span-20 last">
			<c:if test="${cartData.deliveryItemsQuantity > 0}">
				<multi-checkout:deliveryCartItems cartData="${cartData}"/>
			</c:if>
			<c:forEach items="${cartData.pickupOrderGroups}" var="groupData" varStatus="status">
				<multi-checkout:pickupCartItems cartData="${cartData}" groupData="${groupData}" index="${status.index}"/>
			</c:forEach>
		</div>

		<div class="span-12">
			<cart:cartPromotions cartData="${cartData}"/>
		</div>

		<div class="span-8 right last place-order-cart-total">
			<cart:ajaxCartTotals/>
		</div>

		<div class="span-20 place-order-bottom">
			<%-- Dummy second form at bottom of page - submits the placeOrderForm1 above. --%>
			<form:form action="${placeOrderUrl}" id="placeOrderForm2" commandName="placeOrderForm">
				<button type="button" class="positive right pad_right place-order placeOrderWithSecurityCode">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
				<dl class="terms right">
					<dt class="left">
						<form:checkbox id="Terms2" path="termsCheck" />
						<label for="Terms2"><spring:theme code="checkout.summary.placeOrder.readTermsAndConditions"/></label>
					</dt>
					<dd></dd>
				</dl>
			</form:form>
		</div>
	</div>
</template:page>
