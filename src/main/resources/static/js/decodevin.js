var APP_CONTEXT = '/altimetrik';
$(function() {
	$('#decode-vin-btn').on('click', function() {
		var vin = $('#vin-holder').val();
		if (vin.length > 0) {
			decodeVin_toJson(vin);
		}
	});
});

function decodeVin_toJson(vin) {
	$.get(APP_CONTEXT + '/api/vin/decode/' + vin, {
			'format': 'json'
		}).done(function(data) {
			processDecodedVinData(data);
		}).fail(function(xhr, textStatus, errorThrown) {
			var status = xhr.status;
			if (status !== 400) {
				alert("ERROR - Something went wrong");
				return;
			}
			processDecodedVinData(JSON.parse(xhr.responseText));
		}).always(function() {
			
		});
}

function processDecodedVinData(data) {
	var messageHolder = $('#message-holder');
	var resultsArray = data.Results;
	
	messageHolder.hide();
	
	// check for errors
	var errorCode = resultsArray.find(function(data) {
		return data.Variable === "Error Code"
	});
	
	var errorText = resultsArray.find(function(data) {
		return data.Variable === "Error Text"
	});
	
	if (errorCode !== undefined) {
		messageHolder.show();		
		$('#decodevin-error-code').text(errorCode.Value);
		$('#decodevin-error-text').text(errorText.Value);
	}
	
	// show all properties
	var resultsList = $('#results-list');
	resultsList.html();
	
	$.each(resultsArray, function(i, v) {
		if (v.Variable === 'Error Code' || v.Variable === 'Error Text') {
			return;
		}
		// add item
		var item = '<div class="item">'
			+ '<i class="check icon"></i>'
			+ '<div class="content">'
			+ '<a class="header">' + v.Variable + '</a>'
			+ '<div class="description">' + v.Value + '</div>'
			+ '</div></div>';
		
		resultsList.append(item);
	});
}