package accesBDD;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JDBC {
	private static String url = "jdbc:mysql://localhost:3306/test2pjs4?autoReconnect=true&useSSL=false";
	private static String utilisateur = "root";
	private static String mdp = "";
	private static Connection connection = null;
	public static void connexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			System.err.println(e + "c");
		}
		try {
			connection = (Connection) DriverManager.getConnection(url, utilisateur, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}
}
