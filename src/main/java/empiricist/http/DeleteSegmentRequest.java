package empiricist.http;

public class DeleteSegmentRequest {
	public String id;
	
	public DeleteSegmentRequest() {
		
	}
	
//	public DeleteSegmentRequest(String id) {
//		this.id = id;
//	}
//	
//	
//	public void setID(String id) {
//		this.id = id;
//	}
//	
//	public String getID() {
//		return this.id;
//	}
	
	public String toString() {
		return "Delete(" + id + ")";
	}
}
