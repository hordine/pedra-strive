<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="storeSearchPageData" required="false" type="de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		
<c:if test="${storeSearchPageData ne null and !empty storeSearchPageData.results}">
	<div class="store_map" id="map_canvas"></div>
	<script type="text/javascript">
		function drawStoresFinal() {
		drawStores();
		
	<c:forEach items="${storeSearchPageData.results}" var="singlePos">

		addStore(new google.maps.LatLng(${singlePos.geoPoint.latitude}, ${singlePos.geoPoint.longitude}), "${singlePos.name}");

	</c:forEach>
		}
		
	
	
	</script>

</c:if>
