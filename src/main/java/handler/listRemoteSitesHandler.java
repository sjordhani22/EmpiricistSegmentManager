package handler;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import empiricist.database.RemoteSitesDAO;
import empiricist.http.GetPlaylistReponse;
import empiricist.http.GetSitesResponse;
import empiricist.model.Library;
import empiricist.model.Playlist;
import empiricist.model.RemoteSite;
import empiricist.model.Segment;

public class listRemoteSitesHandler implements RequestHandler<Object, GetSitesResponse> {

    
    public LambdaLogger logger;

  
    
    
    @Override
    public GetSitesResponse handleRequest(Object thisone, Context context) {
    	
    	GetSitesResponse response;
    	logger = context.getLogger();
    	logger.log("Loading");
       
        try {       
            RemoteSitesDAO rsd = new RemoteSitesDAO();
            List<RemoteSite>lister = rsd.getAllRemoteSites();   
            response = new GetSitesResponse(lister,200);
            response.error = lister.get(0).toString();
            
        } catch (Exception e) {
            response = new GetSitesResponse(403, e.getMessage());
        }
        
        return response;
    }
}