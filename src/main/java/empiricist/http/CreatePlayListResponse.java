package empiricist.http;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */

public class CreatePlayListResponse {
	public final String response;
	public  int httpCode;
	public String error;

	
	public CreatePlayListResponse (String s, int code) {
		this.response= s;
		this.httpCode = code;
		this.error= "";
	}
	
	// 200 means success
	public CreatePlayListResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}
}


