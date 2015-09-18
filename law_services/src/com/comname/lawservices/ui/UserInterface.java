package com.comname.lawservices.ui;

import com.comname.lawservices.core.Core;
import com.comname.lawservices.core.access.AccessException;
import com.comname.lawservices.core.access.Accessor;
import com.comname.lawservices.db.DBUtilities;

/**
 * Class with user interface standard
 * set up procedures.
 * 
 * @version 1.0
 * @since 9/18/2015
 */
public abstract class UserInterface implements Accessor {

	/** Private access key that allows access to the core. */
	private String accessKey;
	
	/** Core object to retrieve utilities from. */
	private Core core;
	
	/** Database utilites for information storage. */
	private DBUtilities dB;
	
	
	/**
	 * Initiates the user interface by
	 * establishing core access.
	 */
	public UserInterface() {
		try {
			this.core = Core.getCore(this);
		} catch (AccessException e) {
			this.displayMessage("Could not access Core on startup: " + e.getMessage());
			this.kill();
		}
	}
	
	/**
	 * Sets the key for the object to access
	 * the core.
	 * 
	 * @param key unique key of this accessor.
	 */
	@Override
	public void setAccessKey(String key) {
		this.accessKey = key;
	}

	/**
	 * Kill method to allow core to relinquish
	 * access to itself.
	 * 
	 * @param key unique key of this accessor.
	 */
	@Override
	public void kill(String key) {
		if ((key != null) && (key.equals(accessKey))) {
			// Nullify all utilities
			core = null;
			accessKey = null;
			dB = null;
		}
	}
	
	/**
	 * Allows the specific user interface
	 * to access the database.
	 * 
	 * @return dB to make database changes.
	 */
	protected DBUtilities getDB() {
		if (dB == null) {
			try {
				dB = core.accessDatabase(accessKey);
			} catch (AccessException e) {
				this.displayMessage("Could not access Core: " + e.getMessage());
				this.kill();
			}
		}
		
		return dB;
	}
	
	/**
	 * Kill method for specific user
	 * interface type.
	 */
	protected abstract void kill();
	
	/**
	 * Display message method for specific
	 * user interface type.
	 * 
	 * @param message String to be displayed to user.
	 */
	protected abstract void displayMessage(String message);
}
