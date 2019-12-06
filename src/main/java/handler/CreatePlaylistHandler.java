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
    /*
    boolean createSystemConstant(String name, byte[]  contents) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}

		String bucket = REAL_BUCKET;
		boolean useTestDB = System.getenv("TESTING") != null;
		if (useTestDB) {
			bucket = TEST_BUCKET;
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest("cs3733wpi", bucket + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	
	@Override 
	public CreatePlayListResponse handleRequest(CreatePlayListRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreatePlayListResponse response;
		try {
			byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			if (req.system) {
				if (createSystemConstant(req.name, encoded)) {
					response = new CreatePlayListResponse(req.name);
				} else {
					response = new CreatePlayListResponse(req.name, 422);
				}
			} else {
				String contents = new String(encoded);
				double value = Double.valueOf(contents);
				
				if (createPlaylist(req.name, value)) {
					response = new CreatePlayListResponse(req.name);
				} else {  
					response = new CreatePlayListResponse(req.name, 422);
				}
			}
		} catch (Exception e) {
			response = new CreatePlayListResponse("Unable to create constant: " + req.name + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
	*/
}