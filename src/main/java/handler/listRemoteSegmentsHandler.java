package handler;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import empiricist.http.RemoteSegmentsResponse;
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

			Segment one = new Segment("KirkLogic","Kirk","Logic is not enough; I’ve got to feel my way Make absolutely sure","https://empiricistbucket2.s3.amazonaws.com/KirkLogic-converted.ogg", false);
			Segment two = new Segment("KirkNextMove","Kirk","What’s your next move","https://empiricistbucket2.s3.amazonaws.com/KirkNextMove-converted.ogg",false);
			Segment three = new Segment("KirkPickup","Kirk","I think we’re due for a pickup","https://empiricistbucket2.s3.amazonaws.com/KirkPickup-converted.ogg",false);
			
			List<Segment> list = new ArrayList<Segment>();
			list.add(one);
			list.add(two);
			list.add(three);
			
			RemoteSegmentsResponse response = new RemoteSegmentsResponse(list, 200);
			return response;
		}
	}


