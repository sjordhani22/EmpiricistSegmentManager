package handler;

import org.junit.Assert;
import org.junit.Test;

import empiricist.http.AllSegmentsResponse;

public class ListSegmentsHandlerTest extends LambdaTest {
	
	@Test
	public void TestListsSeg() {
		
		 listVideoSegmentsHandler handler = new listVideoSegmentsHandler();
	        
	        AllSegmentsResponse resp = handler.handleRequest(null, createContext(null));
	    
	        Assert.assertEquals(200, resp.statusCode);
	    }
	
		
		
	}


