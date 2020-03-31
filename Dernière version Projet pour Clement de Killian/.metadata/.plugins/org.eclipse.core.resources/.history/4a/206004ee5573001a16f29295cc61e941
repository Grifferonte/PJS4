package persistance;
import java.util.List;

import Partage.AbstractProjet;
import Partage.AbstractUtilisateur;
import Partage.Drive;
import Partage.Projet;
import Partage.utilisateur;
import accesBDD.Requetes;

public class Fichier extends AbstractProjet {
	private String extension;
	
	public Fichier(int id, String nom, String extension, String type, String dateStockage) {
		super(id, nom, dateStockage);
		this.extension = extension;
	}
	
	
	//permet d'avoir le nom complet (nom + extension) pour les chemins d'accès
	// dossier n'a pas d'extension
	public String getNomComplet() {
		
		return this.getNom() + "." + this.extension;
	}
	
	public String toString() {
		return "Fichier";
	}

}
