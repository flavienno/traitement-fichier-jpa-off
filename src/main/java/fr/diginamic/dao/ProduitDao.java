package fr.diginamic.dao;

import fr.diginamic.entites.Produit;

public interface ProduitDao {
	/**
	 * Verifie si un produit existe dans la bdd
	 * 
	 * @param nom
	 * @return un Produit sinon null
	 */
	Produit exist(String nom);

	/**
	 * Ajoute un produit dans la bdd
	 * 
	 * @param prod
	 */
	void insert(Produit produit);

}
