package empiricist.model;

import java.util.ArrayList;

public class Playlist {
	 public String name; 
	 ArrayList<Segment> segments = new ArrayList<Segment>();
	 
	 
	 public Playlist(String name, ArrayList<Segment> segments) {
		 this.name = name;
		 this.segments = segments;  
	 }
	 
	 public Playlist(String name) {
		 this.name = name;
	 }
	 
	 
	 public String getName() {return this.name;}
	 public ArrayList<Segment> getListOfSegs() {return this.segments;}
	 
	 public void appendSegment(Segment s) {
		 segments.add(segments.size() + 1, s);  
	 }
	 
	 public void removeSegment(int order) {
		 segments.remove(order);
	 }
	 
	
}
