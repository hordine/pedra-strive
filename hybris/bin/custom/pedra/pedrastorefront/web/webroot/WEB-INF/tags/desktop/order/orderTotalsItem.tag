<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<%@ attribute name="containerCSS" required="false" type="java.lang.String" %>

<div class="item_container_holder ${containerCSS}">
	<div class="title_holder">
		<h2><spring:theme code="text.account.order.orderTotals" text="Order Totals"/></h2>
	</div>
	<div class="item_container">
		<dl class="order_totals">
			<dt><spring:theme code="text.account.order.subtotal" text="Subtotal:"/></dt>
			<dd><format:price priceData="${order.subTotal}"/></dd>
			<c:if test="${order.totalDiscounts.value > 0}">
				<dt class="savings"><spring:theme code="text.account.order.savings" text="Savings:"/></dt>
				<dd class="savings"><format:price priceData="${order.totalDiscounts}"/></dd>
			</c:if>
			<dt><spring:theme code="text.account.order.delivery" text="Delivery:"/></dt>
			<dd>
				<format:price priceData="${order.deliveryCost}" displayFreeForZero="true"/>
			</dd>
			<c:choose>
				<c:when test="${order.net}">
					<dt><spring:theme code="text.account.order.netTax" text="Tax:"/></dt>
					<dd>
						<format:price priceData="${order.totalTax}"/>
					</dd>
					<dt class="total"><spring:theme code="text.account.order.total" text="Total:"/></dt>
					<dd class="total"><format:price priceData="${order.totalPriceWithTax}"/></dd>
				</c:when>
				<c:otherwise>
					<dt class="total"><spring:theme code="text.account.order.total" text="Total:"/></dt>
					<dd class="total"><format:price priceData="${order.totalPrice}"/></dd>
				</c:otherwise>
			</c:choose>
		</dl>

		<c:if test="${not order.net}" >
			<p><spring:theme code="text.account.order.includesTax" text="Your order includes {0} tax" arguments="${order.totalTax.formattedValue}" argumentSeparator="###"/></p>
		</c:if>

	</div>
</div>
