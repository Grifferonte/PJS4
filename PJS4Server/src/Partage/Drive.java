package Partage;

import java.util.ArrayList;
import java.util.List;

import accesBDD.Requetes;

public class Drive {
	private Requetes req;
	private static Drive instance = new Drive();

	private Drive() {

	}

	public void setData(Requetes r) {
		req = r;
	}

	public static Drive getInstance() {
		return instance;
	}

	public List<utilisateur> tousLesUtilisateurs() {
		return req.tousLesUtilisateurs();
	}

	public utilisateur getUser(String mail, String mdp) throws Exception {
		return req.getUser(mail, mdp);
	}
	
	public utilisateur getUserByMail(String mail) throws Exception {
		return req.getUserByMail(mail);
	}

	public List<Projet> getDocsPartages(utilisateur u) {
		return req.getDocsPartages(u);
	}

	public List<Projet> getDocuments(utilisateur u) {
		return req.getDocuments(u);
	}

	public Projet getDocumentById(int numDocument) {
		return req.getDocumentById(numDocument);
	}

	public void supprimerDoc(utilisateur u, int numDocument) {
		req.supprimerDoc(u,numDocument);
	}

	public void creerNouveauDoc(utilisateur u, String nom, String cheminFTP) {
		req.creerNouveauDoc(u, nom, cheminFTP);
	}
	
	public void creerNouveauDossier(utilisateur u, String nom, String cheminFTP) {
		req.creerNouveauDossier(u, nom, cheminFTP);
	}

	public void PartagerDoc(utilisateur u1, utilisateur u2, int numDocument) {
		req.partagerDoc(u1, u2, numDocument);
	}

	public void inscritpion(String mail, String motdepasse, String pseudo) {

		req.inscription(mail, motdepasse, pseudo);

	}

	public void changerMotDePasse(utilisateur u, String newMDP) {
		req.changerMotDePasse(u, newMDP);
	}

	public void changerPseudo(utilisateur u, String newPseudo) {
		req.changerPseudo(u, newPseudo);
	}
	
	public void renommerDocument(utilisateur u, int numDoc, String nouveauNom) {
		req.renommerDocument(u, numDoc, nouveauNom);
	}
	
	public void supprimerCompte(utilisateur u) {
		req.supprimerCompte(u);
	}
	
	public void updateDocumentPrive(utilisateur u) {
		req.updateDocumentPrive(u);
	}
	
	public void updateDocumentPublic(utilisateur u) {
		req.updateDocumentPublic(u);
	}
	
	public void updateVisibility(utilisateur u) {
		req.updateDocumentPrive(u);
	}
	
	public List<Projet> getTousLesDocumentsArchives(utilisateur u) {
		return req.getTousLesDocumentsArchives(u);
	}
	
	public List<Projet> getTousLesDocumentsFavoris(utilisateur u) {
		return req.getTousLesDocumentsFavoris(u);
	}
	
	public List<Projet> getTousLesDocumentsPublics(utilisateur u) {
		return req.getTousLesDocumentsArchives(u);
	}
	
	public List<Projet> getDocumentInside(Projet p){
		return req.getDocumentsInside(p);
	}
	
	public Projet getRepertoirePere(Projet p) {
		return req.getRepertoirePere(p);
	}
	
	public Projet getDocumentByCheminFTP(String cheminFTP) {
		return req.getDocumentByCheminFTP(cheminFTP);
	}

	public List<Projet> getDocumentsInside(String urlDossierCourant) {
		// TODO Auto-generated method stub
		return null;
	}

}
