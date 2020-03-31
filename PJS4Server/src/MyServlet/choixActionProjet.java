package MyServlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Partage.Drive;

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
		if (request.getParameter("Partager") != null) {
			//Code
		}
		else if (request.getParameter("telecharger") != null) {
			//Tester si le projet est un rï¿½pertoire ou un fichier
			//Code pour ouvrir document (si txt l'ouvrir dans une fenetre java)
			URL myUrl = new URL("ftp://localhost:2121/README.txt");
		      URLConnection yc = myUrl.openConnection();
		      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		      String inputLine;
		      String contenu = null;
		      while ((inputLine = in.readLine()) != null) 
		          contenu += inputLine;
		      in.close();
		      
		      String[] infosUrl = "ftp://localhost:2121/README.txt".split("/");
		      String NomFichier = infosUrl[infosUrl.length-1];
		      BufferedWriter out = new BufferedWriter(new FileWriter(NomFichier));
		      File fichierTel = new File(NomFichier);
		      out.write(contenu); 
		      out.close();
			
			
			/* Initialise la réponse HTTP */
			response.reset();
			response.setBufferSize( DEFAULT_BUFFER_SIZE );
			response.setHeader( "Content-Length", String.valueOf( fichierTel.length() ) );
			response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichierTel.getName() + "\"" );
			
			/* Prépare les flux */
			BufferedInputStream entree = null;
			BufferedOutputStream sortie = null;
			try {
			    /* Ouvre les flux */
			    entree = new BufferedInputStream( new FileInputStream( fichierTel ), TAILLE_TAMPON );
			    sortie = new BufferedOutputStream( response.getOutputStream(), TAILLE_TAMPON );
			 
			    /* Lit le fichier et écrit son contenu dans la réponse HTTP */
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
			Drive.getInstance().supprimerDoc(numDoc);
		}
		else if (request.getParameter("ouvrir") != null) {
			
		}
		else if (request.getParameter("upload") != null) {
			
		}
	}
}
