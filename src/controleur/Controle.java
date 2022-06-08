package controleur;

import java.util.ArrayList;

import javax.swing.JLabel;

import vue.*;
import modele.*;

public class Controle {
	private static FormConnexion pageConnexion ;
	private static GestionPersonnel pageGestionPersonnel ;

	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.pageConnexion = new FormConnexion() ;
		this.pageConnexion.setVisible(true);
	}
	
	public static void connexion(String utilisateurTextField, String mdpTextField, JLabel erreurChampVideLabel) {

		if(utilisateurTextField.isEmpty() || mdpTextField.isEmpty()) {
			erreurChampVideLabel.setText("Veuillez entrer tous les champs");
		} else {
			Responsable leResponsable = AccesDonnees.requeteTestConnexion(utilisateurTextField, mdpTextField);
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
	
	public static ArrayList<Personnel> listPersonnel(){
		ArrayList<Personnel> laListPersonnel = AccesDonnees.requeteRecupPersonnel();
		return laListPersonnel;
	}
	
	public static void suppressionPersonnel(Personnel lePersonnel) {
		AccesDonnees.requeteSuppressionPersonnel(lePersonnel);
	}
	public static void suppressionAbsence(Absence uneAbsence) {
		AccesDonnees.requetesuppressionAbsence(uneAbsence);
	}
	
	public static ArrayList<Service> recupService() {
		return AccesDonnees.requeteRecupService();
	}
	public static void modifierPersonnel(ArrayList<String> nouvInformationsPersonnel, int idService, int idPersonnel) {
		AccesDonnees.requeteModifierPersonnel(nouvInformationsPersonnel, idService, idPersonnel);
	}
	public static void ajouterPersonnel(ArrayList<String> informationsPersonnel, int idService) {
		AccesDonnees.requeteInsertPersonnel(informationsPersonnel, idService);
	}
	public static ArrayList<Absence> recupAbsences(Personnel lePersonnel) {
		return AccesDonnees.requeteRecupAbsence(lePersonnel);
	}
	public static ArrayList<Motif> recupMotif() {
		return AccesDonnees.requeteRecupMotif();
	}
	public static void modifierAbsence(ArrayList<Object> nouvInformationsPersonnel, Absence uneAbsence) {
		AccesDonnees.requeteModifierAbsence(nouvInformationsPersonnel, uneAbsence);
	}

}
