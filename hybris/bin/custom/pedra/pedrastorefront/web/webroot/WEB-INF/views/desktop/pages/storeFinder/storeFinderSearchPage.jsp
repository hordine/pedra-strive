<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/desktop/store" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page pageTitle="${pageTitle}">

	<jsp:body>
	
		<script type="text/javascript">
			var map;
			var markers = [];
			var infowindow;
			var storelatitude = '${store.latitude}';
			var storelongitude = '${store.longitude}';
			var storename = '${store.name}';
			var storeaddressline1 = '${store.address.line1}';
			var storeaddressline2 = '${store.address.line2}';
			var storeaddresstown = '${store.address.town}';
			var storeaddresspostalCode = '${store.address.postalCode}';
			var storeaddresscountryname = '${store.address.country.name}';
			var storeSearchPageDatasourceLatitude = '${storeSearchPageData.sourceLatitude}';
			var storeSearchPageDatasourceLongitude = '${storeSearchPageData.sourceLongitude}';
			var storeSearchPageDataboundSouthLatitude = '${storeSearchPageData.boundSouthLatitude}';
			var storeSearchPageDataboundWestLongitude = '${storeSearchPageData.boundWestLongitude}';
			var storeSearchPageDataboundNorthLatitude = '${storeSearchPageData.boundNorthLatitude}';
			var storeSearchPageDataboundEastLongitude = '${storeSearchPageData.boundEastLongitude}';
			var storeSearchPageDataresults = '${storeSearchPageData.results}';
			var singlePoslatitude = '${singlePos.geoPoint.latitude}';
			var singlePoslongitude = '${singlePos.geoPoint.longitude}';
			var singlePosname = '${singlePos.name}';	
		</script>
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
		</div>
		<div id="globalMessages">
			<common:globalMessages/>
		</div>

		<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 left-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>

		<div class="span-20 right last">
			<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 last top-content-slot cms_disp-img_slot">
				<cms:component component="${feature}"/>
			</cms:pageSlot>

			<div class="span-7 store_finder">
				<store:storeSearch errorNoResults="${errorNoResults}"/>
			</div>
			<div class="span-13 last">
				<store:storesMap storeSearchPageData="${storeSearchPageData}"/>
			</div>
			<div class="span-20 last">
				<store:storeListForm storeSearchPageData="${storeSearchPageData}" locationQuery="${locationQuery}"
									 geoPoint="${geoPoint}"/>
			</div>
		</div>

	</jsp:body>

</template:page>
