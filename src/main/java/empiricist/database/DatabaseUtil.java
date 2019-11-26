package empiricist.database;

import java.sql.Connection;
import java.sql.DriverManager;

/* AWS Lambda Environment Variables ^ info page
 * https://docs.aws.amazon.com/lambda/latest/dg/env_variables.html
 * 
 * Environment variables are key-value pairs that you create in AWS, 
 * passed to you by the lambda function which calls things, 
 * which contains information that can change dynamically (eg. passwords)
 * 
 */

public class DatabaseUtil {
	
	// not sure where to set these? They seem to attach to a lambda function, but which one?
//	  dbUsername = System.getenv("dbUsername");
//	  dbPassword = System.getenv("dbPassword");
//	  rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
	
	public final static String rdsMySqlDatabaseUrl = "databaseempiricist-1.cqbdgfeayuat.us-east-2.rds.amazonaws.com";
	public final static String dbUsername = "Empiricist373"; // db username or overall?
	public final static String dbPassword = "Computerscience";
		
	public final static String jdbcTag = "jdbc:mysql://";
	public final static String rdsMySqlDatabasePort = "3306";
	public final static String multiQueries = "?allowMultiQueries=true";
	   
	public final static String dbName = "innodb";    // default created from MySQL WorkBench

	// pooled across all usages.
	static Connection conn;
	  
	  
	/**
	 * Singleton access to DB connection to share resources effectively across multiple accesses.
	 */
	protected static Connection connect() throws Exception {
		if (conn != null) { return conn; }
		
		try {
			//System.out.println("start connecting......");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					jdbcTag + rdsMySqlDatabaseUrl + ":" + rdsMySqlDatabasePort + "/" + dbName + multiQueries,
					dbUsername,
					dbPassword);
			//System.out.println("Database has been connected successfully.");
			return conn;
		} catch (Exception ex) {
			throw new Exception("Failed in database connection");
		}
	}
} 
