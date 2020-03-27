package MyServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Partage.Drive;
import Partage.utilisateur;
import accesBDD.Requetes;


public class connexion extends HttpServlet{
	/**
	 * Classe de connexion à la base de donnée 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		Requetes.getInstance();
		String mail = request.getParameter( "mail" );
		String motDePasse = request.getParameter("motDePasse");
		try {
			utilisateur u = Drive.getInstance().getUser(mail, motDePasse);
			request.setAttribute("User", u);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("UtilisateurExistePas", e.getMessage());
			try {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
			} catch (ServletException | IOException ex) {
				ex.printStackTrace();
			}
		}
		/*System.out.println(mail);
		System.out.println(motDePasse);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			System.err.println(e + "c");
		}
		try {
			connection = (Connection) DriverManager.getConnection(url, utilisateur, mdp);
			PreparedStatement preparedstatement = (PreparedStatement) connection.prepareStatement("Select nom from compte where mail=? and motDePasse=?;");
			//-------------------requetes-----------------------//
			preparedstatement.setString(1, mail);
			preparedstatement.setString(2, motDePasse);
			ResultSet resultat = preparedstatement.executeQuery();
			String nom = null;
			while (resultat.next()) {
				nom = resultat.getString("nom");
				System.out.println(nom);
			}
			request.setAttribute("nomPers", nom);
			try {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}catch (SQLException e) {
			System.out.println(e + "a");
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
		}*/
	}
}
