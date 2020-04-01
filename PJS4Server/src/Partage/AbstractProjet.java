package Partage;

public abstract class AbstractProjet implements Projet{
	private int id;
	private String nom;
	private String cheminFTP;

	/*
	 * Les deux attributs ci-dessous sont peut-être à remanier avec de l'héritage et les requêtes associées également
	 * L'un des deux n'est peut être pas nécessaire
	 */
	private String dateStockage;
	
	public AbstractProjet(int id, String nom, String dateStockage, String chemin) {
		this.id = id;
		this.nom = nom;
		this.dateStockage = dateStockage;
		this.cheminFTP = chemin;
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
	public void Partager(utilisateur u1, utilisateur u2) {
		Drive.getInstance().PartagerDoc(u1, u2, this.id);
		
	}

}
