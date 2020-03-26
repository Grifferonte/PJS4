package Fichier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	 /* FTPClient f = new FTPClient();
	  System.setProperty(FTPClient.FTP_SYSTEM_TYPE_DEFAULT, FTPClientConfig.SYST_UNIX);
	  f.connect("localhost", 2121);
      f.login("admin", "clembat69");
	  FTPFile[] files = f.listDirectories("./res/home/");
	  System.out.println(files.length);
	  for (FTPFile fichiers : files) {
		  System.out.println("rr");
	      System.out.println(fichiers.getName());
	      System.out.println(fichiers.getSize());
    }*/
    
    BufferedReader lecteurAvecBuffer = null;
	try {
  		lecteurAvecBuffer = new BufferedReader(new FileReader("hello.txt"));
    }
    catch(FileNotFoundException exc){
    	System.err.println("Erreur d'ouverture");
    }
    String ligne;
	while ((ligne = lecteurAvecBuffer.readLine()) != null) {
    	field.setText(ligne);
    }
    lecteurAvecBuffer.close();
  }
} 