/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshConstantsList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", segment_url, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
	  
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	
      console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
      
    } else {
      processListResponse("N/A");
    }
  };
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processListResponse(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var constList = document.getElementById('segmentList');
  var output = "";
  if (js.statusCode != 200){
	  var error = document.createElement('Error');
	  error.innerHTML = 'Error'+js.statusCode+': '+js.error;
	  list.append(error);
	  return;
  }
  
  for(segment in json.videoSegments) {
		segment = json.videoSegments[segment];
		var li = document.createElement('li');
		var title = document.createElement('placehold');
		title.innerHTML = "Sentence: " + segment['name'];
		var characterCreate = document.createElement('placehold')
		character.innerHTML = "Character: " + segment['characterCreate'];
		var sourceCreate = document.createElement('source');
		var videoCreate = document.createElement('video');
		source.type = 'video/ogg';
		source.src = segment['location'];
		video.height = 250;
		video.width = 300;
		video.appendChild(source);
		li.appendChild(title);
		li.appendChild(character);
		li.appendChild(video);
		list.appendChild(li);
	}

  // Update computation result
  constList.innerHTML = output;
}

