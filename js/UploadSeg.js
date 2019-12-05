
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
  var form = document.addForm;
  var newChar = form.newChar.value;
  var newQuote = form.newQuote.value;
  var newName = newChar + "7"							// have it randomly generate a new id number FIX

  var data = {};
  data["arg1"] = arg1;
  data["arg2"] = arg2;

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
      processAddResponse(arg1, arg2, xhr.responseText);
    } else {
      processAddResponse(arg1, arg2, "N/A");
    }
  };
}