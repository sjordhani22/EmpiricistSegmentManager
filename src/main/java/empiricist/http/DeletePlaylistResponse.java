package empiricist.http;

public class DeletePlaylistResponse {
	public final String name;
	public final int statusCode;
	public final String error;

	public DeletePlaylistResponse(String name, int statusCode) {
		this.name = name;
		this.statusCode = statusCode;
		this.error = " ";
	}

	public DeletePlaylistResponse(String name, int statusCode, String error) {
		this.name = name;
		this.statusCode = statusCode;
		this.error = error;
	}

	public String toString() {
		if (statusCode == 200) {
			return "DeleteResponse(" + name + ")";
		} else {
			return "ErrorResult(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
