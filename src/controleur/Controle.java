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
	
	/**
	 * Verifie les informations de connexion, ferme la frame et ouvre un frame pageGestionPersonnel.
	 * @param utilisateurTextField Texte du nom d'utilisateur inséré.
	 * @param mdpTextField Texte du mot de passe inséré.
	 * @param erreurChampVideLabel Texte pour afficher une erreur si un champ est vide.
	 */
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
	 * Demande de recuperer les motifs de la bdd.
	 * @return laListMotif Liste des motifs.
	 */
	public static ArrayList<Motif> getListMotif() {
		ArrayList<Motif> laListMotif = AccesDonnees.requeteGetMotif();
		return laListMotif;
	}
	
	/**
	 * Demande de recuperer les personnels de la bdd.
	 * @return laListPersonnel Liste des personnels.
	 */
	public static ArrayList<Personnel> getListPersonnel(){
		ArrayList<Personnel> laListPersonnel = AccesDonnees.requeteGetPersonnel();
		return laListPersonnel;
	}
	
	/**
	 * Demande de recuperer les absences de la bdd.
	 * @param lePersonnel Personnel associé aux absences.
	 * @return laListAbsence La liste des absences.
	 */
	public static ArrayList<Absence> getListAbsences(Personnel lePersonnel) {
		ArrayList<Absence> laListAbsence = AccesDonnees.requeteGetAbsence(lePersonnel);
		return laListAbsence;
	}
	
	/**
	 * Demande de recuperer les services de la bdd.
	 * @return lesServices La liste des services.
	 */
	public static ArrayList<Service> getListService() {
		ArrayList<Service> lesServices = AccesDonnees.requeteGetService(); 
		return lesServices;
	}
	
	/*setters*/
	
	/**
	 * Demande de modifier un personnel.
	 * @param nouvInformationsPersonnel Informations qui remplaceront les enciennes.
	 * @param idPersonnel Id du personnel modifié.
	 */
	public static void setPersonnel(ArrayList<Object> nouvInformationsPersonnel, int idPersonnel) {
		AccesDonnees.requeteSetPersonnel(nouvInformationsPersonnel, idPersonnel);
	}
	
	/**
	 * Demande de modifier une absence dans la bdd.
	 * @param nouvInformationsPersonnel Informations qui remplaceront les enciennes.
	 * @param uneAbsence absence qui sera modifié.
	 */
	public static void setAbsence(ArrayList<Object> nouvInformationsPersonnel, Absence uneAbsence) {
		AccesDonnees.requeteSetAbsence(nouvInformationsPersonnel, uneAbsence);
	}
	
	/*suppression*/
	
	/**
	 * Demande de supprimer un personnel dans la bdd.
	 * @param lePersonnel Personnel qui sera supprimé.
	 */
	public static void supprimerPersonnel(Personnel lePersonnel) {
		AccesDonnees.requeteSupprimerPersonnel(lePersonnel);
	}
	
	/**
	 * Demande de supprimer une absence dans la bdd.
	 * @param uneAbsence Absence qui sera supprimé.
	 */
	public static void supprimerAbsence(Absence uneAbsence) {
		AccesDonnees.requeteSupprimerAbsence(uneAbsence);
	}
	
	/*insertion*/
	
	/**
	 * Demande d'ajouter un personnel dans la bdd.
	 * @param informationsPersonnel Liste des informations du personnel.
	 * @param idService Id du sevice.
	 */
	public static void ajouterPersonnel(ArrayList<String> informationsPersonnel, int idService) {
		AccesDonnees.requeteInsererPersonnel(informationsPersonnel, idService);
	}
	
	/**
	 * Demande d'ajouter une absence dans la bdd.
	 * @param lePersonnel Personnel associé à l'absence
	 * @param informationsAbsence Informations de l'absence inséré par l'utilisateur.
	 */
	public static void ajouterAbsence(Personnel lePersonnel, ArrayList<Object> informationsAbsence) {
		AccesDonnees.requeteInsererAbsence(lePersonnel, informationsAbsence);
	}

}
