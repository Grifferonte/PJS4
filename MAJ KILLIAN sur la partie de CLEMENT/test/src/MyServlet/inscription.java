package MyServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Partage.Drive;


@WebServlet("/inscription")
public class inscription extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("mdp");
		String pseudo = request.getParameter("pseudo");
		
		try {
			Drive.getInstance().inscription(email, password, pseudo);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp" ).forward( request, response );
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
