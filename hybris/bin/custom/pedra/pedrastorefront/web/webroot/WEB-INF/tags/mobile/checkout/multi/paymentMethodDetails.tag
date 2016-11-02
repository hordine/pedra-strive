<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="paymentMethod" required="true" type="de.hybris.platform.commercefacades.order.data.CCPaymentInfoData"%>
<%@ attribute name="isSelected" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set value="select-payment-method" var="selectPaymentMethodUrl" />
<c:set value="${not empty paymentMethod}" var="paymentInfoOk" />
<c:set value="${not empty paymentMethod and not empty paymentMethod.billingAddress}" var="billingAddressOk" />
<div class="ui-grid-a" data-theme="d">
	<c:choose>
		<c:when test="${paymentInfoOk}">
			<div class="ui-grid-a" style="width: 100%">
				<c:choose>
					<c:when test="${isSelected}">
						<input type="radio" name="selectedPaymentMethodId" id="${paymentMethod.id}" value="${paymentMethod.id}" checked="checked" data-theme="d" />
					</c:when>
					<c:otherwise>
						<input type="radio" name="selectedPaymentMethodId" id="${paymentMethod.id}" value="${paymentMethod.id}" data-theme="d" />
					</c:otherwise>
				</c:choose>
				<label for="${paymentMethod.id}">
					<ul class="mFormList">
						<li>${paymentMethod.accountHolderName}</li>
						<li><spring:theme code="payment.cardNumber" text="Card Number" />:${paymentMethod.cardNumber}</li>
					</ul>
				</label>
			</div>
		</c:when>
		<c:otherwise>
			<div class="ui-grid-a" data-theme="d">
				<spring:theme code="checkout.multi.paymentMethod.savedCards.noExistingSavedCards" />
			</div>
		</c:otherwise>
	</c:choose>
</div>
