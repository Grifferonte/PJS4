package MyServlet;

import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import EnvoiMail.ConfigMail;
import Fichier.OperationFichier;
import LoginFTP.FTPConnectAndLogin;
import Partage.Drive;
import Partage.Projet;
import Partage.utilisateur;

/**
 * Servlet implementation class choixActionProjet
 */

@WebServlet("/choixActionProjet")
public class choixActionProjet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko
	private static final int TAILLE_TAMPON = 10000;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idProjetPere = request.getParameter("idProjetPere");
		Projet pere = Drive.getInstance().getDocumentById(Integer.parseInt(idProjetPere));
		
		String cheminServer = request.getParameter("UrlServeur");
		String[] cheminDecoupe = cheminServer.split("/");
		String UrlDossierCourant = new String();
		for (int i=0; i<cheminDecoupe.length-1; ++i) {
			UrlDossierCourant += cheminDecoupe[i] + "/";
		}
		
		if (request.getParameter("Partager") != null) {
			try {
				utilisateur user1 = Drive.getInstance().getUserByMail(((utilisateur) session.getAttribute("client")).getLogin());
				utilisateur user2 = Drive.getInstance().getUserByMail(request.getParameter("PseudoUser2"));
				Drive.getInstance().PartagerDoc(user1, user2, Integer.parseInt(request.getParameter("idDoc")));
				
				String BodyMail = user1.getPseudo() + "vous a partagé le projet" + Drive.getInstance().getDocumentById(Integer.parseInt(request.getParameter("idProjet"))).getNom();
				ConfigMail mail = new ConfigMail("Partage de documents", BodyMail, user1.getLogin() );
				mail.sendMailGmail();
				
				request.setAttribute("idProjet", pere.getId());
			    request.setAttribute("Rep", "ActionRep");
				this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (request.getParameter("Telecharger") != null) {
			String cheminFichier = Drive.getInstance().getDocumentById(Integer.parseInt(request.getParameter("idProjet"))).getUrlServeur();
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
			    
			    request.setAttribute("idProjet", pere.getId());
			    request.setAttribute("Rep", "ActionRep");
			    this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
			}
		}
		else if (request.getParameter("Supprimer") != null) {
			String cheminFichierServer = request.getParameter("UrlServeur");
			String cheminFichier = cheminFichierServer.substring(0, cheminFichierServer.length() -1);
			int numDoc = Integer.parseInt(request.getParameter("numDoc"));
			Drive.getInstance().supprimerDoc((utilisateur) session.getAttribute("client"), numDoc);
			FTPConnectAndLogin.getInstance().connect().deleteFile(cheminFichier);
			
			request.setAttribute("Rep", "ActionRep");
			request.setAttribute("idProjet", pere.getId());
			
		    this.getServletContext().getRequestDispatcher( "/WEB-INF" + (String) session.getAttribute("pageCourante") ).forward( request, response );
		}
		else if (request.getParameter("Ouvrir") != null) {
			String cheminFichier = Drive.getInstance().getDocumentById(Integer.parseInt(request.getParameter("idProjet"))).getUrlServeur();
			//String cheminFichier = cheminFichierServer.substring(0, cheminFichierServer.length() -1);
			JTextArea field = new JTextArea();
			field.setLineWrap(true);
			JButton valider = new JButton("valider");
			JPanel formulaire = new JPanel();
			formulaire.setLayout(new GridLayout(1, 1));
			formulaire.add(field);
			valider.setPreferredSize(new Dimension(250, 100));
			formulaire.add(valider);
			JFrame fenetre = new JFrame("dimensionnement bouton");
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetre.setContentPane(formulaire);
			fenetre.pack();
			//fenetre.setLocationRelativeTo(null);
			fenetre.setVisible(true);
			
			OperationFichier.lireFichier("", field);
			valider.addMouseListener(new BoutonEnregListener(cheminFichier, field));
			
			request.setAttribute("Rep", "ActionRep");
			request.setAttribute("idProjet", pere.getId());
			this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
		}
		else if (request.getParameter("OuvrirRep") != null) {
			int idProjet = Integer.parseInt(request.getParameter("idProjet"));
			
			request.setAttribute("idProjet", idProjet);
			request.setAttribute("Rep", "ActionRep");
			this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
		}
		else if (request.getParameter("RenommerDoc") != null) {
			String NouveauNom = request.getParameter("NewName");
			int idProjet = Integer.parseInt(request.getParameter("idProjet"));
			Projet projetActuel = Drive.getInstance().getDocumentById(idProjet);
			Drive.getInstance().renommerDocument((utilisateur) session.getAttribute("client"), idProjet, NouveauNom);
			FTPConnectAndLogin.getInstance().connect().rename(projetActuel.getUrlServeur(), UrlDossierCourant + "/" + NouveauNom);
			
			request.setAttribute("idProjet", pere.getId());
			request.setAttribute("Rep", "ActionRep");
			this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
		}
		else if (request.getParameter("ChangerVisibiliteDoc") != null) {
			String NouvelleVisibilite = request.getParameter("NewVisibility");
			int idDocument = Integer.parseInt(request.getParameter("idProjet"));
			Drive.getInstance().updateVisibility(idDocument, NouvelleVisibilite);
			
			request.setAttribute("idProjet", pere.getId());
			request.setAttribute("Rep", "ActionRep");
			this.getServletContext().getRequestDispatcher( "/WEB-INF"+(String) session.getAttribute("pageCourante") ).forward( request, response );
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
}
