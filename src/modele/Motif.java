package modele;

public class Motif {
	private String libelle;
	private int idMotif;
	
	public Motif(int unIdMotif, String leLibelle) {
		this.idMotif = unIdMotif;
		this.libelle = leLibelle;
	}
	
	public String getLibelle() {
		return this.libelle;
	}
	
	public int getIdMotif() {
		return this.idMotif;
	}
}
