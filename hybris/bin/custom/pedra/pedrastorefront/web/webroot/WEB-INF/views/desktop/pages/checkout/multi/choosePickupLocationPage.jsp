<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>

<c:url value="/checkout/multi/choose-delivery-location" var="continueToDeliveryLocationUrl"/>
<c:url value="/checkout/multi/choose-payment-method" var="continueToPaymentMethodUrl"/>

<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
		<cart:cartValidation/>
	</div>
	
	<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>

	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="2" stepName="deliveryMethod"/>

	<div class="span-20 last">
		<div class="span-20 last">
			<div class="span-9 first checkout_multi_a complete ">
				<div class="item_container_holder">
					<div class="title_holder">
						<h2>
							<span></span>
							<spring:theme code="checkout.multi.deliveryAddress.stepHeader.done"/>
						</h2>
					</div>
					<div class="item_container delivery_addresses_list">
						<multi-checkout:selectedDeliveryAddressDetails deliveryAddress="${cartData.deliveryAddress}" cartData="${cartData}"/>
					</div>
				</div>
			</div>
			<div class="span-11 last checkout_multi_b complete">
				<div class="item_container_holder">
					<div class="title_holder">
						<h2>
							<span></span>
							<spring:theme code="checkout.multi.deliveryMethod.stepHeader.done"/>
						</h2>
					</div>
					<div class="item_container delivery_method_list">
						<multi-checkout:selectedDeliveryMethodDetails deliveryMethod="${cartData.deliveryMode}" cartData="${cartData}"/>
					</div>
				</div>
			</div>
		</div>
		<multi-checkout:pickupConsolidationOptions cartData="${cartData}" pickupConsolidationOptions="${pickupConsolidationOptions}"/>		
		<multi-checkout:pickupGroups cartData="${cartData}"/>
		<form:form id="selectDeliverylocationForm" action="${continueToPaymentMethodUrl}" method="get">	
			<button id="chooseDeliveryLocation_continue_button" class="positive continue right pad_right">
				<spring:theme code="checkout.pickup.continue.button" text="Continue"/>
			</button>
		</form:form>		
	</div>
</template:page>