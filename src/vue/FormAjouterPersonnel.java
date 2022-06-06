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

public class FormAjouterPersonnel extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTel;
	private JTextField textFieldMail;
	private JComboBox comboBoxService;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblTel;
	private JLabel lblMail;
	private JLabel lblService;
	private JButton btnConfirmerModif;

	private Personnel lePersonnel;
	private ArrayList<modele.Service> laListService;
	private GestionPersonnel laPageGestionPersonnel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAjouterPersonnel frame = new FormAjouterPersonnel();
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
	public FormAjouterPersonnel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnConfirmerModif = new JButton("Confirmer");
		btnConfirmerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmerInsert();
			}
		});
		btnConfirmerModif.setBounds(69, 259, 100, 31);
		contentPane.add(btnConfirmerModif);
		
		JButton btnAnnulerModif = new JButton("Annuler");
		btnAnnulerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnulerInsert();
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
		
		comboBoxService = new JComboBox();
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