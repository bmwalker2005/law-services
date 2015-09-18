package com.comname.lawservices.core.access;

/**
 * Class representing an error
 * occurring while accessing the core.
 * 
 * @version 1.0
 * @since 9/18/2015
 */
public class AccessException extends Exception {

	/**
	 * Creates an access exception
	 * with the default error message.
	 */
	public AccessException() {
		this("Unkown access error");
	}
	
	/**
	 * Creates an access exception
	 * with the inputed string as its
	 * message.
	 * 
	 * @param message for exception.
	 */
	public AccessException(String message) {
		super(message);
	}

}
