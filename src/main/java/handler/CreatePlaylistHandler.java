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
	
	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
		
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String REAL_BUCKET = "empiricistbucket2/";
	
	boolean failed=false;

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
    
	boolean createSystemPlaylist(String name) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}

		String bucket = REAL_BUCKET;

		byte[] contents = new byte[10];
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest("empiricistbucket2", bucket + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
    
    
    @Override
    public CreatePlayListResponse handleRequest(CreatePlayListRequest request, Context context) {	
        logger = context.getLogger();
        logger.log("Loading Java CreatePlaylistHandler to create playlists");
        logger.log(request.toString());
        
        CreatePlayListResponse response;
        try {
			//byte[] encoded = java.util.Base64.getDecoder().decode(request.base64EncodedValue);
			if (request.system) {
				if (createSystemPlaylist(request.name)) {response = new CreatePlayListResponse(request.name);} 
				else {response = new CreatePlayListResponse(request.name, 422);}
			} else {
				//String contents = new String(encoded);
				String contents = new String();
				double value = Double.valueOf(contents);
				if (createPlaylist(request.name)) {
					response = new CreatePlayListResponse(request.name);} 
				else {response = new CreatePlayListResponse(request.name, 422);}
			}
		} catch (Exception e) {
			response = new CreatePlayListResponse("Unable to create playlist: " + request.name + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}