package Appli;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Fenetre.Frame;
import Fenetre.Panneau;
import Fichier.Op�rationFichier;

public class main {
	public static void main(String[] args) {
		JTextArea field = new JTextArea();
		field.setLineWrap(true);
		Frame fenetre = new Frame();
		//fenetre.setContentPane(pan);
		fenetre.add(field);
		fenetre.setVisible(true);
		try {
			Op�rationFichier.lireFichier("hello.txt", field);
			//Op�rationFichier.�crireFichier("hello.txt", field); -> ne marche pas
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
