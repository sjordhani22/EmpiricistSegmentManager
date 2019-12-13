package empiricist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.amazonaws.auth.policy.Statement;

import empiricist.model.Playlist;
import empiricist.model.RemoteSite;

public class RemoteSitesDAO {
	
	java.sql.Connection conn;
	PreparedStatement prepare;
	ResultSet result;

	
	
	public RemoteSitesDAO() {
		try {
			conn = DatabaseUtil.connect();
		}
		catch(Exception e){
			conn =null;
		}
	}
	
	public boolean AddRemote(RemoteSite remote) throws Exception {
		 
		try {
         prepare = conn.prepareStatement("SELECT * FROM Remote WHERE url=?;");
         prepare.setString(1,  remote.getUrl());
         result = prepare.executeQuery();
         
         
         while (result.next()) {
             result.close();
             return false;
         }
         
         prepare = conn.prepareStatement("INSERT INTO Remote (url) values(?);");
         prepare.setString(1, remote.getUrl());
         prepare.execute();
         return true;


		
	}
	catch (Exception ex) {
		throw new Exception("Failed register RemoteSite:" +ex.getMessage());
		
	}
	}
	
	private RemoteSite makeRemoteSite(ResultSet result) throws Exception {
        String URL= result.getString("url");
        
        return new RemoteSite(URL);
    }
	
public ArrayList<RemoteSite> getAllRemoteSites() throws Exception {
        
        ArrayList<RemoteSite> Remotes = new ArrayList<RemoteSite>();
        try {
            java.sql.Statement statement = conn.createStatement();
            String query = "SELECT * FROM Remote";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                RemoteSite remote = makeRemoteSite(result);
                Remotes.add(remote);
            }
            result.close();
            statement.close();
            return Remotes;

        } catch (Exception e) {
            throw new Exception("Could not get Remote Sites: " + e.getMessage());
        }
    }
}
