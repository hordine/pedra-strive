<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template" %>
<%@ taglib prefix="checkout-cart" tagdir="/WEB-INF/tags/mobile/checkout" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/mobile/cart" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/mobile/user" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/mobile/checkout/multi" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/mobile/common" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<script type="text/javascript" src="${commonResourcePath}/js/accmob.cart.js"></script>
	</jsp:attribute>
	<jsp:body>
		<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="1" stepName="deliveryAddress"/>
		<div class="ui-grid-a item_container_holder" data-theme="b" data-role="content">
			<div class="span-20 last">
				<div class="ui-grid-a" data-theme="b">
					<h2>
						<spring:theme code="mobile.checkout.deliveryAddress"/>
					</h2>
					<h3 class="infotext">
						<spring:theme code="mobile.checkout.deliveryAddress.selectAddressMessage"/>
					</h3>
				</div>
				<div class="ui-grid-a" data-theme="b">
					<multi-checkout:deliveryAddressSelector deliveryAddresses="${deliveryAddresses}"
															selectedAddressId="${cartData.deliveryAddress.id}"/>
				</div>
			</div>
		</div>
		<div class="checkoutOverviewItems">
			<div><checkout-cart:summaryCartItems cartData="${cartData}"/></div>
			<div><cart:cartPromotions cartData="${cartData}"/></div>
		</div>
	</jsp:body>
</template:page>
