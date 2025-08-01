package java.GestioneTurni.Interfacce;

import java.GestioneTurni.Control.GestoreAssenzaCongedo;
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

public class InterfacciaAssenzaCongedo {

	private JFrame frame;
	private LocalDate date = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaAssenzaCongedo window = new InterfacciaAssenzaCongedo();
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
	public InterfacciaAssenzaCongedo() {
		
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
		
		JLabel lblNewLabel = new JLabel("Inserire la data di inizio del congedo.");
		lblNewLabel.setBounds(113, 10, 256, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(113, 44, 199, 152);
		frame.getContentPane().add(calendar);
		
		JButton btnNewButton = new JButton("Conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (date == null) {
					System.out.println(calendar.getDate());
					date = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					System.out.println(date);
					
					lblNewLabel.setText("Inserire la data di fine ferie");
				}else{
					LocalDate date2 = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					System.out.println(date2);
					DateTimeFormatter dataFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String data1 = dataFmt.format(date);
					String data2 = dataFmt.format(date2);
					System.out.println(data1);
					System.out.println(data2);
					int compareValue = date2.compareTo(date)+1;
					int giorniPresi = compareValue;
					System.out.println(compareValue);
					boolean verifica = GestoreAssenzaCongedo.creaPeriodo(date, date2);	
					if (verifica) {
						frame.dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(161, 206, 111, 21);
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
				
			
				//Fine PulsanteIndietro
	}
}
