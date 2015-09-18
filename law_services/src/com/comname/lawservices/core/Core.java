package com.comname.lawservices.core;

import com.comname.lawservices.core.access.AccessException;
import com.comname.lawservices.core.access.Accessor;
import com.comname.lawservices.core.access.ExternalAccessManager;
import com.comname.lawservices.db.DBUtilities;

/**
 * Core class of the system.
 */
public class Core {
	
	/** Database Utilites Object for storage. */
	private DBUtilities dbUtil;
	
	/** Manages all accessors of the core. */
	private static ExternalAccessManager accessManager = new ExternalAccessManager();
	
	/** Single instance of this core class. */
	private static Core systemInstance;
	

	/**
	 * Private constructor for Core to limit
	 * access to the Core.
	 */
	private Core() {
		dbUtil = new DBUtilities();
	}
	
	
	/**
	 * Gets the single instance of
	 * the Core object.
	 * 
	 * @return Core object.
	 */
	static Core getCore() {
		if (systemInstance == null) {
			systemInstance = new Core();
		}
		
		return systemInstance;
	}
	
	/**
	 * Gets the instance of this core,
	 * if the requester is a valid accessor.
	 * 
	 * @param accessor object requesting access.
	 * @return reference to this core.
	 * @throws AccessException
	 */
	public static Core getCore(Object accessor) throws AccessException {
		// Implement Factory to return specific core subclass based
		// upon accessor type in order to limit core functionalities
		// each accessor has access to.
		if (accessor instanceof Accessor) {
			accessManager.addExternalAccessor((Accessor) accessor);
			
			return getCore();
		}
		
		throw new AccessException("Not a valid external accessor.");
	}

	/**
	 * Give reference to the database
	 * to the caller.
	 * 
	 * @return reference to dbUtil.
	 * @throws AccessException 
	 */
	public DBUtilities accessDatabase(String accessKey) throws AccessException {
		if (accessManager.isValidKey(accessKey))
			return dbUtil;
		else
			throw new AccessException("Invalid access key.");
	}
}
