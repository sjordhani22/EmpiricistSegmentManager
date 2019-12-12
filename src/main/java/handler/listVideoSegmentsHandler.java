package handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
//import empiricist.http.SearchSegmentRequest;
import empiricist.model.Segment;

public class listVideoSegmentsHandler implements RequestHandler<Object,AllSegmentsResponse> {

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Segment> getConstants() throws Exception {
		logger.log("in getConstants");
		SegmentsDAO dao = new SegmentsDAO();
		
		return dao.getAllSegments();
	}
	
	@Override
	public AllSegmentsResponse handleRequest(Object wasted, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		Map<String,String> map = System.getenv();
		for (String k: map.keySet()) {
			logger.log(k + "=" + map.get(k));
		}
		AllSegmentsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Segment> list = getConstants();
			ArrayList<Segment> passList = new ArrayList<>();
			for (Segment c : list) {
				//if (c.value >= input.minValue && c.value <= input.maxValue) {
					passList.add(c);
				//}
			}
			
			response = new AllSegmentsResponse(passList, 200);
		} catch (Exception e) {
			response = new AllSegmentsResponse(403, e.getMessage());
		}
		
		return response;
		
	}
}