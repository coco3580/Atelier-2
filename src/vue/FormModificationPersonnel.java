package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JComboBox;

import controleur.Controle;
import modele.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * @author CorentinAdmin
 * class gastion du formulaire de la modification d'un personnel
 */
@SuppressWarnings("serial")
public class FormModificationPersonnel extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTel;
	private JTextField textFieldMail;
	private JComboBox<String> comboBoxService;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblTel;
	private JLabel lblMail;
	private JLabel lblService;
	private JLabel erreurChampVideLabel;
	private JButton btnConfirmerModif;
	private JButton btnAnnulerModif;

	private ArrayList<modele.Service> laListService;
	
	private GestionPersonnel laPageGestionPersonnel;
	private Personnel lePersonnel;

	/**
	 * Launch the application.
	 * @param args Methode main.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormModificationPersonnel frame = new FormModificationPersonnel();
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
	public FormModificationPersonnel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnConfirmerModif = new JButton("Confirmer");
		btnConfirmerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmerModif();
			}
		});
		btnConfirmerModif.setBounds(69, 259, 100, 31);
		contentPane.add(btnConfirmerModif);
		
		btnAnnulerModif = new JButton("Annuler");
		btnAnnulerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnulerModif();
			}
		});
		btnAnnulerModif.setBounds(258, 259, 100, 31);
		contentPane.add(btnAnnulerModif);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(152, 34, 178, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(152, 76, 178, 20);
		contentPane.add(textFieldPrenom);
		
		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		textFieldTel.setBounds(152, 118, 178, 20);
		contentPane.add(textFieldTel);
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(152, 161, 178, 20);
		contentPane.add(textFieldMail);
		
		comboBoxService = new JComboBox<String>();
		comboBoxService.setBounds(152, 203, 178, 20);
		contentPane.add(comboBoxService);
		
		lblNom = new JLabel("Nom");
		lblNom.setForeground(new Color(25, 25, 112));
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNom.setBounds(85, 35, 46, 14);
		contentPane.add(lblNom);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(new Color(25, 25, 112));
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrenom.setBounds(85, 79, 57, 14);
		contentPane.add(lblPrenom);
		
		lblTel = new JLabel("Tel");
		lblTel.setForeground(new Color(25, 25, 112));
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTel.setBounds(85, 121, 57, 14);
		contentPane.add(lblTel);
		
		lblMail = new JLabel("Mail");
		lblMail.setForeground(new Color(25, 25, 112));
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMail.setBounds(85, 164, 57, 14);
		contentPane.add(lblMail);
		
		lblService = new JLabel("Service");
		lblService.setForeground(new Color(25, 25, 112));
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblService.setBounds(85, 206, 57, 14);
		contentPane.add(lblService);
		
		erreurChampVideLabel = new JLabel("");
		erreurChampVideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		erreurChampVideLabel.setForeground(Color.RED);
		erreurChampVideLabel.setBounds(92, 234, 249, 14);
		contentPane.add(erreurChampVideLabel);
	}

	/**
	 * Insert les donnees dans la bdd et ferme la frame.
	 */
	public void confirmerModif() {ArrayList<String> champs = new ArrayList<String>();
		ArrayList<Object> nouvInformationsPersonnel = new ArrayList<Object>();
		champs.add(textFieldNom.getText());
		champs.add(textFieldPrenom.getText());
		champs.add(textFieldTel.getText());
		champs.add(textFieldMail.getText());
		
		for(String champ : champs) {
			if("".equals(champ)) {
				erreurChampVideLabel.setText("Veuillez rentrer tous les champs");
				return;
			}
			nouvInformationsPersonnel.add(champ);
		}
		
		int confirmInput = JOptionPane.showConfirmDialog(null, "Confirmer les modifications?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if(confirmInput == 0) {
			
			for(modele.Service leService : laListService) {
				if(leService.getNom() == comboBoxService.getSelectedItem().toString()) {
					nouvInformationsPersonnel.add(leService.getId());
				}
			}
			
			Controle.setPersonnel(nouvInformationsPersonnel, lePersonnel.getId());
			
			this.laPageGestionPersonnel.resetListPersonnel();
			this.setVisible(false);
			this.setEnabled(false);
		}
	}

	/**
	 * Ferme la frame.
	 */
	public void AnnulerModif() {
		this.setVisible(false);
		this.setEnabled(false);
	}
	
	/**
	 * Recupere des informations.
	 * @param personnel Personnel lié à la modification
	 * @param PageGestionPersonnel Page de gestion des personnels.
	 */
	public void insertInformations(Personnel personnel, GestionPersonnel PageGestionPersonnel) {
		lePersonnel = personnel;
		laPageGestionPersonnel = PageGestionPersonnel;
		
		textFieldNom.setText(lePersonnel.getNom());
		textFieldPrenom.setText(lePersonnel.getPrenom());
		textFieldTel.setText(lePersonnel.getTel());
		textFieldMail.setText(lePersonnel.getMail());
		
		laListService = Controle.getListService();
		for(modele.Service leService : laListService) {
			comboBoxService.addItem(leService.getNom());
		}
		comboBoxService.setSelectedIndex(lePersonnel.getService().getId()-1);
	}
}
