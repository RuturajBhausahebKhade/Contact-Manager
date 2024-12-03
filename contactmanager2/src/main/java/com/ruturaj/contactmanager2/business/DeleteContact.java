package com.ruturaj.contactmanager2.business;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DeleteContact {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	private static Query query;
	
	
	public void deleteContact(Scanner scanner) {
		
		System.out.println("Enter the First Name of Contact to Delete Contact : ");
		String fname = scanner.nextLine();	
		
		System.out.println("Enter the Mobile Number of Contact to Delete Contact : ");
		long mobile = scanner.nextLong();
		
		openConnection();
		entityTransaction.begin();
		query = entityManager.createQuery("DELETE FROM Contact c  WHERE c.firstname = ?1 AND c.mobile = ?2");
		query.setParameter(1, fname);
		query.setParameter(2, mobile);
		int rowUpdated = query.executeUpdate();
		entityTransaction.commit();
		
		if(rowUpdated>0)
			System.out.println("Contact Deleted Successfully.");
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
