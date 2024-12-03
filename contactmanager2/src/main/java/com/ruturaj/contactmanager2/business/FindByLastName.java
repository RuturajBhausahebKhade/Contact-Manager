package com.ruturaj.contactmanager2.business;

import java.util.List;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ruturaj.contactmanager2.entity.Contact;

public class FindByLastName {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static Query query;
	
	public void findByLastName(Scanner scanner) {
		
		System.out.println("Enter Last Name to Find Contact : ");
		String lname = scanner.nextLine();
		
		openConnection();
		query = entityManager.createQuery("SELECT c FROM Contact c WHERE c.lastname =?1");
		query.setParameter(1, lname);
		
		try {
			@SuppressWarnings("unchecked")
			List<Contact> contact = query.getResultList();
			System.out.println(contact);
		} catch (Exception e) {
			System.out.println("User Not Found.");
		}
	}
		
	public static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("contactmanager");
		entityManager = entityManagerFactory.createEntityManager();
	}
	public static void closeConnection() {
		if(entityManagerFactory != null)
			entityManagerFactory.close();
		if(entityManager != null)
			entityManager.close();
	}
}
