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
import java.util.List;

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
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import Fichier.OperationFichier;
import LoginFTP.FTPConnectAndLogin;
import Partage.Drive;
import Partage.Projet;
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
			try {
				utilisateur user1 = Drive.getInstance().getUserByMail(request.getParameter("PseudoUser1"));
				utilisateur user2 = Drive.getInstance().getUserByMail(request.getParameter("PseudoUser2"));
				Drive.getInstance().PartagerDoc(user1, user2, Integer.parseInt(request.getParameter("idDoc")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (request.getParameter("Telecharger") != null) {
			String cheminFichierServer = request.getParameter("UrlServeur");
			String cheminFichier = cheminFichierServer.substring(0, cheminFichierServer.length() -1);
			System.out.println(cheminFichier);
			//Tester si le projet est un rï¿½pertoire ou un fichier
			//Code pour ouvrir document (si txt l'ouvrir dans une fenetre java)
			URL myUrl = new URL("ftp://localhost:2121" + cheminFichier);
		      URLConnection yc = myUrl.openConnection();
		      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		      String inputLine;
		      String contenu = null;
		      while ((inputLine = in.readLine()) != null) 
		          contenu += inputLine;
		      in.close();
		      
		      String[] infosUrl = cheminFichier.split("/");// A modifier
		      String NomFichier = infosUrl[infosUrl.length-1];
		      BufferedWriter out = new BufferedWriter(new FileWriter(NomFichier));
		      File fichierTel = new File(NomFichier);
		      out.write(contenu); 
		      out.close();
			
			
			/* Initialise la rï¿½ponse HTTP */
			response.reset();
			response.setBufferSize( DEFAULT_BUFFER_SIZE );
			response.setHeader( "Content-Length", String.valueOf( fichierTel.length() ) );
			response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichierTel.getName() + "\"" );
			
			/* Prï¿½pare les flux */
			BufferedInputStream entree = null;
			BufferedOutputStream sortie = null;
			try {
			    /* Ouvre les flux */
			    entree = new BufferedInputStream( new FileInputStream( fichierTel ), TAILLE_TAMPON );
			    sortie = new BufferedOutputStream( response.getOutputStream(), TAILLE_TAMPON );
			 
			    /* Lit le fichier et ï¿½crit son contenu dans la rï¿½ponse HTTP */
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
		else if (request.getParameter("Supprimer") != null) {
			String cheminFichierServer = request.getParameter("UrlServeur");
			String cheminFichier = cheminFichierServer.substring(0, cheminFichierServer.length() -1);
			int numDoc = Integer.parseInt(request.getParameter("numDoc"));
			Drive.getInstance().supprimerDoc((utilisateur) session.getAttribute("client"), numDoc);
			FTPConnectAndLogin.getInstance().connect().deleteFile(cheminFichier);
		}
		else if (request.getParameter("Ouvrir") != null) {
			String nomUtilisateur = (String) session.getAttribute("pseudo");
			String cheminFichierServer = request.getParameter("UrlServeur");
			String cheminFichier = cheminFichierServer.substring(0, cheminFichierServer.length() -1);
			System.out.println(cheminFichier);
			
			FTPFile[] listDocs = FTPConnectAndLogin.getInstance().connect().listDirectories(cheminFichier);
			
			if (listDocs != null) {
				request.setAttribute("ListDocsInside", listDocs);
			}
			else {
				JTextArea field = new JTextArea();
				field.setLineWrap(true);
				JFrame fenetre = new JFrame();
				fenetre.setTitle("Ma première fenêtre Java");
			    //Définit sa taille : 400 pixels de large et 100 pixels de haut
			    fenetre.setSize(400, 100);
			    //Nous demandons maintenant à notre objet de se positionner au centre
			    fenetre.setLocationRelativeTo(null);
				fenetre.add(field);
				fenetre.setVisible(true);
				JButton bouton = new JButton("Enregistrer");
				fenetre.add(bouton);
				OperationFichier.lireFichier(cheminFichier, field);
				bouton.addMouseListener(new BoutonEnregListener(cheminFichier, field));
			}
		}
	}
	
	private final class BoutonEnregListener implements MouseListener{
		private String cheminFichier;
		private JTextArea field;
		
		public BoutonEnregListener(String n, JTextArea field) {
			this.cheminFichier = n;
			this.field = field;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				OperationFichier.ecrireFichier(cheminFichier, field);
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
