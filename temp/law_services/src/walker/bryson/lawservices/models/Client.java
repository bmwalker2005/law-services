package walker.bryson.lawservices.models;

public class Client {
	
	private int clientID;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String address;
	
	private String email;
	
	private String note;
	
	public Client(int clientID) {
		this.clientID = clientID;
	}
	
	/**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
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
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	@Override
	public boolean equals(Object o) {
		if ((o == null) || !(o instanceof Client))
			return false;
		
		Client other = (Client) o;
		
		if (other.getClientID() == this.getClientID())
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		String clientStr = "Client: " + clientID + " -- " + getFullName()
				+ "\n\tPhone: " + phone + "\tAddress: " + address + "\tEmail" + email
				+ "\n\t\tNote: " + note;
		
		return clientStr;
	}
}
