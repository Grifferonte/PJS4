package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ConnexionBDD.JDBC;

public class Requetes {
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
		String request = "SELECT * FROM COMPTE WHERE mail = ? AND mdp = ?;";
		try {

			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, mail);
			st.setString(2, mdp);
			ResultSet res = st.executeQuery();

			while (res.next())
				if (res.getString("typeCompte").equals("admin"))
					return new Admin(res.getInt("idCompte"), res.getString("mail"), res.getString("pseudo"),
							res.getInt("idStockage"));
				else if (res.getString("typeCompte").equals("client"))
					return new Client(res.getInt("idCompte"), res.getString("mail"), res.getString("pseudo"),
							res.getInt("idStockage"));

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

				while (res.next())
					list.add(new Entite(res.getInt("idEntite"), res.getString("nomEntite"), res.getString("extension"),
							res.getString("typeEntite"), res.getString("dateStockage")));
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

			while (res.next())
				list.add(new Entite(res.getInt("idEntite"), res.getString("nomEntite"), res.getString("extension"),
						res.getString("typeEntite"), res.getString("dateStockage")));

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

	public Projet getDocumentByName(String nomDocument) {
		// TODO Auto-generated method stub
		return null;
	}

	public void supprimerDoc(int numDocument) {
		// TODO Auto-generated method stub
		
	}

	public void creerDoc(String nomDocument) {
		// TODO Auto-generated method stub
		
	}

	public void PartagerDoc(utilisateur u, int numDocument) {
		// TODO Auto-generated method stub
		
	}
}
