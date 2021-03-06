package vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controleur.Controle;

/**
 * @author CorentinAdmin
 * class gestion du formulaire de connexion
 */
@SuppressWarnings("serial")
public class FormConnexion extends JFrame {

	private JPanel contentPane;
	private JTextField utilisateurTextField;
	private JPasswordField mdpTextField;
	private JLabel erreurChampVideLabel;
	private JLabel lblMotDePasse;
	private JLabel utilisateurLabel;
	private JPanel panelChampsConnexion;
	private JLabel titreConnexionLabel;
	private JButton btnValider;
	/**
	 * Launch the application.
	 * @param args Methode main.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormConnexion frame = new FormConnexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormConnexion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 426);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titreConnexionLabel = new JLabel("Connexion");
		titreConnexionLabel.setForeground(new Color(51, 102, 51));
		titreConnexionLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titreConnexionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titreConnexionLabel.setBounds(149, 39, 179, 23);
		contentPane.add(titreConnexionLabel);
		
		panelChampsConnexion = new JPanel();
		panelChampsConnexion.setBackground(new Color(245, 255, 250));
		panelChampsConnexion.setBorder(null);
		panelChampsConnexion.setForeground(Color.LIGHT_GRAY);
		panelChampsConnexion.setBounds(58, 92, 362, 268);
		contentPane.add(panelChampsConnexion);
		panelChampsConnexion.setLayout(null);
		
		utilisateurTextField = new JTextField();
		utilisateurTextField.setBounds(91, 41, 179, 20);
		panelChampsConnexion.add(utilisateurTextField);
		utilisateurTextField.setColumns(10);
		
		utilisateurLabel = new JLabel("Nom d'utilisateur");
		utilisateurLabel.setForeground(new Color(47, 79, 79));
		utilisateurLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		utilisateurLabel.setHorizontalAlignment(SwingConstants.CENTER);
		utilisateurLabel.setBounds(105, 20, 151, 14);
		panelChampsConnexion.add(utilisateurLabel);
		
		mdpTextField = new JPasswordField();
		mdpTextField.setColumns(10);
		mdpTextField.setBounds(91, 126, 179, 20);
		panelChampsConnexion.add(mdpTextField);
		
		lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setForeground(new Color(47, 79, 79));
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotDePasse.setBounds(105, 105, 151, 14);
		panelChampsConnexion.add(lblMotDePasse);

		btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnValider.setBounds(126, 198, 109, 27);
		panelChampsConnexion.add(btnValider);
		
		erreurChampVideLabel = new JLabel("");
		erreurChampVideLabel.setForeground(new Color(255, 0, 0));
		erreurChampVideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		erreurChampVideLabel.setBounds(56, 173, 249, 14);
		panelChampsConnexion.add(erreurChampVideLabel);

		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seConnecter();
			}
		});
	}

	/**
	 * Verifie la connexion.
	 */
	@SuppressWarnings("deprecation")
	private void seConnecter() {
		Controle.seConnecter(utilisateurTextField.getText(), mdpTextField.getText(), erreurChampVideLabel);
	}
}
