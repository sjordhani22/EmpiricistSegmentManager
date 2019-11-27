package empiricist.http;

public class SearchSegmentRequest {
	public double minValue;
	public double maxValue;
	
	public double getMinValue( ) { return minValue; }
	public void setMinValue(double minValue) { this.minValue = minValue; }
	
	public double getMaxValue( ) { return maxValue; }
	public void setMaxValue(double maxValue) { this.maxValue = maxValue; }
	
	public SearchSegmentRequest() {
	}
	
	public SearchSegmentRequest (double minv, double maxv) {
		this.minValue = minv;
		this.maxValue = maxv;
	}
	
	public String toString() {
		return "SearchConstantRequest(" + minValue + "," + maxValue + ")";
	}
}
