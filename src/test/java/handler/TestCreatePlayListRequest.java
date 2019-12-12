package handler;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import empiricist.http.CreatePlayListRequest;

public class TestCreatePlayListRequest {
	
	@Test
	
	public void TestCreateReq() {
		CreatePlayListRequest a = new CreatePlayListRequest();
		a.setName("abc");
		a.setSystem(true);

		Assert.assertEquals(a.getName(),"abc");
		Assert.assertEquals(a.getSystem(),true);
		
	}
	

}
