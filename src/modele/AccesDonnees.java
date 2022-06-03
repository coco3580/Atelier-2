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
	
	public static ArrayList<Personnel> recupPersonnel() {
		String sql = "select personnel.*, service.nom from personnel join service on personnel.idservice = service.idservice";
		ArrayList<Personnel> lesPersonnels = new ArrayList<Personnel>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Service leService = new Service(cn.field("service.nom").toString());
			Personnel unPersonnel = new Personnel(cn.field("personnel.nom").toString(), cn.field("personnel.prenom").toString(), 
					cn.field("personnel.tel").toString(), cn.field("personnel.mail").toString(), leService);
			lesPersonnels.add(unPersonnel);
		}
		cn.close();
		return lesPersonnels;
	}
}
