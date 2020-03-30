package accesBDD;
import java.util.List;

import Partage.Projet;
import Partage.utilisateur;

public class Entite implements Projet {
	
	private int id;
	private String nom;
	/*
	 * Les deux attributs ci-dessous sont peut-être à remanier avec de l'héritage et les requêtes associées également
	 * L'un des deux n'est peut être pas nécessaire
	 */
	private String extension;
	private String type;
	
	private String dateStockage;
	
	public Entite(int id, String nom, String extension, String type, String dateStockage) {
		this.id = id;
		this.nom = nom;
		this.extension = extension;
		this.type = type;
		this.dateStockage = dateStockage;
	}
	
	@Override
	public void put(utilisateur u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Partager(List<utilisateur> ListUt) {
		// TODO Auto-generated method stub
		
	}
	
	//permet d'avoir le nom complet (nom + extension) pour les chemins d'accès
	// dossier n'a pas d'extension
	public String getNomComplet() {
		
		return this.nom + "." + this.extension;
	}

}
