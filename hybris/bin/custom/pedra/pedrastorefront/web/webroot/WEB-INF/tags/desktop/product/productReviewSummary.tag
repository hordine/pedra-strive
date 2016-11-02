<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>


<div class="prod_review">
			
				<span class="stars large" style="display: inherit;">
					<span style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
				</span>
				<c:if test="${not empty product.reviews}"><p class="average-rating"><fmt:formatNumber maxFractionDigits="1" value="${product.averageRating}" />/5</p></c:if>
			
			<div style="clear:left;">
			<c:if test="${not empty product.reviews}">
				<p class="prod_review-info">
					<c:choose>
						<c:when test="${fn:length(product.reviews) > 1}">
							<a href="#" id="based_on_reviews"><spring:theme code="review.based.on" arguments="${fn:length(product.reviews)}"/></a>
						</c:when>
						<c:otherwise>
							<a href="#" id="based_on_reviews"><spring:theme code="review.based.on.one" arguments="${fn:length(product.reviews)}"/></a>
						</c:otherwise>
					</c:choose>
				</p>
			</c:if>
			
			<p class="prod_review-new">
				<a href="#" id="write_review_action_main"><spring:theme code="review.write.title" arguments="${product.name}" /></a>
			</p>
			
			</div>
		</div>