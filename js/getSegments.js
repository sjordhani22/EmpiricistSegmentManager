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
	  var library = {};
	  
	  for (var i = 0; i < js.list.length; i++) {
	    var segmentJson = js.list[i];
	    console.log(segmentJson);
	    var cid = segmentJson["id"];
	    var cname = segmentJson["name"];
	    var cquote = segmentJson["quote"];
	    var caddress = segmentJson["address"];
	    //String id, String name, String quote, String address, boolean system
	    //var thisSegment = new Segment (cid, cname, cquote, caddress, true);
	    //library.add(thisSegment);
	    var width = "320";
	    var height = "240;"
	    	
	    var sysvar = true; // segmentJson["system"];
	    if (sysvar) {
//	    	output = output + "<div id=\"seg" + cname + "\"><b>" + cname + ":</b> = " + caddress + "<br></div>";
	    	output = output + "<video id="+cid+" width="+width+" height="+height+" controls> <source src=" +caddress+" type=video/ogg></video>";
	    	console.log(output);
	    } else {
	    	output = AAAH;
	    	//output = output + "<div id=\"seg" + cid + "\"><b>" + cseg + ":</b> = " + caddress + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
	    }
	  }
	  // Update computation result
	  segList.innerHTML = output;
//	  return output;
}


//This was in participant html

//var data = {};
//data["arg1"] = "77";
//data["arg2"] = "123";
//
//var js = JSON.stringify(data);
//console.log("JS:" + js);
//var xhr = new XMLHttpRequest();
//xhr.open("POST", calculate_url, true);
//
//// send the collected data as JSON
//xhr.send(js);
//
//// This will process results and update HTML as appropriate. 
//xhr.onloadend = function () {
//  console.log(xhr);
//  console.log(xhr.request);
//  
//  if (xhr.readyState == XMLHttpRequest.DONE) {
//    console.log ("XHR-add-response:" + xhr.responseText);
//  } 
//};