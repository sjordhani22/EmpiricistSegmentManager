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
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE name=?;");
	            ps.setString(1,  name);
	            ResultSet resultSet = ps.executeQuery();
	            
	            while (resultSet.next()) {
	                playlist = generatePlaylist(resultSet);
	            }
	            resultSet.close();
	            ps.close();
	            
	            return playlist;

	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Failed in getting playlist: " + e.getMessage());
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
	            throw new Exception("Failed to update playlist: " + e.getMessage());
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


	    public boolean addPlaylist(Playlist playlist) throws Exception {
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE name = ?;");
	            ps.setString(1, playlist.name);
	            ResultSet resultSet = ps.executeQuery();
	            
	            // already present?
	            while (resultSet.next()) {
	                Playlist p = generatePlaylist(resultSet);
	                resultSet.close();
	                return false;
	            }

	            ps = conn.prepareStatement("INSERT INTO Playlist (name) values(?);");
	            ps.setString(1, playlist.name);
	            ps.execute();
	            return true;

	        } catch (Exception e) {
	            throw new Exception("Failed to insert constant: " + e.getMessage());
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
	    
	    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
	        String name  = resultSet.getString("name");
	        return new Playlist (name);
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
	    