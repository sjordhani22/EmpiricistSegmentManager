package handler;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import empiricist.database.PlaylistsDAO;
import empiricist.http.CreatePlayListRequest;
import empiricist.http.CreatePlayListResponse;
import empiricist.model.Playlist;
import empiricist.model.Segment;

public class CreatePlaylistHandler implements RequestHandler<CreatePlayListRequest, CreatePlayListResponse> {
	boolean failed=false;
	LambdaLogger logger;
	String Wresponse = "";
	String FResponse ="";
	String playlistName;
	boolean worked=false;
   // private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	//private AmazonS3 s3 = null;			// should we use this instead?
    

    public CreatePlaylistHandler() {}

//    // Test purpose only.
//    CreatePlaylistHandler(AmazonS3 s3) {
//        this.s3 = s3;
//    }
    
    boolean createPlaylist(String name /*String segname, int order*/ ) throws Exception { 
		if (logger != null) { logger.log("in createConstant"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		
	//	ArrayList<Segment> segments = new ArrayList<Segment>();
	//
		// check if present
		Playlist exist = dao.getPlaylist(name);
//		Playlist playlist = new Playlist (name, segments);
		
		if (exist == null) {
			return dao.addPlaylist(name);				// in his version he gave it the whole "playlist"
		} else {
			return false;
		}
	}
    
    
    @Override
    public CreatePlayListResponse handleRequest(CreatePlayListRequest request, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java CreatePlaylistHandler to create playlists");
        logger.log(request.toString());
        
        CreatePlayListResponse response;

        // Get the object from the event and show its content type
        
        try {
        	
         playlistName = request.getName();
        	
        	
           
//            String contentType = response.getObjectMetadata().getContentType();
//            context.getLogger().log("CONTENT TYPE: " + contentType);
//            return contentType;
        } catch (Exception e) {
        FResponse = "The name doesn't exist ";	
        failed = true;
           
        }
        
        try {
        	worked = createPlaylist(playlistName);
        	
        	if(worked) {
        	Wresponse = "Done";
        }
        	else {
        		Wresponse ="A Playlist with this name already exists.";
        	}
        }
        catch(Exception e) {
        	
        	FResponse= "Failed to create playlist" ;
        	failed= true;
        }
        
  
        	
        	
        
      
        
        if (failed) {
    		response = new CreatePlayListResponse(Wresponse, 200);
    	}
        
        else {
    		response = new CreatePlayListResponse(FResponse, 403);
    	}
        return response;
    }
}