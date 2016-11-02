<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="single-checkout" tagdir="/WEB-INF/tags/desktop/checkout/single" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/checkout/single/summary/getCheckoutCart.json" var="getCheckoutCartUrl" />

<script type="text/javascript">
	getCheckoutCartUrl = '${getCheckoutCartUrl}';
</script>

<div class="checkout_summary_flow">
	<single-checkout:summaryFlowDeliveryAddress deliveryAddress="${cartData.deliveryAddress}" />
	<single-checkout:summaryFlowDeliveryMode deliveryMode="${deliveryMode}" />
	<single-checkout:summaryFlowPayment paymentInfo="${paymentInfo}" />
</div>
