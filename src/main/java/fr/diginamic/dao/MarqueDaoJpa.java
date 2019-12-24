package fr.diginamic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.diginamic.entites.Marque;

public class MarqueDaoJpa implements MarqueDao {

	@Override
	public Marque exist(String nom) {
		Marque marque = null;
		List<Marque> marques = new ArrayList<>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT m FROM Marque m where m.nom = ?1");

		query.setParameter(1, nom);
		marques = query.getResultList();

		if (marques.size() > 0) {
			marque = marques.get(0);
		}

		em.close();
		emf.close();
		return marque;
	}

	@Override
	public void insert(Marque marque) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(marque);

		et.commit();

		em.close();
		emf.close();

	}

}
