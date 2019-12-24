package fr.diginamic.dao;

import fr.diginamic.entites.Marque;

public interface MarqueDao {
	/**
	 * Verifie si une marque existe dans la bdd
	 * 
	 * @param nom
	 * @return une marque sinon null
	 */

	Marque exist(String nom);

	/**
	 * Ajoute une Categorie en bdd
	 * 
	 * @param cat
	 */
	void insert(Marque marque);

}
