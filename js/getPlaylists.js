/**
 * 
 */

function refreshPlaylistList(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getPlaylist_url, true); //Correct URL
	xhr.send(); //SEND
	
	console.log("sent");
	
	xhr.onloadend = function(){
		console.log(xhr);
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
		      console.log ("XHR:" + xhr.responseText);
		      processListResponse(xhr.responseText);
		    } else {
		      processListResponse("N/A");
		    }
		  };
}


		function processListResponse(result) {
		  console.log("res:" + result);
		  var js = JSON.parse(result);
		  var PlayListList = document.getElementById('playlistList');	//Replace the contents of 'constantList' with a <br>* -separated list of name,value pairs. ?????
		  
		  var output = "";    
		  for (var i = 0; i < js.list.length; i++) {
		    var constantJson = js.list[i];
		    console.log(constantJson);
		    
		    
		    var cname = constantJson["name"];
		    var cseg = constantJson["segname"];
		    var corder = constantJson["order"];
		    var sysvar = constantJson["system"];
		    if (sysvar) {
		    	output = output + cname + "(<a href='javaScript:requestDeletePlaylist(\"" + cname + "\")'><img src='trash icon.png'></img></a>) <br></div>"; 
		    } else {
		    	output = output + cname + "(<a href='javaScript:requestDeletePlaylist(\"" + cname + "\")'><img src='trash icon.png'></img></a>) <br></div>";
		    	//"(<a href='javaScript:requestDelete(\"" + cseg + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
		    }
		  }

		  // Update computation result
		  PlayListList.innerHTML = output;
		}