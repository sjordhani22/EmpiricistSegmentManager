package handler;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;


import empiricist.database.PlaylistsDAO;
import empiricist.http.CreatePlayListRequest;
import empiricist.http.CreatePlayListResponse;
import empiricist.model.Playlist;
import empiricist.model.Segment;

public class CreatePlaylistHandler implements RequestHandler<CreatePlayListRequest, CreatePlayListResponse> {
	boolean failed=false;
	LambdaLogger logger;
	String Wresponse = "";		//success or win response
	String FResponse ="";		//fail response
	String name;	
	boolean worked=false;
   // private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	//private AmazonS3 s3 = null;			// should we use this instead?
    

    public CreatePlaylistHandler() {}

//    // Test purpose only.
//    CreatePlaylistHandler(AmazonS3 s3) {
//        this.s3 = s3;
//    }
    
    
    /*String segname, int order*/ 
    boolean createPlaylist(String name) throws Exception { 
		if (logger != null) { logger.log("in createPlaylist"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		
		// check if present
		Playlist exist = dao.getPlaylist(name); // gets from database
		Playlist playlist = new Playlist(name);
		
		if (exist == null) {
			return dao.addPlaylist(playlist);
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
        	
          name = request.getPlayListName();
        	
        	
           
//            String contentType = response.getObjectMetadata().getContentType();
//            context.getLogger().log("CONTENT TYPE: " + contentType);
//            return contentType;
          
        } catch (Exception e) {
        FResponse = "The name doesn't exist ";	
        failed = true;
           
        }
        
        try {
        	worked = createPlaylist(name);
        	
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
    		response = new CreatePlayListResponse(403, FResponse);
    	}
        
        else {
    		response = new CreatePlayListResponse(Wresponse, 200);
    	}
        return response;
    }
}