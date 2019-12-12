package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;

import empiricist.http.CreatePlayListRequest;
import empiricist.http.CreatePlayListResponse;
import empiricist.http.DeletePlaylistRequest;
import empiricist.http.DeletePlaylistResponse;
import empiricist.http.DeleteSegmentRequest;
import empiricist.http.DeleteSegmentResponse;
import empiricist.http.UploadSegmentRequest;
import empiricist.http.UploadSegmentResponse;
import junit.framework.Assert;

public class DeleteSegmentTest extends LambdaTest {

	
	
	public void TestDeleteInput(String incoming, String outgoing) throws IOException {
		DeleteSegmentHandler handler = new DeleteSegmentHandler();
		DeleteSegmentRequest request = new Gson().fromJson(incoming, DeleteSegmentRequest.class);
		DeleteSegmentResponse response = handler.handleRequest(request, createContext(null));
		// FIX ME FIX ME
		//Assert.assertEquals(outgoing, response.name);
        Assert.assertEquals(200, response.statusCode);
    }

public void testInput(String incoming, String outgoing) throws IOException {
	 UploadSegmentHandler handler = new UploadSegmentHandler();
	 UploadSegmentRequest request = new Gson().fromJson(incoming, UploadSegmentRequest.class);
	 
	 UploadSegmentResponse response = handler.handleRequest(request, createContext(null));
	
	 Assert.assertEquals(outgoing, response.response);
       Assert.assertEquals(422, response.httpCode);
   }

	
	
	 @Test
	 public void TestDeleteSegment() throws IOException {
		 String SAMPLE_INPUT_STRING = "{\"id\": \"Test\"}";
		 String RESULT = "Test"; //
		 try {
	        	
	        	TestDeleteInput(SAMPLE_INPUT_STRING,RESULT);
	 
	 }
		 
	 
	 catch (IOException ioe) {
		 Assert.fail("Unable to Delete Playlist:" + RESULT+ ioe.getMessage());
	 }
 }
}

	 
	
