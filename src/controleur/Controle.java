package controleur;

import java.util.ArrayList;

import javax.swing.JLabel;

import vue.*;
import modele.*;

/**
 * class Controle
 * @author Corentin Dufeu
 */
public class Controle {
	private static FormConnexion pageConnexion ;
	private static GestionPersonnel pageGestionPersonnel ;

	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructor
	 */
	private Controle() {
		Controle.pageConnexion = new FormConnexion() ;
		Controle.pageConnexion.setVisible(true);
	}
	
	public static void seConnecter(String utilisateurTextField, String mdpTextField, JLabel erreurChampVideLabel) {

		if(utilisateurTextField.isEmpty() || mdpTextField.isEmpty()) {
			erreurChampVideLabel.setText("Veuillez entrer tous les champs");
		} else {
			Responsable leResponsable = AccesDonnees.requeteConnexion(utilisateurTextField, mdpTextField);
			if(leResponsable.getLogin() == null) {
				erreurChampVideLabel.setText("Les champs ne sont pas valides");
			} else {
				pageConnexion.setEnabled(false);
				pageConnexion.setVisible(false);
				
				pageGestionPersonnel = new GestionPersonnel();
				pageGestionPersonnel.setVisible(true);
			}
		}
	}
	
	/*getters*/
	
	/**
	 * return list motif
	 */
	public static ArrayList<Motif> getListMotif() {
		return AccesDonnees.requeteGetMotif();
	}
	
	/**
	 * return list personnel
	 */
	public static ArrayList<Personnel> getListPersonnel(){
		ArrayList<Personnel> laListPersonnel = AccesDonnees.requeteGetPersonnel();
		return laListPersonnel;
	}
	
	/**
	 * param lePersonnel
	 * return list absences
	 */
	public static ArrayList<Absence> getListAbsences(Personnel lePersonnel) {
		return AccesDonnees.requeteGetAbsence(lePersonnel);
	}
	
	/**
	 * return list service
	 */
	public static ArrayList<Service> getListService() {
		return AccesDonnees.requeteGetService();
	}
	
	/*setters*/
	
	/**
	 * param nouvInformationsPersonnel
	 * param idPersonnel
	 */
	public static void setPersonnel(ArrayList<Object> nouvInformationsPersonnel, int idPersonnel) {
		AccesDonnees.requeteSetPersonnel(nouvInformationsPersonnel, idPersonnel);
	}
	
	/**
	 * param nouvInformationsPersonnel
	 * param uneAbsence
	 */
	public static void setAbsence(ArrayList<Object> nouvInformationsPersonnel, Absence uneAbsence) {
		AccesDonnees.requeteSetAbsence(nouvInformationsPersonnel, uneAbsence);
	}
	
	/*suppression*/
	
	/**
	 * param lePersonnel
	 */
	public static void supprimerPersonnel(Personnel lePersonnel) {
		AccesDonnees.requeteSupprimerPersonnel(lePersonnel);
	}
	
	/**
	 * param uneAbsence
	 */
	public static void supprimerAbsence(Absence uneAbsence) {
		AccesDonnees.requeteSupprimerAbsence(uneAbsence);
	}
	
	/*insertion*/
	
	/**
	 * param informationsPersonnel
	 * param idService
	 */
	public static void ajouterPersonnel(ArrayList<String> informationsPersonnel, int idService) {
		AccesDonnees.requeteInsererPersonnel(informationsPersonnel, idService);
	}
	
	/**
	 * param lePersonnel
	 * param nouvInformationsAbsence
	 */
	public static void ajouterAbsence(Personnel lePersonnel, ArrayList<Object> nouvInformationsAbsence) {
		AccesDonnees.requeteInsererAbsence(lePersonnel, nouvInformationsAbsence);
	}

}
