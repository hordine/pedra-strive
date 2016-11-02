<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>

<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>

<c:set value="${not empty product.potentialPromotions}" var="hasPromotion"/>

<ycommerce:testId code="product_wholeProduct">
	<div class="prod_grid ${hasPromotion ? 'prod_grid_has_promotion' : ''}">
		<a href="${productUrl}" title="${product.name}" class="productMainLink">
			<span class="thumb">
					<product:productPrimaryImage product="${product}" format="thumbnail"/>
				<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
					<img class="promo" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
				</c:if>
			</span>
			<strong class="details">
				<ycommerce:testId code="product_productName">${product.name}</ycommerce:testId>
			</strong>
			
		</a>
		<div class="cart">
		
		
		<div class="price_container">
			<c:set var="buttonType">submit</c:set>
      		<ycommerce:testId code="product_productPrice">
          		<p class="price"><format:price priceData="${product.price}"/></p>
      		</ycommerce:testId>
      		<c:choose>
	        	<c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
	            	<c:set var="buttonType">button</c:set>
	              	<spring:theme code="text.addToCart.outOfStock" var="addToCartText"/>
	              	<span class='listProductLowStock listProductOutOfStock mlist-stock'>${addToCartText}</span>
	          </c:when>
	          <c:when test="${product.stock.stockLevelStatus.code eq 'lowStock' }">
	              	<span class='listProductLowStock mlist-stock'><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></span>
	          </c:when>
	      </c:choose>
	     </div>
		   
		  <form id="addToCartForm${product.code}" action="<c:url value="/cart/add" />" method="post" class="add_to_cart_form clear_fix">
				<input type="hidden" name="productCodePost" value="${product.code}"/>
				<c:if test="${not empty product.averageRating}">
					<span class="stars large" style="display: inherit;">
						<span style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
					</span>
				</c:if>
				<ycommerce:testId code="product_addProduct_button">
					<button type="${buttonType}" class="positive large <c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">out-of-stock</c:if>" <c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">disabled="true" aria-disabled="true"</c:if>>
						<span class="icon-cart">${addToCartText}</span>
					</button>
				</ycommerce:testId>
			</form>
			
			
		         


			<c:if test="${product.availableForPickup}">
				<storepickup:clickPickupInStore product="${product}" entryNumber="0" cartPage="false" searchResultsPage="true"/>
			</c:if>

		</div>
	</div>
</ycommerce:testId>
