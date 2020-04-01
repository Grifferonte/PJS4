package MyServlet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import Fichier.OperationFichier;
import LoginFTP.FTPConnectAndLogin;
import Partage.Drive;
import Partage.utilisateur;

/**
 * Servlet implementation class choixActionProjet
 */

@WebServlet("/choixAction")
public class choixActionProjet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko
	private static final int TAILLE_TAMPON = 10000;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("Partager") != null) {
			//Code
		}
		else if (request.getParameter("telecharger") != null) {
			//Tester si le projet est un r�pertoire ou un fichier
			//Code pour ouvrir document (si txt l'ouvrir dans une fenetre java)
			URL myUrl = new URL("ftp://localhost:2121/" /*A compl�ter*/);
		      URLConnection yc = myUrl.openConnection();
		      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		      String inputLine;
		      String contenu = null;
		      while ((inputLine = in.readLine()) != null) 
		          contenu += inputLine;
		      in.close();
		      
		      String[] infosUrl = ("ftp://localhost:2121/" + request.getParameter("fichierDown")).split("/");// A modifier
		      String NomFichier = infosUrl[infosUrl.length-1];
		      BufferedWriter out = new BufferedWriter(new FileWriter(NomFichier));
		      File fichierTel = new File(NomFichier);
		      out.write(contenu); 
		      out.close();
			
			
			/* Initialise la r�ponse HTTP */
			response.reset();
			response.setBufferSize( DEFAULT_BUFFER_SIZE );
			response.setHeader( "Content-Length", String.valueOf( fichierTel.length() ) );
			response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichierTel.getName() + "\"" );
			
			/* Pr�pare les flux */
			BufferedInputStream entree = null;
			BufferedOutputStream sortie = null;
			try {
			    /* Ouvre les flux */
			    entree = new BufferedInputStream( new FileInputStream( fichierTel ), TAILLE_TAMPON );
			    sortie = new BufferedOutputStream( response.getOutputStream(), TAILLE_TAMPON );
			 
			    /* Lit le fichier et �crit son contenu dans la r�ponse HTTP */
			    byte[] tampon = new byte[TAILLE_TAMPON];
			    int longueur;
			    while ( ( longueur= entree.read( tampon ) ) > 0 ) {
			        sortie.write( tampon, 0, longueur );
			    }
			} finally {
			    try {
			        sortie.close();
			    } catch ( IOException ignore ) {
			    }
			    try {
			        entree.close();
			    } catch ( IOException ignore ) {
			    }
			}
		}
		else if (request.getParameter("supprimer") != null) {
			int numDoc = Integer.parseInt(request.getParameter("numDoc"));
			Drive.getInstance().supprimerDoc((utilisateur) session.getAttribute("client"), numDoc);
		}
		else if (request.getParameter("ouvrir") != null) {
			String nomUtilisateur = (String) session.getAttribute("pseudo");
			String nomFichier = request.getParameter("nomFichier");
			JTextArea field = new JTextArea();
			field.setLineWrap(true);
			JFrame fenetre = new JFrame();
			JButton bouton = new JButton("Enregistrer");
			bouton.addMouseListener(new BoutonEnregListener(nomUtilisateur, nomFichier, field));
			fenetre.add(field);
			fenetre.add(bouton);
			fenetre.setVisible(true);
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
	
	private final class BoutonEnregListener implements MouseListener{
		private String nomFichier;
		private String nomUt;
		private JTextArea field;
		
		public BoutonEnregListener(String n, String nom, JTextArea field) {
			this.nomFichier = n;
			this.nomUt = nom;
			this.field = field;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				OperationFichier.ecrireFichier(nomUt, nomFichier, field);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	public void trouverFileOrDirectory(String projet) {
		
	}
}
