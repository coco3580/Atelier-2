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
		titreGestionPersonnel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titreGestionPersonnel.setHorizontalAlignment(SwingConstants.CENTER);
		titreGestionPersonnel.setBounds(158, 0, 326, 86);
		contentPane.add(titreGestionPersonnel);
		
		/*Btn Ajouter Personnel*/
		JButton btnAjouterPersonnel = new JButton("Ajouter");
		btnAjouterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAjouterPersonnel.setBounds(158, 347, 100, 28);
		contentPane.add(btnAjouterPersonnel);
		
		/*Btn Supprimer Personnel*/
		JButton btnSuppPersonnel = new JButton("Supprimer");
		btnSuppPersonnel.setEnabled(false);
		btnSuppPersonnel.setBounds(395, 347, 100, 28);
		contentPane.add(btnSuppPersonnel);
		

		/*List personnel*/
		JList listePersonnel = new JList();
		listePersonnel.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
	            int index = list.locationToIndex(evt.getPoint());
	            btnSuppPersonnel.setEnabled(true);
		    }
		});
		listePersonnel.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		ArrayList<Personnel> laListPersonnel = new ArrayList();
		laListPersonnel = Controle.listPersonnel();
		DefaultListModel listModel = new DefaultListModel();
		for(Personnel lePersonnel : laListPersonnel) {
			String personnel = lePersonnel.getPrenom()+ " " + lePersonnel.getNom() + " - " + lePersonnel.getTel() + " - " + lePersonnel.getMail() +
					lePersonnel.getService().getNom();
			listModel.addElement(personnel);
		};
		listePersonnel.setModel(listModel);
		listePersonnel.setBounds(79, 97, 484, 239);
		contentPane.add(listePersonnel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(79, 336, 484, 50);
		contentPane.add(panel);
	}
}
