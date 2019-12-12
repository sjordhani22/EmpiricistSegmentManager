package handler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import empiricist.database.SegmentsDAO;
import empiricist.http.DeleteSegmentRequest;
import empiricist.http.DeleteSegmentResponse;
import empiricist.model.Segment;


public class DeleteSegmentHandler implements RequestHandler<DeleteSegmentRequest,DeleteSegmentResponse>{
	LambdaLogger logger;
	

	private AmazonS3 s3 = null;
	public static final String REAL_BUCKET = "empiricistbucket2/";
	
	
	boolean deleteSegmentFromDatabase(String id) throws Exception { 
		if (logger != null) { logger.log("in createConstant "); }
		SegmentsDAO dao = new SegmentsDAO();

		// check if present
		Segment exist = dao.getSegment(id);
		Segment segment = new Segment (id); /// ?????????
		if (exist == null) {
			return dao.deleteSegment(segment);
		} else {
			return false;
		}
	}

	boolean deleteSystemSegment(String id) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }

		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
			return true;
		}
		return false;
	}

		@Override 
		public DeleteSegmentResponse handleRequest(DeleteSegmentRequest req, Context context)  {
			logger = context.getLogger();
			logger.log(req.toString());

			DeleteSegmentResponse response;
			try {
				if (deleteSystemSegment(req.id)) {
					// things have now been added to the bucket	
					// ID, Address, and everything else are already set by the js which passes it off through "request"
					// stuff gets written to the database through the DAO
					deleteSegmentFromDatabase(req.id); 
					response = new DeleteSegmentResponse(req.id, 200);
				} else {
					response = new DeleteSegmentResponse(req.id, 422);
				}
			} catch (Exception e) {
				response = new DeleteSegmentResponse("Unable to create constant: " + req.id + "(" + e.getMessage() + ")", 400);
			}

			return response;
		}
}



