<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address" %>

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
		<div class="item_container_holder">
			<div class="title_holder">
				<h2><spring:theme code="checkout.multi.addressDetails" text="Address Details"/></h2>
			</div>
			<div class="item_container">
				<p><spring:theme code="checkout.multi.addEditform" text="Please use this form to add/edit an address."/></p>
				<p class="required"><spring:theme code="form.required" text="Fields marked * are required"/></p>

				<address:addressFormSelector supportedCountries="${countries}" regionsForCountries="${regionsForCountries}"
											 cancelUrl="/checkout/multi/choose-delivery-address"/>
			</div>
		</div>
		<address:suggestedAddresses selectedAddressUrl="/checkout/multi/select-suggested-address"/>
	</div>
</template:page>
