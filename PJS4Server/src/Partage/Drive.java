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
	
	public utilisateur getUserById(int idUt) throws Exception {
		return req.getUserById(idUt);
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
	
	public void creerNouveauDossier(utilisateur u, String nom, String cheminFTP,int idParent, int publicOuPrive) {
		req.creerNouveauDossier(u, nom, idParent, cheminFTP, publicOuPrive);
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
	
	public void updateDocumentPrive(int idDoc) {
		req.updateDocumentPrive(idDoc);
	}
	
	public void updateDocumentPublic(int idDoc) {
		req.updateDocumentPublic(idDoc);
	}
	
	public void updateVisibility(int idDoc, String nouvelleVisibilite) {
		req.updateVisibilite(idDoc, nouvelleVisibilite);
	}
	
	public List<Projet> getTousLesDocumentsArchives(utilisateur u) {
		return req.getTousLesDocumentsArchives(u);
	}
	
	public List<Projet> getTousLesDocumentsPrives(utilisateur u) {
		return req.getTousLesDocumentsPrives(u);
	}
	
	public List<Projet> getTousLesDocumentsPublics(utilisateur u) {
		return req.getTousLesDocumentsArchives(u);
	}
	
	public List<Projet> getTousLesDocumentsFavoris(utilisateur u) {
		return req.getTousLesDocumentsFavoris(u);
	}
	
	public List<Projet> getDocumentInside(Projet p, String v){
		return req.getDocumentsInside(p,v);
	}
	
	public Projet getRepertoirePere(Projet p) {
		return req.getRepertoirePere(p);
	}
	
	public Projet getDocumentByCheminFTP(String cheminFTP) {
		return req.getDocumentByCheminFTP(cheminFTP);
	}

	public List<Projet> getDocumentsBySearch(String motsClefs){
		return req.getDocumentsBySearch(motsClefs);
	}
	
	public List<utilisateur> getUsersBySearch(String motsClefs){
		return req.getUsersBySearch(motsClefs);
	}
	
	public void suivreUtilisateur(utilisateur u1, utilisateur u2) {
		req.SuivreUtilisateur(u1, u2);
	}

	public void creerNouveauDoc(utilisateur u, String nomFichier, int idProjetPere, String url, int publicOuPrive, String visibilite) {
		req.creerNouveauDoc(u, nomFichier, idProjetPere, url, publicOuPrive, visibilite);
	}

	public List<utilisateur> getPersonnesSuivies(utilisateur u){
		return (List<utilisateur>) req.getPersonnesSuivies(u);
	}
	

}
