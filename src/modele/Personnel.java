package modele;

public class Personnel {
	private int id;
	private String nom;
	private String prenom;
	private String tel;
	private String mail;
	private Service leService;
	
	public Personnel(int unId, String leNom, String lePrenom, String leTel, String leMail, Service leService) {
		this.id = unId;
		this.nom = leNom;
		this.prenom = lePrenom;
		this.tel = leTel;
		this.mail = leMail;
		this.leService = leService;
	}
	public int getId() {
		return this.id;
	}
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getTel() {
		return this.tel;
	}
	public String getMail() {
		return this.mail;
	}
	public Service getService() {
		return this.leService;
	}
}
