package persistance;

import Partage.AbstractProjet;
import Partage.Drive;
import Partage.utilisateur;

public class Repertoire extends AbstractProjet{
	
	public Repertoire(int id, String nom, String type, String dateStockage, String chemin) {
		super(id, nom, dateStockage, chemin);
	}
	
	public String toString() {
		return "Repertoire";
	}
	
	@Override
	public void put(utilisateur u) {
		Drive.getInstance().creerNouveauDossier(u, this.getNom());
		
	}

	@Override
	public String getUrlServeur() {
		// TODO Auto-generated method stub
		return this.getCheminFTP();
	}
}
