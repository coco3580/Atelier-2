package controleur;

import vue.Connexion;

public class Controle {
	private Connexion pageConnexion ;

	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.pageConnexion = new Connexion() ;
		this.pageConnexion.setVisible(true);
	}
}
