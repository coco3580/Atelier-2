package modele;
import java.util.Date;

public class Absence {
	private Date datedebut;
	private Date datefin;
	private Personnel lePersonnel;
	private Motif leMotif;
	
	public Absence(Date laDateDebut, Date laDateFin, Personnel personnel, Motif motif) {
		this.datedebut = laDateDebut;
		this.datefin = laDateFin;
		this.lePersonnel = personnel;
		this.leMotif = motif;
	}
	
	public Date getDateDebut() {
		return this.datedebut;
	}
	
	public Date getDateFin() {
		return this.datefin;
	}
	
	public Personnel getPersonnel() {
		return this.lePersonnel;
	}
	
	public Motif getMotif() {
		return this.leMotif;
	}
}
