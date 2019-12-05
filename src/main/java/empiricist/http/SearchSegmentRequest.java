package empiricist.http;

public class SearchSegmentRequest {
	String character;
	
	public String getCharacter( ) { return character; }
	public void setCharName(String charName) { this.character = charName; }
	
	public SearchSegmentRequest() {
	}
	
	public SearchSegmentRequest (String character) {
		this.character = character;
	}
	
	public String toString() {
		return "SearchSegmentRequest(" + character + ")";
	}
}
