package empiricist.segment;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

public class SegmentHandler {

	private AmazonS3 s3 = null;
	
	//Im not sure what we would return when trying to grab segments from our s3 bucket 
	/*
	Segment getSegmentFromBucket(String segID) throws Exception {
		if(s3 == null) {
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		}
		S3Object obj = s3.getObject("Empiricist3733/segments", segID);

	}
	*/
	
}
