package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.Font;
import java.awt.Color;

public class FormAjoutAbsence extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAjoutAbsence frame = new FormAjoutAbsence();
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
	public FormAjoutAbsence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBoxMotif = new JComboBox();
		comboBoxMotif.setBounds(167, 186, 133, 22);
		contentPane.add(comboBoxMotif);
		
		JLabel lblMotif = new JLabel("Choisir un motif");
		lblMotif.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotif.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotif.setBounds(167, 153, 133, 22);
		contentPane.add(lblMotif);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTerminer.setBounds(96, 246, 100, 27);
		contentPane.add(btnTerminer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnnuler.setBounds(274, 246, 100, 27);
		contentPane.add(btnAnnuler);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1654034400000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(117, 101, 84, 20);
		contentPane.add(spinner);
		
		JSpinner spinnerAnnee = new JSpinner();
		spinnerAnnee.setModel(new SpinnerDateModel(new Date(1654034400000L), null, null, Calendar.DAY_OF_YEAR));
		spinnerAnnee.setBounds(274, 101, 84, 20);
		contentPane.add(spinnerAnnee);
		
		JLabel lblNewLabel = new JLabel("Ajouter une absence");
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(123, 30, 221, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblDateDebut = new JLabel("Date debut");
		lblDateDebut.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateDebut.setBounds(117, 76, 79, 14);
		contentPane.add(lblDateDebut);
		
		JLabel lblDateFin = new JLabel("Date fin");
		lblDateFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateFin.setBounds(274, 76, 79, 14);
		contentPane.add(lblDateFin);

	}
}
