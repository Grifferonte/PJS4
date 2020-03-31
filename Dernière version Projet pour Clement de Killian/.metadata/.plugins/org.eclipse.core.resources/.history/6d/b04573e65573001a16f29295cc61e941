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
	public Projet getDocumentByName(String nomDocument) {
		return req.getDocumentByName(nomDocument);
	}
	public void supprimerDoc(int numDocument) {
		req.supprimerDoc(numDocument);
	}
	public void creerDoc(utilisateur u, String nomDocument) {
		req.creerDoc(u, nomDocument);
	}
	public void PartagerDoc(utilisateur u1, utilisateur u2, int numDocument) {
		req.PartagerDoc(u1, u2, numDocument);
	}
	
	public void inscritpion(String mail, String motdepasse, String pseudo) {
	
		req.inscription(mail, motdepasse, pseudo);
		
	}
}
