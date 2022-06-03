package modele;

import java.util.ArrayList;

public abstract class AccesDonnees {
	private static String url = "jdbc:mysql://localhost/atelier2";
	private static String login = "root";
	private static String pwd = "";
	
	public static Responsable requeteTestConnexion(String loginResponsable, String pwdResponsable) {
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
	
	public static ArrayList<Personnel> requeteRecupPersonnel() {
		String sql = "select personnel.*, service.nom from personnel join service on personnel.idservice = service.idservice";
		ArrayList<Personnel> lesPersonnels = new ArrayList<Personnel>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Service leService = new Service(cn.field("service.nom").toString());
			Personnel unPersonnel = new Personnel(Integer.parseInt(cn.field("personnel.idpersonnelle").toString()), cn.field("personnel.nom").toString(), cn.field("personnel.prenom").toString(), 
					cn.field("personnel.tel").toString(), cn.field("personnel.mail").toString(), leService);
			lesPersonnels.add(unPersonnel);
		}
		cn.close();
		return lesPersonnels;
	}
	
	public static ArrayList<Personnel> requeteSuppressionPersonnel(Personnel lePersonnel) {
		/*Supp personnel*/
		String sql = "delete from personnel where idpersonnelle = ?";
		ArrayList<Object> lesParamsPersonnels = new ArrayList<Object>();
		lesParamsPersonnels.add(lePersonnel.getId());
		ArrayList<Personnel> lesPersonnels = new ArrayList<Personnel>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParamsPersonnels);
		
		/*Recup id motif des absence du personnel*/
		sql = "select idmotif from absence where idpersonnelle = ?";
		cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, lesParamsPersonnels);
		
		ArrayList<Object> lesParamsMotifs = new ArrayList<Object>();
		while (cn.read()) {
			lesParamsMotifs.add(cn.field("idmotif").toString());
		}
		
		/*Supp motif*/
		if (!lesParamsMotifs.isEmpty()) {
			sql = "delete from motif where idmotif = ?";
			cn = ConnexionBDD.getInstance(url, login, pwd);
			cn.reqUpdate(sql, lesParamsMotifs);
			
			/*Supp absence*/
			sql = "delete from absence where idpersonnelle = ?";
			cn = ConnexionBDD.getInstance(url, login, pwd);
			cn.reqUpdate(sql, lesParamsPersonnels);
		}
		
		cn.close();
		return lesPersonnels;
	}
}
