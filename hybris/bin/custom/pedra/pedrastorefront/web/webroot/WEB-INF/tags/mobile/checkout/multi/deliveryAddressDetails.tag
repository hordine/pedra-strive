<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="deliveryAddress" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData"%>
<%@ attribute name="isSelected" required="false" type="java.lang.Boolean"%>
<%@ attribute name="url" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set value="${not empty deliveryAddress}" var="deliveryAddressOk" />
<c:set value="select-delivery-address" var="selectDeliveryAddressUrl" />
<c:set value="${not empty deliveryAddress}" var="deliveryAddressOk" />
<c:url value="/checkout/multi/remove-address" var="removeDeliveryAddressUrl"/>

<div class="deliveryAddressDetailsList" data-theme="d">
	<c:if test="${empty url}">
		<c:choose>
			<c:when test="${isSelected}">
				<input type="radio" name="selectedAddressCode" id="${deliveryAddress.id}" value="${deliveryAddress.id}" checked="checked" data-theme="d" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="selectedAddressCode" id="${deliveryAddress.id}" value="${deliveryAddress.id}" data-theme="d" />
			</c:otherwise>
		</c:choose>
		<label for="${deliveryAddress.id}">
			<ul class="mFormList">
				<li>${deliveryAddress.title}&nbsp;${deliveryAddress.firstName}&nbsp; ${deliveryAddress.lastName}</li>
				<li>${deliveryAddress.line1}</li>
				<c:if test="${fn:length(deliveryAddress.line2) > 0}">
					<li>${deliveryAddress.line2}</li>
				</c:if>
				<li>${deliveryAddress.town}</li>
				<li>${deliveryAddress.region.name}</li>
				<li>${deliveryAddress.postalCode}</li>
				<li>${deliveryAddress.country.name}</li>
			</ul>
		</label>
	</c:if>
	<c:if test="${not empty url}">
		<label for="${deliveryAddress.id}">
			<ul class="mFormList">
				<li>${deliveryAddress.title}&nbsp;${deliveryAddress.firstName}&nbsp; ${deliveryAddress.lastName}</li>
				<li>${deliveryAddress.line1}</li>
				<c:if test="${fn:length(deliveryAddress.line2) > 0}">
					<li>${deliveryAddress.line2}</li>
				</c:if>
				<li>${deliveryAddress.town}</li>
				<li>${deliveryAddress.region.name}</li>
				<li>${deliveryAddress.postalCode}</li>
				<li>${deliveryAddress.country.name}</li>
			</ul>
		</label>
	</c:if>
	<div class="deliveryAddressDetailsList-edit">
		<a href="${removeDeliveryAddressUrl}/?addressCode=${deliveryAddress.id}" data-role="button" data-icon="arrow-r" data-theme="d">
			<spring:theme code="checkout.multi.deliveryAddress.delete" text="Delete" />
		</a>
	</div>
</div>
