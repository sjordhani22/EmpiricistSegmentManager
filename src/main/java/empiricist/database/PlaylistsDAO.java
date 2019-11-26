package empiricist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	    }
	    }

