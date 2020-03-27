package Partage;

import java.util.ArrayList;
import java.util.List;

import accesBDD.Requetes;

public class Drive{
	private Requetes req;
	private static Drive instance = new Drive();
	
	private Drive() {
		
	}
	public void setData (Requetes r) {
		req = r;
	}
	
	public static Drive getInstance() {
		return instance;
	}
	
	public List<utilisateur> tousLesUtilisateurs(){
		return req.tousLesUtilisateurs();
	}
	
	public utilisateur getUser(String mail, String mdp) throws Exception{
		return req.getUser(mail, mdp);
	}
	
	public List<Projet> getDocsPartages(utilisateur u){
		return req.getDocsPartages(u);
	}
	
	public List<Projet> getDocuments(utilisateur u){
		return req.getDocuments(u);
	}
	
	public void inscription(String email, String mdp, String pseudo ) {
		req.inscription(email, mdp, pseudo);
	}
}
