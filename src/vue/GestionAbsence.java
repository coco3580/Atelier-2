package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controleur.Controle;
import modele.Absence;
import modele.Personnel;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;


/**
 * @author CorentinAdmin
 * class gestion des absences
 */
@SuppressWarnings("serial")
public class GestionAbsence extends JFrame {
	
	private JList<String> listAbsences;
	private JButton btnModifierAbsence;
	private JButton btnAjouterAbsence;
	private JButton btnSuppAbsence;
	
	private JPanel contentPane;
	private Personnel lePersonnel;
	private FormModificationAbsence leFormModification;
	private FormAjoutAbsence leFormAjouter;
	ArrayList<Absence> laListAbsences = new ArrayList<Absence>();

	/**
	 * Launch the application.
	 * @param args Methode main.
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
		
		listAbsences = new JList<String>();
		listAbsences.setBorder(new LineBorder(new Color(0, 0, 0)));
		listAbsences.setBounds(65, 54, 352, 284);
		contentPane.add(listAbsences);
		listAbsences.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        activeBouton();
		    }
		});
		
		JLabel lblAbsence = new JLabel("Absences");
		lblAbsence.setForeground(new Color(47, 79, 79));
		lblAbsence.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAbsence.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbsence.setBounds(65, 11, 352, 32);
		contentPane.add(lblAbsence);
		
		btnAjouterAbsence = new JButton("Ajouter");
		btnAjouterAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajouterAbsence();
			}
		});
		btnAjouterAbsence.setBounds(10, 349, 100, 28);
		contentPane.add(btnAjouterAbsence);
		
		btnModifierAbsence = new JButton("Modifier");
		btnModifierAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificationAbsence();
			}
		});
		btnModifierAbsence.setEnabled(false);
		btnModifierAbsence.setBounds(132, 349, 100, 28);
		contentPane.add(btnModifierAbsence);
		
		btnSuppAbsence = new JButton("Supprimer");
		btnSuppAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suppressionAbsence();
			}
		});
		btnSuppAbsence.setEnabled(false);
		btnSuppAbsence.setBounds(253, 349, 100, 28);
		contentPane.add(btnSuppAbsence);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Terminer();
			}
		});
		btnTerminer.setBackground(SystemColor.menu);
		btnTerminer.setForeground(new Color(0, 0, 205));
		btnTerminer.setBounds(374, 349, 100, 28);
		contentPane.add(btnTerminer);
	}

	/**
	 * Recupere une instance Personnel.
	 * @param unPersonnel Personnel li? aux absences.
	 */
	public void insertInformations(Personnel unPersonnel) {
		lePersonnel = unPersonnel;
		resetListAbsence();
	}

	/**
	 * Ferme la frame.
	 */
	public void Terminer() {
		this.setVisible(false);
		this.setEnabled(false);
	}

	/**
	 * Ouvre une frame FormModificationAbsence.
	 */
	private void modificationAbsence() {
		leFormModification = new FormModificationAbsence();
		leFormModification.setVisible(true);
		leFormModification.insertInformations(laListAbsences.get(listAbsences.getSelectedIndex()), this);
	}

	/**
	 * Active les bouton btnModifierAbsence et btnSuppAbsence
	 */
	private void activeBouton() {
        btnModifierAbsence.setEnabled(true);
        btnSuppAbsence.setEnabled(true);
	}

	/**
	 * Recharge la liste des absences.
	 */
	public void resetListAbsence() {
		laListAbsences = Controle.getListAbsences(lePersonnel);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(Absence uneAbsence : laListAbsences) {
			String absenceItem = uneAbsence.getMotif().getLibelle() + " du " + uneAbsence.getDateDebut() + " au " + uneAbsence.getDateFin();
			listModel.addElement(absenceItem);
		}
		listAbsences.setModel(listModel);
		
	}

	/**
	 * Supprime une absence dans la bdd.
	 */
	private void suppressionAbsence() {
		int confirmInput = JOptionPane.showConfirmDialog(null, "Supprimer ce personnel?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if(confirmInput == 0) {
			Controle.supprimerAbsence(laListAbsences.get(listAbsences.getSelectedIndex()));
			resetListAbsence();
		}
	}

	/**
	 * Ouvre une frame FormAjouterAbsence.
	 */
	private void ajouterAbsence() {
		leFormAjouter = new FormAjoutAbsence();
		leFormAjouter.insertInformations(lePersonnel, this);
		leFormAjouter.setVisible(true);
	}
	
}
