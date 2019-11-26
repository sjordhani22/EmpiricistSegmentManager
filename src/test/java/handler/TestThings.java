package handler;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import empiricist.database.SegmentsDAO;
import empiricist.model.Segment;

public class TestThings {

	@Test
	public void testSeg() {
	    SegmentsDAO sd = new SegmentsDAO();
	    try {
	    	List<Segment> lists = sd.getAllSegments();
	    	for (Segment s : lists) {
	    		System.out.println(s);
	    	}
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	@Test
	public void testPlay() {
	    SegmentsDAO sd = new SegmentsDAO();
	    try {
	    	List<Segment> lists = sd.getAllSegments();
	    	for (Segment s : lists) {
	    		System.out.println(s);
	    	}
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
