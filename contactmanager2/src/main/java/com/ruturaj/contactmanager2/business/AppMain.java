package com.ruturaj.contactmanager2.business;

import java.util.Scanner;

public class AppMain {

	public static AddContact add = new AddContact();
	public static DeleteContact delete = new DeleteContact();
	public static UpdateContact update = new UpdateContact();
	public static FindByFirstName findByFirstName = new FindByFirstName();
	public static FindByLastName findByLastName = new FindByLastName();
	public static FindAllContacts findAllContacts = new FindAllContacts();

	private static boolean run = true;

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
		while (run) {
			System.out.println("Enter 1 to add contact");
			System.out.println("Enter 2 to delete contact");
			System.out.println("Enter 3 to update contact");
			System.out.println("Enter 4 to find contact by first name");
			System.out.println("Enter 5 to find contact by last name");
			System.out.println("Enter 6 to find all contacts");
			System.out.println("Enter 7 to exit");
			System.out.println("Enter your choice : ");

			int choice = scanner.nextInt();

			try {
				switch (choice) {
				case 1:
					add.addContact(scanner);
					break;
				case 2:
					delete.deleteContact(scanner);
					break;
				case 3:
					update.updateContact(scanner);
					break;
				case 4:
					findByFirstName.findByFirstName(scanner);
					break;
				case 5:
					findByLastName.findByLastName(scanner);
					break;
				case 6:
					findAllContacts.findAllContacts();
					break;
				case 7:
					System.out.println("See you soon");
					run = false;
					break;
				default:
					System.out.println("Invalid choice");
				}
			} catch (Exception e) {
				System.out.println("Somthing went Wrong");
				
			}
			
		}
		scanner.close();

	}

}