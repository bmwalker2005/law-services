package com.comname.lawservices.models;

import java.util.Date;

/**
 * Legal File Model for use in System.
 * 
 * @version 1.0
 * @since 09/09/2015
 */
public class LegalFile {
	
	/** Unique ID for the file. */
	private int fileID;
	
	/** Name for the Legal file. */
	private String name;
	
	/** Updated date of the file. */
	private Date updated;
	
	/** Raw data of the file. */
	private byte[] data;
	
	/** Unique ID for the case. */
	private int caseID;
	
	/** Unique ID for the client. */
	private int clientID;
	
	
	
	/**
	 * Creates a LegalFile object.
	 * 
	 * @param fileID required File ID.
	 */
	public LegalFile(int fileID) {
		this.fileID = fileID;
	}
	
	/**
	 * @return the fileID
	 */
	public int getFileID() {
		return fileID;
	}

	/**
	 * @param fileID the fileID to set
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	
	/**
	 * @return the caseID
	 */
	public int getCaseID() {
		return caseID;
	}

	/**
	 * @param caseID the case ID to set
	 */
	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	/**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
	}
	
	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	/**
	 * Checks if two File objects are equivalent.
	 * 
	 * @return true if equivalent, false if not.
	 */
	@Override
	public boolean equals(Object o) {
		if ((o == null) || !(o instanceof LegalFile))
			return false;
		
		LegalFile other = (LegalFile) o;
		
		if (other.getFileID() == this.getFileID())
			return true;
		
		return false;
	}
	
	/**
	 * Creates a String representation of this File.
	 * 
	 * @return String representing this File.
	 */
	@Override
	public String toString() {
		String fileStr = "File: " + fileID + " -- " + getName()
				+ "\n\tLast Updated: " + getUpdated()
				+ "\n\tFile Size: " + getData().length + " bytes"
				+ "\n\tClient: " + clientID + "\tCase: " + getCaseID();
		
		return fileStr;
	}
}
