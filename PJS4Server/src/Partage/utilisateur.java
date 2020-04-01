package Partage;

import java.util.List;

public interface utilisateur {
	public int getId();
	public String getLogin();
	public void CreerProjet();
	public boolean isAdmin();
	public String getPseudo();
}
