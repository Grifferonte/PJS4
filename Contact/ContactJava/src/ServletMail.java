import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/envoyerEmail")
public class ServletMail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ConfigMail config = new ConfigMail(request.getParameter("objet"), request.getParameter("message"),
				request.getParameter("email"));

		config.sendMailGmail();


	}

}
