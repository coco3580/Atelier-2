package modele;

import java.util.ArrayList;

public abstract class AccesDonnees {
	private static String url = "jdbc:mysql://localhost/atelier2";
	private static String login = "root";
	private static String pwd = "";
	
	public static Responsable testConnexion(String loginResponsable, String pwdResponsable) {
		String sql = "select login from responsable where login = ? and pwd = SHA2(?, 256)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(loginResponsable);
		lesParams.add(pwdResponsable);
		
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, lesParams);
		
		cn.read();
		Responsable unResponsable = new Responsable();
		unResponsable.setLogin((String)cn.field("login"));
		cn.close();
		
		return unResponsable;
	}
}
