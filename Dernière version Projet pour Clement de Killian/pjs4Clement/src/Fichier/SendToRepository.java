package Fichier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class SendToRepository extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TAILLE_TAMPON = 10000;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String server = "localhost";
	      int port = 2121;
	      String user = "anonymous";
	      String pass = "me@nowhere.com";
	      FTPClient ftpClient = new FTPClient();
	      ftpClient.connect(server, port);
	      //showServerReply(ftpClient);
	      int replyCode = ftpClient.getReplyCode();
	      if (!FTPReply.isPositiveCompletion(replyCode)) {
	          System.out.println("Operation failed. Server reply code: " + replyCode);
	          return;
	      }
	      boolean success = ftpClient.login(user, pass);
	      //showServerReply(ftpClient);
	      if (!success) {
	          System.out.println("Could not login to the server");
	          return;
	      }
			InputStream fis = new FileInputStream("C:/fichiers/test.txt");
	        OutputStream os = ftpClient.storeFileStream("test.txt");

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
