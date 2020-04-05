package MyServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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
@WebServlet("/choixActionPage")
public class ChoixPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized(this) {
			HttpSession session=request.getSession();
			utilisateur u = (utilisateur) session.getAttribute("client");
			String cheminServer = null;
			String UrlDossierCourant = null;
			Projet pere = null;
			if (request.getParameter("UrlServeur") != null){
				cheminServer = request.getParameter("UrlServeur");
				String[] cheminDecoupe = cheminServer.split("/");
				UrlDossierCourant = new String();
				for (int i=0; i<cheminDecoupe.length-1; ++i) {
					UrlDossierCourant += cheminDecoupe[i] + "/";
				}
				String chemin = cheminServer.substring(0, cheminServer.length() -1);
			}
			if (request.getParameter("idProjetPere") != null){
				pere = Drive.getInstance().getDocumentById(Integer.parseInt(request.getParameter("idProjetPere")));
			}
			if (request.getParameter("ajouterFichier") != null) {
				try {
					String cheminFichier = request.getParameter("cheminFichier");
					String nomFichier = request.getParameter("nomFichier");
					String Url = UrlDossierCourant + nomFichier;
					int publicOuPrive = Integer.parseInt(request.getParameter("privOuPubl"));
					int idProjetPere = Integer.parseInt(request.getParameter("idProjetPere"));
					Drive.getInstance().creerNouveauDoc(u, nomFichier, idProjetPere, Url ,publicOuPrive, (String) session.getAttribute("visibilite"));
					
					
					//Drive.getInstance().creerNouveauDoc(u, nomFichier, chemin);
					
					
					FTPClient ftpClient = FTPConnectAndLogin.getInstance().connect();
					System.out.println(cheminFichier);
					System.out.println(nomFichier);
					InputStream fis = new FileInputStream(cheminFichier);
			        OutputStream os = ftpClient.storeFileStream(UrlDossierCourant + nomFichier);
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
					String cheminFichier = request.getParameter("cheminFichier");
					int publicOuPrive = Integer.parseInt(request.getParameter("privOuPubl"));
					int idProjetPere = Integer.parseInt(request.getParameter("idProjetPere"));
					
					
					Drive.getInstance().creerNouveauDossier(u, nomRep, cheminFichier, idProjetPere, publicOuPrive);
					FTPConnectAndLogin.getInstance().connect().makeDirectory(UrlDossierCourant + "/" + nomRep);
					
					request.setAttribute("Rep", "ActionRepertoire");
					request.setAttribute("idProjet", pere.getId());
					this.getServletContext().getRequestDispatcher( "/WEB-INF" + (String) session.getAttribute("pageCourante") ).forward( request, response );
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else if (request.getParameter("recherche") != null) {
				String motsClefs = request.getParameter("motsClefs");
				request.setAttribute("motsClefs", motsClefs);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Search.jsp" ).forward( request, response );
			}
			else if (request.getParameter("changerPseudo") != null) {
				String NouveauPseudo = request.getParameter("NewPseudo");
				Drive.getInstance().changerPseudo((utilisateur) session.getAttribute("client"), NouveauPseudo);
				
				request.setAttribute("Rep", "ActionRep");
				request.setAttribute("idProjet", pere.getId());
				this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
			}
			else if (request.getParameter("changerMotDePasse") != null) {
				String NouveauMdp = request.getParameter("NewMdp");
				Drive.getInstance().changerMotDePasse((utilisateur) session.getAttribute("client"), NouveauMdp);
				
				request.setAttribute("Rep", "ActionRep");
				request.setAttribute("idProjet", pere.getId());
				this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
			}
			
			else if (request.getParameter("DocsPublicsSuiviUt") != null) {
				int idUtSuivi = Integer.parseInt(request.getParameter("pseudoUtSuivi"));
				request.setAttribute("idPseudSuivi", idUtSuivi);
				request.setAttribute("DocsPublicsSuiviUt", "OK");
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Search.jsp").forward( request, response );
			}
			
			else if (request.getParameter("suivre") != null) {
				int idUtSuivi = Integer.parseInt(request.getParameter("pseudoUtSuivi"));
				try {
					utilisateur UtSuivi = Drive.getInstance().getUserById(idUtSuivi);
					request.setAttribute("MessageConfirmation", "Vous suivez d�sormais cette personne");
					Drive.getInstance().suivreUtilisateur((utilisateur) session.getAttribute("client"), UtSuivi );
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Search.jsp").forward( request, response );
			}
			else if (request.getParameter("Accueil") != null) {
			
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp").forward( request, response );
			}
		}
	}
}
