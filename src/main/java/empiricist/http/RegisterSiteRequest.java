package empiricist.http;

public class RegisterSiteRequest {

String Url;

public void setUrl(String url) {
	this.Url = url;
	
}

public String getUrl() {
	
	return this.Url;

}

public RegisterSiteRequest(String Url) {
	
	this.Url = Url;
	
	
}

public String toString() {
	
	return "Register(" + Url + ")";
}

}
