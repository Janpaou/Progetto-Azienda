package java.GestioneTurni.Interfacce;

import java.GestioneTurni.Control.GestoreAssenzaCongedo;
import java.GestioneTurni.Control.GestoreAssenza;
import java.GestioneTurni.Control.GestoreRichiestaFerie;
import java.Utils.Utente;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.sqliteConnection;
import progetto.java.GestioneAccount.Interfacce.ModuloLogin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InterfacciaAssenza {

	private JFrame frame;
	private LocalDate date = null;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaAssenza window = new InterfacciaAssenza();
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
	public InterfacciaAssenza() {
		
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
		frame.setTitle("Comunicazione Assenza per congedo parentale");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserire la data e il motivo dell'assenza.");
		lblNewLabel.setBounds(44, 10, 325, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(113, 44, 199, 152);
		frame.getContentPane().add(calendar);
		
		JButton btnNewButton = new JButton("Conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String motivo = textField.getText();
				GestoreAssenza.verificaData(Utente.getMatricola(), date, motivo);	
					
				}
			}
		);
		btnNewButton.setBounds(159, 232, 111, 21);
		frame.getContentPane().add(btnNewButton);
		frame.move(550, 250);
				//PulsanteIndietro
				JButton Indietro = new JButton("Indietro");
				Indietro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Utente.getRuolo()==1) {
							InterfacciaDirettore.main(null);
							frame.dispose();
						}else {
							frame.dispose();
							InterfacciaImpiegato.main(null);
						}
					}
				});
				Indietro.setBounds(0, 232, 85, 21);
				frame.getContentPane().add(Indietro);
				
				textField = new JTextField();
				textField.setBounds(96, 196, 273, 26);
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				
			
				//Fine PulsanteIndietro
	}
}
