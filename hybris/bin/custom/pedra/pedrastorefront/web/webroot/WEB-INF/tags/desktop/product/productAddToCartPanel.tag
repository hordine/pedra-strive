<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="allowAddToCart" required="true" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>


<div class="prod_add_to_cart">
	<form id="addToCartForm" class="add_to_cart_form" action="<c:url value="/cart/add"/>" method="post">
		<c:if test="${product.purchasable}">
			<label for="qty"><spring:theme code="basket.page.quantity" /></label>
			<input type="text" maxlength="3"  size="1" id="qty" name="qty" class="qty" value="1">
		</c:if>
		<input type="hidden" name="productCodePost" value="${product.code}"/>

		<c:if test="${product.stock.stockLevel gt 0}">
			<c:set var="productStockLevel">${product.stock.stockLevel}&nbsp;<spring:theme code="product.variants.in.stock"/></c:set>
		</c:if>
		<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}">
			<c:set var="productStockLevel"><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></c:set>
		</c:if>
		<c:if test="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}">
			<c:set var="productStockLevel"><spring:theme code="product.variants.available"/></c:set>
		</c:if>
		

		<ycommerce:testId code="productDetails_productInStock_label">
			<p class="stock_message">${productStockLevel}</p>
		</ycommerce:testId>

		<c:if test="${allowAddToCart}">		
			<c:set var="buttonType">button</c:set>
			<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
				<c:set var="buttonType">submit</c:set>
			</c:if>
			<c:if test="${product.internalOnly and !user.internal}">
            <c:set var="buttonType">button</c:set>
         </c:if>
		
			<c:choose>
				<c:when test="${fn:contains(buttonType, 'button')}">
					<button type="${buttonType}" class="positive large out-of-stock">
						<spring:theme code="product.variants.out.of.stock" />
					</button>
				</c:when>
			
				<c:otherwise>
					<button id="addToCartButton" type="${buttonType}" class="positive large">
						<spring:theme code="basket.add.to.basket" />
					</button>
				</c:otherwise>
			</c:choose>
		</c:if>
	</form>
	<c:if test="${allowAddToCart and product.availableForPickup}">
		<storepickup:clickPickupInStore product="${product}" cartPage="false"/>
		
		<div id="popup_store_pickup_form" class="pickup_store_search clearfix" style="display:none">
	<div class="item_container_holder clearfix">
		<div class="title_holder">
			<h2><spring:theme code="pickup.product.availability" /></h2>
		</div>
	</div>

	<div class="prod_grid span-4">
		<span class="thumb">
			
		</span>
		<strong class="details"></strong>
		<div class="cart">
			<p><strong></strong></p>
			<c:if test="${not empty product.baseOptions[0].selected.variantOptionQualifiers}">
				<c:forEach var="variant" items="${product.baseOptions[0].selected.variantOptionQualifiers}">
					<c:if test="${not empty variant.value}">
						<p><spring:theme code="basket.pickup.product.variant" arguments="${variant.name},${variant.value}" /></p>
						
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<div class="quantity pickup_store_search-quantity">
			<label data-for="pickupQty"><spring:theme code="basket.page.quantity" /></label>
			<input type="text" size="1" maxlength="3"  data-id="pickupQty" name="qty" class="qty" />
		</div>
	</div>
	
	<div class="span-17 last">
		<div class="pickup_store_search-form">
			<form name="pickupInStoreForm" action="#" method="post" class="form_field-input">
				<label for="locationForSearch" class="nostyle"><spring:theme code="pickup.search.message" /></label>
				<input type="text" name="locationQuery" data-id="locationForSearch" />
				<input type="hidden" name="cartPage" data-id="atCartPage" value="${cartPage}" />
				<input type="hidden" name="entryNumber" value="${entryNumber}" class="entryNumber" />
				<button type="button" class="form" data-id="pickupstore_search_button"><spring:theme code="pickup.search.button" /></button>
				<button type="button" class="form" data-id="find_pickupStoresNearMe_button"><spring:theme code="storeFinder.findStoresNearMe"/></button>
			</form>
		</div>
		<div data-id="pickup_store_results" class="pickup_store_results"></div>
	</div>
</div>
		
	</c:if>
</div>
