package com.comname.lawservices.db.tables;

/**
 * Client Table information.
 * 
 * @version 1.0
 * @since 8/01/2015
 */
public class ClientTable {

	/** Name of Client Table. */
	public final static String TABLE_NAME = "client";
	
	
	/** Name of Client ID column. */
	public final static String COLUMN_CLIENT_ID = "client_id";
	
	/** Name of Client First Name column. */
	public final static String COLUMN_CLIENT_FIRST_NAME = "client_fname";
	
	/** Name of Client Last Name column. */
	public final static String COLUMN_CLIENT_LAST_NAME = "client_lname";
	
	/** Name of Client Phone column. */
	public final static String COLUMN_PHONE = "phone";
	
	/** Name of Client Address column. */
	public final static String COLUMN_ADDRESS = "address";
	
	/** Name of Client Email column. */
	public final static String COLUMN_EMAIL = "email";
	
	/** Name of Client Note column. */
	public final static String COLUMN_NOTE = "note";
			

	/** Array of all Client Column Names. */
	public final static String[] ALL_COLUMNS = {COLUMN_CLIENT_ID, COLUMN_CLIENT_FIRST_NAME, COLUMN_CLIENT_LAST_NAME, COLUMN_PHONE, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_NOTE};
}
