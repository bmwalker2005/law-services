package walker.bryson.lawservices.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import walker.bryson.lawservices.core.Constants;
import walker.bryson.lawservices.db.tables.ClientTable;
import walker.bryson.lawservices.models.Client;


public class DBUtil {
	
	private DBDriver driver;
	
	public DBUtil() {
		this.driver = new DBDriver(Constants.DEFAULT_DB_URL, Constants.DEFAULT_DB_USER, Constants.DEFAULT_DB_PASS);
	}
	
	public void insertClient(String cFirstName, String cLastName, String cPhone, String cAddress, String cEmail, String cNote) throws SQLException {
		String query = "INSERT INTO " + ClientTable.TABLE_NAME + "(" + ClientTable.COLUMN_CLIENT_FIRST_NAME + ", " + ClientTable.COLUMN_CLIENT_LAST_NAME 
				+ ", " + ClientTable.COLUMN_PHONE + ", " + ClientTable.COLUMN_ADDRESS + ", " + ClientTable.COLUMN_EMAIL + ", " + ClientTable.COLUMN_NOTE + ") "
				+ "VALUES (" + cFirstName + ", " + cLastName + ", " + cPhone + ", " + cAddress + ", " + cEmail + ", " + cNote + ")";
		
		driver.close(driver.executeQuery(query));
	}
	
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
		
		return clients;
	}
}
