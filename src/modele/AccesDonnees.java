package modele;

import java.util.ArrayList;
import java.util.Date;

import java.lang.NullPointerException;

import javax.swing.JLabel;

/**
 * @author CorentinAdmin
 * Class requetage avec la bdd
 */
public abstract class AccesDonnees {
	private static String url = "jdbc:mysql://localhost/atelier2";
	private static String login = "root";
	private static String pwd = "";
	
	/**
	 * @param loginResponsable Nom d'utilisateur du responsable inséré.
	 * @param pwdResponsable Mot de passe du responsable inséré.
	 * @return leResponsable Classe Responsable.
	 */
	public static Responsable requeteConnexion(String loginResponsable, String pwdResponsable) {
		String sql = "select login from responsable where login = ? and pwd = SHA2(?, 256)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(loginResponsable);
		lesParams.add(pwdResponsable);
		
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, lesParams);
		cn.read();
		Responsable leResponsable;
		try {
			leResponsable = new Responsable(cn.field("login").toString());
		} catch(NullPointerException e) {
			leResponsable = new Responsable(null);
		}
		cn.close();
		return leResponsable;
	}
	
	/*getters*/
	
	/**
	 * @return liste lesServices
	 */
	public static ArrayList<Service> requeteGetService() {
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
	
	/**
	 * @param lePersonnel Personnel associé aux absences.
	 * @return liste lesAbsences Toutes les absences liées au personnel.
	 */
	public static ArrayList<Absence> requeteGetAbsence(Personnel lePersonnel) {
		String sql = "select a.*, m.* from absence a join motif m on a.idmotif = m.idmotif where a.idpersonnel = ? ORDER BY `a`.`datefin` DESC";
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
	
	
	/**
	 * @return liste lesPersonnels
	 */
	public static ArrayList<Personnel> requeteGetPersonnel() {
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
	
	
	/**
	 * @return liste lesMotifs
	 */
	public static ArrayList<Motif> requeteGetMotif() {
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
	
	/*setters*/
	
	/**
	 * Modifie les information du personnel dans la bdd.
	 * @param nouvInformationsPersonnel Liste des nouvelles informations du personnel.
	 * @param idPersonnel Id du personnel.
	 */
	public static void requeteSetPersonnel(ArrayList<Object> nouvInformationsPersonnel, int idPersonnel) {
		String sql = "UPDATE personnel SET nom = ?, prenom = ?, tel = ?, mail = ?, idservice = ? WHERE idpersonnel = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		for(Object param : nouvInformationsPersonnel) {
			lesParams.add(param);
		}
		lesParams.add(idPersonnel);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * @param nouvInformationsPersonnel Nouvelles informations d'une absence.
	 * @param uneAbsence Absence lié aux nouvelles informations.
	 */
	public static void requeteSetAbsence(ArrayList<Object> nouvInformationsPersonnel, Absence uneAbsence) {
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
	
	/*suppression*/
	
	/**
	 * @param lePersonnel Personnel supprimé dans la bdd.
	 */
	public static void requeteSupprimerPersonnel(Personnel lePersonnel) {
		/*Delete personnel*/
		String sql = "delete from personnel where idpersonnel = ?";
		ArrayList<Object> lesParamsPersonnels = new ArrayList<Object>();
		lesParamsPersonnels.add(lePersonnel.getId());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParamsPersonnels);
		
		/*Get id motif absence, personnel*/
		sql = "select idmotif from absence where idpersonnel = ?";
		cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqSelect(sql, lesParamsPersonnels);
		
		ArrayList<Object> lesParamsMotifs = new ArrayList<Object>();
		while (cn.read()) {
			lesParamsMotifs.add(cn.field("idmotif").toString());
		}
		
		/*Delete motif*/
		if (!lesParamsMotifs.isEmpty()) {
			sql = "delete from motif where idmotif = ?";
			cn = ConnexionBDD.getInstance(url, login, pwd);
			cn.reqUpdate(sql, lesParamsMotifs);
			
			/*Delete absence*/
			sql = "delete from absence where idpersonnel = ?";
			cn = ConnexionBDD.getInstance(url, login, pwd);
			cn.reqUpdate(sql, lesParamsPersonnels);
		}
		
		cn.close();
	}
	
	/**
	 * @param uneAbsence Absence supprimé dans la bdd.
	 */
	public static void requeteSupprimerAbsence(Absence uneAbsence) {
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
	
	/*insertion*/
	
	/**
	 * @param informationsPersonnel Liste des informations du personnel inséré.
	 * @param idService Id du service associé au personnel.
	 */
	public static void requeteInsererPersonnel(ArrayList<String> informationsPersonnel, int idService) {
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
	
	/**
	 * @param lePersonnel Personnel lié à l'absence.
	 * @param informationsAbsence Liste des informations de l'absence.
	 */
	public static void requeteInsererAbsence(Personnel lePersonnel, ArrayList<Object> informationsAbsence) {
		String sql = "insert into absence(datedebut, datefin, idmotif, idpersonnel) values(?, ?, ?, ?)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		for(Object param : informationsAbsence) {
			lesParams.add(param);
		}
		lesParams.add(lePersonnel.getId());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login, pwd);
		cn = ConnexionBDD.getInstance(url, login, pwd);
		cn.reqUpdate(sql, lesParams);
	}
}
