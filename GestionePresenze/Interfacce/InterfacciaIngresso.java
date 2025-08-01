package java.GestionePresenze.Interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.GestionePresenze.Control.GestorePresenza;
import progetto.java.Utils.Utente;

public class InterfacciaIngresso {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textMatricola;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaIngresso window = new InterfacciaIngresso();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacciaIngresso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Interfaccia Ingresso");
		frame.move(550, 250);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(54, 33, 75, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setBounds(54, 77, 75, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Matricola");
		lblNewLabel_2.setBounds(54, 123, 75, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textNome = new JTextField();
		textNome.setBounds(166, 30, 96, 19);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField();
		textCognome.setColumns(10);
		textCognome.setBounds(166, 74, 96, 19);
		frame.getContentPane().add(textCognome);
		
		textMatricola = new JTextField();
		textMatricola.setColumns(10);
		textMatricola.setBounds(166, 120, 96, 19);
		frame.getContentPane().add(textMatricola);
		
		JButton btnNewButton = new JButton("Conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorePresenza.prendiDatiI(textNome.getText(), textCognome.getText(), textMatricola.getText());
			}
		});
		btnNewButton.setBounds(166, 199, 96, 21);
		frame.getContentPane().add(btnNewButton);
		
		//PulsanteIndietro
				JButton Indietro = new JButton("Indietro");
				Indietro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Utente.getRuolo()==1) {
							InterfacciaDirettore.main(null);;
							frame.dispose();
						}else {
							frame.dispose();
							InterfacciaImpiegato.main(null);
						}
					}
				});
				Indietro.setBounds(0, 232, 85, 21);
				frame.getContentPane().add(Indietro);
				//Fine PulsanteIndietro
	}
}
