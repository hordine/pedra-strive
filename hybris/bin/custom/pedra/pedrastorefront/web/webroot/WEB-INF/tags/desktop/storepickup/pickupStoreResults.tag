<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="entryNumber" required="false" type="java.lang.Long" %>
<%@ attribute name="cartPage" required="false" type="java.lang.Boolean" %>
<%@ attribute name="searchPageData" required="true" type="de.hybris.platform.commerceservices.search.pagedata.SearchPageData" %>


<c:url var="addToCartToPickupInStoreUrl" value="/store-pickup/cart/add"/>
<c:url var="updateSelectStoreUrl" value="/store-pickup/cart/update"/>

<script type="text/javascript">
	var addToCartToPickupInStoreUrl = '${addToCartToPickupInStoreUrl}';
	var searchLocation = '${locationQuery}';
</script>

<ul class="pickup_store_results-list clear_fix">
	<c:forEach items="${searchPageData.results}" var="pickupStore">
		<li class="pickup_store_results-item span-8">
			
			<div class="clear_fix address">
				<span class="pickup_store_results-entry pickup_store_results-name">${pickupStore.name}</span>
				<span class="pickup_store_results-entry pickup_store_results-line1">${pickupStore.address.line1}</span>
				<span class="pickup_store_results-entry pickup_store_results-line2">${pickupStore.address.line2}</span>
				<span class="pickup_store_results-entry pickup_store_results-town">${pickupStore.address.town}</span>
				<span class="pickup_store_results-entry pickup_store_results-zip">${pickupStore.address.postalCode}</span>
			</div>
			<span class="pickup_store_results-entry pickup_store_results-distance">${pickupStore.formattedDistance}</span>
			
			<c:choose>
				<c:when test="${pickupStore.stockData.stockLevelStatus.code eq 'outOfStock'}">
					<span class="pickup_store_results-entry pickup_store_results-stock pickup_store_results-outOfStock">
						<spring:theme code="pickup.out.of.stock"/>
					</span>
				</c:when>
				<c:when test="${pickupStore.stockData.stockLevelStatus.code ne 'outOfStock' and empty pickupStore.stockData.stockLevel}">
					<span class="pickup_store_results-entry pickup_store_results-stock">
						<spring:theme code="pickup.force.in.stock"/>
					</span>
				</c:when>
				<c:otherwise>
					<span class="pickup_store_results-entry pickup_store_results-stock">
						<spring:theme code="pickup.in.stock" arguments="${pickupStore.stockData.stockLevel}"/>
					</span>
				</c:otherwise>
			</c:choose>
			
			
			
			<c:if test="${pickupStore.stockData.stockLevel gt 0 or empty pickupStore.stockData.stockLevel}">
				<c:choose>
					<c:when test="${cartPage}">
						<form id="selectStoreForm" class="select_store_form" action="${updateSelectStoreUrl}" method="post">
							<input type="hidden" name="storeNamePost" value="${pickupStore.name}"/>
							<input type="hidden" name="entryNumber" value="${entryNumber}"/>
							<input type="hidden" name="hiddenPickupQty" value="1" class="hiddenPickupQty"/>
							<button type="submit" class="positive large pickup_here_instore_button">
								<spring:theme code="pickup.here.button"/>
							</button>
						</form>
					</c:when>
					<c:otherwise>
						<form  class="add_to_cart_storepickup_form" action="${addToCartToPickupInStoreUrl}" method="post">
							<input type="hidden" name="storeNamePost" value="${pickupStore.name}"/>
							<input type="hidden" name="productCodePost" value="${searchPageData.product.code}"/>
							<input type="hidden" name="hiddenPickupQty" value="1" class="hiddenPickupQty" />
							<button type="submit" class="positive large pickup_add_to_bag_instore_button">
								<spring:theme code="text.addToCart"/>
							</button>
						</form>
					</c:otherwise>
				</c:choose>
			</c:if>
		</li>
	</c:forEach>
</ul>
