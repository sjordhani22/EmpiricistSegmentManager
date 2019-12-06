package empiricist.http;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */

public class CreatePlayListResponse {
	public  String name;
	public  int httpCode;
	public String error;
	
	public CreatePlayListResponse (String s, int code) {
		this.name= s;
		this.httpCode = code;
		this.error= "";
	}
	
	// 200 means success
	public CreatePlayListResponse (int httpCode, String errorMessage) {
		
		this.httpCode = httpCode;
		this.error = errorMessage;
		this.name ="";
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}
}


