package empiricist.http;

public class UploadSegmentRequest {
	public String id;
	public String charName;
	public String quote; 
	//public String base64EncodedValue;
	public boolean system;
	
	// not sure how to make a random id for a segment?
	
	public void setCharName(String character) {
		this.charName = character;
	}
	
	public String getCharName() {
		return this.charName;
	}
	
	public void setQuote(String q) {
		this.quote = q;
	}
	
	public String getQuote(){
		return this.quote;
	}
	
	public void setSystem(boolean system) {
		this.system = system;
	}
	
	public boolean getSystem(){
		return this.system;
	}
	
	public String getID() {
		return this.id;
	}
//	public void setbase64EncodedValue(String val) {
//		this.base64EncodedValue = val;
//	}
	
//	public String getbase64EncodedValue(){
//		return this.base64EncodedValue;
//	}
	
	public UploadSegmentRequest() {
	}
	
	public UploadSegmentRequest(String charName, String quote) { //String encode
		this.charName = charName;
		this.quote = quote;
//		this.base64EncodedValue = encode;
	}
	
	public UploadSegmentRequest(String charName, String quote, boolean system) {
		this.charName = charName;
		this.quote = quote;
		this.system = system;
	}
	
	public String toString() {
		return "UploadSegment("+ id +"," + charName + "," + quote + ")"; //," + base64EncodedValue + "
	}  
	
	
}
