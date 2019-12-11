package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import empiricist.database.SegmentsDAO;
import empiricist.http.DeleteSegmentRequest;
import empiricist.http.DeleteSegmentResponse;
import empiricist.model.Segment;


public class DeleteSegmentHandler implements RequestHandler<DeleteSegmentRequest,DeleteSegmentResponse>{
	public LambdaLogger logger = null;

	public DeleteSegmentResponse handleRequest(DeleteSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");
		

		DeleteSegmentResponse response = null;
		logger.log(req.toString());

		SegmentsDAO dao = new SegmentsDAO();

		// See how awkward it is to call delete with an object, when you only
		// have one part of its information?
		Segment seg = new Segment(req.id);
		try {
			if (dao.deleteSegment(seg)) {
				response = new DeleteSegmentResponse(req.id, 200);
			} else {
				response = new DeleteSegmentResponse(req.id, 422, "Unable to delete Segment.");
			}
		} catch (Exception e) {
			response = new DeleteSegmentResponse(req.id, 403, "Unable to delete Segment: " + req.id + "(" + e.getMessage() + ")");
		}

		return response;
	}
}


