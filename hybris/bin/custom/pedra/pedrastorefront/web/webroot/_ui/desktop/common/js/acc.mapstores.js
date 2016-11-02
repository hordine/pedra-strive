function drawStores()
{
	var centerPoint = new google.maps.LatLng(storeSearchPageDatasourceLatitude, storeSearchPageDatasourceLongitude);
	var mapOptions = {
		zoom: 13,
		zoomControl: true,
		panControl: true,
		streetViewControl: false,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		center: centerPoint
	}

	ScrollInterceptOverlay.prototype = new google.maps.OverlayView();
	overlay = new ScrollInterceptOverlay(new google.maps.Map(document.getElementById("map_canvas"), mapOptions));
	var bounds = new google.maps.LatLngBounds(new google.maps.LatLng(storeSearchPageDataboundSouthLatitude, storeSearchPageDataboundWestLongitude), new google.maps.LatLng(storeSearchPageDataboundNorthLatitude, storeSearchPageDataboundEastLongitude));
	overlay.getMap().fitBounds(bounds);

}
if (typeof drawStoresFinal == 'undefined')
{
}
else
{
	drawStoresFinal();
}

function addStore(coord, name)
{
	var marker = new google.maps.Marker({
		position: coord,
		map: overlay.getMap(),
		title: name,
		icon: "http://maps.google.com/mapfiles/marker" + String.fromCharCode(markers.length + 65) + ".png"
	});
	markers.push(marker);
	var infowindow = new google.maps.InfoWindow({
		content: name,
		disableAutoPan: true
	});
	google.maps.event.addListener(marker, 'click', function ()
	{
		infowindow.open(overlay.getMap(), marker);
	});

}
