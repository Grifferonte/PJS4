package persistance;

public class Admin extends AbstractUtilisateur {
	
	public Admin(int id, String mail, String pseudo, int idStockage) {
		super(id,mail,pseudo,idStockage);
	}

	@Override
	public boolean isAdmin() {
		return true;
	}

}
