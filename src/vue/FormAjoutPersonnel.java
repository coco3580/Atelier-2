package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.Color;
import javax.swing.JComboBox;

import controleur.Controle;
import modele.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormAjoutPersonnel extends JFrame {

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
	private JLabel lblAjouterPersonnel;
	private JButton btnConfirmerModif;
	private JButton btnAnnulerModif;

	private Personnel lePersonnel;
	private GestionPersonnel laPageGestionPersonnel;
	
	private ArrayList<modele.Service> laListService;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAjoutPersonnel frame = new FormAjoutPersonnel();
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
	public FormAjoutPersonnel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnConfirmerModif = new JButton("Confirmer");
		btnConfirmerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmerInsert();
			}
		});
		btnConfirmerModif.setBounds(71, 306, 100, 31);
		contentPane.add(btnConfirmerModif);
		
		btnAnnulerModif = new JButton("Annuler");
		btnAnnulerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnulerInsert();
			}
		});
		btnAnnulerModif.setBounds(260, 306, 100, 31);
		contentPane.add(btnAnnulerModif);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(154, 81, 178, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(154, 123, 178, 20);
		contentPane.add(textFieldPrenom);
		
		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		textFieldTel.setBounds(154, 165, 178, 20);
		contentPane.add(textFieldTel);
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(154, 208, 178, 20);
		contentPane.add(textFieldMail);
		
		comboBoxService = new JComboBox<String>();
		comboBoxService.setBounds(154, 250, 178, 20);
		contentPane.add(comboBoxService);
		
		lblNom = new JLabel("Nom");
		lblNom.setForeground(new Color(25, 25, 112));
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNom.setBounds(87, 82, 46, 14);
		contentPane.add(lblNom);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(new Color(25, 25, 112));
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrenom.setBounds(87, 126, 57, 14);
		contentPane.add(lblPrenom);
		
		lblTel = new JLabel("Tel");
		lblTel.setForeground(new Color(25, 25, 112));
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTel.setBounds(87, 168, 57, 14);
		contentPane.add(lblTel);
		
		lblMail = new JLabel("Mail");
		lblMail.setForeground(new Color(25, 25, 112));
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMail.setBounds(87, 211, 57, 14);
		contentPane.add(lblMail);
		
		lblService = new JLabel("Service");
		lblService.setForeground(new Color(25, 25, 112));
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblService.setBounds(87, 253, 57, 14);
		contentPane.add(lblService);
		
		lblAjouterPersonnel = new JLabel("Ajouter un personnel");
		lblAjouterPersonnel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterPersonnel.setForeground(new Color(47, 79, 79));
		lblAjouterPersonnel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAjouterPersonnel.setBounds(128, 31, 221, 22);
		contentPane.add(lblAjouterPersonnel);
	}
	
	public void confirmerInsert() {
		ArrayList<String> informationsPersonnel = new ArrayList<String>();
		informationsPersonnel.add(textFieldNom.getText());
		informationsPersonnel.add(textFieldPrenom.getText());
		informationsPersonnel.add(textFieldTel.getText());
		informationsPersonnel.add(textFieldMail.getText());
		
		String servicePersonnel = (String) comboBoxService.getSelectedItem();
		for(modele.Service service : laListService) {
			if(service.getNom() == servicePersonnel) {
				int serviceId = service.getId();
				Controle.ajouterPersonnel(informationsPersonnel, serviceId);
				break;
			}
		}
		this.laPageGestionPersonnel.resetListPersonnel();
		this.setVisible(false);
		this.setEnabled(false);
	}
	
	public void AnnulerInsert() {
		this.setVisible(false);
		this.setEnabled(false);
	}
	
	public void insertInformations(GestionPersonnel PageGestionPersonnel) {
		/*Recuperation services*/
		laListService = AccesDonnees.requeteRecupService();
		for(modele.Service leService : laListService) {
			comboBoxService.addItem(leService.getNom());
		}
		laPageGestionPersonnel = PageGestionPersonnel;
	}
}
