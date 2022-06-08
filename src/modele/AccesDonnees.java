package modele;

import java.util.ArrayList;
import java.util.Date;

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
		String sql = "select personnel.*, service.* from personnel join service on personnel.idservice = service.idservice";
		ArrayList<Personnel> lesPersonnels = new ArrayList<Personnel>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Service leService = new Service(Integer.parseInt(cn.field("service.idService").toString()), cn.field("service.nom").toString());
			Personnel unPersonnel = new Personnel(Integer.parseInt(cn.field("personnel.idpersonnel").toString()), cn.field("personnel.nom").toString(), cn.field("personnel.prenom").toString(), 
					cn.field("personnel.tel").toString(), cn.field("personnel.mail").toString(), leService);
			lesPersonnels.add(unPersonnel);
		}
		cn.close();
		return lesPersonnels;
	}
	
	public static ArrayList<Personnel> requeteSuppressionPersonnel(Personnel lePersonnel) {
		/*Supp personnel*/
		String sql = "delete from personnel where idpersonnel = ?";
		ArrayList<Object> lesParamsPersonnels = new ArrayList<Object>();
		lesParamsPersonnels.add(lePersonnel.getId());
		ArrayList<Personnel> lesPersonnels = new ArrayList<Personnel>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParamsPersonnels);
		
		/*Recup id motif des absence du personnel*/
		sql = "select idmotif from absence where idpersonnel = ?";
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
			sql = "delete from absence where idpersonnel = ?";
			cn = ConnexionBDD.getInstance(url, login, pwd);
			cn.reqUpdate(sql, lesParamsPersonnels);
		}
		
		cn.close();
		return lesPersonnels;
	}
	public static ArrayList<Service> requeteRecupService() {
		String sql = "select * from service";
		ArrayList<Service> lesServices = new ArrayList<Service>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Service leService = new Service(Integer.parseInt(cn.field("idService").toString()), cn.field("nom").toString());
			lesServices.add(leService);
		}
		cn.close();
		return lesServices;
	}
	public static ArrayList<Absence> requeteRecupAbsence(Personnel lePersonnel) {
		/*Récupérer Absence*/
		String sql = "select a.*, m.* from absence a join motif m on a.idmotif = m.idmotif where a.idpersonnel = ? order by a.datedebut, a.datefin";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(lePersonnel.getId());
		ArrayList<Absence> lesAbsences = new ArrayList<Absence>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, lesParams);
		while (cn.read()) {
			Motif leMotif = new Motif(Integer.parseInt(cn.field("m.idmotif").toString()), cn.field("m.libelle").toString());
			Absence uneAbsence = new Absence((Date) cn.field("a.datedebut"), (Date) cn.field("a.datefin"), lePersonnel, leMotif);
			lesAbsences.add(uneAbsence);
		}
		cn.close();
		return lesAbsences;
	}
	public static void requeteModifierPersonnel(ArrayList<String> nouvInformationsPersonnel, int idService, int idPersonnel) {
		String sql = "UPDATE personnel SET nom = ?, prenom = ?, tel = ?, mail = ?, idservice = ? WHERE idpersonnel = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		for(String param : nouvInformationsPersonnel) {
			lesParams.add(param);
		}
		lesParams.add(idService);
		lesParams.add(idPersonnel);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParams);
	}
	
	public static void requeteInsertPersonnel(ArrayList<String> informationsPersonnel, int idService) {
		String sql = "insert into personnel(nom, prenom, tel, mail, idservice) values(?, ?, ?, ?, ?)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		for(String param : informationsPersonnel) {
			lesParams.add(param);
		}
		lesParams.add(idService);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParams);
	}
	public static ArrayList<Motif> requeteRecupMotif() {
		String sql = "select * from motif";
		ArrayList<Motif> lesMotifs = new ArrayList<Motif>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Motif leMotif = new Motif(Integer.parseInt(cn.field("idmotif").toString()), cn.field("libelle").toString());
			lesMotifs.add(leMotif);
		}
		cn.close();
		return lesMotifs;
	}
	public static void requeteModifierAbsence(ArrayList<Object> nouvInformationsPersonnel, Absence uneAbsence) {
		String sql = "UPDATE absence SET datedebut = ?, datefin = ?, idmotif = ? WHERE idpersonnel = ? AND idmotif = ? AND datedebut = ? AND datefin = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		for(Object param : nouvInformationsPersonnel) {
			lesParams.add(param);
		}
		lesParams.add(uneAbsence.getPersonnel().getId());
		lesParams.add(uneAbsence.getMotif().getIdMotif());
		lesParams.add(uneAbsence.getDateDebut());
		lesParams.add(uneAbsence.getDateFin());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParams);
	}
	public static void requetesuppressionAbsence(Absence uneAbsence) {
		/*Supp Absence*/
		String sql = "delete from absence where idpersonnel = ? AND idmotif = ? AND datedebut = ? AND datefin = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(uneAbsence.getPersonnel().getId());
		lesParams.add(uneAbsence.getMotif().getIdMotif());
		lesParams.add(uneAbsence.getDateDebut());
		lesParams.add(uneAbsence.getDateFin());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParams);
		cn.close();
	}
}
