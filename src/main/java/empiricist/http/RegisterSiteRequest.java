package empiricist.http;

public class RegisterSiteRequest {

public String Url;

public void setUrl(String url) {
	this.Url = url;
	
}

public String getUrl() {
	
	return this.Url;

}

public RegisterSiteRequest(String Url) {
	
	this.Url = Url;
	
	
}

public RegisterSiteRequest() {
}

public String toString() {
	
	return "RegisterSite(" + Url + ")"; 
	//
}

}
