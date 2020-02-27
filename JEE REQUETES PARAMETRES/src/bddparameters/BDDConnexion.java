package bddparameters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDDConnexion {
	
	public static Connection getConnexionBDD() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pjs4", "root", null);
		
		return connexion;
	}
}
