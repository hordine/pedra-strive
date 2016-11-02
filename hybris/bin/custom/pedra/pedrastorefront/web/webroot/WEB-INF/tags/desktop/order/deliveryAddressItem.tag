<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hasShippedItems" value="${order.deliveryItemsQuantity > 0}" />

<div class="item_container_holder positive">
	<div class="title_holder">
		<h2><spring:theme code="text.deliveryAddress" text="Delivery Address"/></h2>
	</div>
	<div class="item_container">
		<c:if test="${not hasShippedItems}">
			<spring:theme code="checkout.pickup.no.delivery.required"/>
		</c:if>
		<c:if test="${hasShippedItems}">		
			<ul class="pad_none">
				<li>${fn:escapeXml(order.deliveryAddress.title)}&nbsp;${fn:escapeXml(order.deliveryAddress.firstName)}&nbsp;${fn:escapeXml(order.deliveryAddress.lastName)}</li>
				<li>${fn:escapeXml(order.deliveryAddress.line1)}</li>
				<li>${fn:escapeXml(order.deliveryAddress.line2)}</li>
				<li>${fn:escapeXml(order.deliveryAddress.town)}</li>
				<li>${fn:escapeXml(order.deliveryAddress.region.name)}</li>
				<li>${fn:escapeXml(order.deliveryAddress.postalCode)}</li>
				<li>${fn:escapeXml(order.deliveryAddress.country.name)}</li>
			</ul>
		</c:if>
	</div>
</div>