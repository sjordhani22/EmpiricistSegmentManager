// adapted from delete.js

function processDeletePlaylistResponse(result) {
  console.log("deleted :" + result);
  
  refreshSegmentsList();
  //refreshRemoteList();									// COMMENT ME BACK IN EVENTUALLY
}

function requestDeletePlaylist(val) {
   if (confirm("Request to delete " + val)) {
     processDelete(val);
   }
}

function processDeletePlaylist(val) {
  var data = {};
  data["name"] = val;										// FIX ME

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", deletePlaylist_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processDeleteResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processDeletePlaylistResponse("N/A");
	  }
  };
}

