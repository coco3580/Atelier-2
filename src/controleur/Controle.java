package controleur;

import javax.swing.JLabel;

import vue.FormConnexion;

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
			erreurChampVideLabel.setText("Les informations ne sont pas valides");
		}
	}
}
