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
import empiricist.http.RegisterSiteRequest;
import empiricist.http.RegisterSiteResponse;
import empiricist.model.RemoteSite;

public class RegisterRemoteSiteHandler implements RequestHandler<RegisterSiteRequest, RegisterSiteResponse> {
	
	LambdaLogger logger;

   // private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

   //  public RegisterRemoteSiteHandler() {}

    // Test purpose only.
//    RegisterRemoteSiteHandler(AmazonS3 s3) {
//        this.s3 = s3;
//    }
    
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
    public RegisterSiteResponse handleRequest(RegisterSiteRequest request, Context context) {
    	logger = context.getLogger();
    	logger.log(" Loading Java Handler to Register Remote Sites");
    	
    	RegisterSiteResponse response;
    	logger.log(request.toString());
    	
    	
    	try {
    		if(RegisterRemoteSite(request.Url)) {
    			
    			response = new RegisterSiteResponse(request.Url,200);
    		}
    	
    		else {
    			response = new RegisterSiteResponse(request.Url,422);
    		}
    	}
    	catch(Exception e){
    		
    		response = new RegisterSiteResponse("Unable to register remote site: " + request.Url + "(" + e.getMessage() + ")", 403);
    		
    	}
    	
    	return response;
}
}