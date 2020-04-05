package MyServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Partage.Drive;
import Partage.utilisateur;
import accesBDD.Requetes;



@WebServlet("/co")
public class connexion extends HttpServlet{
	/**
	 * Classe de connexion � la base de donn�e 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String mail = request.getParameter( "mail" );
		String motDePasse = request.getParameter("motDePasse");
		try {
			HttpSession session = request.getSession();
			utilisateur u = Drive.getInstance().getUser(mail, motDePasse);
			session.setAttribute("dossierCourant", "/classes/"+u.getPseudo());
			request.setAttribute("User", u);
			session.setAttribute("client", u);
			try {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
			} catch (ServletException | IOException ex) {
				ex.printStackTrace();
			}
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
