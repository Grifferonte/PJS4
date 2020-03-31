package Fichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.util.TrustManagerUtils;

public class OpérationFichier {
  public static void lireFichier(String fichier, JTextArea field) throws IOException{ 
	  //code pour lire un fichier depuis serveur ftp
	  URL myUrl = new URL("ftp://localhost:2121/README.txt");
      URLConnection yc = myUrl.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) 
          field.setText(inputLine);
      in.close();
  }
  
  public static void écrireFichier(String fichier, JTextArea field) throws IOException {
	  //code pour écrire sur un fichier sur serveur ftp, ne marche pas
	  URL myUrl = new URL("ftp://localhost:2121/texte/bonjour.txt");
      URLConnection yc = myUrl.openConnection();
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(yc.getOutputStream()));
      out.write(field.getText()); 
      out.close();
  }
  
  public static void UploadFichier() throws SocketException, IOException {
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