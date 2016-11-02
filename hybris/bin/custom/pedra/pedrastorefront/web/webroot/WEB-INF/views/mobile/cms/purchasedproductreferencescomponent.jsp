<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/mobile/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productReferences and component.maximumNumberProducts > 0}">
		<div class="scroller vertical">
			<div class="title_holder">
				<h2>${component.title}</h2>
			</div>
			<ul id="carousel" class="jcarousel-skin">
				<c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
					<c:url value="${productReference.url}/quickView" var="productQuickViewUrl" />
					<li>
						<span>
							<a href="${productQuickViewUrl}">
								<product:productPrimaryImage product="${productReference}" format="thumbnail" zoomable="false" />
							</a>
						</span>
						<c:if test="${component.displayProductTitles}">
							<h3>
								<a href="${productQuickViewUrl}">${productReference.name}</a>
							</h3>
						</c:if>
						<c:if test="${component.displayProductPrices}">
							<p><a href="${productQuickViewUrl}"><format:fromPrice priceData="${productReference.price}" /></a></p>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>
