package fr.diginamic.dao;

import fr.diginamic.entites.Ingredient;

public interface IngredientDao {

	/**
	 * Verifie si un ingredient existe dans la bdd
	 * 
	 * @param nom
	 * @return un Ingredient sinon null
	 */
	Ingredient exist(String nom);

	/**
	 * Ajoute un ingredient dans la bdd
	 * 
	 * @param ingredient
	 */
	void insert(Ingredient ingredient);

}
