package accesBDD;

import java.util.List;
import Partage.Drive;
import Partage.Projet;
import Partage.utilisateur;

public class Requetes {
	private static Requetes instance = new Requetes();
	static {
		JDBC.connexion();
		Drive.getInstance().setData(instance);
	}
	private Requetes() {
	}
	public static Requetes getInstance() {
		return instance;
	}
	
	public List<utilisateur> tousLesUtilisateurs(){
		return null;
	}
	public utilisateur getUser(String mail, String mdp) throws Exception {
		return null;
	}
	public List<Projet> getDocsPartages(utilisateur u) {
		return null;
	}
	public List<Projet> getDocuments(utilisateur u) {
		return null;
	}

}
