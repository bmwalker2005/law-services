package com.comname.lawservices.db.tables;

/**
 * Litigation Party Table information.
 * 
 * @version 1.0
 * @since 8/20/2015
 */
public class PartyTable {

	/** Name of Party Table. */
	public final static String TABLE_NAME = "party";
	
	
	/** Name of Party ID column. */
	public final static String COLUMN_ID = "party_id";
	
	/** Name of Party First Name column. */
	public final static String COLUMN_FIRST_NAME = "party_fname";
	
	/** Name of Party Middle Name column. */
	public final static String COLUMN_MIDDLE_NAME = "party_mname";
	
	/** Name of Party Last Name column. */
	public final static String COLUMN_LAST_NAME = "party_lname";
	
	/** Name of Party Phone column. */
	public final static String COLUMN_PHONE = "phone";
	
	/** Name of Party Birth Date column. */
	public final static String COLUMN_BIRTH_DATE = "birth_date";
	
	/** Name of Party Address column. */
	public final static String COLUMN_ADDRESS = "address";
	
	/** Name of Party Email column. */
	public final static String COLUMN_EMAIL = "email";
	
	/** Name of Party Note column. */
	public final static String COLUMN_NOTE = "note";
			

	/** Array of all Party Column Names. */
	public final static String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_MIDDLE_NAME, COLUMN_LAST_NAME, COLUMN_PHONE, COLUMN_BIRTH_DATE, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_NOTE};
}
