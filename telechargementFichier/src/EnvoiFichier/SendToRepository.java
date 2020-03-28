package EnvoiFichier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendToRepository extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serveur = "adresseDuServeur";
		  int port = 21;
		  String user = "nomUtilisateur";
		  String password = "votreMotdePasse";

		  FTPClient ftpClient = new FTPClient();
		  try {

		   ftpClient.connect(serveur, port);
		   ftpClient.login(user, password );
		   ftpClient.enterLocalPassiveMode();

		   ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		   // Approche 1: upload d'un fichier en utilisant InputStream
		   File file = new File("C:/plugins et styles.txt");

		   String chemin = "plugins et styles.txt";
		   InputStream inputStream = new FileInputStream(file);

		   System.out.println("Début de l'upload");
		   //résultat de l'upload
		   boolean res = ftpClient.storeFile(chemin, inputStream);
		   //fermet le flut de lecture
		   inputStream.close();
		   
		   if (res==true) {
		     System.out.println("Le fichier "+chemin+" a été transféré avec succès");
		   }

	}
}
