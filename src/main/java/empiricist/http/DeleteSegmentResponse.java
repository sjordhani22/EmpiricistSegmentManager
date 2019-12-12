package empiricist.http;

public class DeleteSegmentResponse {
	public final String id;
	public final int statusCode;
	public final String error;

	public DeleteSegmentResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = " ";
	}

	public DeleteSegmentResponse(String id, int statusCode, String error) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = error;
	}

	public String toString() {
		if (statusCode == 200) {
			return "DeleteResponse(" + id + ")";
		} else {
			return "ErrorResult(" + id + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}


