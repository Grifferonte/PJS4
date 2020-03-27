package Fichier;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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

import connexion.ConnexionFTP;


public class OperationFichier{
	private static final int BUFFER_SIZE = 4096;
	
  public static void lireFichier(String fichier, JTextArea field) throws IOException{ 
	  //code pour lire un fichier depuis serveur ftp
	  URL myUrl = new URL("ftp://localhost:2121/testPierre/test.txt");
      URLConnection yc = myUrl.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) 
          field.setText(inputLine);
      in.close();
  }
  
  public static void upload(){
	 
	  
	  FTPClient ftp = ConnexionFTP.connexion();
	  
	  File firstLocalFile = new File("C:/Users/Pierre/Desktop/ecrire.txt");
	  
      String firstRemoteFile = "ecrire.txt";
      InputStream inputStream = null;
	try {
		inputStream = new FileInputStream(firstLocalFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

      System.out.println("Start uploading first file");
      boolean done = false;
	try {
		done = ftp.storeFile(firstRemoteFile, inputStream);
		inputStream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      if (done) {
          System.out.println("The first file is uploaded successfully.");
      }else {
    	  System.out.println("Ca marche pas");
      }
	  
	  
	}
  
  public static void ecrireFichier(String fichier, JTextArea field) throws IOException {
	  
      String text = "C:\\Users\\Pierre\\Desktop\\ecrire.txt";
	  String remoteFileName = "test2.txt";
	  FTPClient ftp = ConnexionFTP.connexion(); 

	  try {
		  	ByteArrayInputStream local = new ByteArrayInputStream(text.getBytes("UTF-8"));
		      if(!ftp.appendFile(remoteFileName, local)) {
		    	  System.out.println("putain");
		      }
		  } catch (IOException e) {
    		      e.printStackTrace();
		  }
      
      
  }
} 