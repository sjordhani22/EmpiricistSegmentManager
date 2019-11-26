package empiricist.model;

import java.util.ArrayList;
import java.util.List;

import empiricist.database.PlaylistsDAO;
import empiricist.database.SegmentsDAO;

public class Library {
	
	List<Playlist> playplay;
	List<Segment> seggy;
	
	public Library() throws Exception{
		seggy = new ArrayList<Segment>();
		playplay = new ArrayList<Playlist>();
		databaseloader();
	}
	
	void databaseloader()throws Exception {
		SegmentsDAO seggyd;
		PlaylistsDAO playplayd;
		try {
			seggyd = new SegmentsDAO();
			playplayd = new PlaylistsDAO();
			seggy = seggyd.getAllSegments();
			playplay = playplayd.getAllPlaylists();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		
		public boolean addPlaylist(String name)throws Exception{ //creates a playlist
			
			for (int i = 0; i<playplay.size();i++) {
				if(playplay.get(i).name.equals(name)) {
					throw new Exception("This segment is already in the library");
				}
			}
			playplay.add(new Playlist(name));
			return true;
		}
		
		public Playlist getPlaylist(String name)throws Exception{ //gets a playlist
			
			for (int i = 0; i<playplay.size();i++) {
				if(playplay.get(i).name.equals(name)) {
					return playplay.get(i);
				}
			}
			throw new Exception("The named Playlist doesnt exist.");
		}
		
		public List<Playlist> getAllPlaylist(){
			return this.playplay;
		}
		
		public List<Segment> getSegments(){ // gets all segments
			return this.seggy;
		}
		
}

