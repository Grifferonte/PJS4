package persistance;

import Partage.AbstractUtilisateur;

public class Client extends AbstractUtilisateur {
	
	/*
	 Les utilisateurs "Lambda" sont appel�s des clients qui utilisent nos services
	 */
	
	public Client(int id, String mail, String pseudo, int idStockage) {
		super(id,mail,pseudo,idStockage);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

}
