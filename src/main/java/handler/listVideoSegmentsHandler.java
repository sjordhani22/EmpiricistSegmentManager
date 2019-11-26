package handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import empiricist.database.SegmentsDAO;
import empiricist.http.AllSegmentsResponse;
import empiricist.model.Segment;

public class listVideoSegmentsHandler implements RequestHandler<S3Event, AllSegmentsResponse> {

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

    List<Segment> systemSegments() throws Exception {
		logger.log("in systemConstants");
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		ArrayList<Segment> sysSegments = new ArrayList<>();
	    
		// retrieve listing of all objects in the designated bucket
		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
				  .withBucketName("cs3733empiricist")    // top-level bucket
				  .withPrefix("NewVideoSegs");       // sub-folder declarations here (i.e., a/b/c)
												  
		
		// request the s3 objects in the s3 bucket 'cs3733wpi/constants' -- change based on your bucket name
		logger.log("process request");
		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
		logger.log("process request succeeded");
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		
		for (S3ObjectSummary os: objects) {
	      String id = os.getKey();
		  logger.log("S3 found:" + id);

	      // If name ends with slash it is the 'constants/' bucket itself so you skip
	      if (id.endsWith("/")) { continue; }
			
	      S3Object obj = s3.getObject("cs3733empiricist", id);
	    	
	    	try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
				Scanner sc = new Scanner(constantStream);
				String val = sc.nextLine();
				sc.close();
				
				// just grab name *after* the slash. Note this is a SYSTEM constant
				int postSlash = id.indexOf('/');
				sysSegments.add(new Segment(id.substring(postSlash+1), val,val,val, true));
			} catch (Exception e) {
				logger.log("Unable to parse contents of " + id);
			}
	    }
		
		return sysSegments;
	}
    
    @Override
    public AllSegmentsResponse handleRequest(S3Event event, Context context) {
    		logger = context.getLogger();
    		logger.log("Loading Java Lambda handler to list all constants");

    		AllSegmentsResponse response;
    		try {
    			// get all user defined constants AND system-defined constants.
    			// Note that user defined constants override system-defined constants.
    			List<Segment> list = getSegment();
    			for (Segment c : systemSegments()) {
    				if (!list.contains(c)) {
    					list.add(c);
    				}
    			}
    			response = new AllSegmentsResponse(list, 200);
    		} catch (Exception e) {
    			response = new AllSegmentsResponse(403, e.getMessage());
    		}
    		
    		return response;
    	}
}