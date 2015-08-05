package walker.bryson.lawservices.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import walker.bryson.lawservices.core.Constants;

public class DBDriver {
	
	private String dbURL = Constants.DEFAULT_DB_URL;
	private String username = Constants.DEFAULT_DB_USER;
	private String password = Constants.DEFAULT_DB_PASS;
	
	public DBDriver() {
		this(Constants.DEFAULT_DB_URL, Constants.DEFAULT_DB_USER, Constants.DEFAULT_DB_PASS);
	}
	
	public DBDriver(String dbURL, String username, String password) {
		
		this.dbURL = dbURL;
		this.username = username;
		this.password = password;
		
		try {
			Class.forName(Constants.DB_DRIVER_CLASS_NAME);
			
			// TEST CONNECTION
			DriverManager.getConnection(dbURL, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return this.getConn().prepareStatement(query).executeQuery();
	}
	
	private Connection getConn() throws SQLException {
		return DriverManager.getConnection(dbURL, username, password);
	}
	
	public static void close(ResultSet rS) throws SQLException {
		if (rS != null) {
			close(rS.getStatement());
			rS.close();
		}
	}

	private static void close(Statement statement) throws SQLException {
		if (statement != null) {
			close(statement.getConnection());
			statement.close();
		}
	}

	private static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
