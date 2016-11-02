<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="deliveryAddresses" required="true" type="java.util.List"%>
<%@ attribute name="selectedAddressId" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/mobile/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set value="select-delivery-address" var="selectDeliveryAddressUrl" />
<c:if test="${not empty  deliveryAddresses}">
	<form:form id="selectDeliveryAddress" action="${selectDeliveryAddressUrl}" method="get">
		<fieldset data-role="controlgroup" data-theme="d">
			<c:forEach items="${deliveryAddresses}" var="deliveryAddress">
				<multi-checkout:deliveryAddressDetails deliveryAddress="${deliveryAddress}" isSelected="${deliveryAddress.id eq selectedAddressId}" />
			</c:forEach>
		</fieldset>
		<a href="add-delivery-address" class="onefullWidth" data-role="button" data-icon="plus" data-transition="slide" data-theme="c">
			<spring:theme code="checkout.multi.deliveryAddress.addAddress" text="Add new address" />
		</a>
		<div class="fakeHR"></div>
		<div>
			<button type="submit" data-icon="arrow-r" data-iconpos="right" data-theme="b" class="onefullWidth">
				<spring:theme code="mobile.checkout.continue.button" />
			</button>
		</div>
	</form:form>
</c:if>
