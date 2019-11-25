package empiricist.model;

// Code developed by Professor Heineman
// Adapted by Shannon Carey

public class Segment {

	public final String id;
	public final String Quote;
	public final String seg;
	boolean system;	// when TRUE this is actually stored in S3 bucket

//		public Constant (String name, double value) {
//			this.name = name;
//			this.value = value;
//		}
		
	public Segment (String name, String Quote, String seg, boolean system) {
		this.id = id;
		this.Quote = Quote;
		this.seg = seg;
		this.system = system;
	}
		
	public boolean getSystem() { return system; }
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
	
