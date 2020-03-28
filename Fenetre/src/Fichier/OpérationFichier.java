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

import LoginFTP.FTPConnectAndLoginDemo;

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
} 