package Fenetre;
import javax.swing.*;

public class Frame extends JFrame {
	public Frame() {
	    this.setTitle("Ma première fenêtre Java");
	    //Définit sa taille : 400 pixels de large et 100 pixels de haut
	    this.setSize(400, 100);
	    //Nous demandons maintenant à notre objet de se positionner au centre
	    this.setLocationRelativeTo(null);
	    //Termine le processus lorsqu'on clique sur la croix rouge
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	}
	
	public void placerFenetre(int x, int y) {
		this.setLocation(x, y);
	}
	
	public void EmpecherRedimensionnement() {
		this.setResizable(false);
	}
	
	public void GarderPremierPlan() {
		this.setAlwaysOnTop(true);
	}
}
