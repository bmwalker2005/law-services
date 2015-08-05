package walker.bryson.lawservices.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import walker.bryson.lawservices.core.Constants;
import walker.bryson.lawservices.db.tables.ClientTable;
import walker.bryson.lawservices.models.Client;


public class DBUtilities {
	
	/** Database Driver for making database changes. */
	private DBDriver driver;
	
	/**
	 * Creates a DBDriver to perform
	 * Database Actions with.
	 */
	public DBUtilities() {
		this.driver = new DBDriver(Constants.DEFAULT_DB_URL, Constants.DEFAULT_DB_USER, Constants.DEFAULT_DB_PASS);
	}
	
	/**
	 * Inserts a new Client into the client database
	 * table.
	 * 
	 * @param cFirstName First Name of Client.
	 * @param cLastName Last Name of Client.
	 * @param cPhone Phone number of Client.
	 * @param cAddress Address of Client.
	 * @param cEmail Email address of Client.
	 * @param cNote Note about Client.
	 * @throws SQLException if an error occurs inserting
	 * 		Client information into database.
	 */
	public void insertClient(String cFirstName, String cLastName, String cPhone, String cAddress, String cEmail, String cNote) throws SQLException {
		String update = "INSERT INTO " + ClientTable.TABLE_NAME + "(" + ClientTable.COLUMN_CLIENT_FIRST_NAME + ", " + ClientTable.COLUMN_CLIENT_LAST_NAME 
				+ ", " + ClientTable.COLUMN_PHONE + ", " + ClientTable.COLUMN_ADDRESS + ", " + ClientTable.COLUMN_EMAIL + ", " + ClientTable.COLUMN_NOTE + ") "
				+ "VALUES (" + "\'" + cFirstName + "\'" + ", " + "\'" + cLastName + "\'" + ", " + cPhone + ", " +  "\'" + cAddress + "\'" + ", " + "\'" + cEmail + "\'" + ", " + "\'" + cNote + "\'" + ")";
		
		driver.executeUpdate(update);
	}
	
	/**
	 * Updates Client information in the client database
	 * table.
	 * 
	 * @param cFirstName First Name of Client.
	 * @param cLastName Last Name of Client.
	 * @param cPhone Phone number of Client.
	 * @param cAddress Address of Client.
	 * @param cEmail Email address of Client.
	 * @param cNote Note about Client.
	 * @throws SQLException if an error occurs updating
	 * 		Client information into database.
	 */
	public void updateClient(int clientID, String cFirstName, String cLastName, String cPhone, String cAddress, String cEmail, String cNote) throws SQLException {
		String update = "UPDATE " + ClientTable.TABLE_NAME + " SET "
				+ ClientTable.COLUMN_CLIENT_FIRST_NAME + " = \'" + cFirstName + "\'," 
				+ ClientTable.COLUMN_CLIENT_LAST_NAME + " = \'" + cLastName + "\',"
				+ ClientTable.COLUMN_PHONE + " = \'" + cPhone + "\',"
				+ ClientTable.COLUMN_ADDRESS + " = \'"+ cAddress + "\',"
				+ ClientTable.COLUMN_EMAIL + " = \'" + cEmail + "\',"
				+ ClientTable.COLUMN_NOTE + " = \'" + cNote + "\'"
				+ " WHERE " + ClientTable.COLUMN_CLIENT_ID + " = " + clientID;
		
		driver.executeUpdate(update);
	}
	
	public Client getClient(int clientID) {
		String query = "SELECT "+ ClientTable.COLUMN_CLIENT_ID + ", " + ClientTable.COLUMN_CLIENT_FIRST_NAME + ", " + ClientTable.COLUMN_CLIENT_LAST_NAME 
				+ ", " + ClientTable.COLUMN_PHONE + ", " + ClientTable.COLUMN_ADDRESS + ", " + ClientTable.COLUMN_EMAIL 
				+ ", " + ClientTable.COLUMN_NOTE 
				+ " FROM " + ClientTable.TABLE_NAME
				+ " WHERE " + ClientTable.COLUMN_CLIENT_ID + " = " + clientID;
		
		ResultSet rs = driver.executeQuery(query);
		Client c = null;
		
		if (rs.next()) {
			c = new Client(rs.getInt(ClientTable.COLUMN_CLIENT_ID));
			
			c.setFirstName(rs.getString(ClientTable.COLUMN_CLIENT_FIRST_NAME));
			c.setLastName(rs.getString(ClientTable.COLUMN_CLIENT_LAST_NAME));
			c.setPhone(rs.getString(ClientTable.COLUMN_PHONE));
			c.setAddress(rs.getString(ClientTable.COLUMN_ADDRESS));
			c.setEmail(rs.getString(ClientTable.COLUMN_EMAIL));
			c.setNote(rs.getString(ClientTable.COLUMN_NOTE));
		}
		
		driver.close(rs);
		
		return c;
	}
	
	/**
	 * 
	 * @param clientID
	 */
	public void deleteClient(int clientID) {
		// TODO Implement delete Client code.
	}
	
	/**
	 * Retrieves all clients from the database.
	 * 
	 * @return List of Client objects.
	 * @throws SQLException if an error occurs getting
	 * 		client info from the database.
	 */
	public List<Client> getAllClients() throws SQLException {
		List<Client> clients = new ArrayList();
		
		String query = "SELECT "+ ClientTable.COLUMN_CLIENT_ID + ", " + ClientTable.COLUMN_CLIENT_FIRST_NAME + ", " + ClientTable.COLUMN_CLIENT_LAST_NAME 
				+ ", " + ClientTable.COLUMN_PHONE + ", " + ClientTable.COLUMN_ADDRESS + ", " + ClientTable.COLUMN_EMAIL 
				+ ", " + ClientTable.COLUMN_NOTE 
				+ " FROM " + ClientTable.TABLE_NAME;
		
		ResultSet rs = driver.executeQuery(query);
		Client c = null;
		
		while (rs.next()) {
			c = new Client(rs.getInt(ClientTable.COLUMN_CLIENT_ID));
			
			c.setFirstName(rs.getString(ClientTable.COLUMN_CLIENT_FIRST_NAME));
			c.setLastName(rs.getString(ClientTable.COLUMN_CLIENT_LAST_NAME));
			c.setPhone(rs.getString(ClientTable.COLUMN_PHONE));
			c.setAddress(rs.getString(ClientTable.COLUMN_ADDRESS));
			c.setEmail(rs.getString(ClientTable.COLUMN_EMAIL));
			c.setNote(rs.getString(ClientTable.COLUMN_NOTE));
			
			clients.add(c);
		}
		
		driver.close(rs);
		
		return clients;
	}
}
