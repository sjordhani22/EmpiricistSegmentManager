package handler;

import java.io.ByteArrayInputStream;

//taken from CreatePlaylistHandler

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

import empiricist.database.SegmentsDAO;
import empiricist.http.UploadSegmentRequest;
import empiricist.http.UploadSegmentResponse;
import empiricist.model.Segment;

public class UploadSegmentHandler implements RequestHandler<UploadSegmentRequest, UploadSegmentResponse> {
	
	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	public static final String REAL_BUCKET = "empiricistbucket2/";
	
	boolean failed=false;

	String Wresponse = "";		//success or win response
	String FResponse ="";		//fail response
	String name;	
	boolean worked=false;
	
   // private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	//private AmazonS3 s3 = null;			// should we use this instead?
    

    public UploadSegmentHandler() {}

//    // Test purpose only.
//    CreatePlaylistHandler(AmazonS3 s3) {
//        this.s3 = s3;
//    }
    

    boolean uploadSegment(String id, String charname, String quote, String address, boolean system) throws Exception { 
		if (logger != null) { logger.log("in uploadSegment"); }
		SegmentsDAO dao = new SegmentsDAO();
		//
		// check if present
	
		Segment exist = dao.getSegment(id); // gets from database
		Segment segment = new Segment(id, charname, quote, address, system);
		
		dao.addSegment(segment);
		
		if (exist == null) {
			return dao.addSegment(segment);
		} else {
			return false;
		}
	}
    
	boolean createSystemPlaylist(String name, byte[]  contents) throws Exception {
		if (logger != null) { logger.log("in createSystemPlaylist"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}

		String bucket = REAL_BUCKET;
//		boolean useTestDB = System.getenv("TESTING") != null;
//		if (useTestDB) {
//			bucket = TEST_BUCKET;
//		}

		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest("empiricistbucket2", bucket + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	//
	@Override 
	public UploadSegmentResponse handleRequest(UploadSegmentRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("UploadSegmentResponse");
		String segname = req.id;
		String charname = req.charName;
		String address = req.base64EncodedValue;

		UploadSegmentResponse response;
		try {
			// how do I store the actual file?
			//byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			logger.log("no encoding");
			if (req.system) {
				if (createSystemPlaylist(req.id, null)) {
					response = new UploadSegmentResponse(req.id);
					logger.log("sys=True, if");
				} else {
					response = new UploadSegmentResponse(req.id, 422);
					logger.log("sys=True, else");
				}
			} else {
				//String contents = new String();
				//double value = Double.valueOf(contents);
				if (uploadSegment(req.id)) {
					response = new UploadSegmentResponse(req.id);
					logger.log("sys=False, if");
					req.system = true;
				} else {
					response = new UploadSegmentResponse(req.id, 422);
					logger.log("sys=False, else");
					req.system = true;
				}
			}
		} catch (Exception e) {
			response = new UploadSegmentResponse("Unable to create playlist: " + req.id + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}