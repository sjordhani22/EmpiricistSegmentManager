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

			Segment one = new Segment("KirkLogic","Kirk","Logic is not enough; I've got to feel my way Make absolutely sure","https://empiricistbucket2.s3.amazonaws.com/KirkLogic-converted.ogg", false);
			Segment two = new Segment("KirkNextMove","Kirk","What's your next move","https://empiricistbucket2.s3.amazonaws.com/KirkNextMove-converted.ogg",false);
			Segment three = new Segment("KirkPickup","Kirk","I think we're due for a pickup","https://empiricistbucket2.s3.amazonaws.com/KirkPickup-converted.ogg",false);
			Segment four = new Segment("KirkAWoman", "Kirk", "Worlds may change, galaxies disintegrate, but a woman always remains a woman.", "https://empiricistbucket2.s3.amazonaws.com/KirkAWoman-converted.ogg", false);
			Segment five = new Segment("KirkTheCaptain", "Kirk", "I'm the captain.","https://empiricistbucket2.s3.amazonaws.com/KirkTheCaptain-converted.ogg", false);
			Segment six = new Segment("SpockEmpiricist", "Spock", "He's a good empirical research scientist; steady, reputable, occasionally brilliant.", "https://empiricistbucket2.s3.amazonaws.com/SpockEmpiricist-converted.ogg", false );
			Segment seven = new Segment("SpockResume", "Spock", "Ready to resume course, captain.","https://empiricistbucket2.s3.amazonaws.com/SpockResume-converted.ogg", false);
			Segment eight = new Segment("McCoyHisJob", "McCoy", "It's his job, and you know it.", "https://empiricistbucket2.s3.amazonaws.com/McCoyHisJob-converted.ogg", false);
			Segment nine = new Segment("McCoyAnswerMyQuestion", "McCoy", "You're not going to answer my question, are you?","https://empiricistbucket2.s3.amazonaws.com/McCoyAnswerMyQuestion-converted.ogg", false);
			Segment ten = new Segment("McCoyTheAnswer", "McCoy", "That's an answer.", "https://empiricistbucket2.s3.amazonaws.com/McCoyTheAnswer-converted.ogg", false);
			
			List<Segment> list = new ArrayList<Segment>();
			list.add(one);
			list.add(two);
			list.add(three);
			list.add(four);
			list.add(five);
			list.add(six);
			list.add(seven);
			list.add(eight);
			list.add(nine);
			list.add(ten);  


			
			RemoteSegmentsResponse response = new RemoteSegmentsResponse(list, 200);
			return response;
		}
	}


