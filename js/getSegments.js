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

function processSegmentsResponse(result) {
	  console.log("res:" + result);
	  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	  var js = JSON.parse(result);
	  var segList = document.getElementById('Library');
	  
	  var output = "";
	  for (var i = 0; i < js.list.length; i++) {
	    var constantJson = js.list[i];
	    console.log(constantJson);
	    
	    var cid = constantJson["id"];
	    var cname = constantJson["name"];
	    var cquote = constantJson["quote"];
	    var caddress = constantJson["address"];
	    var sysvar = constantJson["system"];
	    if (sysvar) {
	    	output = output + "<div id=\"const" + cid + "\"><b>" + cid + ":</b> = " + caddress + "<br></div>";
	    } else {
	    	output = output + "<div id=\"const" + cid + "\"><b>" + cid + ":</b> = " + caddress + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
	    }
	  }

	  // Update computation result
	  segList.innerHTML = output;
}
