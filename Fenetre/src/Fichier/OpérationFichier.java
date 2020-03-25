package Fichier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OpérationFichier {
  public static void lireFichier(String fichier, JTextArea field) throws IOException{
    BufferedReader lecteurAvecBuffer = null;
    String ligne;

    try {
    	lecteurAvecBuffer = new BufferedReader(new FileReader(fichier));
    }
    catch(FileNotFoundException exc){
    	System.err.println("Erreur d'ouverture");
    }
    while ((ligne = lecteurAvecBuffer.readLine()) != null) {
    	field.setText(ligne);
    }
    lecteurAvecBuffer.close();
  }
} 