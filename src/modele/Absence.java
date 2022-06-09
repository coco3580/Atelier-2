package modele;
import java.util.Date;

/**
 * class Controle
 * @author Corentin Dufeu
 */
public class Absence {
	private Date datedebut;
	private Date datefin;
	private Personnel lePersonnel;
	private Motif leMotif;
	
	/**
	 * Constructor
	 * @param laDateDebut Date du debut de l'absence.
	 * @param laDateFin Date de fin de l'absence.
	 * @param personnel Personnel lié à l'absence.
	 * @param motif Classe du motif de l'absence.
	 */
	public Absence(Date laDateDebut, Date laDateFin, Personnel personnel, Motif motif) {
		this.datedebut = laDateDebut;
		this.datefin = laDateFin;
		this.lePersonnel = personnel;
		this.leMotif = motif;
	}
	
	/**
	 * @return datedebut
	 */
	public Date getDateDebut() {
		return this.datedebut;
	}
	
	/**
	 * @return datefin
	 */
	public Date getDateFin() {
		return this.datefin;
	}
	
	/**
	 * @return lePersonnel
	 */
	public Personnel getPersonnel() {
		return this.lePersonnel;
	}
	
	/**
	 * @return leMotif
	 */
	public Motif getMotif() {
		return this.leMotif;
	}
}
