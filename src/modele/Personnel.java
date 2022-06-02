package modele;

public class Personnel {
	private String nom;
	private String prenom;
	private String tel;
	private String mail;
	private Service leService;
	
	public Personnel(String leNom, String lePrenom, String leTel, String leMail, Service leService) {
		this.nom = leNom;
		this.prenom = lePrenom;
		this.tel = leTel;
		this.mail = leMail;
		this.leService = leService;
		
		
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
