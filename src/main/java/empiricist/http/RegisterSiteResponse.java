package empiricist.http;

public class RegisterSiteResponse {
	
	public final String response;
	public final int httpCode;
	public final String error;
	
	public RegisterSiteResponse(String s, int code) {
		this.response = s;
		this.httpCode= code;
		this.error = "";
	}
	
	public RegisterSiteResponse(String s, int code, String error) {
		this.response = s;
		this.httpCode = 200;
		this.error = error;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
	
}


