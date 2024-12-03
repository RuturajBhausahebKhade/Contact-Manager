package com.ruturaj.contactmanager2.business;

import java.util.Scanner;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ruturaj.contactmanager2.entity.Contact;

public class AddContact {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	
	public void addContact(Scanner scanner) {
		Contact contact = new Contact();
		
		System.out.println("Enter First Name : ");
		contact.setFirstname(scanner.next());
		
		System.out.println("Enter Last Name : ");
		contact.setLastname(scanner.next());
		
		System.out.println("Emter Mobile Number : ");
		contact.setMobile(scanner.nextLong());
		
		System.out.println("Enter Email Id : ");
		contact.setEmail(scanner.next());
		
		
		openConnection();
		entityTransaction.begin();
		entityManager.persist(contact);
		entityTransaction.commit();
		closeConnection();		
	}
	
	public static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("contactmanager");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();		 
	}
	
	public static void closeConnection() {
		if(entityManagerFactory != null)
			entityManagerFactory.close();
		if(entityManager != null)
			entityManager.close();
		if(entityTransaction != null) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
}
