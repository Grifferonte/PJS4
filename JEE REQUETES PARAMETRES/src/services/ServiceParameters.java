package services;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bddparameters.RequestParameters;

public class ServiceParameters extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		
		//new RequestParameters().updateEmail(request.getParameter("newmail"), (Utilisateur) session.getAttribute("User").getAttribute());
		
		
	}
}
