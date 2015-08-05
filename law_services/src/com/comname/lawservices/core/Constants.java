package com.comname.lawservices.core;

/**
 * System Constants.
 * 
 * @version 1.0
 * @since 8/01/2015
 */
public class Constants {
	
	/** Port to access the database on. */
	private static final Integer DB_PORT = new Integer(3306);
	
	/** Host to access the database on. */
	private static final String DB_HOST = "localhost";
	
	/** Name of database to access. */
	private static final String DB_NAME = "law_services";
	
	/** Database URL for accessing database. */
	public static final String DEFAULT_DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
	
	/** Database username used for accessing. */
	public static final String DEFAULT_DB_USER = "sysuser";
	
	/** Database password used for accessing. */
	public static final String DEFAULT_DB_PASS = "pass";
	
	/** Database Driver Class Name. */
	public static final String DB_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
}
