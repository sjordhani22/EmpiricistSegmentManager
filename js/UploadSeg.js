
// some of this is from the add.js file

function processUploadResponse(newChar, newQuote, result) {
  console.log("result:" + result);
  var js = JSON.parse(result);

  var computation = js["result"];
  var status      = js["statusCode"];
  
  if (status == 200) {
    // Update computation result
    document.addForm.result.value = computation
  } else {
    var msg = js["error"];
    document.addForm.result.value = "error:" + msg
  }
  
  refreshSegmentsList();								// added this so that it refreshes the segments at the end: from create.js
}

function handleUploadClick(e) {
  var form = document.uploadSeg;
  var newChar = form.newSegChar.value;
  var newQuote = form.newQuote.value;
  var newID = newChar + newQuote.substring(0,5);
  

  var data = {};										// FIXME
  data["id"] = newID;
  data["name"] = newChar;
  data["quote"] = newQuote;
  data["address"] = "https://empiricistbucket2.s3.amazonaws.com/" + newID + ".ogg" ;

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