
function refreshRemoteList() {
	
		var xhr = new XMLHttpRequest();
		xhr.open("GET", listRemoteSites_url, true); // from API
		xhr.send();
		console.log("sent");

		// This will process results and update HTML as appropriate.
		xhr.onloadend = function() {
			console.log(xhr);
			console.log(xhr.request);
			if (xhr.readyState == XMLHttpRequest.DONE) {
				processRemoteListResponse(xhr.responseText);
				// processRemoteVideoResponse(xhr.responseText);
			} else {
				processRemoteListResponse("N/A");
				// processRemoteVideoResponse("N/A");
			}
		};
	}
  
  
// // ALSO SEND remote video segments
// var another = new XMLHttpRequest();
// another.open("GET", get_remote_url, true);
// another.setRequestHeader("x-api-key", apikey);
//
// // send the collected data as JSON
// another.send();
//   
// console.log("sent");
//
// // This will process results and update HTML as appropriate.
// another.onloadend = function () {
// if (another.readyState == XMLHttpRequest.DONE) {
// console.log ("XHR:" + another.responseText);
// processRemoteVideoResponse(another.responseText);
// }
// };
// }

function handleRegisterSite(e){
	var userIn = document.getElementById("remName");	
	  console.log(userIn);
	  var siteName = userIn.value;
	  console.log(siteName);
	  
	  if (siteName != ""){
		  
		  var data = {};
		  data["url"] = siteName;
		  
		  var js = JSON.stringify(data);
		  
		  var xhr = new XMLHttpRequest();
		  xhr.open("POST",registerTP_url, true);
		  xhr.send(js);
		  
		  
		  console.log("sent remote site data");
		// This will process results and update HTML as appropriate.
		  xhr.onloadend = function (){
			  if(xhr.readyState == XMLHttpRequest.DONE){
				  if(xhr.status == 200){
					  console.log("This is what we're getting: " + xhr.responseText);
					  //processRemoteListResponse(xhr.responseText);
					  refreshRemoteList();
				  }else{
					  console.log("Actual Response" + xhr.responseText);
					  var js = JSON.parse(xhr.responseText);
					  var err = js["response"];
					  alert (err);
				  }
			  }else{
				  //processRemoteListResponse("N/A");
				  refreshRemoteList();
			  }
		  }
	  };
	}


	// function processRemoteVideoResponse(result) {
	// console.log("res-vs:" + result);
	//
	// var remoteVideoSegmentList =
	// document.getElementById('remoteVieoSegmentsList');
	// remoteVideoSegmentList.innerHTML = "<code>" + result + "</code><p>";
	// }
	


	function processRemoteListResponse(result) {
		
		
		//refreshRemoteList();
	  console.log("res:" + result);
	  var js = JSON.parse(result);
	  var remSiteList = document.getElementById('remoteSiteList');
	  console.log("list:" + js.list);
	  var output = "";
	  
	  for (var i = 0; i < js.list.length; i++) { 
	    var constantJson = js.list[i];
	    console.log(constantJson);
	    
	    var url = constantJson["url"];
	
	    output = output + "<b>" + url + "<br/></b>"

	  }
	  
	  // Update computation result
	  remSiteList.innerHTML = output;
	}
