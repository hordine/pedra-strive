<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="pickupInStoreUrl" value="/store-pickup/${searchPageData.product.code}/pointOfServices"/>

<storepickup:pickupStoreResults searchPageData="${searchPageData}" cartPage="${cartPage}" entryNumber="${entryNumber}"/>

<div class="pickup_store_search-form-footer span-17">
	<div class="pickup_store_search-paging pad_left pad_right">
		<c:if test="${(searchPageData.pagination.totalNumberOfResults gt 0)}">
			<span class="pickup_store_search-paging-info">
				<spring:theme code="search.page.currentPage" arguments="${searchPageData.pagination.currentPage + 1},${searchPageData.pagination.numberOfPages}"/>
			</span>
			<c:if test="${(searchPageData.pagination.currentPage + 1) lt searchPageData.pagination.numberOfPages}">
				<form action="${pickupInStoreUrl}" id="next_results_storepickup_form" method="get">
					<input type="hidden" name="page" id="next_page_value" value="${searchPageData.pagination.currentPage+1}"/>
					<button class="neutral paginate_next_pickupstores_button" type="button"><spring:theme code="pickup.pagination.next"/></button>
				</form>
			</c:if>
			<c:if test="${searchPageData.pagination.currentPage gt 0}">
				<form action="${pickupInStoreUrl}" id="back_results_storepickup_form" method="get">
					<input type="hidden" name="page" id="back_page_value" value="${searchPageData.pagination.currentPage-1}"/>
					<button class="neutral paginate_back_pickupstores_button" type="button"><spring:theme code="pickup.pagination.previous"/></button>
				</form>
			</c:if>
		</c:if>
		<c:if test="${(searchPageData.pagination.totalNumberOfResults eq 0)}">
			<spring:theme code="search.no.results"/>
		</c:if>
	</div>
</div>