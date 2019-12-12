package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;


import empiricist.http.DeleteSegmentRequest;
import empiricist.http.DeleteSegmentResponse;
import junit.framework.Assert;

public class DeleteSegmentTest extends LambdaTest {

	
	
	public void TestDeleteInput(String incoming, String outgoing) throws IOException {
		DeleteSegmentHandler handler = new DeleteSegmentHandler();
		DeleteSegmentRequest request = new Gson().fromJson(incoming, DeleteSegmentRequest.class);
		DeleteSegmentResponse response = handler.handleRequest(request, createContext(null));
		//
		Assert.assertEquals(outgoing, response.id);
        Assert.assertEquals(200, response.statusCode);
    }


	
	 @Test
	 public void TestDeleteSegment() throws IOException {
		 String SAMPLE_INPUT_STRING = "{\"id\": \"UhuraAll c\"}";
		 String RESULT = "UhuraAll c"; //
		 try {
	        	
	        	TestDeleteInput(SAMPLE_INPUT_STRING,RESULT);
	 
	 }
		 
	 
	 catch (IOException ioe) {
		 Assert.fail("Unable to Delete Playlist:" + RESULT+ ioe.getMessage());
	 }
 }
}

	 
	
