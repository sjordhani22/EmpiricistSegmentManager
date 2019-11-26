package empiricist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Playlist;

public class PlaylistsDAO {
	
	
		java.sql.Connection conn;

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
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE name=?;");
	            ps.setString(1,  name);
	            ResultSet resultSet = ps.executeQuery();


	    
	    	while (resultSet.next()) {
                playlist = generatePlaylist(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return playlist;
	    	}
	    	catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Cannot get playlist: " + e.getMessage());
	        }
	    }
	    
	    public boolean updatePlaylist(Playlist playlist) throws Exception {
	        try {
	        	String query = "UPDATE playlist SET value=? WHERE name=?;";
	        	PreparedStatement ps = conn.prepareStatement(query);
	            ps.setString(2, playlist.name);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected == 1);
	        } catch (Exception e) {
	            throw new Exception("Failed to update report: " + e.getMessage());
	        }
	    }
	    
	    public boolean deletePlaylist(Playlist playlist) throws Exception {
	        try {
	            PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist WHERE name = ?;");
	            ps.setString(1, playlist.name);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected == 1);

	        } 
	        
	        catch (Exception e) {
	            throw new Exception("Failed to insert playlist: " + e.getMessage());
	        }
	    }


	    public boolean addPlaylist(Playlist playlist) throws Exception {
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE name = ?;");
	            ps.setString(1, playlist.name);
	            ResultSet resultSet = ps.executeQuery();
	            
	              while (resultSet.next()) {
	                Playlist c = generatePlaylist(resultSet);
	                resultSet.close();
	                return false;
	            }

	            ps = conn.prepareStatement("INSERT INTO playlist (name,value) values(?,?);");
	            ps.setString(1,  playlist.name);
	            
	            ps.execute();
	            return true;

	        } catch (Exception e) {
	            throw new Exception("Error Inserting: " + e.getMessage());
	        }
	    }

	    public List<Playlist> getAllPlaylists() throws Exception {
	        
	        List<Playlist> allConstants = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT * FROM playlist";
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	                Playlist p = generatePlaylist(resultSet);
	                allConstants.add(p);
	            }
	            resultSet.close();
	            statement.close();
	            return allConstants;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting playlist: " + e.getMessage());
	        }
	    }
	    
	    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
	        String name  = resultSet.getString("name");
	       Arraylist<Segment>segment = resultSet.getString("segments");
	        
	        return new Playlist(name,segment);
	    }

	}
	    