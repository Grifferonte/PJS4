package MyServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Partage.Drive;
import Partage.utilisateur;

/**
 * Servlet implementation class choixAccueil
 */

@WebServlet("/choixAccueil")
public class choixAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized(this) {
			HttpSession session=request.getSession();
			utilisateur u = (utilisateur) session.getAttribute("client");
			if (request.getParameter("Partages") != null) {
				try {
					request.setAttribute("listeDocsPartages", Drive.getInstance().getDocsPartages(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/ListDocsPartages.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("Publics") != null) {
				try {
					request.setAttribute("listeDocsPublics", Drive.getInstance().getTousLesDocumentsPublics(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsPublics.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("Favoris") != null) {
				try {
					System.out.println("hello");
					request.setAttribute("listeDocsFavoris", Drive.getInstance().getTousLesDocumentsFavoris(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsFavoris.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("Archives") != null) {
				try {
					request.setAttribute("listeArchives", Drive.getInstance().getTousLesDocumentsArchives(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsArchives.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("rien");
				return;
			}
		}
	}
}
