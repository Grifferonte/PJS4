package Partage;

import java.util.List;

public interface Projet {
	public void put(utilisateur u);
	public void Partager(utilisateur u1, utilisateur u2);
	public String getNom();
	public int getId();
	public String getUrlServeur();
	public String getVisibilite();
}
