package com.comname.lawservices.core;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.comname.lawservices.db.DBUtilities;
import com.comname.lawservices.models.Client;
import com.comname.lawservices.models.LegalCase;
import com.comname.lawservices.models.Party;

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
	@Deprecated
	private String commands = "Commands:"
			+ "\n\t1) Insert Client"
			+ "\n\t2) Update Client"
			+ "\n\t3) See All Clients"
			+ "\n\t4) Delete Client"
			+ "\n\n\t" + sentinelValue + " To Exit";
	
	/** Main menu with list of main commands. */
	private String mainCommands = "Commands:"
			+ "\n\t1) Insert Party"
			+ "\n\t2) Update Party"
			+ "\n\t3) See All Parties"
			+ "\n\t5) Insert Legal Case"
			+ "\n\t6) Update Legal Case"
			+ "\n\t7) See All Legal Cases"
			+ "\n\t8) Delete Legal Case"
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
	private void mainControl(String mainInput) {
		try {
			
			Integer command = Integer.parseInt(mainInput);
			
			if (command.equals(1)) {
				storeParty();
			} else if (command.equals(2)) {
				updateParty();
			} else if (command.equals(3)) {
				displayParties();				
			} else if (command.equals(4)) {
				deleteParty();
			} else if (command.equals(5)) {
				storeLegalCase();
			} else if (command.equals(6)) {
				updateLegalCase();
			} else if (command.equals(7)) {
				displayLegalCases();
			} else if (command.equals(8)) {
				deleteLegalCase();
			} else {
				System.out.println("Unsupported Command Value: " + command);
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Unexpected input: " + mainInput);
		}
	}

	/** Controls method takes main input and converts it
	 * into appropriate command procedure.
	 * 
	 * @param mainInput User input from main menu.
	 */
	@Deprecated
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
					System.out.println("Could not retrieve clients from system (Contact Sys Admin): " + e.getMessage());
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
	 * Command procedure for storing a new Legal Case.
	 */
	private void storeLegalCase() {
		System.out.println("Enter Legal Case Name: ");
		String caseName = in.nextLine();
		
		int clientID = Constants.NULL_ID;
		
		while (clientID == Constants.NULL_ID) {
			try {
				System.out.println("Enter Client ID: ");
				clientID = in.nextInt();
				
				in.nextLine();// Used to eat unused token.
			} catch (InputMismatchException e) {
				System.out.println("\n Improper Input While Entering Client ID, enter numerals [0-9] only.\n");
				in.nextLine();
			}
			
			try {
				System.out.println("Client: \n" + dbUtil.getParty(clientID) + "\n\n(y to continue)");
				String cont = in.nextLine();
				
				if (!"y".equalsIgnoreCase(cont)) {
					clientID = Constants.NULL_ID;
				}
				
			} catch (SQLException e) {
				System.out.println("Could not retrieve Party with ID (" + clientID + ") : " + e.getMessage());
				clientID = Constants.NULL_ID;
			}
		}
		
		int oppID = Constants.NULL_ID;
		
		while (oppID == Constants.NULL_ID) {
			try {
				System.out.println("Enter Opposition ID: ");
				oppID = in.nextInt();
				
				in.nextLine();// Used to eat unused token.
			} catch (InputMismatchException e) {
				System.out.println("\n Improper Input While Entering Opposistion ID, enter numerals [0-9] only.\n");
				in.nextLine();
			}
			
			try {
				System.out.println("Opposistion: \n" + dbUtil.getParty(oppID) + "\n\n(y to continue)");
				String cont = in.nextLine();
				
				if (!"y".equalsIgnoreCase(cont)) {
					oppID = Constants.NULL_ID;
				}
				
			} catch (SQLException e) {
				System.out.println("Could not retrieve Party with ID (" + oppID + ") : " + e.getMessage());
				oppID = Constants.NULL_ID;
			}
		}
		

		Date start = new Date(0); // Defaults to "the epoch" (standard Java base time).
		
		try {
			System.out.println("Enter Legal Case Start Year: ");
			int startYear = in.nextInt();
			
			in.nextLine(); // Used to eat unused token.
			
			System.out.println("Enter Legal Case Start Month: ");
			int startMonth = in.nextInt();
			startMonth -= 1; // Convert from user's (1-12) format to Java's (0-11) format.
			
			in.nextLine(); // Used to eat unused token.
			
			System.out.println("Enter Legal Case Start Day: ");
			int startDay = in.nextInt();
			
			in.nextLine(); // Used to eat unused token.
			
			Calendar c = Calendar.getInstance();
			c.set(startYear, startMonth, startDay);
			start.setTime(c.getTimeInMillis());
			
		} catch (InputMismatchException e) {
			System.out.println("\n Improper Input While Entering Start Date Information, using default. Please update later.\n");
			in.nextLine();
		}
		
		Date end = new Date(0); // Defaults to "the epoch" (standard Java base time).
		
		try {
			System.out.println("Enter Legal Case End Year: ");
			int endYear = in.nextInt();
			
			in.nextLine(); // Used to eat unused token.
			
			System.out.println("Enter Legal Case End Month: ");
			int endMonth = in.nextInt();
			endMonth -= 1; // Convert from user's (1-12) format to Java's (0-11) format.
			
			in.nextLine(); // Used to eat unused token.
			
			System.out.println("Enter Legal Case End Day: ");
			int endDay = in.nextInt();
			
			in.nextLine(); // Used to eat unused token.
			
			Calendar c = Calendar.getInstance();
			c.set(endYear, endMonth, endDay);
			end.setTime(c.getTimeInMillis());
			
		} catch (InputMismatchException e) {
			System.out.println("\n Improper Input While Entering End Date Information, using default. Please update later.\n");
			in.nextLine();
		}
		
		System.out.println("Enter Legal Case Description: ");
		String description = in.nextLine();
		
		System.out.println("Enter a short note about the Legal Case: ");
		String note = in.nextLine();
		
		try {
			dbUtil.insertLegalCase(caseName, clientID, oppID, start, end, description, note);
			System.out.println("Sucessfully Added Legal Case");
		} catch (SQLException e) {
			System.out.println("Could not add new Legal Case (contact Sys Admin): " + e.getMessage());
		}
	}
	
	/**
	 * Command procedure for updating a Legal Case.
	 */
	private void updateLegalCase() {
		
		System.out.println("Enter Legal Case ID of Legal Case to update:");
		int legalCaseID = in.nextInt();
		
		// Used to eat unused token.
		in.nextLine();
		
		try {
			System.out.println("Current Legal Case Info: \n" + dbUtil.getLegalCase(legalCaseID));
		
			
			System.out.println("Enter Legal Case Name: ");
			String caseName = in.nextLine();
			
			int clientID = Constants.NULL_ID;
			
			while (clientID == Constants.NULL_ID) {
				try {
					System.out.println("Enter Client ID: ");
					clientID = in.nextInt();
					
					in.nextLine();// Used to eat unused token.
				} catch (InputMismatchException e) {
					System.out.println("\n Improper Input While Entering Client ID, enter numerals [0-9] only.\n");
					in.nextLine();
				}
				
				try {
					System.out.println("Client: \n" + dbUtil.getParty(clientID) + "\n\n(y to continue)");
					String cont = in.nextLine();
					
					if (!"y".equalsIgnoreCase(cont)) {
						clientID = Constants.NULL_ID;
					}
					
				} catch (SQLException e) {
					System.out.println("Could not retrieve Party with ID (" + clientID + ") : " + e.getMessage());
					clientID = Constants.NULL_ID;
				}
			}
			
			int oppID = Constants.NULL_ID;
			
			while (oppID == Constants.NULL_ID) {
				try {
					System.out.println("Enter Opposition ID: ");
					oppID = in.nextInt();
					
					in.nextLine();// Used to eat unused token.
				} catch (InputMismatchException e) {
					System.out.println("\n Improper Input While Entering Opposistion ID, enter numerals [0-9] only.\n");
					in.nextLine();
				}
				
				try {
					System.out.println("Opposistion: \n" + dbUtil.getParty(oppID) + "\n\n(y to continue)");
					String cont = in.nextLine();
					
					if (!"y".equalsIgnoreCase(cont)) {
						oppID = Constants.NULL_ID;
					}
					
				} catch (SQLException e) {
					System.out.println("Could not retrieve Party with ID (" + oppID + ") : " + e.getMessage());
					oppID = Constants.NULL_ID;
				}
			}
			
	
			Date start = new Date(0); // Defaults to "the epoch" (standard Java base time).
			
			try {
				System.out.println("Enter Legal Case Start Year: ");
				int startYear = in.nextInt();
				
				in.nextLine(); // Used to eat unused token.
				
				System.out.println("Enter Legal Case Start Month: ");
				int startMonth = in.nextInt();
				startMonth -= 1; // Convert from user's (1-12) format to Java's (0-11) format.
				
				in.nextLine(); // Used to eat unused token.
				
				System.out.println("Enter Legal Case Start Day: ");
				int startDay = in.nextInt();
				
				in.nextLine(); // Used to eat unused token.
				
				Calendar c = Calendar.getInstance();
				c.set(startYear, startMonth, startDay);
				start.setTime(c.getTimeInMillis());
				
			} catch (InputMismatchException e) {
				System.out.println("\n Improper Input While Entering Start Date Information, using default. Please update later.\n");
				in.nextLine();
			}
			
			Date end = new Date(0); // Defaults to "the epoch" (standard Java base time).
			
			try {
				System.out.println("Enter Legal Case End Year: ");
				int endYear = in.nextInt();
				
				in.nextLine(); // Used to eat unused token.
				
				System.out.println("Enter Legal Case End Month: ");
				int endMonth = in.nextInt();
				endMonth -= 1; // Convert from user's (1-12) format to Java's (0-11) format.
				
				in.nextLine(); // Used to eat unused token.
				
				System.out.println("Enter Legal Case End Day: ");
				int endDay = in.nextInt();
				
				in.nextLine(); // Used to eat unused token.
				
				Calendar c = Calendar.getInstance();
				c.set(endYear, endMonth, endDay);
				end.setTime(c.getTimeInMillis());
				
			} catch (InputMismatchException e) {
				System.out.println("\n Improper Input While Entering End Date Information, using default. Please update later.\n");
				in.nextLine();
			}
			
			System.out.println("Enter Legal Case Description: ");
			String description = in.nextLine();
			
			System.out.println("Enter a short note about the Legal Case: ");
			String note = in.nextLine();
			
			try {
				dbUtil.updateLegalCase(legalCaseID, caseName, clientID, oppID, start, end, description, note);
				System.out.println("Sucessfully Updated Legal Case");
			} catch (SQLException e) {
				System.out.println("Could not update Legal Case with ID (" + legalCaseID + ") (contact Sys Admin): " + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("Could not retrieve Legal Case with ID (" + legalCaseID + ") : " + e.getMessage());
		}
	}
	
	/**
	 * Displays all Legal cases recorded 
	 * in the system, one at a time.
	 */
	private void displayLegalCases() {
		System.out.println("Legal Cases will be displayed one at a time. Press enter to continue.");
		
		try {
			for (LegalCase legalCase : dbUtil.getAllLegalCases()) {
				System.out.println("\n" + legalCase);
				in.nextLine();
			}
		} catch (SQLException e) {
			System.out.println("Could not retrieve legal cases from system (Contact Sys Admin): " + e.getMessage());
		}
	}
	
	/**
	 * Command procedure for deleting a legal case.
	 */
	private void deleteLegalCase() {
		System.out.println("Enter ID of Legal Case to delete: ");
		int legalCaseID = in.nextInt();
		
		// Used to eat unused token.
		in.nextLine();
		
		try {
			System.out.println("Delete Legal Case? (y to continue) \n" + dbUtil.getLegalCase(legalCaseID));
			String cont = in.nextLine();
			
			if ("y".equalsIgnoreCase(cont)) {
				try {
					dbUtil.deleteParty(legalCaseID);
					System.out.println("Sucessfully deleted Legal Case.");
				} catch (SQLException e) {
					System.out.println("Could not delete Legal Case with ID (" + legalCaseID + ") : " + e.getMessage());
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve Legal Case with ID (" + legalCaseID + ") : " + e.getMessage());
		}
	}
	
	/**
	 * Displays all parties recorded 
	 * in the system, one at a time.
	 */
	private void displayParties() {
		System.out.println("Parties will be displayed one at a time. Press enter to continue.");
		
		try {
			for (Party party : dbUtil.getAllParties()) {
				System.out.println("\n" + party);
				in.nextLine();
			}
		} catch (SQLException e) {
			System.out.println("Could not retrieve parties from system (Contact Sys Admin): " + e.getMessage());
		}
	}
	
	/**
	 * Command procedure for deleting a party.
	 */
	private void deleteParty() {
		System.out.println("Enter ID of Party to delete: ");
		int partyID = in.nextInt();
		
		// Used to eat unused token.
		in.nextLine();
		
		try {
			System.out.println("Delete Party? (y to continue) \n" + dbUtil.getParty(partyID));
			String cont = in.nextLine();
			
			if ("y".equalsIgnoreCase(cont)) {
				try {
					dbUtil.deleteParty(partyID);
					System.out.println("Sucessfully deleted Party.");
				} catch (SQLException e) {
					System.out.println("Could not delete Party with ID (" + partyID + ") : " + e.getMessage());
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve Party with ID (" + partyID + ") : " + e.getMessage());
		}
	}
	
	/**
	 * Command procedure for deleting a client.
	 */
	@Deprecated
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
	 * Command procedure for updating a party.
	 */
	private void updateParty() {
		System.out.println("Enter ID of Party to update: ");
		int partyID = in.nextInt();
		
		// Used to eat unused token.
		in.nextLine();
		
		try {
			System.out.println("Current Party Info: \n" + dbUtil.getParty(partyID));
			
			System.out.println("Enter Party First Name: ");
			String fName = in.nextLine();
			
			System.out.println("Enter Party Middle Name: ");
			String mName = in.nextLine();
			
			System.out.println("Enter Party Last Name: ");
			String lName = in.nextLine();
			
			String phone = null;
			
			while (phone == null) {
				System.out.println("Enter Party Phone: ");
				phone = in.nextLine();
				
				if (!phone.matches("\\d{10}")) {
					System.out.println("Phone does not match correct format:"
							+ "\n\t1) 10 Digits"
							+ "\n\t2) Numbers only"
							+ "\nPlease Try Again\n");
					phone = null;
				}
			}
			
			
			Date birthDate = new Date(0); // Defaults to "the epoch" (standard Java base time).
			
			try {
				System.out.println("Enter Party Birth Year: ");
				int birthYear = in.nextInt();
				
				in.nextLine(); // Used to eat unused token.
				
				System.out.println("Enter Party Birth Month: ");
				int birthMonth = in.nextInt();
				birthMonth -= 1; // Convert from user's (1-12) format to Java's (0-11) format.
				
				in.nextLine(); // Used to eat unused token.
				
				System.out.println("Enter Party Birth Day: ");
				int birthDay = in.nextInt();
				
				in.nextLine(); // Used to eat unused token.
				
				Calendar c = Calendar.getInstance();
				c.set(birthYear, birthMonth, birthDay);
				birthDate.setTime(c.getTimeInMillis());
				
			} catch (InputMismatchException e) {
				System.out.println("\n Improper Input While Entering Birth Information, using default. Please update later.\n");
				in.nextLine(); // Used to eat unused token.
			}
			
			System.out.println("Enter Party Address: ");
			String address = in.nextLine();
			
			System.out.println("Enter Party Email: ");
			String email = in.nextLine();
			
			System.out.println("Enter a short note about Party: ");
			String note = in.nextLine();
			
			try {
				dbUtil.updateParty(partyID, fName, mName, lName, phone, birthDate, address, email, note);
				System.out.println("Sucessfully Updated Party");
			} catch (SQLException e) {
				System.out.println("Could not update Party (contact Sys Admin): " + e.getMessage());
			}			
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve Party with ID (" + partyID + ") : " + e.getMessage());
		}
	}

	
	/** 
	 * Command procedure for updating a client.
	 */
	@Deprecated
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
	 * Command procedure for storing a new Party.
	 */
	private void storeParty() {
		System.out.println("Enter Party First Name: ");
		String fName = in.nextLine();
		
		System.out.println("Enter Party Middle Name: ");
		String mName = in.nextLine();
		
		System.out.println("Enter Party Last Name: ");
		String lName = in.nextLine();
		
		String phone = null;
	
		while (phone == null) {
			System.out.println("Enter Party Phone: ");
			phone = in.nextLine();
			
			if (!phone.matches("\\d{10}")) {
				System.out.println("Phone does not match correct format:"
						+ "\n\t1) 10 Digits"
						+ "\n\t2) Numbers only"
						+ "\nPlease Try Again\n");
				phone = null;
			}
		}
		

		Date birthDate = new Date(0); // Defaults to "the epoch" (standard Java base time).
		
		try {
			System.out.println("Enter Party Birth Year: ");
			int birthYear = in.nextInt();
			
			in.nextLine(); // Used to eat unused token.
			
			System.out.println("Enter Party Birth Month: ");
			int birthMonth = in.nextInt();
			birthMonth -= 1; // Convert from user's (1-12) format to Java's (0-11) format.
			
			in.nextLine(); // Used to eat unused token.
			
			System.out.println("Enter Party Birth Day: ");
			int birthDay = in.nextInt();
			
			in.nextLine(); // Used to eat unused token.
			
			Calendar c = Calendar.getInstance();
			c.set(birthYear, birthMonth, birthDay);
			birthDate.setTime(c.getTimeInMillis());
			
		} catch (InputMismatchException e) {
			System.out.println("\n Improper Input While Entering Birth Information, using default. Please update later.\n");
			in.nextLine();
		}
		
		System.out.println("Enter Party Address: ");
		String address = in.nextLine();
		
		System.out.println("Enter Party Email: ");
		String email = in.nextLine();
		
		System.out.println("Enter a short note about Party: ");
		String note = in.nextLine();
		
		try {
			dbUtil.insertParty(fName, mName, lName, phone, birthDate, address, email, note);
			System.out.println("Sucessfully Added Party");
		} catch (SQLException e) {
			System.out.println("Could not add new Party (contact Sys Admin): " + e.getMessage());
		}
	}
	
	/**
	 * Command procedure for storing a new client.
	 */
	@Deprecated
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
			if (mainInput != null)
				c.mainControl(mainInput);
//				c.control(mainInput);
			
			System.out.println("\n" + c.mainCommands);
//			System.out.println("\n" + c.commands);
			mainInput = c.in.nextLine();
		}
		
		System.out.println("\n" + c.goodbyeStr);
	}
}
