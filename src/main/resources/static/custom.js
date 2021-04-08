let map;

// Demo below:
// Map centers at this lat/long - N/E are positive, S/W are negative
//let coords = { lat: 38.9259, lng: -79.8467 };

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center: coords,
		zoom: 13,
		//scrollwheel: false
	});

	let image = {
		url: '/gojira.png',
		scaledSize: new google.maps.Size(50, 50)
	};

	// Marker placed on our coordinates on a map
	let marker = new google.maps.Marker({
		position: coords,
		map: map,
		icon: image,
		animation: google.maps.Animation.BOUNCE
	});

	//var contentString = '<h2>Elkins, WV</h2> <p>Its okay, I guess...</p>';
	var contentString = '<h2>' + city + ', ' + state + '</h2>';

	var infowindow = new google.maps.InfoWindow({
		content: contentString
	});

	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map, marker);
	});
}