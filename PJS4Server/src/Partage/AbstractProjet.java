package Partage;

public abstract class AbstractProjet implements Projet{
	private int id;
	private String nom;
	private String cheminFTP;
	private String visibilite;

	/*
	 * Les deux attributs ci-dessous sont peut-�tre � remanier avec de l'h�ritage et les requ�tes associ�es �galement
	 * L'un des deux n'est peut �tre pas n�cessaire
	 */
	private String dateStockage;
	
	public AbstractProjet(int id, String nom, String dateStockage, String v, String chemin) {
		this.id = id;
		this.nom = nom;
		this.dateStockage = dateStockage;
		this.cheminFTP = chemin;
		this.visibilite = v;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCheminFTP() {
		return cheminFTP;
	}
	@Override
	public String getVisibilite() {
		return this.visibilite;
	}

}
