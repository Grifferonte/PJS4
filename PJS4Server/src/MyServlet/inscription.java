package MyServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

import LoginFTP.FTPConnectAndLogin;
import Partage.Drive;


@WebServlet("/inscription")
public class inscription extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("param")!= null) {
			try {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Inscription.jsp" ).forward( request, response );
				return;
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String mail = (String) request.getParameter("mailUt");
		String pseudo = (String) request.getParameter("pseudoUt");
		String mdp = (String) request.getParameter("mdpUt");
		String mdp2 = (String) request.getParameter("confirmMdpUt");
		System.out.println(mail);
		System.out.println(mdp);
		System.out.println(mdp2);
		
		if(!mdp.equals(mdp2)) {
			
			try {
				request.setAttribute("erreur", "veuillez entrer des mots de passe valides");
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Inscription.jsp" ).forward( request, response );
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			Drive.getInstance().inscritpion(mail, mdp, pseudo);
			FTPConnectAndLogin.getInstance().connect().makeDirectory("/classes/" + pseudo);
			try {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Ident.jsp" ).forward( request, response );
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
