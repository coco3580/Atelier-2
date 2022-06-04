package modele;

public class Service {
	private int id;
	private String nom;
	
	public Service(int unId, String leNom) {
		this.id = unId;
		this.nom = leNom;
	}
	
	public String getNom() {
		return this.nom;
	}
	public int getId() {
		return this.id;
	}
}
