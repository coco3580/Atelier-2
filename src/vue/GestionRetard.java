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
import javax.swing.JButton;
import java.awt.Color;

public class GestionRetard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionRetard frame = new GestionRetard();
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
	public GestionRetard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listAbsences = new JList();
		listAbsences.setBounds(65, 54, 352, 284);
		contentPane.add(listAbsences);
		
		JLabel lblAbsence = new JLabel("Absences");
		lblAbsence.setForeground(new Color(0, 0, 128));
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
		btnTerminer.setBackground(new Color(0, 0, 139));
		btnTerminer.setForeground(new Color(0, 0, 205));
		btnTerminer.setBounds(374, 349, 100, 28);
		contentPane.add(btnTerminer);
	}
}
