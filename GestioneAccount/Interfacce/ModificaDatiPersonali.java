package progetto.java.GestioneAccount.Interfacce;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.GestioneAccount.Control.GestoreModificaDati;
import progetto.java.Utils.Utente;

import javax.swing.JButton;

public class ModificaDatiPersonali {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textCap;
	private JTextField textCitta;
	private JTextField textIban;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaDatiPersonali window = new ModificaDatiPersonali();
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
	public ModificaDatiPersonali() {
		initialize();
		frame.setTitle("Modifica Dati Personali");
		frame.getContentPane().setLayout(null);
		String[] dati = new String[5];
		dati = GestoreModificaDati.richiediDatiPersonali();
		
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(50, 37, 63, 13);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText(dati[0]);
		textField.setBounds(133, 34, 185, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Indirizzo");
		lblNewLabel_1.setBounds(50, 70, 73, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setText(dati[1]);
		textField_1.setBounds(133, 67, 185, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton ModEmail = new JButton("Modifica");
		ModEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreModificaDati.modificaDato("mail", textField.getText());
			}
		});
		ModEmail.setBounds(328, 33, 85, 21);
		frame.getContentPane().add(ModEmail);
		
		JButton ModIndirizzo = new JButton("Modifica");
		ModIndirizzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreModificaDati.modificaDato("indirizzo", textField_1.getText());
				
			}
		});
		ModIndirizzo.setBounds(328, 66, 85, 21);
		frame.getContentPane().add(ModIndirizzo);
		
		JLabel ModCap = new JLabel("CAP");
		ModCap.setBounds(50, 103, 45, 13);
		frame.getContentPane().add(ModCap);
		
		textCap = new JTextField();
		textCap.setText(dati[2]);
		textCap.setBounds(133, 103, 185, 19);
		frame.getContentPane().add(textCap);
		textCap.setColumns(10);
		
		JButton ModCapp = new JButton("Modifica");
		ModCapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreModificaDati.modificaDato("cap", textCap.getText());
			}
		});
		ModCapp.setBounds(328, 103, 85, 21);
		frame.getContentPane().add(ModCapp);
		
		JLabel ModCitta = new JLabel("Città");
		ModCitta.setBounds(50, 133, 45, 13);
		frame.getContentPane().add(ModCitta);
		
		textCitta = new JTextField();
		textCitta.setText(dati[3]);
		textCitta.setColumns(10);
		textCitta.setBounds(133, 132, 185, 19);
		frame.getContentPane().add(textCitta);
		
		JButton ModCittaa = new JButton("Modifica");
		ModCittaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreModificaDati.modificaDato("citta", textCitta.getText());
			}
		});
		ModCittaa.setBounds(328, 134, 85, 21);
		frame.getContentPane().add(ModCittaa);
		
		JLabel modIban = new JLabel("Iban");
		modIban.setBounds(50, 168, 45, 13);
		frame.getContentPane().add(modIban);
		
		textIban = new JTextField();
		textIban.setText(dati[4]);
		textIban.setColumns(10);
		textIban.setBounds(133, 165, 185, 19);
		frame.getContentPane().add(textIban);
		
		JButton ModIban = new JButton("Modifica");
		ModIban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreModificaDati.modificaDato("iban", textIban.getText());
			}
		});
		ModIban.setBounds(328, 164, 85, 21);
		frame.getContentPane().add(ModIban);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


