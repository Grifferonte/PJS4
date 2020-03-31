package persistance;

import Partage.AbstractProjet;
import Partage.Drive;
import Partage.utilisateur;

public class Repertoire extends AbstractProjet{
	
	public Repertoire(int id, String nom, String extension, String type, String dateStockage) {
		super(id, nom, dateStockage);
	}
	
	public String toString() {
		return "Repertoire";
	}
	
	@Override
	public void put(utilisateur u) {
		Drive.getInstance().creerNouveauDossier(u, this.getNom());
		
	}
}
