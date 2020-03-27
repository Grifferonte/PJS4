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
			else if (request.getParameter("DocsPublics") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsPublics.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("DocsFavoris") != null) {
				try {
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsFavoris.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("DocsArchives") != null) {
				try {
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
