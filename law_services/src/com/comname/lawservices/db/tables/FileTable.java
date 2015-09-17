package com.comname.lawservices.db.tables;

/**
 * File Table information.
 * 
 * @version 1.0
 * @since 9/08/2015
 */
public class FileTable {

	/** Name of File Table. */
	public final static String TABLE_NAME = "file";
	
	
	/** Name of File ID column. */
	public final static String COLUMN_ID = "file_id";
	
	/** Name of the File Name column. */
	public final static String COLUMN_NAME = "name";
	
	/** Name of the File Updated Date column. */
	public final static String COLUMN_UPDATED = "updated";
	
	/** Name of the File Data column. */
	public final static String COLUMN_DATA = "data";
	
	/** Name of Case ID column. */
	public final static String COLUMN_CASE_ID = "case_id";
	
	/** Name of Client ID column. */
	public final static String COLUMN_CLIENT_ID = "client_id";
	

	/** Array of all File Column Names. */
	public final static String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_ID, COLUMN_NAME, COLUMN_UPDATED, COLUMN_DATA, COLUMN_CASE_ID, COLUMN_CLIENT_ID};
}
