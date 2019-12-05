
// All from API Gateway										TYPE		CODE
var base_url = "https://y8m0vdypsc.execute-api.us-east-1.amazonaws.com/Test/"; 

// NEW ONES

// GETs
var getSegment_url = base_url + "listVideoSegments";    	// GET		getSegments.js

// POSTs
var calculate_url  = base_url + "calculator";  // only here for testing.


// OLD ONES to be moved up on a per-case basis


//POSTs
var deletePlaylist_url = base_url + "deletePlaylist"; 		// POST 	DeletePlaylist.js
var createPlaylist_url = base_url + "createPlayList"; 		// POST		CreatePlaylist.js
var deleteSeg_url = base_url + "deleteVideoSegment";		// POST		DeleteSeg.js
var appendSeg_url = base_url + "appendSeg";					// POST
var playPlaylist_url = base_url + "playPlaylist"			// POST
var registerTP_url = base_url + "registerTP"				// POST
var removeSegFromPlay_url = base_url + "removeSegFromPlay"	// POST
var searchCharSegment_url = base_url + "searchCharSegment"	// POST
var searchStringSegment_url = base_url + "searchCharSegment"// POST
var upload_url = base_url + "uploadVideoSegment"// POST
var markSeg_url = base_url + "markSeg";						// POST
var unmarkSeg_url = base_url + "unmarkSeg"					// POST
var unregisterTP_url = base_url + "unregisterTP"			// POST

//GETs
var getPlaylist_url = base_url + "listPlaylists";			// GET		getPlaylists.js
var listRemoteSites_url = base_url + "listRemoteSites";		// GET
var listSegInPlaylist_url = base_url + "ListSegInPlaylist";	// GET


//If we didn't need this before I'm not sure why we would now but they're in the API
var directAdmin_url = base_url + "directAdmin"			// POST
var directParticipant_url = base_url + "directParticipant"

// NEEDED FOR G3					JS FILE MADE?		Handler?			NOTES:
// List of segments							Y 				Y			- need help
// Upload new segment						  							- no idea how to do this
// Delete segment							Y

// List of playlists						Y 				Y			- need help
// Create new playlist						Y				Y
// Delete new playlist						Y

// Search for segment by character name		  							- a tomorrow problem
// Search for segment by text				  							- a tomorrow problem

// Add a remote site						  							- a tomorrow problem