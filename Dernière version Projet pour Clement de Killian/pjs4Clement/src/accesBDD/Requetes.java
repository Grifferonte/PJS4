package accesBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Partage.Drive;
import Partage.Projet;
import Partage.utilisateur;
import persistance.Admin;
import persistance.Client;
import persistance.Fichier;
import persistance.Repertoire;

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

	public List<utilisateur> tousLesUtilisateurs() {

		List<utilisateur> list = new ArrayList<>(); // la liste est class�e par odre alphab�tique de pseudo
		Connection connection = JDBC.getConnection();

		String request = "SELECT * FROM COMPTE ORDER BY pseudo;";
		try {
			PreparedStatement st = connection.prepareStatement(request);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if (res.getString("typeCompte").equals("admin"))
					list.add(new Admin(res.getInt("idCompte"), res.getString("mail"), res.getString("pseudo"),
							res.getInt("idStockage")));
				else if (res.getString("typeCompte").equals("client"))
					list.add(new Client(res.getInt("idCompte"), res.getString("mail"), res.getString("pseudo"),
							res.getInt("idStockage")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public utilisateur getUser(String mail, String mdp) throws Exception {

		Connection connection = JDBC.getConnection();
		String request = "SELECT * FROM COMPTE WHERE mail = ? AND motDePasse = ?;";
		try {

			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, mail);
			st.setString(2, mdp);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (res.getString("typeCompte").equals("admin"))
					return new Admin(res.getInt("idCompte"), res.getString("mail"), res.getString("nom"),
							res.getInt("idStockage"));
				else if (res.getString("typeCompte").equals("client"))
					return new Client(res.getInt("idCompte"), res.getString("mail"), res.getString("nom"),
							res.getInt("idStockage"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Projet> getDocsPartages(utilisateur u) {

		List<Projet> list = new ArrayList<>();
		Connection connection = JDBC.getConnection();

		String request = "SELECT * FROM PARTAGE WHERE idCompte2 = ?";
		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			ResultSet res = st.executeQuery();

			ArrayList<Integer> tab = new ArrayList<>();
			while (res.next())
				tab.add(res.getInt("idEntite"));

			for (int i : tab) {
				request = "SELECT * FROM ENTITE WHERE idEntite = ? ";
				st = connection.prepareStatement(request);
				st.setString(1, "" + i);
				res = st.executeQuery();

				while (res.next()) {
					if (res.getString("type").equals("repertoire"))
						list.add(new Repertoire(res.getInt("idEntite"), res.getString("nomEntite"),
								res.getString("typeEntite"), res.getString("dateStockage")));
					else
						list.add(new Fichier(res.getInt("idEntite"), res.getString("nomEntite"),
								res.getString("extension"), res.getString("typeEntite"),
								res.getString("dateStockage")));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Projet> getDocuments(utilisateur u) {

		List<Projet> list = new ArrayList<>();
		Connection connection = JDBC.getConnection();

		String request = "SELECT * FROM ENTITE WHERE idCompte = ? ;";
		try {

			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (res.getString("type").equals("repertoire"))
					list.add(new Repertoire(res.getInt("idEntite"), res.getString("nomEntite"),
							res.getString("typeEntite"), res.getString("dateStockage")));
				else
					list.add(new Fichier(res.getInt("idEntite"), res.getString("nomEntite"), res.getString("extension"),
							res.getString("typeEntite"), res.getString("dateStockage")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public void inscription(String email, String mdp, String pseudo) {

		Connection connection = JDBC.getConnection();
		String request = "INSERT INTO COMPTE (typeCompte,pseudo,mail,mdp) VALUES ('client',?,?,?); ";

		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, pseudo);
			st.setString(2, email);
			st.setString(3, mdp);
			st.executeUpdate();

			utilisateur u = this.getUser(email, mdp);
			request = "INSERT INTO STOCKAGE (idCompte,taille,dateCreation) VALUES (?,?,CURDATE())";
			st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			st.setString(2, "500");
			st.executeUpdate();

			request = "SELECT idStockage FROM STOCKAGE WHERE idCompte = ?";
			st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			ResultSet res = st.executeQuery();
			String idStockage = null;
			while (res.next())
				idStockage = "" + res.getInt("idStockage");

			request = "UPDATE COMPTE SET idStockage= ? WHERE idCompte = ? ";
			st = connection.prepareStatement(request);
			st.setString(1, idStockage);
			st.setString(2, "" + u.getId());
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changerMotDePasse(utilisateur u, String newMDP) {

		Connection connection = JDBC.getConnection();

		try {

			String update = "UPDATE COMPTE SET mdp = ? WHERE idCompte = ?;";
			PreparedStatement requete = connection.prepareStatement(update);
			requete.setString(1, newMDP);
			requete.setString(2, "" + u.getId());
			requete.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changerPseudo(utilisateur u, String newPseudo) {

		Connection connection = JDBC.getConnection();

		try {
			String update = "UPDATE COMPTE SET pseudo = ? WHERE idCompte = ?;";
			PreparedStatement requete = connection.prepareStatement(update);
			requete.setString(1, newPseudo);
			requete.setString(2, "" + u.getId());
			requete.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Projet getDocumentByName(String nomDocument) {
		// TODO Auto-generated method stub
		return null;
	}

	public void supprimerDoc(int numDocument) {
		// TODO Auto-generated method stub

	}

	public void creerNouveauDoc(utilisateur u, String nom) {

		Connection connection = JDBC.getConnection();
		String insert = "INSERT INTO ENTITE (nomEntite,extension,dateStockage,typeEntite,idCompte) VALUES(?,'txt',CURDATE(),'fichier texte',?);";
		String update = "UPDATE STOCKAGE SET nombreElements = nombreElements +1 WHERE idCompte = ? ";
		try {
			PreparedStatement st = connection.prepareStatement(insert);
			st.setString(1, nom);
			st.setString(2, "" + u.getId());
			st.executeUpdate();

			st = connection.prepareStatement(update);
			st.setString(1, "" + u.getId());
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creerNouveauDossier(utilisateur u, String nom) {
		Connection connection = JDBC.getConnection();
		String insert = "INSERT INTO ENTITE (nomEntite,extension,dateStockage,typeEntite,idCompte) VALUES(?,null,CURDATE(),'Dossier',?);";

		try {
			PreparedStatement st = connection.prepareStatement(insert);
			st.setString(1, nom);
			st.setString(2, "" + u.getId());
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void renommerDocument(utilisateur u, int numDoc, String nouveauNom) {
		Connection connection = JDBC.getConnection();
		String request = "SELECT idCompte FROM ENTITE WHERE idEntite = ?;";

		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + numDoc);
			ResultSet res = st.executeQuery();
			while (res.next())
				if (u.getId() == res.getInt("idCompte")) {
					String update = "UPDATE ENTITE SET nomEntite = ? WHERE idEntite = ?;";
					PreparedStatement requete = connection.prepareStatement(update);
					requete.setString(1, nouveauNom);
					requete.executeUpdate();
				}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PartagerDoc(utilisateur u1, utilisateur u2, int numDocument) {
		Connection connection = JDBC.getConnection();
		String request = "SELECT idCompte FROM ENTITE WHERE idEntite = ?";

		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + numDocument);
			ResultSet res = st.executeQuery();
			while (res.next())
				if (u1.getId() == res.getInt("idCompte")) {
					String insert = "INSERT INTO PARTAGE (idCompte, idCompte2,idEntite,datePartage) VALUES (?,?,?,CURDATE());";
					PreparedStatement requete = connection.prepareStatement(insert);
					requete.setString(1, "" + u1.getId());
					requete.setString(2, "" + u2.getId());
					requete.setString(1, "" + numDocument);
					requete.executeUpdate();
					
					
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Projet> getTousLesDocumentsPublics(utilisateur u) {
		List<Projet> list = new ArrayList<>();
		Connection connection = JDBC.getConnection();

		String request = "SELECT * FROM ENTITE WHERE idCompte = ? AND public = 1;";
		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if (res.getString("typeEntite").equals("repertoire"))
					list.add(new Repertoire(res.getInt("idEntite"), res.getString("nomEntite"),
							res.getString("typeEntite"), res.getString("dateStockage")));
				else
					list.add(new Fichier(res.getInt("idEntite"), res.getString("nomEntite"), res.getString("extension"),
							res.getString("typeEntite"), res.getString("dateStockage")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Projet> getTousLesDocumentsFavoris(utilisateur u) {
		List<Projet> list = new ArrayList<>();
		Connection connection = JDBC.getConnection();
		String request = "SELECT * FROM ENTITE WHERE idCompte = ? AND visibilite ='favoris';";

		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if (res.getString("typeEntite").equals("repertoire"))
					list.add(new Repertoire(res.getInt("idEntite"), res.getString("nomEntite"),
							res.getString("typeEntite"), res.getString("dateStockage")));
				else
					list.add(new Fichier(res.getInt("idEntite"), res.getString("nomEntite"), res.getString("extension"),
							res.getString("typeEntite"), res.getString("dateStockage")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Projet> getTousLesDocumentsArchives(utilisateur u) {
		List<Projet> list = new ArrayList<>();
		Connection connection = JDBC.getConnection();

		String request = "SELECT * FROM ENTITE WHERE idCompte = ? AND visibilite ='archive';";

		try {
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, "" + u.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if (res.getString("typeEntite").equals("repertoire"))
					list.add(new Repertoire(res.getInt("idEntite"), res.getString("nomEntite"),
							res.getString("typeEntite"), res.getString("dateStockage")));
				else
					list.add(new Fichier(res.getInt("idEntite"), res.getString("nomEntite"), res.getString("extension"),
							res.getString("typeEntite"), res.getString("dateStockage")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
