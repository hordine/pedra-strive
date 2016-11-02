<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

	<div class="span-20 right last">
		<ycommerce:testId code="orderConfirmation_yourOrderResults_text">
			<div class="span-20 your_order">
				<h1>
					<spring:theme code="checkout.orderConfirmation.thankYouForOrder" />
				</h1>

				<p>
					<spring:theme code="checkout.orderConfirmation.copySentTo" arguments="${email}"/>
				</p>
			</div>
		</ycommerce:testId>

		<div class="span-20 last">
			<cms:pageSlot position="TopContent" var="feature" element="div" class="span-10 top-content-slot cms_disp-img_slot">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
		</div>
		
		<div class="span-20 last">
			<p><spring:theme code="text.account.order.orderNumber" text="Order number is {0}" arguments="${orderData.code}"/></p>
			<p><spring:theme code="text.account.order.orderPlaced" text="Placed on {0}" arguments="${orderData.created}"/></p>
			<c:if test="${not empty orderData.statusDisplay}">
				<spring:theme code="text.account.order.status.display.${orderData.statusDisplay}" var="orderStatus"/>
				<p><spring:theme code="text.account.order.orderStatus" text="The order is {0}" arguments="${orderStatus}"/></p>
			</c:if>
		</div>
		
		<div class="span-20 delivery_stages-guest last">
			<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
				<user:guestRegister actionNameKey="guest.register.submit"/>
			</sec:authorize>
		</div>
		
		<div class="span-20 last delivery_stages">
			<h2><spring:theme code="text.account.order.summary" /></h2>
			<div class="span-5 delivery_stages-address">
				<order:deliveryAddressItem order="${orderData}"/>
			</div>

			<div class="span-5 delivery_stages-delivery">
				<order:deliveryMethodItem order="${orderData}"/>
			</div>

			<div class="span-10 delivery_stages-payment last">
				<order:paymentMethodItem order="${orderData}"/>
			</div>
		</div>

		<div class="span-20 delivery_stages-details last">
			<c:forEach items="${orderData.deliveryOrderGroups}" var="orderGroup">
				<order:orderDetailsItem order="${orderData}" orderGroup="${orderGroup}" />
			</c:forEach>		

			<c:forEach items="${orderData.pickupOrderGroups}" var="orderGroup">
				<order:orderPickupDetailsItem order="${orderData}" orderGroup="${orderGroup}" />
			</c:forEach>				

			<div class="span-12 delivery_stages-promotions">
				<order:receivedPromotions order="${orderData}"/>
			</div>
			<div class="span-8 delivery_stages-total right last">
				<order:orderTotalsItem order="${orderData}" containerCSS="positive"/>
			</div>
		</div>

	</div>
</template:page>
