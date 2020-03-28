package MyServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Partage.Drive;

/**
 * Servlet implementation class choixActionProjet
 */
public class choixActionProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("Partager") != null) {
			//Code
		}
		else if (request.getParameter("ouvrir") != null) {
			//Tester si le projet est un répertoire ou un fichier
			//Code pour ouvrir document (si txt l'ouvrir dans une fenetre java)
		}
		else if (request.getParameter("supprimer") != null) {
			int numDoc = Integer.parseInt(request.getParameter("numDoc"));
			Drive.getInstance().supprimerDoc(numDoc);
		}
	}
}
