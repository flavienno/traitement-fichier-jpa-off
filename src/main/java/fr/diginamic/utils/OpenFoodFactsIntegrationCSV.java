package fr.diginamic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.io.FileUtils;
import fr.diginamic.dao.CategorieDaoJpa;
import fr.diginamic.dao.IngredientDaoJpa;
import fr.diginamic.dao.MarqueDaoJpa;
import fr.diginamic.dao.ProduitDaoJpa;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class OpenFoodFactsIntegrationCSV {

	/**
	 * Lit le contenu du fichier en paramètre et insère les donnees du .csv dans
	 * une BDD
	 * 
	 * @param cheminFichier
	 *            chemin d'accès au fichier sur le disque dur
	 * @return
	 */
	public boolean Convert() {

		// private static final Logger LOG =
		// LoggerFactory.getLogger(OpenFoodFactsIntegrationCSV.class);
		// String path =
		// ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();

		List<String> liste = new ArrayList<>();

		try {
			File file = new File("D:/open-food-facts.csv");
			// File file = new File(path);
			liste = FileUtils.readLines(file, "UTF-8");

			// On supprime la ligne d'entéte avec les noms des colonnes
			liste.remove(0);

			for (String ligne : liste) {

				ajoutLigneBDD(ligne);
			}

		} catch (IOException e) {

			// LOG.error(e.getMessage());
			e.getMessage();
			return false;
		}

		return true;

	}

	/**
	 * Ajout d'une ligne du .csv dans la BDD
	 * 
	 * @param ligne
	 */
	public static void ajoutLigneBDD(String ligne) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		CategorieDaoJpa categorieDao = new CategorieDaoJpa();
		MarqueDaoJpa marqueDao = new MarqueDaoJpa();
		ProduitDaoJpa produitDao = new ProduitDaoJpa();
		IngredientDaoJpa ingredientDao = new IngredientDaoJpa();

		et.begin();

		String m1 = ligne.replace('|', '@');

		m1 = m1.replace('[', ' ');
		m1 = m1.replace(']', ' ');

		String[] m = m1.split("@");

		String nom = m[2];
		String scoreNutritionnel = m[3];

		Produit produit = new Produit(nom, scoreNutritionnel);

		Categorie categorie = new Categorie(m[0]);

		Marque marque = new Marque(m[1]);

		List<Ingredient> ingredients = Ingredient.listeProduit(m[4]);

		if (produitDao.exist(nom) == null) {

			if (categorieDao.exist(categorie.getNom()) == null) {

				categorieDao.insert(categorie);

				categorie = categorieDao.exist(categorie.getNom());

			} else {

				categorie = categorieDao.exist(categorie.getNom());

			}

			if (marqueDao.exist(marque.getNom()) == null) {

				marqueDao.insert(marque);

				marque = marqueDao.exist(marque.getNom());

			} else {

				marque = marqueDao.exist(marque.getNom());

			}

			int count = 0;
			for (Ingredient ing : ingredients) {

				if (ingredientDao.exist(ing.getNom()) == null) {

					ingredientDao.insert(ing);

					ingredients.get(count).setId(ingredientDao.exist(ing.getNom()).getId());

				} else {

					ingredients.get(count).setId(ingredientDao.exist(ing.getNom()).getId());

				}

				count++;
			}

			produit.setCategorie(categorie);
			produit.setIngredients(ingredients);
			produit.setMarque(marque);

			em.persist(produit);

			et.commit();

		}

		em.close();
		emf.close();

	}

}
