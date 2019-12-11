package empiricist.http;

public class UploadSegmentRequest {
	public String id;
	public String name;
	public String quote;
	public String address;
	public String base64EncodedValue;
	public boolean system;
	
	// not sure how to make a random id for a segment?
	
	public void setCharName(String character) {
		this.name = character;
	}
	
	public String getCharName() {
		return this.name;
	}
	
	public void setQuote(String q) {
		this.quote = q;
	}
	
	public String getQuote(){
		return this.quote;
	}
	
	public void setAddress(String add) {
		this.base64EncodedValue = add;
	}
	
	public String getAddress() {
		return this.base64EncodedValue;
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
	public void setbase64EncodedValue(String val) {
		this.base64EncodedValue = val;
	}
	
	public String getbase64EncodedValue(){
		return this.base64EncodedValue;
	}
	
	public UploadSegmentRequest() {
	}
	
	public UploadSegmentRequest(String id, String charName, String quote, String encode) {
		this.id = id;
		this.name = charName;
		this.quote = quote;
		this.base64EncodedValue = encode;
	}
	
	public UploadSegmentRequest(String charName, String quote, String encode, boolean system) {
		this.name = charName;
		this.quote = quote;
		this.base64EncodedValue = encode;
		this.system = system;
	}
	
	public String toString() {
		return "UploadSegment("+ id +"," + name + "," + quote + ",\" + base64EncodedValue + \")";
	}  
	
	
}
