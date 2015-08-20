package com.comname.lawservices.db.tables;

/**
 * Litigation Legal Case Table information.
 * 
 * @version 1.0
 * @since 8/20/2015
 */
public class LegalCaseTable {

	/** Name of Case Table. */
	public final static String TABLE_NAME = "legal_case";
	
	
	/** Name of Case ID column. */
	public final static String COLUMN_ID = "case_id";
	
	/** Name of Client ID column. */
	public final static String COLUMN_CLIENT_ID = "client_id";
	
	/** Name of Opposition ID column. */
	public final static String COLUMN_OPPOSITION_ID = "opposition_id";
	
	/** Name of Case Start Date column. */
	public final static String COLUMN_START_DATE = "start";
	
	/** Name of Case End Date column. */
	public final static String COLUMN_END_DATE = "end";
	
	/** Name of Case Description column. */
	public final static String COLUMN_DESCRIPTION = "description";

	/** Name of Case Note column. */
	public final static String COLUMN_NOTE = "note";
			

	/** Array of all Case Column Names. */
	public final static String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_CLIENT_ID, COLUMN_OPPOSITION_ID, COLUMN_START_DATE, COLUMN_END_DATE, COLUMN_DESCRIPTION, COLUMN_NOTE};
}
