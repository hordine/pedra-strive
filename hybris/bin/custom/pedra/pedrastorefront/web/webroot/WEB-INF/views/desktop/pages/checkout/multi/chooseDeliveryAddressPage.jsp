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
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/checkout/multi/choose-delivery-method" var="chooseDeliveryMethodUrl"/>

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

	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="1" stepName="deliveryAddress"/>
	
	<div class="span-20 last multicheckout">
		<div class="span-20 last checkout_multi_a ">
			<div class="item_container_holder">
				<div class="title_holder">
					<h2><span></span><spring:theme code="checkout.multi.deliveryAddress.stepHeader"/></h2>
				</div>
				<div class="item_container">
					<div class="left item_container">
						<p><spring:theme code="checkout.multi.deliveryAddress.selectAddressMessage"/></p>
					</div>
					<div class="right item_container">
						<ycommerce:testId code="chooseDeliveryAddress_addNewAddress_button">
							<a href="add-delivery-address" class="neutral right">
								<spring:theme code="checkout.multi.deliveryAddress.addAddress" text="Add new address"/>
							</a>
						</ycommerce:testId>
					</div>
					<multi-checkout:deliveryAddressSelector deliveryAddresses="${deliveryAddresses}" selectedAddressId="${cartData.deliveryAddress.id}" />
				</div>
			</div>
		</div>
		<c:if test="${not empty cartData.deliveryAddress.id}">
			<div class="span-20 last">
				<div class="span-10 right">
					<ycommerce:testId code="chooseDeliveryAddress_continue_link">
						<a href="${chooseDeliveryMethodUrl}" class="positive right">
							<spring:theme code="checkout.multi.deliveryAddress.continue"/>
						</a>
					</ycommerce:testId>
				</div>
			</div>
		</c:if>
			
		<multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="false"/>

	</div>
</template:page>