<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">


	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<nav:accountNav selected="payment-details" />
	<div class="span-20 last">
		<div class="item_container_holder">
			<div class="title_holder">
				<h2><spring:theme code="text.account.paymentDetails" text="Payment Details"/></h2>
			</div>
			<div class="item_container">
				<p><spring:theme code="text.account.paymentDetails.managePaymentDetails" text="Manage your saved payment details."/></p>
				
				<c:choose>
					<c:when test="${not empty paymentInfoData}">
						<table id="payment_details">
							<thead>
								<tr>
									<th id="header1"><spring:theme code="text.account.paymentDetails.paymentCard" text="Payment Card"/></th>
									<th id="header2"><spring:theme code="text.account.paymentDetails.billingAddress" text="Billing Address"/></th>
									<th id="header3"><spring:theme code="text.updates" text="Updates"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${paymentInfoData}" var="paymentInfo">
									<tr>
										<td headers="header1">
											<ul>
												<li>${fn:escapeXml(paymentInfo.cardNumber)}</li>
												<li>${fn:escapeXml(paymentInfo.cardType)}</li>
												<li><spring:theme code="text.expires" text="Expires"/> ${fn:escapeXml(paymentInfo.expiryMonth)} / ${fn:escapeXml(paymentInfo.expiryYear)}</li>
											</ul>
										</td>
										<td headers="header2">
											<ul>
												<li><c:out value="${fn:escapeXml(paymentInfo.billingAddress.title)} ${fn:escapeXml(paymentInfo.billingAddress.firstName)} ${fn:escapeXml(paymentInfo.billingAddress.lastName)}"/></li>
												<li>${fn:escapeXml(paymentInfo.billingAddress.line1)}</li>
												<li>${fn:escapeXml(paymentInfo.billingAddress.line2)}</li>
												<li>${fn:escapeXml(paymentInfo.billingAddress.town)}</li>
												<li>${fn:escapeXml(paymentInfo.billingAddress.postalCode)}</li>
												<li>${fn:escapeXml(paymentInfo.billingAddress.country.name)}</li>
											</ul>
										</td>
										<td headers="header3">
	
												<c:if test="${not paymentInfo.defaultPaymentInfo}">
													<c:url value="/my-account/set-default-payment-details" var="setDefaultPaymentActionUrl"/>
													<form:form id="setDefaultPaymentDetails${paymentInfo.id}" action="${setDefaultPaymentActionUrl}" method="post">
														<input type="hidden" name="paymentInfoId" value="${paymentInfo.id}"/>
														<a class="submitSetDefault" id="${paymentInfo.id}" href="#"><spring:theme code="text.setDefault" text="Set as default"/></a>
													</form:form>
												</c:if>
	
												<c:url value="/my-account/remove-payment-method" var="removePaymentActionUrl"/>
												<form:form id="removePaymentDetails${paymentInfo.id}" action="${removePaymentActionUrl}" method="post">
													<input type="hidden" name="paymentInfoId" value="${paymentInfo.id}"/>
													<a class="submitRemove" id="${paymentInfo.id}" href="#"><spring:theme code="text.remove" text="Remove"/></a>
												</form:form>
												<script type="text/javascript">
													var id = '${paymentInfo.id}';

												</script>
													
												<c:if test="${paymentInfo.defaultPaymentInfo}">
													<spring:theme code="text.default" text="Default"/>
												</c:if>

										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p class="emptyMessage">
							<spring:theme code="text.account.paymentDetails.noPaymentInformation" text="No Saved Payment Details"/>
						</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</template:page>