<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="paymentMethods" required="true" type="java.util.List"%>
<%@ attribute name="selectedPaymentMethodId" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/mobile/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set value="select-payment-method" var="selectPaymentMethodUrl" />
<c:choose>
	<c:when test="${not empty paymentMethods}">
		<form:form id="selectPaymentMethod" action="${selectPaymentMethodUrl}" method="get">
			<fieldset data-role="controlgroup" data-theme="d">
				<c:forEach items="${paymentMethods}" var="paymentMethod">
					<multi-checkout:paymentMethodDetails paymentMethod="${paymentMethod}" isSelected="${paymentMethod.id eq selectedPaymentMethodId}" />
				</c:forEach>
			</fieldset>
			<div class="ui-grid-a" data-theme="d">
				<a href="add-payment-method" class="onefullWidth" data-theme="c" data-role="button">
					<spring:theme code="mobile.checkout.paymentMethod.add.card" />
				</a>
			</div>
			<div class="fakeHR"></div>
			<div class="ui-grid-a" data-theme="d">
				<button type="submit" data-icon="arrow-r" data-iconpos="right" data-theme="b">
					<spring:theme code="mobile.checkout.continue.button" />
				</button>
			</div>
		</form:form>
	</c:when>
	<c:otherwise>
		<div class="ui-grid-a" data-theme="b">
			<spring:theme code="checkout.multi.paymentMethod.savedCards.noExistingSavedCards" />
		</div>
	</c:otherwise>
</c:choose>
