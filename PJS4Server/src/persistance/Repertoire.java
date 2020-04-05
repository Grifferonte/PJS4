package persistance;

import Partage.AbstractProjet;
import Partage.Drive;
import Partage.utilisateur;

public class Repertoire extends AbstractProjet{
	
	public Repertoire(int id, String nom, String type, String dateStockage, String v, String chemin) {
		super(id, nom, dateStockage, v, chemin);
	}
	
	public String toString() {
		return "Repertoire";
	}
	

	@Override
	public String getUrlServeur() {
		// TODO Auto-generated method stub
		return this.getCheminFTP();
	}
}
