package handler;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

import empiricist.http.UploadSegmentRequest;
import empiricist.http.UploadSegmentResponse;
import junit.framework.Assert;


public class UploadSegmentTest extends LambdaTest {
	
	
	
	@Test
	public void TestUpload() {
		
		String path = "src/test/resources/Test64";
		File file = new File(path);
		String truePath = file.getAbsolutePath();
		
		byte[] ArrayofBytes = new byte[(int) file.length()];
		
		FileInputStream input;
		try {
			input = new FileInputStream(file);
	       input.read(ArrayofBytes); 
	    	input.close();
		} catch (Exception e) {
			System.out.println("Unable to create constant: " + e.getMessage());
		}
		
		byte[] encoding = java.util.Base64.getEncoder().encode(ArrayofBytes);
		UploadSegmentRequest  request = new UploadSegmentRequest("UhuraAll c", "Uhura", "All channels cleared sir", new String(encoding));
    	UploadSegmentResponse response = new UploadSegmentHandler().handleRequest(request, createContext(null));
    	Assert.assertEquals("UhuraAll c", response.response);
    	
    	
	}

    	
		
		
		
	}


