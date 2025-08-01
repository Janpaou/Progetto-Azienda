package progetto.java.GestioneTurni.Interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.sqliteConnection;
import progetto.java.GestioneAccount.Interfacce.ModuloLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class InterfacciaVincoli {

	private JFrame frame;
	private LocalDate date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaVincoli window = new InterfacciaVincoli();
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
	public InterfacciaVincoli() {
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
		frame.setTitle("Interfaccia Vincoli");
		frame.move(550, 250);
		
		JLabel lblNewLabel = new JLabel("Inserire vincoli ferie");
		lblNewLabel.setBounds(159, 0, 218, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(107, 41, 199, 152);
		frame.getContentPane().add(calendar);
		
		JButton Conferma = new JButton("Conferma");
		Conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate date2;
				if (date==null) {
					date = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				}
				else {
					date2 = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					DateTimeFormatter dataFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String vincoloInizio = dataFmt.format(date);
					String vincoloFine = dataFmt.format(date2);
					if (GestoreVincoliTurni.creaVincolo(date, date2)){
						frame.dispose;
					}
				}
				
			}
		});
		Conferma.setBounds(159, 203, 102, 21);
		frame.getContentPane().add(Conferma);
		
		//PulsanteIndietro
		JButton Indietro = new JButton("Indietro");
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Utente.getRuolo()== 1) {
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
