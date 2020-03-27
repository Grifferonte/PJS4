package Appli;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Fenetre.Frame;
import Fenetre.Panneau;
import Fichier.OperationFichier;
import connexion.ConnexionFTP;

public class Appli {
	public static void main(String[] args) throws Exception {
		JTextArea field = new JTextArea();
		field.setLineWrap(true);
		Frame fenetre = new Frame();
		//fenetre.setContentPane(pan);
		fenetre.add(field);
		fenetre.setVisible(true);
		try {
			OperationFichier.lireFichier("hello.txt", field);
			//OperationFichier.ecrireFichier("hello.txt", field); //-> ne marche pas
			OperationFichier.upload();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
