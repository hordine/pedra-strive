<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>


<c:if test="${not empty pageData.breadcrumbs}">
<div class="nav_column">
	<div class="title_holder">
		<h2><spring:theme code="search.nav.appliedFilters"/></h2>
	</div>
	<div class="item">
		<ul class="facet_block">
			<c:forEach items="${pageData.breadcrumbs}" var="breadcrumb">
				<li class="remove_item_left">
					<c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>
					<a href="${removeQueryUrl}" class="remove_item_left_name">${breadcrumb.facetValueName}</a>
					<span class="remove">
						<a href="${removeQueryUrl}"  >
							<spring:theme code="search.nav.removeAttribute" var="removeFacetAttributeText"/>
							<theme:image code="img.iconSearchFacetDelete" title="${removeFacetAttributeText}" alt="${removeFacetAttributeText}" />
						</a>
					</span>
					<div class="clear"></div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
</c:if>
