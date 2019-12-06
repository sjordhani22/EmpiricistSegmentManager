package handler;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import empiricist.http.GetPlaylistReponse;
import empiricist.model.Library;
import empiricist.model.Playlist;
import empiricist.model.Segment;

public class listPlaylistsHandler implements RequestHandler<Object, GetPlaylistReponse> {

    
    public LambdaLogger logger;

    @Override
    public GetPlaylistReponse handleRequest(Object thisone, Context context) {
    	
    	GetPlaylistReponse response;
    	logger = context.getLogger();
    	logger.log("Loading");
       
        try {
            logger.log("We are getting Playlists");
            Library lib = new Library();
            logger.log("We are in library!");
            List<Playlist>lister = lib.getAllPlaylist();  
            response = new GetPlaylistReponse(lister,200);
            response.error = lister.get(0).toString();
            
        } catch (Exception e) {
            response = new GetPlaylistReponse(403, e.getMessage());
        }
        
        return response;
    }
}