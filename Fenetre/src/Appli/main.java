package Appli;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Fenetre.Frame;
import Fenetre.Panneau;
import Fichier.OpérationFichier;

public class main {
	public static void main(String[] args) {
		JTextArea field = new JTextArea();
		field.setLineWrap(true);
		Frame fenetre = new Frame();
		//fenetre.setContentPane(pan);
		fenetre.add(field);
		fenetre.setVisible(true);
		try {
			OpérationFichier.lireFichier("hello.txt", field);
			//OpérationFichier.écrireFichier("hello.txt", field); -> ne marche pas
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
