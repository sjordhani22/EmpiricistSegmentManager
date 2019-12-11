// Copied from create.js in cs3733aws

function processAppendResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  refreshPlaylistList;
  // add segmentList or whatever its called
  // refreshRemoteList(); // COMMENT IN EVENTUALLY
}

function handleAppendClick(e) {	
  var userInSeg = document.getElementById("segApp");	
  var userInPlay = document.getElementById("playApp");	
  console.log(userInSeg);
  console.log(userInPlay);
  var segName = userInSeg.value;
  var playName = userInPlay.value;
  console.log(segName);
  console.log(playName);
  
  if (playListName != ""){
	  
	  var data = {};
	  data["segName"] = playListName;
	  data["playName"] = playListName;
	  
	  
	  var js = JSON.stringify(data);
	  
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST",appendSeg_url, true);
	  xhr.send(js);
	  
	  
	  console.log("sent Append data");
	// This will process results and update HTML as appropriate.
	  xhr.onloadend = function (){
		  if(xhr.readyState == XMLHttpRequest.DONE){
			  if(xhr.status == 200){
				  console.log("This is what we're getting: " + xhr.responseText);
				  processCreatePlaylistResponse(xhr.responseText);
			  }else{
				  console.log("Actual Response" + xhr.responseText);
				  var js = JSON.parse(xhr.responseText);
				  var err = js["response"];
				  alert (err);
			  }
		  }else{
			  processAppendResponse("N/A");
		  }
	  }
  };
}
