package empiricist.http;

//Author: George Heineman 
// Modified By: 
public class UploadSegmentResponse {
	public final String response;
	public final int httpCode;
	
	public UploadSegmentResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public UploadSegmentResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}