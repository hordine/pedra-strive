<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/checkout/multi/select-delivery-method" var="continueSelectDeliveryMethodUrl"/>

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
			<div class="span-11 checkout_multi_b last">
				<form:form id="selectDeliveryMethodForm" action="${continueSelectDeliveryMethodUrl}" method="get">
					<div class="item_container_holder">
						<div class="title_holder">
							<h2><span></span><spring:theme code="checkout.multi.deliveryMethod.stepHeader"/></h2>
						</div>
						<div class="item_container delivery_method_list">
							<p><spring:theme code="checkout.multi.deliveryMethod.selectDeliveryMethodMessage"/></p>
							<multi-checkout:deliveryMethodSelector deliveryMethods="${deliveryMethods}" selectedDeliveryMethodId="${cartData.deliveryMode.code}"/>
						</div>
					</div>
					<c:if test="${not empty cartData.deliveryMode.code}">
						<button id="chooseDeliveryMethod_continue_button" class="positive continue right pad_right">
							<spring:theme code="checkout.multi.deliveryMethod.continue" text="Continue"/>
						</button>
					</c:if>
				</form:form>
			</div>
		</div>
		<multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="false"/>
	</div>
</template:page>