package handler;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import empiricist.database.RemoteSitesDAO;
import empiricist.model.RemoteSite;

public class RegisterRemoteSiteHandler implements RequestHandler<S3Event, String> {
	
	LambdaLogger logger;

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

  //  public RegisterRemoteSiteHandler() {}

    // Test purpose only.
    RegisterRemoteSiteHandler(AmazonS3 s3) {
        this.s3 = s3;
    }
    
    boolean RegisterRemoteSite(String Url) throws Exception {
		if(logger!=null) {logger.log("Registering");}
		RemoteSitesDAO dao = new RemoteSitesDAO();
	
		
		ArrayList<RemoteSite> remoteSites =	dao.getAllRemoteSites();
	boolean isthere = false;
	
		
	for(int i= 0; i< remoteSites.size(); i++) {
		
		if( remoteSites.get(i).getUrl().contentEquals(Url)) {
			
			isthere= true;
		}
		
		else if(!isthere) {
			return dao.AddRemote(new RemoteSite(Url));
		}
		
			
		
		else {
    	return false;
    }
		
	}
	return false;
		
	}
    

    @Override
    public String handleRequest(S3Event event, Context context) {
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