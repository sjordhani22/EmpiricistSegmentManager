package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import empiricist.database.SegmentsDAO;
import empiricist.http.DeleteSegmentRequest;
import empiricist.http.DeleteSegmentResponse;
import empiricist.model.Segment;


public class DeleteSegmentHandler implements RequestHandler<DeleteSegmentRequest,DeleteSegmentResponse>{
	public LambdaLogger logger = null;
	
	private AmazonS3 s3  = null;
	
	boolean deleteVideo(String id) throws Exception{
		if (logger !=null) {logger.log("In Delete Segment"); }
		
		SegmentsDAO dao = new SegmentsDAO();
		Segment exists = dao.getSegment(id);
		Segment segment = new Segment("id","", "","", false);
		if (exists !=null) {
			return dao.deleteSegment(segment);
			
		}
		else {
			return false;
		}
		
		
	}

	public DeleteSegmentResponse handleRequest(DeleteSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete segment");
		

		DeleteSegmentResponse response = null;
		logger.log(req.toString());

		SegmentsDAO dao = new SegmentsDAO();

		// See how awkward it is to call delete with an object, when you only
		// have one part of its information?
		Segment seg = new Segment(req.id);
		try {
			if (deleteVideo(req.getID())) {
				response = new DeleteSegmentResponse(req.getID(), 200);
			} else {
				response = new DeleteSegmentResponse(req.id, 422, "Unable to delete Segment." + req.getID());
			}
		} catch (Exception e) {
			response = new DeleteSegmentResponse(req.id, 403, "Unable to delete Segment: " + req.getID() + "(" + e.getMessage() + ")");
		}

		return response;
	}
}


