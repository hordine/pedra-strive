<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template"%>
<%@ taglib prefix="checkout-cart" tagdir="/WEB-INF/tags/mobile/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/mobile/cart"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/mobile/user"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/mobile/checkout/multi"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/mobile/common"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<c:url value="/checkout/multi/summary" var="checkoutSummaryUrl" />
<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<script type="text/javascript" src="${commonResourcePath}/js/accmob.cart.js"></script>
	</jsp:attribute>
	<jsp:body>
		<div class="ui-grid-a" data-theme="d">
			<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="3" stepName="paymentMethod" />
			<div class="ui-grid-a item_container_holder" data-theme="b" data-role="content">
				<div class="ui-grid-a" data-theme="d">
					<h2>
						<spring:theme code="mobile.checkout.paymentMethod" />
					</h2>
					<h3 class="infotext">
						<spring:theme code="mobile.checkout.paymentMethod.addOrSelect.card" />
					</h3>
				</div>
				<div class="ui-grid-a" data-theme="d">
					<multi-checkout:paymentMethodSelector paymentMethods="${paymentMethods}" selectedPaymentMethodId="${selectedPaymentMethodId}" />
				</div>
			</div>
			<div class="checkoutOverviewItems">
				<div data-role="collapsible" data-collapsed="true" data-content-theme="b" data-theme="d">
					<div class="checkoutOverviewItemsHeadline">
						<spring:theme code="checkout.multi.deliveryAddress" />
					</div>
					<div class="ui-grid-a" data-theme="d">
						<multi-checkout:deliveryAddressDetails deliveryAddress="${cartData.deliveryAddress}" isSelected="true" url="choose-delivery-address" />
					</div>
				</div>
				<div data-role="collapsible" data-collapsed="true" data-content-theme="b" data-theme="d">
					<div class="checkoutOverviewItemsHeadline">
						<spring:theme code="checkout.multi.deliveryMethod.header" />
					</div>
					<div class="ui-grid-a" data-theme="d">
						<ul class="mFormList">
							<multi-checkout:deliveryMethodDetails id="${deliveryMethod.code}" deliveryMethod="${cartData.deliveryMode}" isSelected="true" />
						</ul>
					</div>
				</div>
				<div class="ui-grid-a" data-theme="b">
					<checkout-cart:summaryCartItems cartData="${cartData}" summary="true" />
				</div>
				<div class="ui-grid-a" data-theme="b">
					<checkout-cart:cartItemGroupsForPickUp cartData="${cartData}" summary="true" />
				</div>
				<div class="ui-grid-a" data-theme="b">
					<cart:cartPromotions cartData="${cartData}" />
				</div>
				<div class="ui-grid-a" data-theme="b">
					<cart:cartTotals cartData="${cartData}" />
				</div>
			</div>
		</div>
	</jsp:body>
</template:page>
