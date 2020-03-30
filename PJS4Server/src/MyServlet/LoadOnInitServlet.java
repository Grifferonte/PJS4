package MyServlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadOnInitServlet
 */

@WebServlet (urlPatterns="/initializeResources", loadOnStartup=1)
public class LoadOnInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		
		try {
			Class.forName("accesBDD.Requetes");
			System.out.println("******************");
		} catch (ClassNotFoundException e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		}
	}

}
