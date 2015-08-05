package com.comname.lawservices.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.comname.lawservices.core.Constants;

/**
 * Java to MySQL Database interfacer. Manages
 * creating connections, executing queries,
 * and executing updates.
 * 
 * @version 1.0
 * @since 8/01/2015
 */
public class DBDriver {
	/** The Database URL used for accessing the database. */
	private String dbURL = Constants.DEFAULT_DB_URL;
	
	/** The Database username used for accessing the database. */
	private String username = Constants.DEFAULT_DB_USER;
	
	/** The Database password used for accessing the database. */
	private String password = Constants.DEFAULT_DB_PASS;
	
	
	/**
	 * Creates a Database Driver object with
	 * default URL, username, and password.
	 */
	public DBDriver() {
		this(Constants.DEFAULT_DB_URL, Constants.DEFAULT_DB_USER, Constants.DEFAULT_DB_PASS);
	}
	
	/**
	 * Creates a Database Driver object by
	 * creating a connection to the database.
	 * 
	 * @param dbURL the database is accessed at.
	 * @param username username to access database with.
	 * @param password password to access database with.
	 */
	public DBDriver(String dbURL, String username, String password) {
		
		this.dbURL = dbURL;
		this.username = username;
		this.password = password;
		
		try {
			Class.forName(Constants.DB_DRIVER_CLASS_NAME);
			
			// TEST CONNECTION
			getConn();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("SEVERE ERROR: " + e.getMessage() + "\n\n EXITING");
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could not Connect: " + e.getMessage() + "\n\n EXITING");
			System.exit(0);
		}
	}
	
	/**
	 * Executes the inputed query and returns the result.
	 * 
	 * @param query MySQL Query to execute.
	 * @return Results of Query.
	 * @throws SQLException if an error occurs due to query.
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		return this.getConn().prepareStatement(query).executeQuery();
	}
	
	/**
	 * Executes the inputed update.
	 * 
	 * @param update MySQL Update to execute.
	 * @throws SQLException if an error occurs due to update.
	 */
	public void executeUpdate(String update) throws SQLException {
		this.getConn().prepareStatement(update).executeUpdate();
	}
	
	/**
	 * Gets the SQL Connection by creating a new connection.
	 * 
	 * @return new SQL Connection.
	 * @throws SQLException if an error occurs getting connection.
	 */
	private Connection getConn() throws SQLException {
		return DriverManager.getConnection(dbURL, username, password);
	}
	
	/**
	 * Closes the inputed result set
	 * as well as its resources.
	 * 
	 * @param rS ResultSet to close.
	 * @throws SQLException if an error occurs closing
	 * 		ResultSet.
	 */
	public void close(ResultSet rS) throws SQLException {
		if (rS != null) {
			close(rS.getStatement());
			rS.close();
		}
	}

	/**
	 * Closes the inputed statement
	 * as well as its resources.
	 * 
	 * @param statement Statement to close.
	 * @throws SQLException if an error occurs closing
	 * 		statement.
	 */
	private void close(Statement statement) throws SQLException {
		if (statement != null) {
			close(statement.getConnection());
			statement.close();
		}
	}

	/**
	 * Closes the inputed connection
	 * as well as its resources.
	 * 
	 * @param conn Connection to close.
	 * @throws SQLException if an error occurs closing
	 * 		connection.
	 */
	private void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
