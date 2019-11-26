package empiricist.http;

public class CreatePlayListResponse {
	public String result;
	public int  statusCode;
	public String error;
	
	public CreatePlayListResponse(int statusCode, String error) {
		this.result = "";
		this.statusCode = statusCode;
		this.error = error;
	}

	public String toString() {
		if(statusCode == 200) {
			return "Result(" + result + ")";
		}
		else { 
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
