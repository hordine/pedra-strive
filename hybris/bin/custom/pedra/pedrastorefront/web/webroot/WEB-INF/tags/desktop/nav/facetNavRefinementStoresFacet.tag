<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="facetData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetData" %>
<%@ attribute name="userLocation" required="true" type="de.hybris.platform.acceleratorservices.store.data.UserLocationData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/store-finder" var="searchUserLocationUrl" />
<c:url value="/store-finder/position" var="autoUserLocationUrl" />

<script type="text/javascript">
	var searchUserLocationUrl = '${searchUserLocationUrl}';
	var autoUserLocationUrl = '${autoUserLocationUrl}';
</script>

<c:if test="${not empty facetData.values}">

	<c:if test="${empty userLocation}">
		<div class="item">
			<div class="category">
				<spring:theme code="text.hideFacet" var="hideFacetText"/>
				<spring:theme code="text.showFacet" var="showFacetText"/>
				<a class="refinementToggle" href="#" data-hide-facet-text="${hideFacetText}" data-show-facet-text="${showFacetText}">
					<h4><spring:theme code="search.nav.facetTitle" arguments="${facetData.name}"/></h4>
				</a>
				<a class="collapsableArrow" href="#">
					<span class="dropdown">
						<span class="dropdown-img"></span>
					</span>
				</a>
			</div>
			<div class="facetValues search_location">
				<form name="userLocationForm" action="${searchUserLocationUrl}" method="GET" id="user_location_form">
					<span class="form_field-input">
						<input type="text" name="q" id="user_location_query" class="text">
					</span>
					<button type="button" id="user_location_query_button" class="form search">
						<span class="search-icon">
							<spring:theme code="storeFinder.search" />
						</span>
					</button>
				</form>
				<form name="autoLocationForm" action="${autoUserLocationUrl}" method="POST" id="auto_location_form">
					<input type="hidden" id="latitude" name="latitude"/>
					<input type="hidden" id="longitude" name="longitude"/>
					<span class="find-stores-near-me">
						<a href="#" id="findStoresNearMe" class="neutral">
							<span class="find-stores-near-me-icon"></span>
							<spring:theme code="storeFinder.findStoresNearMe"/>
						</a>
					</span>
				</form>
			</div>
		</div>
	</c:if>
	
	<c:if test="${not empty userLocation}">
		<div class="item">
			<div class="category">
				<spring:theme code="text.hideFacet" var="hideFacetText"/>
				<spring:theme code="text.showFacet" var="showFacetText"/>
				<a class="refinementToggle" href="#" data-hide-facet-text="${hideFacetText}" data-show-facet-text="${showFacetText}">
					<h4><spring:theme code="search.nav.facetTitle" arguments="${facetData.name}"/></h4>
				</a>
				<a class="collapsableArrow" href="#">
					<span class="dropdown">
						<span class="dropdown-img"></span>
					</span>
				</a>
			</div>
						
			<div class="search_no_location hidden">
				<form name="userLocationForm" action="${searchUserLocationUrl}" method="GET" id="user_location_form">
					<span class="form_field-input">
					<label for="user_location_query" class="notVisible"><spring:theme code="storeFinder.findStoresNearMe"/></label>
						<input type="text" name="q" id="user_location_query" class="text">
					</span>
					<button type="button" id="user_location_query_button" class="form search">
						<span class="search-icon">
							<spring:theme code="storeFinder.search" />
						</span>
					</button>
				</form>
				<div class="line-text"><span><spring:theme code="storeFinder.line.text"/></span></div>
				<form name="autoLocationForm" action="${autoUserLocationUrl}" method="POST" id="auto_location_form">
					<input type="hidden" id="latitude" name="latitude"/>
					<input type="hidden" id="longitude" name="longitude"/>
					<a href="#" id="findStoresNearMe" class="neutral">
						<span class="find-stores-near-me-icon"></span>
						<spring:theme code="storeFinder.findStoresNearMe"/>
					</a>
				</form>
			</div>
			
			<div class="facetValues search_location change_location">
				<a href="#" class="change_location-link neutral" onclick="$(this).closest('div.search_location').hide(); $(this).parent().siblings().closest('div.search_no_location').show();$(this).parent().siblings().find('div.allFacetValues').hide();$(this).parent().siblings().find('a.refinementToggle').focus(); return false;">
					<spring:theme code="search.nav.changeLocation"/>
				</a>
				<c:if test="${not empty userLocation.searchTerm}">
					<span class="find-stores-near-me user-location-search-term">
						<spring:theme code="search.nav.resultsForStore" arguments="${userLocation.searchTerm}"/>
					</span>
				</c:if>
			</div>
			
			<c:if test="${not empty facetData.values}">
				<ycommerce:testId code="facetNav_facet${facetData.name}_links">
					<div class="facetValues">
						<div class="allFacetValues">
							<ul class="facet_block ${facetData.multiSelect ? '' : 'indent'}">
								<c:forEach items="${facetData.values}" var="facetValue" varStatus="status">
									<li class="${(status.index < 5 or facetValue.selected) ? '' : 'hidden'}">
										<c:if test="${facetData.multiSelect}">
											<form action="#" method="get">
												<input type="hidden" name="q" value="${facetValue.query.query.value}"/>
												<input type="hidden" name="text" value="${searchPageData.freeTextSearch}"/>
												<label class="facet_block-label">
													<input type="checkbox" ${facetValue.selected ? 'checked="checked"' : ''} onchange="$(this).closest('form').submit()"/>
													${facetValue.name} <span class="facet-value-count"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
												</label>
											</form>
										</c:if>
										<c:if test="${not facetData.multiSelect}">
											<c:url value="${facetValue.query.url}" var="facetValueQueryUrl"/>
											<a href="${facetValueQueryUrl}">${facetValue.name}</a>
											<spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/>
										</c:if>
									</li>
								</c:forEach>
							</ul>
							<c:if test="${fn:length(facetData.values) > 5}">
								<span class="more">
									<a href="#" onclick="$('.more a:first').closest('div.allFacetValues').find('li.hidden').slice(0, 5).removeClass('hidden').first().find('input:[type=checkbox]').focus();return false;"><spring:theme code="search.nav.facetShowMore_${facetData.code}" /></a>
								</span>
							</c:if>
						</div>
					</div>
				</ycommerce:testId>
			</c:if>
			
		</div>
	</c:if>
</c:if>
