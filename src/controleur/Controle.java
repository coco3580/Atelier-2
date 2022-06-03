package controleur;

import java.util.ArrayList;

import javax.swing.JLabel;

import vue.*;
import modele.*;

public class Controle {
	private FormConnexion pageConnexion ;

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
			Responsable leResponsable = AccesDonnees.testConnexion(utilisateurTextField, mdpTextField);
			if(leResponsable.getLogin() == null) {
				erreurChampVideLabel.setText("Les champs ne sont pas valides");
			} else {
				System.out.print(leResponsable.getLogin());
			}
		}
	}
}
