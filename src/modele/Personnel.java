package modele;

/**
 * @author CorentinAdmin
 * class personnel
 */
public class Personnel {
	private int id;
	private String nom;
	private String prenom;
	private String tel;
	private String mail;
	private Service leService;
	
	/**
	 * constructor
	 * @param unId Id du personnel.
	 * @param leNom Nom du personnel.
	 * @param lePrenom Prenom du personnel.
	 * @param leTel Tel du personnel.
	 * @param leMail Mail du personnel.
	 * @param leService Classe du service lié au personnel.
	 */
	public Personnel(int unId, String leNom, String lePrenom, String leTel, String leMail, Service leService) {
		this.id = unId;
		this.nom = leNom;
		this.prenom = lePrenom;
		this.tel = leTel;
		this.mail = leMail;
		this.leService = leService;
	}
	
	/**
	 * @return id Id du personnel.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return nom Nom du personnel.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * @return prenom Prenom du personnel.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * @return tel Tel du personnel.
	 */
	public String getTel() {
		return this.tel;
	}
	
	/**
	 * @return mail Mail du personnel.
	 */
	public String getMail() {
		return this.mail;
	}
	
	/**
	 * @return leService Classe du service lié au personnel.
	 */
	public Service getService() {
		return this.leService;
	}
}
