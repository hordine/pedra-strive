<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="store" required="false" type="de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${store ne null and store.geoPoint.latitude ne null and store.geoPoint.longitude ne null}">
	<div class="store_map_details" id="map_canvas"></div>
	<script type="text/javascript">
		function drawStoreFinal() {
			drawStore();
		}
	</script>
</c:if>

