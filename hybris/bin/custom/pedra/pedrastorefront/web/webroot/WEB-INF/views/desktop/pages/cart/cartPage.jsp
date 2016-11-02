<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:theme text="Your Shopping Cart" var="title" code="cart.page.title"/>
<c:url value="/cart/checkout" var="checkoutUrl"/>

<template:page pageTitle="${pageTitle}">

	<script type="text/javascript">
		var checkoutUrl = '${checkoutUrl}';
	</script>

	<spring:theme code="basket.add.to.cart" var="basketAddToCart"/>
	<spring:theme code="cart.page.checkout" var="checkoutText"/>
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<common:globalMessages/>
	<cart:cartRestoration/>
	<cart:cartValidation/>
	<cart:cartPickupValidation/>
	<div class="span-24">
		<div class="span-20">

			<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
				<cms:component component="${feature}"/>
			</cms:pageSlot>

			<c:if test="${not empty cartData.entries}">
				<spring:url value="${continueUrl}" var="continueShoppingUrl" htmlEscape="true"/>
				<div class="span-20">
					<div class="span-20">
						<div class="span-10 first">
						
							<form action="#" method="get" class="left">
								<button type="submit" class="form" onclick="window.location = '${continueShoppingUrl}'; return false">
									<spring:theme text="Continue Shopping" code="cart.page.continue"/>
								</button>
							</form>
						</div>
						<div class="span-10 last">
							<button id="checkoutButtonTop" class="doCheckoutBut positive right">
								<theme:image code="img.addToCartIcon" alt="${basketAddToCart}" title="${basketAddToCart}"/>
								<spring:theme code="checkout.checkout" />
							</button>
							<span class="cart_total">
								<spring:theme text="Total:" code="basket.page.total"/>&nbsp;<format:price priceData="${cartData.totalPrice}"/>
							</span>
						</div>
					</div>

					<div class="span-20">
						<div class="span-20">
							<cart:cartItems cartData="${cartData}"/>
						</div>
						<div class="span-12">
							<cart:cartPromotions cartData="${cartData}"/>
						</div>
						<div class="span-8 last right">
							<cart:cartTotals cartData="${cartData}"/>
						</div>
					</div>
					<div class="span-20 last">
						<form action="#" method="get">
							<button type="submit" class="form left" onclick="window.location = '${continueShoppingUrl}'; return false">
								<spring:theme text="Continue Shopping" code="cart.page.continue"/>
							</button>
						</form>
						<button id="checkoutButtonBottom" class="doCheckoutBut positive right">
							<theme:image code="img.addToCartIcon" alt="${basketAddToCart}" title="${basketAddToCart}" />
							<spring:theme code="checkout.checkout" />
						</button>
					</div>
				</div>
			</c:if>
			<c:if test="${empty cartData.entries}">
				<div class="span-20">
					<div class="span-20 wide-content-slot cms_disp-img_slot">
						<cms:pageSlot position="MiddleContent" var="feature" element="div">
							<cms:component component="${feature}"/>
						</cms:pageSlot>

						<cms:pageSlot position="BottomContent" var="feature" element="div">
							<cms:component component="${feature}"/>
						</cms:pageSlot>
					</div>
				</div>
			</c:if>
		</div>
		<div class="span-4 last">
			<c:if test="${not empty cartData.entries}">
				<div class="span-4">
					<cart:cartPotentialPromotions cartData="${cartData}"/>
				</div>
			</c:if>

			<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 narrow-content-slot cms_disp-img_slot">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
		</div>
		<c:if test="${showCheckoutStrategies && not empty cartData.entries}" >
			<div class="span-20">
				<div class="right">
					<input type="hidden" name="flow" id="flow"/>
					<input type="hidden" name="pci" id="pci"/>
					<select id="selectAltCheckoutFlow" class="doFlowSelectedChange">
						<option value="multistep"><spring:theme code="checkout.checkout.flow.select"/></option>
						<c:if test="${!hasPickUpCartEntries && !isOmsEnabled}">
							<option value="single"><spring:theme code="checkout.checkout.single"/></option>
						</c:if>
						<option value="multistep"><spring:theme code="checkout.checkout.multi"/></option>
						<option value="multistep-pci"><spring:theme code="checkout.checkout.multi.pci"/></option>
					</select>
					<select id="selectPciOption" style="margin-left: 10px; display: none;">
						<option value=""><spring:theme code="checkout.checkout.multi.pci.select"/></option>
						<c:if test="${!isOmsEnabled}">
							<option value="default"><spring:theme code="checkout.checkout.multi.pci-ws"/></option>
							<option value="hop"><spring:theme code="checkout.checkout.multi.pci-hop"/></option>
						</c:if>
						<option value="sop"><spring:theme code="checkout.checkout.multi.pci-sop" text="PCI-SOP" /></option>
					</select>
				</div>
			</div>
		</c:if>
	</div>
</template:page>
