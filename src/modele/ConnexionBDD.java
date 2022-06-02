package modele;
import java.sql.*;
import java.sql.DriverManager;

public class ConnexionBDD {
	private static ConnexionBDD instance = null;
	private Connection cn = null;
	private ResultSet rs = null;
	
	private ConnexionBDD(String url, String login, String pwd) {
		if(cn == null) {
			try {
				cn = DriverManager.getConnection(url, login, pwd);
			}catch(SQLException e) {
				System.out.println("erreur d'accès à la BDD");
				System.exit(0);
			}
		}
	}
	
	public static ConnexionBDD getInstance(String url, String login, String pwd) {
		if (instance == null) {
			instance = new ConnexionBDD(url, login, pwd);
		}
		return instance;
	}
}
