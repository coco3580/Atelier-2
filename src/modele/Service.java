package modele;

/**
 * @author CorentinAdmin
 * class service
 */
public class Service {
	private int id;
	private String nom;
	
	/**
	 * @param unId Id du Service.
	 * @param leNom Nom du Service.
	 */
	public Service(int unId, String leNom) {
		this.id = unId;
		this.nom = leNom;
	}
	
	/**
	 * @return nom Nom du Service.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * @return id Id du Service.
	 */
	public int getId() {
		return this.id;
	}
}
