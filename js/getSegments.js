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
	xhr.withCredentials = true;
	Access-Control-Allow-Credentials: true
	xhr.open("GET", getSegment_url, true); //from API
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate.
	xhr.onloadend = function() {
		console.log(xhr);
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			processSegmentsResponse(xhr.responseText);
		} else {
			processSegmentsResponse("N/A");
		}
	};
}

/**
 * Respond to server JSON object.
 * 
 * Replace the contents of 'constantList' with a <br>
 * -separated list of name,value pairs.
 */

function processSegmentsResponse(result) {
	  console.log("res:" + result);
	  var js = JSON.parse(result);
	  var segList = document.getElementById('segmentList');		//Replace the contents of 'constantList' with a <br>* -separated list of name,value pairs. ?????
	  var output = "";
	  
	  for (var i = 0; i < js.list.length; i++) {
	    var segmentJson = js.list[i];
	    console.log(segmentJson);
	    
	    var cid = segmentJson["id"];
	    var cname = segmentJson["name"];
	    var cquote = segmentJson["quote"];
	    var caddress = segmentJson["address"];
	    var sysvar = true; // segmentJson["system"];
	    if (sysvar) {
	    	output = output + "<div id=\"seg" + cname + "\"><b>" + cname + ":</b> = " + caddress + "<br></div>";
	    	console.log(output);
	    } else {
	    	output = output + "<div id=\"AAAAH" + cid + "\"><b>" + cid + ":</b> = " + caddress + "<br></div>"
	    	//output = output + "<div id=\"seg" + cid + "\"><b>" + cseg + ":</b> = " + caddress + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
	    }
	  }
	  // Update computation result
	  segList.innerHTML = output;
//	  return output;
}
