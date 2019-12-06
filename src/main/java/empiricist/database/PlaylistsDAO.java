package empiricist.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Playlist;

public class PlaylistsDAO {
	
		Connection conn;
		ResultSet result;
		PreparedStatement prepare;

	    public PlaylistsDAO() {
	    	try  {
	    		conn = DatabaseUtil.connect();
	    	} catch (Exception e) {
	    		conn = null;
	    	}
	    }
	    public Playlist getPlaylist(String name) throws Exception {
	    	try {
	            Playlist playlist = null;
	            prepare = conn.prepareStatement("SELECT * FROM Playlist WHERE name=?;");
	            prepare.setString(1,  name);
	            result = prepare.executeQuery();


	    
	    	while (result.next()) {
                playlist = generatePlaylist(name);
            }
	    	result.close();
            prepare.close();
            
            return playlist;
	    	}
	    	catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Cannot get playlist: " + e.getMessage());
	        }
	    }
	    
	    public boolean updatePlaylist(Playlist playlist) throws Exception {
	        try {
	        	String query = "UPDATE Playlist SET name=? WHERE name=?;";
	        	prepare = conn.prepareStatement(query);
	            prepare.setString(2, playlist.name);
	            int numAffected = prepare.executeUpdate();
	            prepare.close();
	            
	            return (numAffected == 1);
	        } catch (Exception e) {
	            throw new Exception("Failed to update report: " + e.getMessage());
	        }
	    }
	    
	    public boolean deletePlaylist(Playlist playlist) throws Exception {
	        try {
	        	prepare = conn.prepareStatement("DELETE FROM Playlist WHERE name = ?;");
	        	prepare.setString(1, playlist.name);
	            int numAffected = prepare.executeUpdate();
	            prepare.close();
	            
	            return (numAffected == 1);

	        } 
	        
	        catch (Exception e) {
	            throw new Exception("Failed to insert playlist: " + e.getMessage());
	        }
	    }


	    public boolean addPlaylist(String name) throws Exception {
	        try {
	        	if (isItInDataBase(name)) {
	        		return false;
	        	}
	        	prepare = conn.prepareStatement("INSERT INTO Playlist(name) VALUE(?)");
	        	prepare.setString(1, name);
	        	prepare.executeQuery();
	        	return true;
	        }catch (Exception e) {
	        	e.printStackTrace();
	        	return false;
	        }
	    }
	    public List<Playlist> getAllPlaylists() throws Exception {
	        
	        try {
	        	List<Playlist>play = new ArrayList<Playlist>();
	        	List<String> playNames = getPlaylistNames();
	        	for(String s : playNames) {
	        		play.add(getPlaylist(s));
	        	}
	        	return play;
	        	
	        }catch (Exception e){
	        	e.printStackTrace();
	        	return null;
	        }
	    }
	    
	    public List<String> getPlaylistNames(){	
	    	try {
	    		prepare = conn.prepareStatement("SELECT DISTINCT name FROM Playlist");
	    		result = prepare.executeQuery();
	    		List<String> names = new ArrayList<String>();
	    		
	    		while (result.next()) {
	    			names.add(result.getString("name"));
	    		}
	    		
	    		return names;
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	    
	    private Playlist generatePlaylist(String name) throws Exception {
	        Playlist playplay = new Playlist(name);
	        SegmentsDAO seggy = new SegmentsDAO();
	        while (result.next()) {
	        	playplay.appendSegment(seggy.getSegment(result.getString("segname")));
	        }
	        return playplay;
	    }
	    
	    public boolean isItInDataBase (String name) throws Exception{
	    	try {
	    		prepare = conn.prepareStatement("SELECT * FROM Playlist WHERE name =? ");
	    		prepare.setString(1,name);
	    		result = prepare.executeQuery();
	    		boolean isit = result.next();
	    		return isit;
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    		throw e;
	    	}
	    }

	}
	    