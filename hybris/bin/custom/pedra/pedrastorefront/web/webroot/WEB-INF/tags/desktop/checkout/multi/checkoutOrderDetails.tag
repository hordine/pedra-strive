<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ attribute name="showShipDeliveryEntries" required="false" type="java.lang.Boolean" %>
<%@ attribute name="showPickupDeliveryEntries" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<script type="text/javascript">
		var showText = '<spring:theme code="checkout.orderDetails.show"/>';
		var hideText = '<spring:theme code="checkout.orderDetails.hide"/>';

</script>

<a href="#" id="checkout-cart-details-btn"><spring:theme code="checkout.orderDetails.show"/></a>
<div id="checkout-cart-details" style="display:none;">
	<div class="span-20 last">
		<c:if test="${showShipDeliveryEntries && !showPickupDeliveryEntries && not empty cartData.pickupOrderGroups}">
			<div class="information_message neutral">
				<span class="single"></span>
				<p><spring:theme code="checkout.pickup.items.appear.later"/></p>
			</div>
		</c:if>	
		<c:if test="${showShipDeliveryEntries && not empty cartData.deliveryOrderGroups}">
			<multi-checkout:deliveryCartItems cartData="${cartData}"/>
		</c:if>
		<c:if test="${showPickupDeliveryEntries}">
			<c:forEach items="${cartData.pickupOrderGroups}" var="groupData" varStatus="status">
				<div class="span-20 last">
					<multi-checkout:pickupCartItems cartData="${cartData}" groupData="${groupData}" index="${status.index}"/>
					
				</div>	
			</c:forEach>
		</c:if>
	</div>
	<c:if test="${showPickupDeliveryEntries && showShipDeliveryEntries}">
		<div class="span-12">
			<cart:cartPromotions cartData="${cartData}"/>
		</div>
		<div class="span-8 last right">
			<cart:cartTotals cartData="${cartData}"/>
		</div>
	</c:if>
</div>
