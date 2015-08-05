package com.comname.lawservices.core;

import java.sql.SQLException;
import java.util.Scanner;

import com.comname.lawservices.db.DBUtilities;
import com.comname.lawservices.models.Client;

/**
 * Command Line Interface for the
 * Law Services System.
 *
 * @version 1.0
 * @since 8/01/2015
 */
public class CLI {
	
	/** Database Utilites Object for storage. */
	private DBUtilities dbUtil;
	
	/** Scanner for retrieving user input. */
	private Scanner in = new Scanner(System.in);
	
	/** Welcome String displayed on startup. */
	private String welcomeStr = "Welcome To Law Services"
			+ "-----------------------------------------------";
	
	/** Sentinel value for quitting program in main menu. */
	private String sentinelValue = "Q";
	
	/** Main menu with list of possible commands. */
	private String commands = "Commands:"
			+ "\n\t1) Insert Client"
			+ "\n\t2) Update Client"
			+ "\n\t3) See All Clients"
			+ "\n\t4) Delete Client"
			+ "\n\n\t" + sentinelValue + " To Exit";
	
	/** Goodbye String printed at program end. */
	private String goodbyeStr = "Goodbye!";
	
	
	/** Creates a new Command Line Interface object. */
	public CLI() {
		dbUtil = new DBUtilities();
	}
	
	/** Controls method takes main input and converts it
	 * into appropriate command procedure.
	 * 
	 * @param mainInput User input from main menu.
	 */
	private void control(String mainInput) {
		try {
			
			Integer command = Integer.parseInt(mainInput);
			
			if (command.equals(1)) {
				storeClient();
			} else if (command.equals(2)) {
				updateClient();
			} else if (command.equals(3)) {
				System.out.println("Clients will be displayed one at a time. Press enter to continue.");
				
				try {
					for (Client client : dbUtil.getAllClients()) {
						System.out.println("\n" + client);
						in.nextLine();
					}
				} catch (SQLException e) {
					System.out.println("Could not retrieve clients from database (Contact Sys Admin): " + e.getMessage());
				}
				
			} else if (command.equals(4)) {
				deleteClient();
			} else {
				System.out.println("Unsupported Command Value: " + command);
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Unexpected input: " + mainInput);
		}
	}
	
	/**
	 * Command procedure for deleting a client.
	 */
	private void deleteClient() {
		System.out.println("Enter Client ID of client to delete: ");
		int clientID = in.nextInt();
		
		// Used to eat unused token.
		in.nextLine();
		
		try {
			System.out.println("Delete Client? (y to continue) \n" + dbUtil.getClient(clientID));
			String cont = in.nextLine();
			
			if ("y".equalsIgnoreCase(cont)) {
				try {
					dbUtil.deleteClient(clientID);
					System.out.println("Sucessfully deleted client.");
				} catch (SQLException e) {
					System.out.println("Could not delete client with ID (" + clientID + ") : " + e.getMessage());
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve client with ID (" + clientID + ") : " + e.getMessage());
		}
	}
	
	/** 
	 * Command procedure for updating a client.
	 */
	private void updateClient() {
		System.out.println("Enter Client ID of client to update: ");
		int clientID = in.nextInt();
		
		// Used to eat unused token.
		in.nextLine();
		
		try {
			System.out.println("Current Client Info: \n" + dbUtil.getClient(clientID));
			
			System.out.println("Enter Client First Name: ");
			String fName = in.nextLine();
			
			System.out.println("Enter Client Last Name: ");
			String lName = in.nextLine();
			
			System.out.println("Enter Client Phone: ");
			String phone = in.nextLine();
			
			System.out.println("Enter Client Address: ");
			String address = in.nextLine();
			
			System.out.println("Enter Client Email: ");
			String email = in.nextLine();
			
			System.out.println("Enter a short note about client: ");
			String note = in.nextLine();
			
			try {
				dbUtil.updateClient(clientID, fName, lName, phone, address, email, note);
				System.out.println("Sucessfully Updated Client");
			} catch (SQLException e) {
				System.out.println("Could not update client (contact Sys Admin): " + e.getMessage());
			}			
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve client with ID (" + clientID + ") : " + e.getMessage());
		}
	}

	/**
	 * Command procedure for storing a new client.
	 */
	private void storeClient() {
		System.out.println("Enter Client First Name: ");
		String fName = in.nextLine();
		
		System.out.println("Enter Client Last Name: ");
		String lName = in.nextLine();
		
		System.out.println("Enter Client Phone: ");
		String phone = in.nextLine();
		
		System.out.println("Enter Client Address: ");
		String address = in.nextLine();
		
		System.out.println("Enter Client Email: ");
		String email = in.nextLine();
		
		System.out.println("Enter a short note about client: ");
		String note = in.nextLine();
		
		try {
			dbUtil.insertClient(fName, lName, phone, address, email, note);
			System.out.println("Sucessfully Added Client");
		} catch (SQLException e) {
			System.out.println("Could not add new client (contact Sys Admin): " + e.getMessage());
		}
	}
	
	
	
	

	/**
	 * Main method that initializes and runs the interface.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		CLI c = new CLI();
		
		System.out.println(c.welcomeStr);
		
		String mainInput = null;
		
		while (!c.sentinelValue.equalsIgnoreCase(mainInput)) {
			if (mainInput != null) {
				c.control(mainInput);
			}
			
			System.out.println("\n" + c.commands);
			mainInput = c.in.nextLine();
		}
		
		System.out.println("\n" + c.goodbyeStr);
	}
}
