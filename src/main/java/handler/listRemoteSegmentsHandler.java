package handler;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import empiricist.model.Segment;

public class listRemoteSegmentsHandler implements RequestHandler<Object,RemoteSegmentsResponse> {

	
	/**
	 * Only expose RDS for the "remotable" constants whose value lies in this range.
	 */

		public LambdaLogger logger;

		@Override
		public RemoteSegmentsResponse handleRequest(Object input, Context context)  {
			logger = context.getLogger();
			logger.log("Loading Java Lambda handler to list all remote segments");

			Segment one = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output1.ogg", "worker", "one to beam up");
			Segment two = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output2.ogg", "bones", "he's dead, Jim.");
			Segment three = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output3.ogg", "worker", "Kirk, Out.");
			
			List<Segment> list = new ArrayList<Segment>();
			list.add(one);
			list.add(two);
			list.add(three);
			
			RemoteSegmentsResponse response = new RemoteSegmentsResponse(list, 200);
			return response;
		}
	}


