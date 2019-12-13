package handler;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import empiricist.http.RemoteSegmentsResponse;



public class RemoteSegmentTest extends LambdaTest{

	@Test
	   public void testListSegments() {
	    	 
	        RemoteSegmentsResponse resp = new listRemoteSegmentsHandler().handleRequest(null, createContext("listsegs"));
	        Assert.assertEquals(3, resp.segments.size());
	        
	        System.out.println(new Gson().toJson(resp));
	    }
	    
	   
	}


