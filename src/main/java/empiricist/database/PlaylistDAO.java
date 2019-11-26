package empiricist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Playlist;


// Order is not a thing which is stored in Playlist
// segname is not a public quality

public class PlaylistDAO {

	java.sql.Connection conn;

    public PlaylistDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public Playlist getPlaylist(Integer name) throws Exception {
        
        try {
            Playlist playlist = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name=?;");
            ps.setInt(1,0);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                playlist = generatePlaylist(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return playlist;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("getPlaylist error: Failed in getting playlist: " + e.getMessage());
        }
    }
    
    public boolean updateLibrary(Playlist Playlist) throws Exception {
        try {
        	String query = "UPDATE Playlist SET Quote=? SET Seg=? WHERE id=?;";	// I added SET seg, not sure if this wil work
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Playlist.getName());
            ps.setString(2, Playlist.segname);
            ps.setInt(3, Playlist.getOrder()); //////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! this is not a thing
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update Library: " + e.getMessage());
        }
    }
    
    public boolean deletePlaylist(Playlist Playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE id = ?;");
            ps.setString(1, Playlist.id);			// not sure what the 1 is for?
            int numAffected = ps.executeUpdate();	// this isnt an int?
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public boolean addPlaylist(Playlist Playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE name = ?;"); // dont know what this is
            ps.setString(1, Playlist.getName());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {						   // already present?
                Playlist s = generatePlaylist(resultSet);
                resultSet.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO Playlist (id,name,quote,address) values(?,?,?,?);");
            ps.setInt(1,Playlist.getName());
            ps.setString(2,Playlist.segName);
            ps.setInt(3,Playlist.order);
            ps.execute();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to add Playlist: " + e.getMessage());
        }
    }

    public List<Playlist> getAllPlaylists() throws Exception {
        
        List<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Playlist";					// I don't know what this line is doing
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {						// used to iterate through rows of a database
                Playlist s = generatePlaylist(resultSet);
                allPlaylists.add(s);
            }
            resultSet.close();
            statement.close();
            return allPlaylists;
        } catch (Exception e) {
            throw new Exception("getAllPlaylists Error- Failed in getting books: " + e.getMessage());
        }
    }
    
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        Integer name  = resultSet.getInt(0);				// this should either be column 1 or column 0
        String quote = resultSet.getString("segName");
        Integer address = resultSet.getInt(2);
        return new Playlist (name, segName, order, true);			// not sure if the boolean should be in the constructor?
    }

	
	
}
