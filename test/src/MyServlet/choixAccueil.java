package MyServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class choixAccueil
 */
public class choixAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized(this) {
			if (request.getParameter("DocsPartages") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/ListDocsPartages.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("CreerProjet") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/CreerProjet.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("tousDocs") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/tousDocs.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("nouveauDoc") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/nouveauDoc.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("reserver") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/reserver.jsp" ).forward( request, response );
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
