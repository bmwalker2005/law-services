package com.comname.lawservices.models;

import java.util.Date;

/**
 * Legal Party Model for use in System.
 * 
 * @version 1.0
 * @since 8/01/2015
 */
public class Party {
	
	/** Unique ID for the party. */
	private int partyID;
	
	/** First Name for the party. */
	private String firstName;
	
	/** Middle Name for the party. */
	private String middleName;
	
	/** Last Name for the party. */
	private String lastName;
	
	/** Phone for the party. */
	private String phone;
	
	/** Birth date for the party. */
	private Date birthDate;
	
	/** Address for the party. */
	private String address;
	
	/** Email for the party. */
	private String email;
	
	/** Note for the party. */
	private String note;
	
	
	/**
	 * Creates a Party object.
	 * 
	 * @param partyID required Party ID.
	 */
	public Party(int partyID) {
		this.partyID = partyID;
	}
	
	/**
	 * @return the partyID
	 */
	public int getPartyID() {
		return partyID;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/**
	 * @param birthDate the birth date to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Creates the full name of the party
	 * by concatenating the first, middle, and last name.
	 * 
	 * @return String full name of party.
	 */
	public String getFullName() {
		
		if ((middleName == null) || (middleName.isEmpty()))
			return firstName + " " + lastName;
		
		return firstName + " " + middleName + " " + lastName;
	}
	
	/**
	 * Checks if two party objects are equivalent.
	 * 
	 * @return true if equivalent, false if not.
	 */
	@Override
	public boolean equals(Object o) {
		if ((o == null) || !(o instanceof Party))
			return false;
		
		Party other = (Party) o;
		
		if (other.getPartyID() == this.getPartyID())
			return true;
		
		return false;
	}
	
	/**
	 * Creates a String representation of this Party.
	 * 
	 * @return String representing this Party.
	 */
	@Override
	public String toString() {
		String partyStr = "Party: " + partyID + " -- " + getFullName()
				+ "\n\tPhone: " + phone + "\tBirth Date: " + birthDate + "\tAddress: " + address + "\tEmail: " + email
				+ "\n\t\tNote: " + note;
		
		return partyStr;
	}
}
