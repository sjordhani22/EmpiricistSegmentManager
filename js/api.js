
// All from API Gateway										TYPE		CODE
var base_url = "https://d7ci5h57fb.execute-api.us-east-1.amazonaws.com/Beta/"; 

var base_remote_url = "https://q644890lfb.execute-api.us-east-1.amazonaws.com/Remote/";


//POSTs
var deletePlaylist_url = base_url + "deletePlaylist"; 		// POST 	DeletePlaylist.js
var createPlaylist_url = base_url + "createPlaylist"; 		// POST		CreatePlaylist.js
var deleteSeg_url = base_url + "deleteVideoSegment";		// POST		DeleteSeg.js
var appendSeg_url = base_url + "appendSeg";					// POST
var playPlaylist_url = base_url + "playPlaylist"			// POST
var registerTP_url = base_url + "registerTP"				// POST
var removeSegFromPlay_url = base_url + "removeSegFromPlay"	// POST
var searchCharSegment_url = base_url + "searchCharSegment"	// POST
var searchStringSegment_url = base_url + "searchCharSegment"// POST
var upload_url = base_url + "uploadVideoSegment"			// POST		UploadSeg.js
var markSeg_url = base_url + "markSeg";						// POST
var unmarkSeg_url = base_url + "unmarkSeg"					// POST
var unregisterTP_url = base_url + "unregisterTP"			// POST
var calculate_url  = base_url + "calculator";  // only here for testing.
var post_remote_url = base_remote_url+ "postsegments";		// POST 	getRemote.js



//GETs
var getSegment_url = base_url + "listVideoSegments";    	// GET		getSegments.js
var getPlaylist_url = base_url + "listPlaylists";			// GET		getPlaylists.js
var listRemoteSites_url = base_url + "listRemoteSites";		// GET
var listSegInPlaylist_url = base_url + "ListSegInPlaylist";	// GET
var get_remote_url = base_remote_url+ "publicsegments";			// GET 		getRemote.js

//
//If we didn't need this before I'm not sure why we would now but they're in the API
var directAdmin_url = base_url + "directAdmin"			// POST
var directParticipant_url = base_url + "directParticipant"
var apikey = "ppdmq8sUtytYCOR6Z2oGa9fLT2qCSs32lk6so4hd";

//var sample_video_segments_url = "https://q644890lfb.execute-api.us-east-1.amazonaws.com/Remote/publicsegments";
