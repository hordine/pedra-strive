<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/desktop/store" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="cartPage" required="true" type="java.lang.Boolean"%>
<%@ attribute name="entryNumber" required="false" type="java.lang.Long"%>
<%@ attribute name="deliveryPointOfService" required="false" type="java.lang.String"%>
<%@ attribute name="quantity" required="false" type="java.lang.Integer"%>
<%@ attribute name="searchResultsPage" required="false" type="java.lang.Boolean"%>




<c:url var="pickUpInStoreFormAction" value="/store-pickup/${product.code}/pointOfServices"/>


<div class="collect_from_store clear_fix">
				<c:choose>
					<c:when test="${cartPage}">
									<a href="javascript:void(0)" class="click_pickupInStore_Button" id="product_${product.code}${entryNumber}" data-productcart='<p><strong>${product.price.formattedValue}</strong></p><c:if test="${not empty product.baseOptions[0].selected.variantOptionQualifiers}"><c:forEach var="variant" items="${product.baseOptions[0].selected.variantOptionQualifiers}"><c:if test="${not empty variant.value}"><p><spring:theme code="basket.pickup.product.variant" arguments="${variant.name},${variant.value}" /></p></c:if></c:forEach></c:if>' data-img='<product:productPrimaryImage product="${product}" format="thumbnail"/>' data-productname="${product.name}" data-cartpage="${cartPage}" data-entryNumber="${entryNumber}"  data-actionurl="${pickUpInStoreFormAction}" data-value="${quantity}" >
							<c:choose>
								<c:when test="${not empty deliveryPointOfService}">
									<spring:theme code="basket.page.shipping.change.store"/>
								</c:when>
								<c:otherwise>
									<spring:theme code="basket.page.shipping.find.store" text="Find A Store"/>
								</c:otherwise>
							</c:choose>
						</a>
					</c:when>
					<c:when test="${searchResultsPage}">
						<button class="neutral large click_pickupInStore_Button" id="product_${product.code}${entryNumber}" type="submit"  data-productcart='<p><strong>${product.price.formattedValue}</strong></p><c:if test="${not empty product.baseOptions[0].selected.variantOptionQualifiers}"><c:forEach var="variant" items="${product.baseOptions[0].selected.variantOptionQualifiers}"><c:if test="${not empty variant.value}"><p><spring:theme code="basket.pickup.product.variant" arguments="${variant.name},${variant.value}" /></p></c:if></c:forEach></c:if>'  data-img='<product:productPrimaryImage product="${product}" format="thumbnail"/>' data-productname="${product.name}" data-cartpage="false" data-entryNumber="0"  data-actionurl="${pickUpInStoreFormAction}" data-value="1">
							<spring:theme code="pickup.in.store"/>
						</button>
					</c:when>
					<c:otherwise>
					
						<button class="neutral large click_pickupInStore_Button" id="product_${product.code}${entryNumber}" type="submit" data-productcart='<p><strong>${product.price.formattedValue}</strong></p><c:if test="${not empty product.baseOptions[0].selected.variantOptionQualifiers}"><c:forEach var="variant" items="${product.baseOptions[0].selected.variantOptionQualifiers}"><c:if test="${not empty variant.value}"><p><spring:theme code="basket.pickup.product.variant" arguments="${variant.name},${variant.value}" /></p></c:if></c:forEach></c:if>' data-img='<product:productPrimaryImage product="${product}" format="thumbnail"/>' data-productname="${product.name}" data-cartpage="false" data-entryNumber="0"  data-actionurl="${pickUpInStoreFormAction}" data-value="1">
							<spring:theme code="pickup.in.store"/>
						</button>
					</c:otherwise>
				</c:choose>
			</div>
						






<%--  this part is direct implemented on the pages so its only one time on each page
<c:url var="pickUpInStoreFormAction" value="/store-pickup/${product.code}/pointOfServices"/>

<div class="collect_from_store">
	<c:choose>
		<c:when test="${cartPage}">
			<a href="javascript:void(0)" class="click_pickupInStore_Button" id="product_${product.code}${entryNumber}">
				<c:choose>
					<c:when test="${not empty deliveryPointOfService}">
						<spring:theme code="basket.page.shipping.change.store"/>
					</c:when>
					<c:otherwise>
						<spring:theme code="basket.page.shipping.find.store"/>
					</c:otherwise>
				</c:choose>
			</a>
		</c:when>
		<c:when test="${searchResultsPage}">
			<button class="neutral large click_pickupInStore_Button" id="product_${product.code}${entryNumber}" type="submit">
				<spring:theme code="pickup.in.store"/>
			</button>
		</c:when>
		<c:otherwise>
			<button class="neutral large click_pickupInStore_Button" id="product_${product.code}${entryNumber}" type="submit">
				<spring:theme code="pickup.in.store"/>
			</button>
		</c:otherwise>
	</c:choose>
</div>


<div id="popup_store_pickup_form_product_${product.code}${entryNumber}" class="pickup_store_search clearfix" style="display:none">
	<div class="item_container_holder clearfix">
		<div class="title_holder">
			<h2><spring:theme code="pickup.product.availability" /></h2>
		</div>
	</div>

	<div class="prod_grid span-4">
		<span class="thumb">
			<product:productPrimaryImage product="${product}" format="thumbnail"/>
		</span>
		<strong class="details">${product.name}</strong>
		<div class="cart">
			<p><strong>${product.price.formattedValue}</strong></p>
			<c:if test="${not empty product.baseOptions[0].selected.variantOptionQualifiers}">
				<c:forEach var="variant" items="${product.baseOptions[0].selected.variantOptionQualifiers}">
					<c:if test="${not empty variant.value}">
						<p><spring:theme code="basket.pickup.product.variant" arguments="${variant.name},${variant.value}" /></p>
						
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<div class="quantity pickup_store_search-quantity">
			<label for="pickupQty"><spring:theme code="basket.page.quantity" /></label>
			<input type="text" size="1" maxlength="3"  id="pickupQty" name="qty" class="qty" value="${quantity}" />
		</div>
	</div>
	
	<div class="span-17 last">
		<div class="pickup_store_search-form">
			<form name="pickupInStoreForm" action="${pickUpInStoreFormAction}" method="post" id="pickup_in_store_search_form_product_${product.code}${entryNumber}" class="form_field-input">
				<spring:theme code="pickup.search.message" />
				<input type="text" name="locationQuery" id="locationForSearch" />
				<input type="hidden" name="cartPage" id="atCartPage" value="${cartPage}" />
				<input type="hidden" name="entryNumber" value="${entryNumber}" class="entryNumber" />
				<button type="button" class="form" id="pickupstore_search_button"><spring:theme code="pickup.search.button" /></button>
				<button type="button" class="form" id="find_pickupStoresNearMe_button"><spring:theme code="storeFinder.findStoresNearMe"/></button>
			</form>
		</div>
		<div id="pickup_store_results"></div>
	</div>
</div>--%>
