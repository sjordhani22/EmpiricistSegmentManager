package handler;

import org.junit.Assert;
import org.junit.Test;

import empiricist.http.RegisterSiteRequest;
import empiricist.http.RegisterSiteResponse;

public class TestRegisterRemote extends LambdaTest {

	@Test
	public  void Tester() {
	RegisterSiteRequest RegiSite = new RegisterSiteRequest("Georgie_is_a_wizard");
	RegisterSiteResponse response = new RegisterRemoteSiteHandler().handleRequest(RegiSite, createContext(null));
	Assert.assertEquals("Georgie_is_a_wizard", response.response);
	Assert.assertEquals(200, response.httpCode);
	
	RegisterSiteRequest duplicate = new RegisterSiteRequest("Georgie_is_a_wizard");
	RegisterSiteResponse res = new RegisterRemoteSiteHandler().handleRequest(RegiSite, createContext(null));
	Assert.assertEquals("Georgie_is_a_wizard", response.response);
	Assert.assertEquals(422, response.httpCode);
	
	
	}
}
