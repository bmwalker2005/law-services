package com.comname.lawservices.core.access;

/**
 * Interface with method requirements
 * of all classes accessing the core.
 * 
 * @version 1.0
 * @since 9/18/2015
 *
 */
public interface Accessor {
	/**
	 * Sets the key for the object to access
	 * the core.
	 * 
	 * @param key unique key of this accessor.
	 */
	public void setAccessKey(String key);
	
	/**
	 * Kill method to allow core to relinquish
	 * access to itself.
	 * 
	 * @param key unique key of this accessor.
	 */
	public void kill(String key);
}
