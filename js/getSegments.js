/**
 * Refresh constant list from server
 * 
 * GET list_url RESPONSE list of [name, value] constants
 * 
 * 
 * 
 * <video id="allFull" controls="" width="320" height="240"> <source
 * src="startrek.ogg" type="video/ogg"> Your browser does not support the video
 * tag. </video>
 */
function refreshSegmentsList() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", segment_url, true);
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate.
	xhr.onloadend = function() {
		console.log(xhr);
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			processAllSegmentsResponse(xhr.responseText);
		} else {
			processAllSegmentsResponse("N/A");
		}
	};
}

/**
 * Respond to server JSON object.
 * 
 * Replace the contents of 'constantList' with a <br>
 * -separated list of name,value pairs.
 */

function processAllSegmentsResponse(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	var segList = document.getElementById('id');

	for (segment in js.videoSegments) {
		
		var title = document.createElement('p');
		var cre = document.createElement('cre');
		var character = document.createElement('name');
		var video = document.createElement('address');
		var source = document.createElement('address');
		segment = json.videoSegments[segment];
		title.innerHTML = "Sentence: " + segment['quote'];
		character.innerHTML = "Character: " + segment['name'];
		source.type = 'video/ogg';
		source.src = segment['address'];
		video.width = 320;
		video.height = 240;
		video.controls = true;
		video.appendChild(source);
		cre.appendChild(title);
		cre.appendChild(character);
		cre.appendChild(video);
		seglist.appendChild(cre);

	}
}
