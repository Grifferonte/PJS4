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
	
	public Fichier(int id, String nom, String extension, String type, String v, String dateStockage, String chemin) {
		super(id, nom, dateStockage, v, chemin);
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
	


	@Override
	public String getUrlServeur() {
		// TODO Auto-generated method stub
		return this.getCheminFTP();
	}

}
