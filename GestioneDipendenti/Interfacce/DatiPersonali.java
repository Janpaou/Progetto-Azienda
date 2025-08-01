package progetto.java.GestioneDipendenti.Interfacce;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.Utils.Utente;

public class DatiPersonali {

	private JFrame frame;
	private String id;
	private String nome;
	private String cognome;
	private String email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String[] dati) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatiPersonali window = new DatiPersonali(dati);
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
	public DatiPersonali(String[] dati) {
		
		
		initialize(dati);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize(String[] dati) {
		id = dati[0];
		nome = dati[1];
		cognome= dati[2];
		email = dati[3];
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Interfaccia Dati Personali");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matricola");
		lblNewLabel.setBounds(59, 26, 66, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel Matricola = new JLabel(id);
		Matricola.setBounds(135, 26, 128, 13);
		frame.getContentPane().add(Matricola);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(59, 60, 66, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cognome");
		lblNewLabel_2.setBounds(59, 93, 66, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(59, 131, 66, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel Nome = new JLabel(nome);
		Nome.setBounds(135, 60, 128, 13);
		frame.getContentPane().add(Nome);
		
		JLabel Cognome = new JLabel(cognome);
		Cognome.setBounds(135, 93, 145, 13);
		frame.getContentPane().add(Cognome);
		
		JLabel Email = new JLabel(email);
		Email.setBounds(135, 131, 277, 13);
		frame.getContentPane().add(Email);
		frame.move(550, 250);
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
