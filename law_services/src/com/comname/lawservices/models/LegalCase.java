package com.comname.lawservices.models;

import java.util.Date;

/**
 * Legal Case Model for use in System.
 * 
 * @version 1.0
 * @since 8/25/2015
 */
public class LegalCase {
	
	/** Unique ID for the case. */
	private int caseID;
	
	/** Name for the Legal Case. */
	private String name;
	
	/** Unique ID for the client. */
	private int clientID;
	
	/** Unique ID for the opposition. */
	private int oppositionID;
	
	/** Start date of the Case. */
	private Date start;
	
	/** end date of the Case. */
	private Date end;
	
	/** Description of the Case. */
	private String description;
	
	/** Note for the Case. */
	private String note;
	
	
	/**
	 * Creates a LegalCase object.
	 * 
	 * @param caseID required Case ID.
	 */
	public LegalCase(int caseID) {
		this.caseID = caseID;
	}
	
	/**
	 * @return the caseID
	 */
	public int getCaseID() {
		return caseID;
	}

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
	 * @return the oppositionID
	 */
	public int getOppositionID() {
		return oppositionID;
	}

	/**
	 * @param oppositionID the oppositionID to set
	 */
	public void setOppositionID(int oppositionID) {
		this.oppositionID = oppositionID;
	}
	
	/**
	 * @return the start date
	 */
	public Date getStart() {
		return start;
	}
	
	/**
	 * @param start the start date to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	
	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * Checks if two Case objects are equivalent.
	 * 
	 * @return true if equivalent, false if not.
	 */
	@Override
	public boolean equals(Object o) {
		if ((o == null) || !(o instanceof LegalCase))
			return false;
		
		LegalCase other = (LegalCase) o;
		
		if (other.getCaseID() == this.getCaseID())
			return true;
		
		return false;
	}
	
	/**
	 * Creates a String representation of this Case.
	 * 
	 * @return String representing this Case.
	 */
	@Override
	public String toString() {
		String caseStr = "Case: " + caseID + " -- " + getName()
				+ "\n\tClient: " + clientID + "\tOpposistion: " + oppositionID 
				+ "\n\tStart: " + start + "\tEnd: " + end
				+ "\n\tDescription: " + description
				+ "\n\t\tNote: " + note;
		
		return caseStr;
	}
}
