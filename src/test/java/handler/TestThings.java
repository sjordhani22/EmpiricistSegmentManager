package handler;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import empiricist.database.PlaylistsDAO;
import empiricist.database.SegmentsDAO;
import empiricist.model.Playlist;
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
	public void testPlayNames() {
	    PlaylistsDAO pd = new PlaylistsDAO();
	    try {
	    	List<String> lists = pd.getPlaylistNames();
	    	for (String s : lists) {
	    		System.out.println(s);
	    	}
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	
	@Test
	public void testPlay() {
	    PlaylistsDAO pd = new PlaylistsDAO();
	    try {
	    	List<Playlist> lists = pd.getAllPlaylists();
	    	for (Playlist s : lists) {
	    		System.out.println(s);
	    	}
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
