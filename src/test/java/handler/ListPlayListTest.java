package handler;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import empiricist.http.GetPlaylistReponse;

public class ListPlayListTest extends LambdaTest{
	
	
	@Test
	public void TestList() throws IOException{
		
		listPlaylistsHandler handler = new listPlaylistsHandler();
		 GetPlaylistReponse resp = handler.handleRequest(null, createContext(null));
		 
		 Assert.assertEquals(200, resp.stats);
		 
		
		
	}
	

}
