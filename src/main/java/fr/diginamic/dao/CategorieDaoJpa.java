package fr.diginamic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.diginamic.entites.Categorie;

public class CategorieDaoJpa implements CategorieDao {

	@Override
	public Categorie exist(String nom) {

		Categorie categorie = null;
		List<Categorie> categories = new ArrayList<>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT c FROM Categorie c where c.nom = ?1");

		query.setParameter(1, nom);

		categories = query.getResultList();

		if (categories.size() > 0) {

			categorie = categories.get(0);

		}

		em.close();
		emf.close();

		return categorie;
	}

	@Override
	public void insert(Categorie categorie) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(categorie);

		et.commit();

		em.close();
		emf.close();

	}

}
