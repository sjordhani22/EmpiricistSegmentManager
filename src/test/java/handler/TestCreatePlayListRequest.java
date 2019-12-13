package handler;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import empiricist.http.CreatePlayListRequest;
import empiricist.http.DeletePlaylistResponse;
import empiricist.http.DeleteSegmentResponse;
import empiricist.http.GetPlaylistReponse;
import empiricist.http.RegisterSiteRequest;
import empiricist.http.UploadSegmentRequest;
import empiricist.http.UploadSegmentResponse;

public class TestCreatePlayListRequest {
	
	@Test
	
	public void TestCreateReq() {
		CreatePlayListRequest a = new CreatePlayListRequest();
		a.setName("abc");
		a.setSystem(true);

		Assert.assertEquals(a.getName(),"abc");
		Assert.assertEquals(a.getSystem(),true);
		
	}
	
	
	@Test
	public void TestCreatePlaylistRequest() {
		
		CreatePlayListRequest random = new CreatePlayListRequest("n",false);
		Assert.assertEquals(random.name, "n");
		Assert.assertEquals(random.system, false);
	}
	@Test
	public void TestRequest() {
		
		CreatePlayListRequest random = new CreatePlayListRequest("n","x");
		Assert.assertEquals(random.name, "n");
		Assert.assertEquals(random.getBase64EncodedValue(),"x" );
		random.setBase64EncodedValue("64");
		Assert.assertEquals(random.getBase64EncodedValue(),"64");
		CreatePlayListRequest why= new CreatePlayListRequest( "n", "encoding", false);
		Assert.assertEquals(why.base64EncodedValue,"encoding");
		Assert.assertEquals( why.name, "n");
		Assert.assertEquals(why.system, false);
		
		
	}
	
	@Test
	public void TestDeleteRequest() {
		DeletePlaylistResponse del = new DeletePlaylistResponse("n",200,"Failed");
		Assert.assertEquals(del.error,"Failed");
		Assert.assertEquals(del.name, "n");
		Assert.assertEquals(del.statusCode, 200);
		assertEquals("DeleteResponse(" + del.name + ")",del.toString());
		DeletePlaylistResponse killme = new DeletePlaylistResponse("fick dich",422,"Failed");
		
		Assert.assertEquals("ErrorResult(" + killme.name + ", statusCode=" + killme.statusCode + ", err=" + killme.error + ")", killme.toString());
	}
	
	@Test
	
	public void TestUploadSegmentresponse() {
		UploadSegmentResponse xd = new UploadSegmentResponse("s",200);
		Assert.assertEquals(xd.response, "s");
		Assert.assertEquals(xd.httpCode, 200);
		
		assertEquals("Response(" + "s" + ")",xd.toString());
	}
	
	@Test
	public void TestGetPlaylistResponse() {
		GetPlaylistReponse a=  new GetPlaylistReponse(200,"Grant me powers lord Heineman");
		Assert.assertEquals(a.error, "Grant me powers lord Heineman");
		Assert.assertEquals(a.stats, 200);
		Assert.assertEquals(a.toString(), "AllPlaylists(0)");
	
		
	}
	@Test
	public void EmptyTestRegisterSite() {
		RegisterSiteRequest request = new RegisterSiteRequest();
		request.setUrl("bbb");
	    assertEquals(request.getUrl(), "bbb");
	}
	@Test
	public void TestDeleteSegmentResponse() {
		DeleteSegmentResponse Softend = new DeleteSegmentResponse("id",200, "We are dying") ;
		assertEquals("id",Softend.id);
		assertEquals(200, Softend.statusCode);
		assertEquals("We are dying", Softend.error);
		assertEquals(Softend.toString(),"DeleteResponse(" + Softend.id + ")");
		DeleteSegmentResponse Softendme = new DeleteSegmentResponse("id",422, "We are dying") ;
		assertEquals(Softendme.toString(),"ErrorResult(" + Softendme.id + ", statusCode=" + 422 + ", err=" + Softendme.error + ")");
		}
	

				
		
	}
	
	

	



