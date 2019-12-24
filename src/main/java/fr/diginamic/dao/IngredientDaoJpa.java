package fr.diginamic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.diginamic.entites.Ingredient;

public class IngredientDaoJpa implements IngredientDao {

	@Override
	public Ingredient exist(String nom) {

		Ingredient ingredient = null;
		List<Ingredient> ingredients = new ArrayList<>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT i FROM Ingredient i where i.nom = ?1");

		query.setParameter(1, nom);

		ingredients = query.getResultList();
		if (ingredients.size() > 0) {

			ingredient = ingredients.get(0);

		}

		em.close();
		emf.close();

		return ingredient;

	}

	@Override
	public void insert(Ingredient ingredient) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(ingredient);

		et.commit();

		em.close();
		emf.close();

	}

}
