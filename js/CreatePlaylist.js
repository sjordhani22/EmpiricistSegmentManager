// Copied from create.js in cs3733aws

function processCreatePlaylistResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  refreshPlaylistList();
  // refreshRemoteList(); // COMMENT IN EVENTUALLY
}

function handleCreatePlaylistClick(e) {	
  var userIn = document.getElementById("playName");	
  console.log(userIn);
  var playListName = userIn.value;
  console.log(playListName);
  
  if (playListName != ""){
	  
	  var data = {};
	  data["name"] = playListName;
	  
	  var js = JSON.stringify(data);
	  
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST",createPlaylist_url, true);
	  xhr.send(js);
	  
	  
	  console.log("sent Playlist data");
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
			  processCreatePlaylistResponse("N/A");
		  }
	  }
  };
}

//; history.go(0);
// from create html
