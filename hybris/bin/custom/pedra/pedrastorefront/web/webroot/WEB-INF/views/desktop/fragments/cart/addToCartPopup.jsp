<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

{	"cartData": {
		"total": 	"${cartData.totalPrice.value}",
		"products": [
		<c:forEach items="${cartData.entries}" var="cartEntry" varStatus="status">
			{
				"sku":		"${cartEntry.product.code}",
				"name": 	"${cartEntry.product.name}",
				"qty": 		"${cartEntry.quantity}",
				"price": 	"${cartEntry.basePrice.value}",
				"categories": [
				<c:forEach items="${cartEntry.product.categories}" var="category" varStatus="categoryStatus">
					"${category.name}"<c:if test="${not categoryStatus.last}">,</c:if>
				</c:forEach>
				]
			}<c:if test="${not status.last}">,</c:if>
		</c:forEach>
		]
	},
	"cartPopupHtml":	"<spring:escapeBody javaScriptEscape="true">
							<spring:theme code="text.addToCart" var="addToCartText"/>
							<c:url value="/cart" var="cartUrl"/>
							<c:url value="/cart/checkout" var="checkoutUrl"/>

							<div class="title">
								<theme:image code="img.addToCartIcon" alt="${addToCartText}" title="${addToCartText}"/>
								<h3> <spring:theme code="basket.added.to.basket" /></h3>
								<a href="#" class="close" id="add_to_cart_close"></a>
							</div>

								<div class="cart_modal_popup">
								<div class="cart_popup_error_msg"><spring:theme code="${errorMsg}" /></div>
								<div class="prod_image">
									<product:productPrimaryImage product="${product}" format="cartIcon"/>
								</div>
								<div class="prod_info">
									<p>${product.name}</p>
									<p class="prod_options">
										<c:forEach items="${product.baseOptions}" var="baseOptions">
											<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
												<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
													<span class="prod_color">
														<spring:theme code="product.variants.colour"/>&nbsp;
														<img src="${baseOptionQualifier.image.url}"  alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}"/>
													</span>
												</c:if>
												<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
													<span class="prod_size"><spring:theme code="product.variants.size"/>&nbsp;${baseOptionQualifier.value}</span>
												</c:if>
											</c:forEach>
										</c:forEach>
									</p>
									<p class="prod_quantity"><spring:theme code="popup.cart.quantity.added"/>&nbsp;${quantity}</p>
									<div class="prod_price">
										<p><format:price priceData="${entry.basePrice}"/></p>
									</div>
								</div>
							</div>

								<div class="links">
								<a href="${cartUrl}" class="positive large">
									<theme:image code="img.addToCartIcon" alt="${addToCartText}" title="${addToCartText}"/>
									<spring:theme code="checkout.checkout" />
								</a>
							</div>
						</spring:escapeBody>"
}
