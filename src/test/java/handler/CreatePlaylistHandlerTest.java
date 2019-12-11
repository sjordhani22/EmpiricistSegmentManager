package handler;

//import static org.mockito.Mockito.when;

import java.io.IOException;
import com.google.gson.Gson;


import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

//import edu.wpi.cs.heineman.calculator.http.AddRequest;
import empiricist.http.CreatePlayListRequest;
import empiricist.http.CreatePlayListResponse;
import empiricist.http.DeletePlaylistRequest;
import empiricist.http.DeletePlaylistResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
//@RunWith(MockitoJUnitRunner.class) 

public class CreatePlaylistHandlerTest extends LambdaTest {
	
	
	public void testInput(String incoming, String outgoing) throws IOException {
		 CreatePlaylistHandler handler = new CreatePlaylistHandler();
		 CreatePlayListRequest request = new Gson().fromJson(incoming, CreatePlayListRequest.class);
		 
		 CreatePlayListResponse response = handler.handleRequest(request, createContext(null));
		
		 Assert.assertEquals(outgoing, response.response);
	        Assert.assertEquals(200, response.httpCode);
	    }
	void testFalseInput(String incoming, String outgoing) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlayListRequest request = new Gson().fromJson(incoming, CreatePlayListRequest.class);

    	CreatePlayListResponse response = handler.handleRequest(request, createContext(null));
    	
        Assert.assertEquals(400, response.httpCode);
    }
	
	@Test
	public void TestDeleteInput(String incoming, String outgoing) throws IOException {
		DeletePlaylistHandler handler = new DeletePlaylistHandler();
		DeletePlaylistRequest request = new Gson().fromJson(incoming, DeletePlaylistRequest.class);
		DeletePlaylistResponse response = handler.handleRequest(request, createContext(null));
		
		Assert.assertEquals(outgoing, response.name);
        Assert.assertEquals(200, response.statusCode);
    }
	
		 
	 @Test
	 public void TestCreatePlaylist() throws IOException{
		 String SAMPLE_INPUT_STRING = "{\"name\": \"Heineman is a god\"}";
		 String RESULT = "Heineman is a god";
		 try {
	        	testInput(SAMPLE_INPUT_STRING, RESULT);
	        	
	 }
		 catch (IOException ioe) {
			 Assert.fail("Unable to Create Playlist:" + RESULT+ ioe.getMessage());
		 }
	 }
	 
	 @Test
	 public void TestDeletePlaylist() throws IOException {
		 String SAMPLE_INPUT_STRING = "{\"name\": \"Heineman is a god\"}";
		 String RESULT = "Heineman youre a wizard"; //
		 try {
	        	testInput(SAMPLE_INPUT_STRING, RESULT);
	        	TestDeleteInput(SAMPLE_INPUT_STRING,RESULT);
	 }
		 catch (IOException ioe) {
			 Assert.fail("Unable to Create Playlist:" + RESULT+ ioe.getMessage());
		 }
	 }
	  
	 
	 @Test
	 public void FailTest() {
		 String SAMPLE_INPUT_STRING = "{\"namef\": \"Heineman is a god\"}";
		 String RESULT = "Unable to Create Playlist:";
		 try {
	        	testFalseInput(SAMPLE_INPUT_STRING, RESULT);
		 
		 
		 
		// 
	 }
		 catch(Exception ioe){
			 Assert.fail("Unable to Create Playlist:"  + ioe.getMessage());
		 }

	 }
}

