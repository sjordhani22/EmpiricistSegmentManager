package handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import edu.wpi.cs.heineman.calculator.db.ConstantsDAO;
import edu.wpi.cs.heineman.calculator.model.Constant;
import empiricist.database.SegmentsDAO;
import empiricist.model.Segment;

public class listVideoSegmentsHandler implements RequestHandler<S3Event, AllSegmentResponse> {

	public LambdaLogger logger;
	
	 List<Segment> getSegment() throws Exception {
		logger.log("in getSegment");
		SegmentsDAO dao = new SegmentsDAO();
		
		return dao.getAllSegments();
	}
	
    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public listVideoSegmentsHandler() {}

    // Test purpose only.
    listVideoSegmentsHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public AllSegmentResponse handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);

        // Get the object from the event and show its content type
        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        String key = event.getRecords().get(0).getS3().getObject().getKey();
        try {
            S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
            String contentType = response.getObjectMetadata().getContentType();
            context.getLogger().log("CONTENT TYPE: " + contentType);
            return contentType;
        } catch (Exception e) {
            e.printStackTrace();
            context.getLogger().log(String.format(
                "Error getting object %s from bucket %s. Make sure they exist and"
                + " your bucket is in the same region as this function.", key, bucket));
            throw e;
        }
    }
}