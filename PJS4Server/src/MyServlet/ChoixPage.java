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

import LoginFTP.FTPConnectAndLogin;
import Partage.Drive;
import Partage.utilisateur;

/**
 * Servlet implementation class ChoixPage
 */
@WebServlet("/ChoixPage")
public class ChoixPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized(this) {
			HttpSession session=request.getSession();
			utilisateur u = (utilisateur) session.getAttribute("client");
			String cheminFichierServer = request.getParameter("UrlServeur");
			String cheminFichier = cheminFichierServer.substring(0, cheminFichierServer.length() -1);
			if (request.getParameter("ajouterDoc") != null) {
				try {
					String nomFichier = request.getParameter("nomFichier");
					Drive.getInstance().creerNouveauDoc(u, nomFichier, cheminFichier);
					//Ajouter la cr�ation sur le serveur
					this.getServletContext().getRequestDispatcher( "/WEB-INF/ListDocsPartages.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("ajouterDossier") != null) {
				try {
					String nomDossier = request.getParameter("nomFichier");
					Drive.getInstance().creerNouveauDoc(u, nomDossier, cheminFichier);
					//Ajouter la cr�ation sur le serveur
					this.getServletContext().getRequestDispatcher( "/WEB-INF/ListDocsPartages.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("RevenirDossierParent") != null) {
				try {
					/*String[] nouveauChemin1 = cheminFichier.split("/");
					if (nouveauChemin1[nouveauChemin1.length-2].equals(u.getPseudo())) {
						request.setAttribute("PasDossierParent", "Pas de dossier parent");
					}
					String nouveauChemin2 = new String();
					for (int i=0 ; i<nouveauChemin1.length-1; ++i ) {
						nouveauChemin2 += nouveauChemin1[i] + "/";
					}*/
					request.setAttribute("listeDocsPublics", Drive.getInstance().getTousLesDocumentsPublics(u));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/DocsPublics.jsp" ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("upload") != null) {
				FTPClient ftpClient = FTPConnectAndLogin.getInstance().connect();
				InputStream fis = new FileInputStream( request.getParameter("chemin") + "/" + request.getParameter("fichierUp"));
		        OutputStream os = ftpClient.storeFileStream("/classes/" /*Acompl�ter*/);

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
		      }
			}
		}
	}
}
