package modele;

/**
 * @author CorentinAdmin
 * Class motif
 */
public class Motif {
	private String libelle;
	private int idMotif;
	
	/**
	 * contructor
	 * @param unIdMotif Id du motif.
	 * @param leLibelle Libelle du motif.
	 */
	public Motif(int unIdMotif, String leLibelle) {
		this.idMotif = unIdMotif;
		this.libelle = leLibelle;
	}
	
	/**
	 * @return libelle Libelle du motif.
	 */
	public String getLibelle() {
		return this.libelle;
	}
	
	/**
	 * @return idMotif Id du motif.
	 */
	public int getIdMotif() {
		return this.idMotif;
	}
}
