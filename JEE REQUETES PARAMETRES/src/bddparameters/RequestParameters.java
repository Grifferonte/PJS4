package bddparameters;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//Les exceptions sont explicitées mais non traitées dans des try/catch pour le moment
public class RequestParameters {
	
	public void updateEmail(String email, int idCompte) throws ClassNotFoundException, SQLException {
		String requestUpdate = "UPDATE COMPTE SET mail = ? WHERE idCompte = ?";
		PreparedStatement st = BDDConnexion.getConnexionBDD().prepareStatement(requestUpdate);
		st.setString(1, email);
		st.setString(2, "" + idCompte);
		st.executeUpdate();
	}
	
	public void updateMdp(String password, int idCompte) throws ClassNotFoundException, SQLException {
		String requestUpdate = "UPDATE COMPTE SET mdp = ? WHERE idCompte = ?";
		PreparedStatement st = BDDConnexion.getConnexionBDD().prepareStatement(requestUpdate);
		st.setString(1, password);
		st.setString(2, "" + idCompte);
		st.executeUpdate();
	}
	
	public void deleteCompte (int idCompte) throws ClassNotFoundException, SQLException {
		String requestDelete = "DELETE FROM COMPTE WHERE idCompte = ? ";
		PreparedStatement st = BDDConnexion.getConnexionBDD().prepareStatement(requestDelete);
		st.setString(1,""+idCompte);
		st.executeUpdate();
	}
	
	public void updateLastConnexionAndIP(int compte) {
		//String requestUpdateLastConnexion  =
	}
	
	public void updatePseudo(String pseudo, int idCompte) throws ClassNotFoundException, SQLException {
		String requestUpdate = "UPDATE COMPTE SET mdp = ? WHERE idCompte = ?";
		PreparedStatement st = BDDConnexion.getConnexionBDD().prepareStatement(requestUpdate);
		st.setString(1, pseudo);
		st.setString(2, "" + idCompte);
		st.executeUpdate();
	}
	
	/*public static void main (String args[]) throws ClassNotFoundException, SQLException {
		
		new RequestParameters().updateEmail("pote", 1);
		new RequestParameters().updateMdp("salope", 1);
	}
	*/
}
