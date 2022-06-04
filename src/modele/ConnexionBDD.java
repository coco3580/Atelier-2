package modele;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	public void reqUpdate(String req, ArrayList<Object> lesParams) {
		if(cn != null) {
			try {
				PreparedStatement pst = cn.prepareStatement(req);
				if(lesParams != null) {
					int k=1;
					for (Object param : lesParams) {
						pst.setObject(k++, param);
					}
				}
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void reqSelect(String req, ArrayList<Object> lesParams) {
		if(cn != null) {
			try {
				PreparedStatement pst = cn.prepareStatement(req);
				if(lesParams != null) {
					int k=1;
					for (Object param : lesParams) {
						pst.setObject(k++, param);
					}
				}
				rs = pst.executeQuery();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public boolean read() {
		if(rs != null) {
			try {
				return rs.next();
			}catch(SQLException e) {
				
			}
		}
		return false;
	}
	
	public Object field(String nameField) {
		if(rs == null) {
			return null;
		}
		try {
			return rs.getObject(nameField);
		}catch(SQLException e) {
			return null;
		}
	}
	public void close() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				rs = null;
			}
		}
	}
}
