package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT")

public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INGREDIENT")
	private Integer id;
	@Column(name = "NOM")
	private String nom;

	@ManyToMany
	@JoinTable(name = "COMPO", joinColumns = @JoinColumn(name = "ID_COMPO_INGREDIENT", referencedColumnName = "ID_INGREDIENT"), inverseJoinColumns = @JoinColumn(name = "ID_COMPO_PRODUIT", referencedColumnName = "ID_PRODUIT"))
	private List<Produit> produits= new ArrayList<>();

	/**
	 * 
	 */
	public Ingredient() {
		super();
	}

	/**
	 * @param id
	 * @param nom
	 * @param produits
	 */
	public Ingredient(Integer id, String nom, List<Produit> produits) {
		super();
		this.id = id;
		this.nom = nom;
		this.produits = produits;
	}

	public Ingredient(String nom){
		this.nom = nom;
	}

	
	/** Extrait une liste de produit a partir d'une chaine de caractere
	 * @param ligne
	 * @return
	 */
	public static List<Ingredient> listeProduit(String ligne){
	
			
		
		List<Ingredient> listeIngredient = new ArrayList<>();
		
		String[]m = ligne.split(",");
		
		for(int i = 0 ; i <m.length;i++){
			
			listeIngredient.add(new Ingredient(m[i].trim()));
					
		}
	
		return listeIngredient;
						
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
