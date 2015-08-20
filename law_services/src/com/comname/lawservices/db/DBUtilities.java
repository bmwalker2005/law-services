package com.comname.lawservices.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comname.lawservices.core.Constants;
import com.comname.lawservices.db.tables.ClientTable;
import com.comname.lawservices.db.tables.PartyTable;
import com.comname.lawservices.models.Client;
import com.comname.lawservices.models.Party;

/**
 * Database Utilities Object for managing proper database
 * storage/retrieval of Law Services System information.
 * 
 * @version 1.0
 * @since 8/01/2015
 */
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
	 * Inserts a new Party into the party database
	 * table.
	 * 
	 * @param firstName First Name of Party.
	 * @param middleName Last Name of Party.
	 * @param lastName Last Name of Party.
	 * @param phone Phone number of Party.
	 * @param birthDate birth date of Party.
	 * @param address Address of Party.
	 * @param email Email address of Party.
	 * @param note Note about Party.
	 * @throws SQLException if an error occurs inserting
	 * 		Party information into database.
	 */
	public void insertParty(String firstName, String middleName, String lastName, String phone, java.util.Date birthDate, String address, String email, String note) throws SQLException {
		String update = "INSERT INTO " + PartyTable.TABLE_NAME + "(" + PartyTable.COLUMN_FIRST_NAME + ", " + PartyTable.COLUMN_MIDDLE_NAME + " , " + PartyTable.COLUMN_LAST_NAME 
				+ ", " + PartyTable.COLUMN_PHONE + ", " + PartyTable.COLUMN_BIRTH_DATE + ", " + PartyTable.COLUMN_ADDRESS + ", " + PartyTable.COLUMN_EMAIL + ", " + PartyTable.COLUMN_NOTE + ") "
				+ "VALUES (" + "\'" + firstName + "\'" + ", "  + "\'" + middleName + "\'" + ", " + "\'" + lastName + "\'" + ", " + phone + ", \'" + (new java.sql.Date(birthDate.getTime())) + "\', " +  "\'" + address + "\'" + ", " + "\'" + email + "\'" + ", " + "\'" + note + "\'" + ")";
		
		System.out.println(update);
		driver.executeUpdate(update);
	}
	
	/**
	 * Updates Party information in the party database
	 * table.
	 * 
	 * @param firstName First Name of Party.
	 * @param middleName Last Name of Party.
	 * @param lastName Last Name of Party.
	 * @param phone Phone number of Party.
	 * @param birthDate birth date of Party.
	 * @param address Address of Party.
	 * @param email Email address of Party.
	 * @param note Note about Party.
	 * @throws SQLException if an error occurs updating
	 * 		Party information into database.
	 */
	public void updateParty(int partyID, String firstName, String middleName, String lastName, String phone, java.util.Date birthDate, String address, String email, String note) throws SQLException {
		String update = "UPDATE " + PartyTable.TABLE_NAME + " SET "
				+ PartyTable.COLUMN_FIRST_NAME + " = \'" + firstName + "\'," 
				+ PartyTable.COLUMN_MIDDLE_NAME + " = \'" + middleName + "\'," 
				+ PartyTable.COLUMN_LAST_NAME + " = \'" + lastName + "\',"
				+ PartyTable.COLUMN_PHONE + " = \'" + phone + "\',"
				+ PartyTable.COLUMN_BIRTH_DATE + " = \'" + new java.sql.Date(birthDate.getTime()) + "\',"
				+ PartyTable.COLUMN_ADDRESS + " = \'"+ address + "\',"
				+ PartyTable.COLUMN_EMAIL + " = \'" + email + "\',"
				+ PartyTable.COLUMN_NOTE + " = \'" + note + "\'"
				+ " WHERE " + PartyTable.COLUMN_ID + " = " + partyID;
		
		driver.executeUpdate(update);
	}
	
	/**
	 * Gets a specific Party object from the party
	 * database table.
	 * 
	 * @param partyID ID of party to get info for.
	 * @return Party object with information about party.
	 * @throws SQLException if an error occurs getting party info.
	 */
	public Party getParty(int partyID) throws SQLException {
		String query = "SELECT "+ PartyTable.COLUMN_ID + ", " + PartyTable.COLUMN_FIRST_NAME + ", " + PartyTable.COLUMN_MIDDLE_NAME + " , " + PartyTable.COLUMN_LAST_NAME 
				+ ", " + PartyTable.COLUMN_PHONE + ", " + PartyTable.COLUMN_BIRTH_DATE + " , " + PartyTable.COLUMN_ADDRESS + ", " + PartyTable.COLUMN_EMAIL 
				+ ", " + PartyTable.COLUMN_NOTE 
				+ " FROM " + PartyTable.TABLE_NAME
				+ " WHERE " + PartyTable.COLUMN_ID + " = " + partyID;
		
		ResultSet rs = driver.executeQuery(query);
		Party p = null;
		
		if (rs.next()) {
			p = new Party(rs.getInt(PartyTable.COLUMN_ID));
			
			p.setFirstName(rs.getString(PartyTable.COLUMN_FIRST_NAME));
			p.setMiddleName(rs.getString(PartyTable.COLUMN_MIDDLE_NAME));
			p.setLastName(rs.getString(PartyTable.COLUMN_LAST_NAME));
			p.setPhone(rs.getString(PartyTable.COLUMN_PHONE));
			p.setBirthDate(new java.util.Date(rs.getDate(PartyTable.COLUMN_BIRTH_DATE).getTime()));
			p.setAddress(rs.getString(PartyTable.COLUMN_ADDRESS));
			p.setEmail(rs.getString(PartyTable.COLUMN_EMAIL));
			p.setNote(rs.getString(PartyTable.COLUMN_NOTE));
			
		} else {
			throw new SQLException("No Party ID (" + partyID + ") was found.");
		}
		
		driver.close(rs);
		
		return p;
	}
	
	/**
	 * Deletes a specific party from the party database.
	 * 
	 * @param partyID specific ID of party to delete.
	 * @throws SQLException if an error occurs deleting party.
	 */
	public void deleteParty(int partyID) throws SQLException {
		String update = "DELETE FROM " + PartyTable.TABLE_NAME
				+ " WHERE " + PartyTable.COLUMN_ID + " = " + partyID;
		
		driver.executeUpdate(update);
	}
	
	/**
	 * Retrieves all parties from the database.
	 * 
	 * @return List of Party objects.
	 * @throws SQLException if an error occurs getting
	 * 		party info from the database.
	 */
	public List<Party> getAllParties() throws SQLException {
		List<Party> parties = new ArrayList<Party>();
		
		String query = "SELECT "+ PartyTable.COLUMN_ID + ", " + PartyTable.COLUMN_FIRST_NAME + ", " + PartyTable.COLUMN_MIDDLE_NAME + " , " + PartyTable.COLUMN_LAST_NAME 
				+ ", " + PartyTable.COLUMN_PHONE + ", " + PartyTable.COLUMN_BIRTH_DATE + " , " + PartyTable.COLUMN_ADDRESS + ", " + PartyTable.COLUMN_EMAIL 
				+ ", " + PartyTable.COLUMN_NOTE 
				+ " FROM " + PartyTable.TABLE_NAME;
		
		ResultSet rs = driver.executeQuery(query);
		Party p = null;
		
		while (rs.next()) {
			p = new Party(rs.getInt(PartyTable.COLUMN_ID));
			
			p.setFirstName(rs.getString(PartyTable.COLUMN_FIRST_NAME));
			p.setMiddleName(rs.getString(PartyTable.COLUMN_MIDDLE_NAME));
			p.setLastName(rs.getString(PartyTable.COLUMN_LAST_NAME));
			p.setPhone(rs.getString(PartyTable.COLUMN_PHONE));
			p.setBirthDate(new java.util.Date(rs.getDate(PartyTable.COLUMN_BIRTH_DATE).getTime()));
			p.setAddress(rs.getString(PartyTable.COLUMN_ADDRESS));
			p.setEmail(rs.getString(PartyTable.COLUMN_EMAIL));
			p.setNote(rs.getString(PartyTable.COLUMN_NOTE));
			
			parties.add(p);
		}
		
		driver.close(rs);
		
		return parties;
	}
	
	
	
	// TO BE PHASED OUT. USING PARTY METHODOLOGY OVER CASE METHODOLOGY
	
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
	@Deprecated
	public void insertClient(String cFirstName, String cLastName, String cPhone, String cAddress, String cEmail, String cNote) throws SQLException {
		String update = "INSERT INTO " + ClientTable.TABLE_NAME + "(" + ClientTable.COLUMN_CLIENT_FIRST_NAME + ", " + ClientTable.COLUMN_CLIENT_LAST_NAME 
				+ ", " + ClientTable.COLUMN_PHONE + ", " + ClientTable.COLUMN_ADDRESS + ", " + ClientTable.COLUMN_EMAIL + ", " + ClientTable.COLUMN_NOTE + ") "
				+ "VALUES (" + "\'" + cFirstName + "\'" + ", " + "\'" + cLastName + "\'" + ", " + cPhone + ", " +  "\'" + cAddress + "\'" + ", " + "\'" + cEmail + "\'" + ", " + "\'" + cNote + "\'" + ")";
		
		driver.executeUpdate(update);
		
		insertParty(cFirstName, null, cLastName, cPhone, new java.util.Date(0), cAddress, cEmail, cNote);
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
	@Deprecated
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
	
	/**
	 * Gets a specific Client object from the client
	 * database table.
	 * 
	 * @param clientID ID of client to get info for.
	 * @return Client object with information about client.
	 * @throws SQLException if an error occurs getting client info.
	 */
	@Deprecated
	public Client getClient(int clientID) throws SQLException {
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
		} else {
			throw new SQLException("No Client ID (" + clientID + ") was found.");
		}
		
		driver.close(rs);
		
		return c;
	}
	
	/**
	 * Deletes a specific client from the client database.
	 * 
	 * @param clientID specific ID of client to delete.
	 * @throws SQLException if an error occurs deleting client.
	 */
	@Deprecated
	public void deleteClient(int clientID) throws SQLException {
		String update = "DELETE FROM " + ClientTable.TABLE_NAME
				+ " WHERE " + ClientTable.COLUMN_CLIENT_ID + " = " + clientID;
		
		driver.executeUpdate(update);
	}
	
	/**
	 * Retrieves all clients from the database.
	 * 
	 * @return List of Client objects.
	 * @throws SQLException if an error occurs getting
	 * 		client info from the database.
	 */
	@Deprecated
	public List<Client> getAllClients() throws SQLException {
		List<Client> clients = new ArrayList<Client>();
		
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
