package walker.bryson.lawservices.core;

public class Constants {
	
	private static final Integer DB_PORT = new Integer(3306);
	private static final String DB_HOST = "localhost";
	
	private static final String DB_NAME = "law_services";
	

	private static final String DEFAULT_DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
	private static final String DEFAULT_DB_USER = "sysuser";
	public static final String DEFAULT_DB_PASS = "pass";
	
	public static final String DB_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
}
