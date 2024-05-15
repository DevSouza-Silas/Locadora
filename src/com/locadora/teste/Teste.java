package com.locadora.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Persistence;

import org.hibernate.annotations.Parameter;

import com.locadora.model.Categoria;

public class Teste {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("LocadoraPU");
		
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.close();
	}

	
}
