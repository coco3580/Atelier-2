package modele;

/**
 * @author CorentinAdmin
 * class responsable
 */
public class Responsable {
	private String login;
	
	/**
	 * constructor
	 * @param leLogin Login du responsable.
	 */
	public Responsable(String leLogin) {
		login = leLogin;
	}
	
	/**
	 * @return login Login du responsable.
	 */
	public String getLogin() {
		return this.login;
	}
}
