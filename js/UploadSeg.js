
// some of this is from the add.js file

function processUploadResponse(newChar, newQuote, result) {
//  console.log("result:" + result);
//  var js = JSON.parse(result);
//
//  var computation = js["result"];
//  var status      = js["statusCode"];
//  
//  if (status == 200) {
//    // Update computation result
//    document.addForm.result.value = computation
//  } else {
//    var msg = js["error"];
//    document.addForm.result.value = "error:" + msg
//  }
  
	console.log("result:" newChar + newQuote + result);
	
	refreshSegmentsList();								// added this so that it refreshes the segments at the end: from create.js
}

function handleUploadClick(e) {

	  var form = document.uploadSeg;
	  
	  var data = {};
	  data["name"]= form.newChar.value;
	  data["quote"]= form.newQuote.value;
	  data["id"]= newChar + newQuote.substring(0,5);
	  data["address"] = "https://empiricistbucket2.s3.amazonaws.com/" + newID + ".ogg" ;
	  
	  
	  // base64EncodedValue":"data:text/plain;base64,My4xND....."
	  var segments = document.uploadSeg.base64Encoding.value.split(',');
	  data["base64EncodedValue"] = segments[1];  // skip first one 
	

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST",upload_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processUploadResponse(name, quote, xhr.responseText);
    } else {
      processUploadResponse(name, quote, "N/A");
    }
  };
}