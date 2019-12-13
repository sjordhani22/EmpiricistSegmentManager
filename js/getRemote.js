function refreshRemoteList() {
	
  var data = {};
//  data["minValue"] = 1;
//  data["maxValue"] = 3;
  var js = JSON.stringify(data);	
	
  var xhr = new XMLHttpRequest();
  xhr.open("POST", post_remote_url, true);
  xhr.setRequestHeader("x-api-key", apikey); 					//where is this from?

  // send the collected data as JSON
  xhr.send(js);
   
  console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processRemoteListResponse(xhr.responseText);
    } else {
    	processRemoteListResponse("N/A");
    }
  };
  
  // ALSO SEND remote video segments
  var another = new XMLHttpRequest();
  another.open("GET", get_remote_url, true);
  another.setRequestHeader("x-api-key", apikey);

  // send the collected data as JSON
  another.send();
   
  console.log("sent");

  // This will process results and update HTML as appropriate. 
  another.onloadend = function () {
    if (another.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + another.responseText);
      processRemoteVideoResponse(another.responseText);
    }
  };
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processRemoteVideoResponse(result) {
  console.log("res-vs:" + result);
  
  var remoteVideoSegmentList = document.getElementById('remoteVieoSegmentsList');
  remoteVideoSegmentList.innerHTML = "<code>" + result + "</code><p>";
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processRemoteListResponse(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var remoteConstList = document.getElementById('remoteSiteList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var constantJson = js.list[i];
    console.log(constantJson);
    
    var cname = constantJson["name"];
    var cval = constantJson["value"];
    var sysvar = constantJson["system"];
    if (sysvar) {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "<br></div>";
    } else {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
  }

  // Update computation result
  remoteConstList.innerHTML = output;
}