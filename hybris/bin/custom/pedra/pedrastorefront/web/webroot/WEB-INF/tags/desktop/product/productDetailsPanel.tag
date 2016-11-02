<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<spring:theme code="text.addToCart" var="addToCartText"/>


<div class="span-4">
	<product:productImageCarousel galleryImages="${galleryImages}" />	
</div>
<div class="span-8"><product:productImagePanel product="${product}" /></div>
<div class="span-8 productDescription last">
	
		<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
				<h1>
					${product.name}
				</h1>
		</ycommerce:testId>
	
	
		<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
			<product:productPricePanel product="${product}"/>
		</ycommerce:testId>
		<p>
			${product.summary}
		</p>
		
		<product:productPromotionSection product="${product}"/>
		<product:productReviewSummary product="${product}"/>	
		<product:productVariantSelector product="${product}"  />
		
		<product:productAddToCartPanel product="${product}" allowAddToCart="${empty showAddToCart ? true : showAddToCart}"/>
		<product:productShareTag /> 			


	
</div>
