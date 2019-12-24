package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;

public interface CategorieDao {

	/**
	 * Verifie si un nom existe dans la bdd
	 * 
	 * @param nom
	 * @return un nom sinon null
	 */

	Categorie exist(String nom);

	/**
	 * Ajoute une Categorie en bdd
	 * 
	 * @param cat
	 */
	void insert(Categorie categorie);

}
