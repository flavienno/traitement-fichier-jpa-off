package fr.diginamic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.diginamic.entites.Produit;

public class ProduitDaoJpa implements ProduitDao {

	@Override
	public Produit exist(String nom) {
		Produit produit = null;

		List<Produit> produits = new ArrayList<>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT p FROM Produit p where p.nom = ?1");

		query.setParameter(1, nom);

		produits = query.getResultList();

		if (produits.size() > 0) {

			produit = produits.get(0);

		}

		em.close();
		emf.close();

		return produit;

	}

	@Override
	public void insert(Produit produit) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		em.persist(produit);

		et.commit();

		em.close();
		emf.close();

	}

}
