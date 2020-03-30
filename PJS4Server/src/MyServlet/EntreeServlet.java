package MyServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EntreeServlet
 */

@WebServlet("/EntreeServlet")
public class EntreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Ident.jsp" ).forward( request, response );
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
