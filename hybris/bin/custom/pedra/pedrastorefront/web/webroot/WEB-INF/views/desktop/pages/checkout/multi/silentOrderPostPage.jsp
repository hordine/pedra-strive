<%@page import="com.pedra.ideal.constants.IdealpaymentConstants"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/checkout/multi/choose-payment-method" var="choosePaymentMethodUrl"/>
<c:set var="PAY_WITH_IDEAL_URL" value="<%=IdealpaymentConstants.PAY_WITH_IDEAL_URL%>" />
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

	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="3" stepName="paymentMethod"/>

    <c:if test="${not empty paymentFormUrl}">
		<div class="span-20 last multicheckout silent-order-post-page">
			<div class="item_container_holder">
				<div class="title_holder">
					<div class="title">
						<div class="title-top"><span></span></div>
					</div>
					<h2>
						<spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.header" text="Payment Details"/>
					</h2>
				</div>
				<div class="item_container">					
					<ycommerce:testId code="paymentDetailsForm">
					<div class="payment_details_left_col">
						<form:form id="silentOrderPostForm" name="silentOrderPostForm" commandName="sopPaymentDetailsForm" class="create_update_payment_form" action="${paymentFormUrl}" method="POST">							
							<input type="hidden" name="orderPage_receiptResponseURL" value="${silentOrderPageData.parameters['orderPage_receiptResponseURL']}"/>
							<input type="hidden" name="orderPage_declineResponseURL" value="${silentOrderPageData.parameters['orderPage_declineResponseURL']}"/>
							<input type="hidden" name="orderPage_cancelResponseURL" value="${silentOrderPageData.parameters['orderPage_cancelResponseURL']}"/>
							<c:forEach items="${sopPaymentDetailsForm.signatureParams}" var="entry" varStatus="status">
								<input type="hidden" id="${entry.key}" name="${entry.key}" value="${entry.value}"/>
							</c:forEach>
							<c:forEach items="${sopPaymentDetailsForm.subscriptionSignatureParams}" var="entry" varStatus="status">
								<input type="hidden" id="${entry.key}" name="${entry.key}" value="${entry.value}"/>
							</c:forEach>

							<div class="payment_details_left_col-card">
								<h1>
									<spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.paymentCard"/>
								</h1>
								<p>
									<spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterYourCardDetails"/>									
								</p>	
								<p style="height:50px; text-align: center;">
									or Pay with iDEAL:
									<br>
									<!-- <a href="${PAY_WITH_IDEAL_URL} " > -->
									<a href="/idealpayment/payment/request" >
										<img id="idealButton" src="${themeResourcePath}/images/button-ideal.jpg" />
									</a>
								</p>														
								<p>
									<spring:theme code="form.required"/>
								</p>
								<div class="form_field-elements">
								   
									<formElement:formSelectBox idKey="card_cardType" labelKey="payment.cardType" path="card_cardType" mandatory="true" skipBlank="false" skipBlankMessageKey="payment.cardType.pleaseSelect" items="${sopCardTypes}" tabindex="1"/>
									<formElement:formInputBox idKey="card_nameOnCard" labelKey="payment.nameOnCard" path="card_nameOnCard" inputCSS="text" mandatory="false" />
									<formElement:formInputBox idKey="card_accountNumber" labelKey="payment.cardNumber" path="card_accountNumber" inputCSS="text" mandatory="true" tabindex="2" autocomplete="off"/>
									<formElement:formInputBox idKey="card_cvNumber" labelKey="payment.cvn" path="card_cvNumber" inputCSS="text" mandatory="true" tabindex="3"/>
									<fieldset id="startDate" class="cardDate">
										<legend><spring:theme code="payment.startDate"/></legend>
										<formElement:formSelectBox idKey="StartMonth" labelKey="payment.month" path="card_startMonth" mandatory="true" skipBlank="false" skipBlankMessageKey="payment.month" items="${months}" tabindex="4"/>
										<formElement:formSelectBox idKey="StartYear" labelKey="payment.year" path="card_startYear" mandatory="true" skipBlank="false" skipBlankMessageKey="payment.year" items="${startYears}" tabindex="5"/>
									</fieldset>
									<fieldset class="cardDate">
										<legend><spring:theme code="payment.expiryDate"/></legend>
										<formElement:formSelectBox idKey="ExpiryMonth" labelKey="payment.month" path="card_expirationMonth" mandatory="true" skipBlank="false" skipBlankMessageKey="payment.month" items="${months}" tabindex="6"/>
										<formElement:formSelectBox idKey="ExpiryYear" labelKey="payment.year" path="card_expirationYear" mandatory="true" skipBlank="false" skipBlankMessageKey="payment.year" items="${expiryYears}" tabindex="7"/>
									</fieldset>
									<div id="issueNum">
										<formElement:formInputBox idKey="card_issueNumber" labelKey="payment.issueNumber" path="card_issueNumber" inputCSS="text" mandatory="false" tabindex="8"/>
									</div>
									<div class="form_field-input">
										<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
											<form:checkbox path="savePaymentInfo" tabindex="9"/>
											<spring:theme code="checkout.multi.sop.savePaymentInfo"/>
										</sec:authorize>
									</div>
								</div>
							</div>

							<div class="payment_details_left_col-billing">
								<h1><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddress"/></h1>
								<c:if test="${cartData.deliveryItemsQuantity > 0}">
									<form:checkbox path="useDeliveryAddress" id="useDeliveryAddress" data-firstname="${deliveryAddress.firstName}" data-lastname="${deliveryAddress.lastName}"
												   data-line1="${deliveryAddress.line1}" data-line2="${deliveryAddress.line2}" data-town="${deliveryAddress.town}" data-postalcode="${deliveryAddress.postalCode}"
												   data-countryisocode="${deliveryAddress.country.isocode}" data-regionisocode="${deliveryAddress.region.isocodeShort}" tabindex="10"/>
									<spring:theme code="checkout.multi.sop.useMyDeliveryAddress"/>
								</c:if>
								<input type="hidden" value="${silentOrderPageData.parameters['billTo_email']}" class="text" name="billTo_email" id="billTo_email">
								<address:billAddressFormSelector supportedCountries="${countries}" regionsForCountries="${regionsForCountries}" tabIndex="11"/>
							</div>
							<div class="save_payment_details">
							<span class="clear_fix">
								<button class="form submit_silentOrderPostForm" tabindex="12">
									<spring:theme code="Submit" text="Submit"/>
								</button>
							</span>
							</div>
						</form:form>
					</div>
					</ycommerce:testId>
					<c:if test="${not empty paymentInfos}">
						<div class="payment_details_right_col saved-payment-list">
							<c:forEach items="${paymentInfos}" var="paymentInfo" varStatus="status">
								<div class="saved-payment-list-entry">
									<form action="${request.contextPath}/checkout/multi/select-payment-method" method="GET">
										<input type="hidden" name="selectedPaymentMethodId" value="${paymentInfo.id}"/>
										<span class="saved-payment-list-item">${fn:escapeXml(paymentInfo.cardType)}</span>
										<span class="saved-payment-list-item">${fn:escapeXml(paymentInfo.cardNumber)}</span>
										<span class="saved-payment-list-item"><spring:theme code="checkout.multi.paymentMethod.paymentDetails.expires" arguments="${fn:escapeXml(paymentInfo.expiryMonth)},${fn:escapeXml(paymentInfo.expiryYear)}"/></span>
										<span class="saved-payment-list-item">${fn:escapeXml(paymentInfo.billingAddress.firstName)}&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.lastName)}</span>
										<span class="saved-payment-list-item">${fn:escapeXml(paymentInfo.billingAddress.line1)}</span>
										<span class="saved-payment-list-item">${fn:escapeXml(paymentInfo.billingAddress.region.isocodeShort)}&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.town)}</span>
										<span class="saved-payment-list-item">${fn:escapeXml(paymentInfo.billingAddress.postalCode)}</span>
										<button type="submit" class="form" tabindex="${status.count + 13}">Use this payment info</button>
									</form>
									<form action="${request.contextPath}/checkout/multi/remove-payment-method" method="POST">
										<input type="hidden" name="paymentInfoId" value="${paymentInfo.id}"/>
										<button type="submit" class="text-button remove-payment-item" tabindex="${status.count + 14}">Remove</button>
									</form>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
			<multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="true"/>
		</div>
	</c:if>
</template:page>
