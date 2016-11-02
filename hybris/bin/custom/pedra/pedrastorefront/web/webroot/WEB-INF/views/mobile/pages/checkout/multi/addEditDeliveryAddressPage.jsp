<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/mobile/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/mobile/common" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/mobile/address"%>

<template:page pageTitle="${pageTitle}">
	<jsp:body>
		<div data-theme="e" class="item_container_holder">
			<h3>
				<spring:theme code="checkout.multi.addressDetails" text="Address Details"/>
			</h3>

			<p></p>

			<p class="continuous-text"><spring:theme code="checkout.multi.addEditform" text="Please use this form to add/edit an address."/></p>
			<p class="continuous-text"><spring:theme code="form.required" text="Fields marked * are required"/></p>

			<address:addressFormSelector supportedCountries="${countries}" regionsForCountries="${regionsForCountries}"
										 cancelUrl="/checkout/multi/choose-delivery-address"/>
			<address:suggestedAddresses selectedAddressUrl="/checkout/multi/select-suggested-address"/>
		</div>
	</jsp:body>
</template:page>
