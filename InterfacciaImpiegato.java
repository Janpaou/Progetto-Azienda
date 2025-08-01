package progetto.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import progetto.java.GestioneAccount.Control.GestoreModificaDati;
import progetto.java.GestioneAccount.Control.GestoreModificaPassword;
import progetto.java.GestioneAccount.Interfacce.ModuloLogin;
import progetto.java.GestioneDipendenti.Control.GestoreTurni;
import progetto.java.GestioneDipendenti.Control.GestoreVisualizzaDatiPesonali;
import progetto.java.GestionePresenze.Interfacce.InterfacciaIngresso;
import progetto.java.GestionePresenze.Interfacce.InterfacciaUscita;
import progetto.java.GestioneTurni.Control.GestoreRichiestaFerie;
import progetto.java.GestioneTurni.Interfacce.AssPcPar;
import progetto.java.Utils.Utente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;    

public class InterfacciaImpiegato {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaImpiegato window = new InterfacciaImpiegato();
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
	
	public InterfacciaImpiegato() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(InterfacciaImpiegato.class.getResource("/progetto/images/Immagine.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Interfaccia Dipendente");
		frame.move(550, 250);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		DateTimeFormatter giorno = DateTimeFormatter.ofPattern("dd");
		DateTimeFormatter mese = DateTimeFormatter.ofPattern("MM"); 
		JLabel background;
		background = new JLabel("", JLabel.CENTER);
		background.setIcon(new ImageIcon(InterfacciaImpiegato.class.getResource("/progetto/images/1673691197131-thumbnail.jpeg")));
		background.setBounds(-14, 0, 450, 241);
		frame.getContentPane().add(background);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benvenuto " + Utente.getNome() + ".");
		background.add(lblNewLabel);
		lblNewLabel.setBounds(15, 0, 230, 30);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JButton Logout = new JButton("Logout");
		background.add(Logout);
		Logout.setBackground(new Color(255, 255, 255));
		Logout.setBounds(367, 0, 85, 21);
		
		JLabel lblNewLabel_1 = new JLabel(dtf.format(now));
		background.add(lblNewLabel_1);
		lblNewLabel_1.setBounds(390, 218, 54, 13);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valore = JOptionPane.showConfirmDialog(null, "Verrai disconnesso, desideri continuare?", null, 0);
				if (valore == 0) {
					ModuloLogin.main(null);
					frame.dispose();
				}
				
			}
		});
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Area Personale");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmIMieiDati = new JMenuItem("I miei Dati");
		mntmIMieiDati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreVisualizzaDatiPesonali.GestoreVisualizzaDati();
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmIMieiDati);
		
		JMenuItem mntmVisualizzazioneTurni = new JMenuItem("Visualizzazione Turni");
		mntmVisualizzazioneTurni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Utente.getMatricola());
				GestoreTurni.lanciaInterfacciaTurniDip();
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmVisualizzazioneTurni);
		
		JMenu mnNewMenu_1 = new JMenu("Presenze");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmRilevazioniPresenze = new JMenuItem("Rilevazione Presenze");
		mntmRilevazioniPresenze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaIngresso.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_1.add(mntmRilevazioniPresenze);
		
		JMenuItem mntmRilevazioneUscite = new JMenuItem("Rilevazione Uscite");
		mntmRilevazioneUscite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaUscita.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_1.add(mntmRilevazioneUscite);
		
		JMenuItem mntmRilevazioneRitardo = new JMenuItem("Rilevazione Ritardi");
		mntmRilevazioneRitardo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		mnNewMenu_1.add(mntmRilevazioneRitardo);
		
		JMenu mnNewMenu_2 = new JMenu("Turni");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmFerie = new JMenuItem("Ferie");
		mntmFerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Integer.parseInt(giorno.format(now)));
				GestoreRichiestaFerie.checkData();
			}
		});
		mnNewMenu_2.add(mntmFerie);
		
		JMenuItem mntmComunicazioneDiAssenza = new JMenuItem("Comunica Assenza");
		mnNewMenu_2.add(mntmComunicazioneDiAssenza);
		
		JMenuItem mntmComunicaAssenzaPer = new JMenuItem("Comunica Assenza per congedo parentale");
		mntmComunicaAssenzaPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(giorno.format(now)) >= 21 & Integer.parseInt(giorno.format(now)) <= 28 ) {
					if(Integer.parseInt(mese.format(now))==3 || Integer.parseInt(mese.format(now))==6 || Integer.parseInt(mese.format(now))==9 || Integer.parseInt(mese.format(now))==12) {
						AssPcPar.main(null);
						frame.dispose();
						
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Data non valida");
				}
			}
		});
		System.out.println(Integer.parseInt(mese.format(now)));
		
		mnNewMenu_2.add(mntmComunicaAssenzaPer);
		JMenu mnNewMenu_3 = new JMenu("Account");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Modifica Password");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mod pass");
				GestoreModificaPassword.moduloModificaPassword();
				frame.dispose();
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem);
		
		JMenuItem mntmModificaDatiPersonali = new JMenuItem("Modifica Dati Personali");
		mntmModificaDatiPersonali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreModificaDati.lanciaModificaDati();
				frame.dispose();
			}
		});
		mnNewMenu_3.add(mntmModificaDatiPersonali);
	}
}