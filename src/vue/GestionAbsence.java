package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import controleur.Controle;
import modele.Absence;
import modele.AccesDonnees;
import modele.Personnel;

import java.awt.SystemColor;
import java.util.ArrayList;

public class GestionAbsence extends JFrame {
	
	private JList listAbsences;

	private JPanel contentPane;
	private Personnel lePersonnel;
	ArrayList<Absence> laListAbsences = new ArrayList<Absence>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAbsence frame = new GestionAbsence();
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
	public GestionAbsence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listAbsences = new JList();
		listAbsences.setBorder(new LineBorder(new Color(0, 0, 0)));
		listAbsences.setBounds(65, 54, 352, 284);
		contentPane.add(listAbsences);
		
		JLabel lblAbsence = new JLabel("Absences");
		lblAbsence.setForeground(new Color(47, 79, 79));
		lblAbsence.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAbsence.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbsence.setBounds(65, 11, 352, 32);
		contentPane.add(lblAbsence);
		
		JButton btnAjouterAbsence = new JButton("Ajouter");
		btnAjouterAbsence.setBounds(10, 349, 100, 28);
		contentPane.add(btnAjouterAbsence);
		
		JButton btnModifierAbsence = new JButton("Modifier");
		btnModifierAbsence.setEnabled(false);
		btnModifierAbsence.setBounds(132, 349, 100, 28);
		contentPane.add(btnModifierAbsence);
		
		JButton btnSuppAbsence = new JButton("Supprimer");
		btnSuppAbsence.setEnabled(false);
		btnSuppAbsence.setBounds(253, 349, 100, 28);
		contentPane.add(btnSuppAbsence);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setBackground(SystemColor.menu);
		btnTerminer.setForeground(new Color(0, 0, 205));
		btnTerminer.setBounds(374, 349, 100, 28);
		contentPane.add(btnTerminer);
	}
	
	public void insertInformations(Personnel lePersonnel) {
		lePersonnel = lePersonnel;

		laListAbsences = Controle.recupAbsences(lePersonnel);
		DefaultListModel listModel = new DefaultListModel();
		for(Absence uneAbsence : laListAbsences) {
			String absenceItem = uneAbsence.getMotif().getLibelle() + " du " + uneAbsence.getDateDebut() + " au " + uneAbsence.getDateFin();
			listModel.addElement(absenceItem);
		}
		listAbsences.setModel(listModel);
	}
}
