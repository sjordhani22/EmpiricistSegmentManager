package empiricist.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Segment;

public class SegmentsDAO {


/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author heineman
 * Modified by Shannon Carey
 */
	

	java.sql.Connection conn;

    public SegmentsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();		// dont know where DatabaseUtil comes from
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Segment getConstant(String name) throws Exception {
        
        try {
            Segment segment = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                segment = generateSegment(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return segment;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean updateLibrary(Segment segment) throws Exception {
        try {
        	String query = "UPDATE library SET Quote=? SET Seg=? WHERE id=?;";	// I added SET seg, not sure if this wil work
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, segment.id);
            ps.setString(2, segment.Quote);
            ps.setString(3, segment.seg);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update Library: " + e.getMessage());
        }
    }
    
    public boolean deleteSegment(Segment segment) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM constants WHERE id = ?;");
            ps.setString(1, segment.id);			// not sure what the 1 is for?
            int numAffected = ps.executeUpdate();	// this isnt an int?
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public boolean addSegment(Segment segment) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name = ?;"); // dont know what this is
            ps.setString(1, segment.id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {						   // already present?
                Segment s = generateSegment(resultSet);
                resultSet.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO constants (id,Quote,seg) values(?,?,?);");
            ps.setString(1,segment.id);
            ps.setString(1,segment.Quote);
            ps.setString(2,segment.seg);
            ps.execute();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to add segment: " + e.getMessage());
        }
    }

    public List<Segment> getAllSegments() throws Exception {
        
        List<Segment> allSegments = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM segments";					// I don't know what this line is doing
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {						// used to iterate through rows of a database
                Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            statement.close();
            return allSegments;
        } catch (Exception e) {
            throw new Exception("getAllSegments Error- Failed in getting books: " + e.getMessage());
        }
    }
    
    private Segment generateSegment(ResultSet resultSet) throws Exception {
        String id  = resultSet.getString("id");
        String Quote  = resultSet.getString("Quote");
        String seg = resultSet.getString("seg");
        return new Segment (id, Quote, seg, true);			// not sure if the boolean should be in the constructor?
    }

}

