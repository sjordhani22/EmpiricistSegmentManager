package empiricist.http;

// NOTE: this has helper constructor that computes request based on FIXED BUCKET NAME

public class UploadSegmentRequest {
	public String id;
	public String name;
	public String quote;
	public String address;
	public String base64EncodedValue;
	public boolean system;
	
	// not sure how to make a random id for a segment?
	// gson naming conventions! camel case
	
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
	
	public String getId() {
		return this.id;
	}
	

	public void setId(String i) {
		this.id = i;
	}
	
	public void setBase64EncodedValue(String val) {
		this.base64EncodedValue = val;
	}
	
	public String getBase64EncodedValue(){
		return this.base64EncodedValue;
	}
	
	public UploadSegmentRequest() {
	}
	
	public UploadSegmentRequest(String id, String charName, String quote, String encode) {
		this.id = id;
		this.name = charName;
		this.quote = quote;
		this.base64EncodedValue = encode;
		String newID = charName + quote.substring(0,5);
		this.address = "https://empiricistbucket2.s3.amazonaws.com/" + newID + ".ogg";
    	
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
