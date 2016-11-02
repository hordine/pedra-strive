<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productData}">
		<div class="scroller">
			<div class="title_holder">
				<h2>${title}</h2>
			</div>

			<c:choose>
				<c:when test="${component.popup}">
					<ul class="carousel jcarousel-skin popup">
						<c:forEach items="${productData}" var="product">

							<c:url value="${product.url}/quickView" var="productQuickViewUrl"/>
							<li>
								<a href="${productQuickViewUrl}" class="popup">
									<span>
										<product:productPrimaryImage product="${product}" format="thumbnail"/>
									</span>
									<h3>
											${product.name}
									</h3>
									<p>
										<format:fromPrice priceData="${product.price}"/>
									</p>
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="carousel jcarousel-skin">
						<c:forEach items="${productData}" var="product">

							<c:url value="${product.url}" var="productUrl"/>
							<li>
								<a href="${productUrl}">
									<span>
										<product:productPrimaryImage product="${product}" format="thumbnail"/>
									</span>
									<h3>
											${product.name}
									</h3>
									<p>
										<format:fromPrice priceData="${product.price}"/>
									</p>
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>
