/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 *    
 *    
 *    
 *    <video id="allFull" controls="" width="320" height="240">
  <source src="startrek.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>
 */
function refreshSegmentsList() {  
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
	  var segList = document.getElementById('id');
	  
	  var output = "";
	  for (var i = 0; i < js.list.length; i++) {
	    var constantJson = js.list[i];
	    console.log(constantJson);
	    
	    var sname = constantJson["name"];
	    var squote = constantJson["quote"];
	    var saddress = constantJson["address"];
	    var sysvar = constantJson["system"];
	    if (sysvar) {
	    	output = output + "<div id=\"const" + sid + "\"><b>" + sname + "\"><b>" + squote +":</b> = " + caddress + "<br></div>";
	    } else {
	    	output = output + "<div id=\"const" + sid + "\"><b>" + sname + "\"><b>" + squote + ":</b> = " + caddress + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
	    }
	  }
	// Update computation result
	constList.innerHTML = output;
}

