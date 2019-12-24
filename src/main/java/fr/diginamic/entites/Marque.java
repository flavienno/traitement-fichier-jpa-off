package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MARQUE")

/**
 * Represente une marque donnée
 */
public class Marque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MARQUE")
	private Integer id;
	@Column(name = "NOM")
	private String nom;

	/**
	 * liste de produit pour une marque
	 */
	@OneToMany(mappedBy = "marque")
	private List<Produit> produits = new ArrayList<>();

	/**
	 * 
	 */
	public Marque() {
		super();
	}

	/**
	 * @param nom
	 */
	public Marque(String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the produits
	 */
	public List<Produit> getProduits() {
		return produits;
	}

	/**
	 * Setter
	 * 
	 * @param produits
	 *            the produits to set
	 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
