package MyServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			try {
				//********code**********
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ListDocsPartages.jsp" ).forward( request, response );
				//A modifier
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		else if (request.getParameter("ouvrir") != null) {
			try {
				//*****Code*********
				this.getServletContext().getRequestDispatcher( "/WEB-INF/CreerProjet.jsp" ).forward( request, response );
				//A modifier
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
