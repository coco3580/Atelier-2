package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import modele.Personnel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionPersonnel extends JFrame {

	private JPanel contentPane;
	private FormModification leFormModification;
	private JList listePersonnel;
	private JButton btnSuppPersonnel;
	private JButton btnAjouterPersonnel;
	private JButton btnModifierPersonnel;
	private ArrayList<Personnel> laListPersonnel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPersonnel frame = new GestionPersonnel();
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
	public GestionPersonnel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titreGestionPersonnel = new JLabel("Gestion du personnel");
		titreGestionPersonnel.setForeground(SystemColor.textHighlight);
		titreGestionPersonnel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titreGestionPersonnel.setHorizontalAlignment(SwingConstants.CENTER);
		titreGestionPersonnel.setBounds(158, 0, 326, 86);
		contentPane.add(titreGestionPersonnel);

		/*List personnel*/
		listePersonnel = new JList();
		listePersonnel.setForeground(SystemColor.controlDkShadow);
		listePersonnel.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
	            int index = list.locationToIndex(evt.getPoint());
	            btnSuppPersonnel.setEnabled(true);
	            btnModifierPersonnel.setEnabled(true);
		    }
		});
		
		listePersonnel.setBounds(79, 88, 484, 248);
		resetListPersonnel();
		contentPane.add(listePersonnel);
		

		/*Btn Ajouter Personnel*/
		btnAjouterPersonnel = new JButton("Ajouter");
		btnAjouterPersonnel.setBounds(124, 347, 100, 28);
		contentPane.add(btnAjouterPersonnel);
		
		/*Btn Modifier Personnel*/
		btnModifierPersonnel = new JButton("Modifier");
		btnModifierPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificationPersonnel();
			}
		});
		btnModifierPersonnel.setEnabled(false);
		btnModifierPersonnel.setBounds(268, 347, 100, 28);
		contentPane.add(btnModifierPersonnel);
		
		/*Btn Supprimer Personnel*/
		btnSuppPersonnel = new JButton("Supprimer");
		btnSuppPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suppressionPersonnel();
			}
		});
		btnSuppPersonnel.setEnabled(false);
		btnSuppPersonnel.setBounds(411, 347, 100, 28);
		contentPane.add(btnSuppPersonnel);
		
	}
	public void resetListPersonnel() {
		laListPersonnel = new ArrayList();
		laListPersonnel = Controle.listPersonnel();
		DefaultListModel listModel = new DefaultListModel();
		for(Personnel lePersonnel : laListPersonnel) {
			String personnel = lePersonnel.getPrenom()+ " " + lePersonnel.getNom() + " - " + lePersonnel.getTel() + " - " + lePersonnel.getMail() +
					 " - " + lePersonnel.getService().getNom();
			listModel.addElement(personnel);
		};
		listePersonnel.setModel(listModel);
	}
	private void modificationPersonnel() {
		leFormModification = new FormModification();
		leFormModification.setVisible(true);
		leFormModification.insertInformations(laListPersonnel.get(listePersonnel.getSelectedIndex()), this);
	}
	private void suppressionPersonnel() {
		int confirmInput = JOptionPane.showConfirmDialog(null, "Supprimer ce personnel?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if(confirmInput == 0) {
			Controle.suppressionPersonnel(laListPersonnel.get(listePersonnel.getSelectedIndex()));
			resetListPersonnel();
		}
	}
}
