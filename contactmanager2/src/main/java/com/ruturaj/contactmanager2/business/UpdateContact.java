package com.ruturaj.contactmanager2.business;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UpdateContact {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	private static Query query;
	
	
	public void updateContact(Scanner scanner) {
		
		System.out.println("Enter the First Name of Contact to Update Mobile Number : ");
		String fname = scanner.nextLine();		
		
		System.out.println("Enter new Mobile Number : ");
		long newMobile = scanner.nextLong();
		
		openConnection();
		entityTransaction.begin();
		query = entityManager.createQuery("UPDATE Contact c SET c.mobile = ?1 WHERE c.firstname = ?2");
		query.setParameter(1, newMobile);
		query.setParameter(2, fname);
		int rowUpdated = query.executeUpdate();
		entityTransaction.commit();
		
		if(rowUpdated>0)
			System.out.println("Contact Updated Successfully.");
		else
			System.out.println("Contact Not Found.");
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
