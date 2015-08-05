package walker.bryson.lawservices.core;

import java.sql.SQLException;
import java.util.Scanner;

import walker.bryson.lawservices.db.DBUtilities;
import walker.bryson.lawservices.models.Client;

public class CLI {
	
	private DBUtilities dbUtil;
	
	private Scanner in = new Scanner(System.in);
	
	private String welcomeStr = "Welcome To Law Services"
			+ "-----------------------------------------------";
	
	private String sentinelValue = "Q";
	
	private String commands = "Commands:"
			+ "\n\t1) Insert Client"
			+ "\n\t2) See All Clients"
			+ "\n\n\t" + sentinelValue + " To Exit";
	
	private String goodbyeStr = "Goodbye!";
	
	
	public CLI() {
		dbUtil = new DBUtilities();
	}
	
	private void control(String mainInput) {
		try {
			
			Integer command = Integer.parseInt(mainInput);
			
			if (command.equals(1)) {
				storeClient();
			} else if (command.equals(2)) {
				
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
				
			} else {
				System.out.println("Unsupported Command Value: " + command);
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Unexpected input: " + mainInput);
		}
	}
	
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
