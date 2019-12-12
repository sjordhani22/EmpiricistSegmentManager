package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;


import empiricist.http.DeleteSegmentRequest;
import empiricist.http.DeleteSegmentResponse;
import empiricist.http.GetPlaylistReponse;
import empiricist.http.UploadSegmentRequest;
import empiricist.http.UploadSegmentResponse;
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
	 @Test
	 public void TestFailDeleteSegment() {
		 String  SAMPLE_INPUT_STRING= "{\"id\": \"hsdhkjsadhsjdh\"}";
		 String RESULT = "hsdhkjsadhsjdh";
		 DeleteSegmentRequest req = new DeleteSegmentRequest();
		 DeleteSegmentHandler handler = new DeleteSegmentHandler();
		 DeleteSegmentResponse resp = handler.handleRequest(req, createContext(null));
	//	 DeleteSegmentResponse response = new DeleteSegmentResponse();
		 try {
			 TestDeleteInput(SAMPLE_INPUT_STRING,RESULT);
			 Assert.assertEquals(400, resp.statusCode);
		 }
		 catch (IOException ioe) {
			 Assert.fail("Unable to Delete Playlist:" + RESULT+ ioe.getMessage());
		 }
	 }
	
	 }




	 
	
