package MyServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import LoginFTP.FTPConnectAndLogin;
import Partage.Drive;
import Partage.Projet;
import Partage.utilisateur;

/**
 * Servlet implementation class ChoixPage
 */
@WebServlet("/ChoixActionPage")
public class ChoixPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized(this) {
			HttpSession session=request.getSession();
			utilisateur u = (utilisateur) session.getAttribute("client");
			
			String cheminServer = request.getParameter("UrlServeur");
			String[] cheminDecoupe = cheminServer.split("/");
			String UrlDossierCourant = new String();
			for (int i=0; i<cheminDecoupe.length-1; ++i) {
				UrlDossierCourant += cheminDecoupe[i] + "/";
			}
			String chemin = cheminServer.substring(0, cheminServer.length() -1);
			Projet pere = Drive.getInstance().getDocumentById(Integer.parseInt(request.getParameter("idProjetPere")));
			if (request.getParameter("ajouterFichier") != null) {
				try {
					String cheminFichier = request.getParameter("cheminFichier");
					String nomFichier = request.getParameter("nomFichier");
					//Drive.getInstance().creerNouveauDoc(u, nomFichier, chemin);
					
					
					FTPClient ftpClient = FTPConnectAndLogin.getInstance().connect();
					InputStream fis = new FileInputStream(cheminFichier.substring(0, cheminFichier.length()-1));
			        OutputStream os = ftpClient.storeFileStream(cheminServer);
			      byte buf[] = new byte[4800];
			      int bytesRead = fis.read(buf);
			      while (bytesRead != -1) {
			          os.write(buf, 0, bytesRead);
			          bytesRead = fis.read(buf);
			      }
			      fis.close();
			      os.close();
			      if(!ftpClient.completePendingCommand()) {
			      	ftpClient.logout();
			      	ftpClient.disconnect();
			          System.err.println("File transfer failed.");
			          System.exit(1);
			      }
			      else {
			    	  System.out.println("File transfer success");
			    	  this.getServletContext().getRequestDispatcher( "/WEB-INF" + (String) session.getAttribute("pageCourante") ).forward( request, response );
			      }
					request.setAttribute("Rep", "ActionRepertoire");
					request.setAttribute("idProjet", pere.getId());
					this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("Rep") != null) {
				try {
					System.out.println("ajoutRep");
					String nomRep = request.getParameter("directory");
					Drive.getInstance().creerNouveauDossier(u, nomRep, chemin);
					FTPConnectAndLogin.getInstance().connect().makeDirectory(UrlDossierCourant + "/" + nomRep);
					request.setAttribute("Rep", "ActionRepertoire");
					request.setAttribute("idProjet", pere.getId());
					this.getServletContext().getRequestDispatcher( "/WEB-INF" + (String) session.getAttribute("pageCourante") ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("upload") != null) {
				
			}
		}
	}
}
