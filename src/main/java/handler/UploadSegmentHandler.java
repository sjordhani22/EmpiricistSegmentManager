package handler;

import java.io.ByteArrayInputStream;

//taken from CreatePlaylistHandler

import java.util.ArrayList;
import java.util.UUID;

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

	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String REAL_BUCKET = "empiricistbucket2/";

	//https://empiricistbucket2.s3.amazonaws.com/KirkLogic-converted.ogg
	public static final String baseBucketURL = "https://empiricistbucket2.s3.amazonaws.com/";  // don't forget to add .ogg!


	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */

	boolean addSegmentToDatabase(String id, String name, String quote, String address, boolean system) throws Exception { 
		if (logger != null) { logger.log("in createConstant "); }
		SegmentsDAO dao = new SegmentsDAO();

		// check if present
		Segment exist = dao.getSegment(id);
		Segment segment = new Segment (id, name, quote, address, system);
		if (exist == null) {
			return dao.addSegment(segment);
		} else {
			return false;
		}
	}

	boolean createSystemSegment(String id, byte[]  contents) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }

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
		PutObjectResult res = s3.putObject(new PutObjectRequest("empiricistbucket2", id, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));

		// if we ever get here, then whole thing was stored
		return true;
	}
	//
	@Override 
	public UploadSegmentResponse handleRequest(UploadSegmentRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UploadSegmentResponse response;
		try {
			byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			if (createSystemSegment(req.id, encoded)) {
				// things have now been added to the bucket	
				// ID, Address, and everthing else are already set by the js which passes it off through "request"
				//String address = baseBucketURL + req.id + ".ogg";				// NOW write this to the RDS database through DAO
				//String uid =  UUID.randomUUID().toString();
				addSegmentToDatabase(req.id, req.name, req.quote, req.address, true); 
				response = new UploadSegmentResponse(req.id);
			} else {
				response = new UploadSegmentResponse(req.id, 422);
			}
		} catch (Exception e) {
			response = new UploadSegmentResponse("Unable to create constant: " + req.id + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}