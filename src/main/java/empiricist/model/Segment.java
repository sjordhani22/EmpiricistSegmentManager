package empiricist.model;

// Code developed by Professor Heineman
// Adapted by Shannon Carey

public class Segment {
	
	public final String id;
	public final String name;  // character name
	public final String quote;
	public final String address;
	public final boolean system;	// when TRUE this is actually stored in S3 bucket

//		public Constant (String name, double value) {
//			this.name = name;
//			this.value = value;
//		}
		
	public Segment (String id, String name, String quote, String address, boolean system) {
		this.id = id;
		this.name = name;
		this.quote = quote;
		this.address = address;
		this.system = system;
	}
	
	// These will be the getters for our Segment
	public String getID() {return this.id;}
	public String getCharName() {return this.name;}
	public String getQuote() {return this.quote;}
	public String getSegAddress() {return this.address;}
	public boolean getSystem() { return system; }
	
	// These will be the setters for our Segment 
	
	public void setSystem(boolean s) { system = s; }
		
		/**
		 * Equality of Constants determined by name alone.
		 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof Segment) {
			Segment other = (Segment) o;
			return id.equals(other.id);
			}	
		return false;  // not a Constant
	}
}
	
