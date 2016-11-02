<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>


<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>

<c:set value="${not empty product.potentialPromotions}" var="hasPromotion"/>

<div class="prod_list ${hasPromotion ? 'prod_list_has_promotion' : ''}">
	<ycommerce:testId code="test_searchPage_wholeProduct">
		<a href="${productUrl}" title="${product.name}" class="productMainLink">
			<div class="thumb">
				<c:if test="${not empty product.averageRating}">
					<span class="stars large" style="display: inherit;">
						<span style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
					</span>
				</c:if>
					<product:productPrimaryImage product="${product}" format="thumbnail"/>
				<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
					<img class="promo" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
				</c:if>
			</div>
			<div class="details">
				<ycommerce:testId code="searchPage_productName_link_${product.code}">
					<h2>${product.name}</h2>
				</ycommerce:testId>
				<c:if test="${not empty product.summary}">
					<p>${product.summary}</p>
				</c:if>			
				<product:productListerClassifications product="${product}"/>
			</div>
		</a>
		<div class="cart">
			<ycommerce:testId code="searchPage_price_label_${product.code}">
				<p class="price"><format:price priceData="${product.price}"/></p>
			</ycommerce:testId>
			<ycommerce:testId code="searchPage_addToCart_button_${product.code}">
				<c:set var="buttonType">submit</c:set>
				<c:choose>
					<c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
						<c:set var="buttonType">button</c:set>
						<spring:theme code="text.addToCart.outOfStock" var="addToCartText"/>
					</c:when>
					<c:when test="${product.stock.stockLevelStatus.code eq 'lowStock' }">
						<span class='listProductLowStock mlist-stock'><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></span>
					</c:when>
				</c:choose>
				<c:if test="${product.internalOnly and !user.internal}">
                <c:set var="buttonType">button</c:set>
         	</c:if>
				<form id="addToCartForm${product.code}" action="<c:url value="/cart/add"/>" method="post" class="add_to_cart_form">
					<input type="hidden" name="productCodePost" value="${product.code}"/>
					<button type="${buttonType}" class="positive large <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>" <c:if test="${fn:contains(buttonType, 'button')}">disabled aria-disabled="true"</c:if>>
						<span class="icon-cart"></span>${addToCartText}
					</button>				
				</form>
			</ycommerce:testId>
			<c:if test="${product.availableForPickup}">
				<storepickup:clickPickupInStore product="${product}" entryNumber="0" cartPage="false"/>
			</c:if>
		</div>
		</ycommerce:testId>	
</div>
