package empiricist.http;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Segment;


public class AllSegmentsResponse {

	public class AllSegmentsResponse {
		public final List<Segment> list;
		public final int statusCode;
		public final String error;
		
		public AllSegmentsResponse (List<Segment> list, int code) {
			this.list = list;
			this.statusCode = code;
			this.error = "";
		}
		
		public AllSegmentsResponse (int code, String errorMessage) {
			this.list = new ArrayList<Segment>();
			this.statusCode = code;
			this.error = errorMessage;
		}
		
		public String toString() {
			if (list == null) { return "EmptyConstants"; }
			return "AllSegments(" + list.size() + ")";
		}
	}

}
