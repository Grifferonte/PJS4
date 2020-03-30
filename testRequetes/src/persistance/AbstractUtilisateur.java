package persistance;

public abstract class AbstractUtilisateur implements utilisateur {
	
	private int id;
	private String login;
	private String pseudo;
	private int idStockage;

	public AbstractUtilisateur(int id, String mail, String pseudo, int idStockage) {
		this.id = id;
		this.login = mail; // dans la base de donn�es, le login est l'adresse mail
		this.pseudo = pseudo;
		this.idStockage = idStockage;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public void CreerProjet() {
	}
	

}
