package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import modele.Absence;
import modele.Motif;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author CorentinAdmin
 * class gastion du formulaire de la modification d'une absence
 */
@SuppressWarnings("serial")
public class FormModificationAbsence extends JFrame {

	private JPanel contentPane;
	private JSpinner spinnerDateDebut;
	private JSpinner spinnerDateFin;
	private JComboBox<String> comboBoxMotif;
	private JLabel lblDateFin;
	private JLabel lblDateDebut;
	private JLabel lblModification;
	private JLabel lblMotif;
	private JButton btnAnnuler;
	private JButton btnValider;

	private Absence uneAbsence;
	private GestionAbsence laPageGestionAbsence;
	
	private ArrayList<Motif> laListMotifs;

	/**
	 * Launch the application.
	 * @param args Methode main.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormModificationAbsence frame = new FormModificationAbsence();
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
	public FormModificationAbsence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxMotif = new JComboBox<String>();
		comboBoxMotif.setBounds(167, 186, 133, 22);
		contentPane.add(comboBoxMotif);
		
		lblMotif = new JLabel("Choisir un motif");
		lblMotif.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotif.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotif.setBounds(167, 153, 133, 22);
		contentPane.add(lblMotif);
		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmerModif();
			}
		});
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnValider.setBounds(96, 246, 100, 27);
		contentPane.add(btnValider);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnulerModif();
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnnuler.setBounds(274, 246, 100, 27);
		contentPane.add(btnAnnuler);
		
		spinnerDateDebut = new JSpinner();
		spinnerDateDebut.setModel(new SpinnerDateModel(new Date(1654034400628L), null, null, Calendar.DAY_OF_YEAR));
		spinnerDateDebut.setEditor(new JSpinner.DateEditor(spinnerDateDebut, "y:M:d"));
		spinnerDateDebut.setBounds(117, 101, 84, 20);
		contentPane.add(spinnerDateDebut);
		
		spinnerDateFin = new JSpinner();
		spinnerDateFin.setModel(new SpinnerDateModel(new Date(1654034400794L), null, null, Calendar.DAY_OF_YEAR));
		spinnerDateFin.setEditor(new JSpinner.DateEditor(spinnerDateFin, "y:M:d"));
		spinnerDateFin.setBounds(274, 101, 84, 20);
		contentPane.add(spinnerDateFin);
		
		lblModification = new JLabel("Modifier une absence");
		lblModification.setForeground(new Color(47, 79, 79));
		lblModification.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblModification.setHorizontalAlignment(SwingConstants.CENTER);
		lblModification.setBounds(123, 30, 221, 22);
		contentPane.add(lblModification);
		
		lblDateDebut = new JLabel("Date debut");
		lblDateDebut.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateDebut.setBounds(117, 76, 79, 14);
		contentPane.add(lblDateDebut);
		
		lblDateFin = new JLabel("Date fin");
		lblDateFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateFin.setBounds(274, 76, 79, 14);
		contentPane.add(lblDateFin);

	}
	
	/**
	 * Recupere des informations.
	 * @param absence Absence li? ? la modification.
	 * @param laGestionAbsence Page de gestion des absences.
	 */
	public void insertInformations(Absence absence, GestionAbsence laGestionAbsence){
		uneAbsence = absence;
		laPageGestionAbsence = laGestionAbsence;
		spinnerDateDebut.setValue(uneAbsence.getDateDebut());
		spinnerDateFin.setValue(uneAbsence.getDateFin());
		
		laListMotifs = Controle.getListMotif();
		for(modele.Motif leMotif : laListMotifs) {
			comboBoxMotif.addItem(leMotif.getLibelle());
		}
		comboBoxMotif.setSelectedIndex(uneAbsence.getMotif().getIdMotif()-1);
	}
	
	/**
	 * Ferme la frame.
	 */
	public void AnnulerModif() {
		this.setVisible(false);
		this.setEnabled(false);
	}
	
	/**
	 * insert les modifs dans la bdd et ferme la frame.
	 */
	public void confirmerModif() {
		int confirmInput = JOptionPane.showConfirmDialog(null, "Confirmer les modifications?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if(confirmInput == 0) {
			ArrayList<Object> nouvInformationsPersonnel = new ArrayList<Object>();
			nouvInformationsPersonnel.add(spinnerDateDebut.getValue());
			nouvInformationsPersonnel.add(spinnerDateFin.getValue());
			for(Motif leMotif : laListMotifs) {
				if(leMotif.getLibelle() == comboBoxMotif.getSelectedItem().toString()) {
					nouvInformationsPersonnel.add(leMotif.getIdMotif());
				}
			}
			Controle.setAbsence(nouvInformationsPersonnel, uneAbsence);
			
			this.laPageGestionAbsence.resetListAbsence();
			this.setVisible(false);
			this.setEnabled(false);
		}
	}
}
