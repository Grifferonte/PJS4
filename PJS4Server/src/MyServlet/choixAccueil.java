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
			if (request.getParameter("DocsPartages") != null) {
				try {
					request.setAttribute("listeDocsPartages", Drive.getInstance().getDocsPartages(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/ListDocsPartages.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("DocsPublics") != null) {
				try {
					request.setAttribute("listeDocsPublics", Drive.getInstance().getDocsPublics(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsPublics.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("DocsFavoris") != null) {
				try {
					request.setAttribute("listeDocsPartages", Drive.getInstance().getDocsFavoris(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsFavoris.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("DocsArchives") != null) {
				try {
					request.setAttribute("listeDocsPartages", Drive.getInstance().getDocsArchives(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsArchives.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else {
				return;
			}
		}
	}
}
