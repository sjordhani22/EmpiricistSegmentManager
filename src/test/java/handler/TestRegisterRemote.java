package handler;

import org.junit.Assert;
import org.junit.Test;

import empiricist.http.RegisterSiteRequest;
import empiricist.http.RegisterSiteResponse;

public class TestRegisterRemote extends LambdaTest {

	@Test
	public  void Tester() {
	RegisterSiteRequest RegiSite = new RegisterSiteRequest("random_url");
	RegisterSiteResponse response = new RegisterRemoteSiteHandler().handleRequest(RegiSite, createContext(null));
	Assert.assertEquals("random_url", response.response);
	Assert.assertEquals(200, response.httpCode);
	}
}
