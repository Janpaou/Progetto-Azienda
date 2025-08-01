package progetto.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import progetto.java.GestioneAccount.Control.GestoreModificaPassword;
import progetto.java.GestioneAccount.Interfacce.ModificaDatiPersonali;
import progetto.java.GestioneAccount.Interfacce.ModuloLogin;
import progetto.java.GestioneDipendenti.Control.GestoreAssunzione;
import progetto.java.GestioneDipendenti.Control.GestoreLicenziamento;
import progetto.java.GestioneDipendenti.Control.GestoreTurni;
import progetto.java.GestioneDipendenti.Control.GestoreVisualizzaDatiPesonali;
import progetto.java.GestioneDipendenti.Interfacce.VisualizzaImpiegati;
import progetto.java.GestioneTurni.Control.GestoreVincoliFerie;
import progetto.java.GestioneTurni.Interfacce.AssPcPar;
import progetto.java.Utils.Utente;
import java.awt.Color;
import javax.swing.ImageIcon;    

public class InterfacciaDirettore {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaDirettore window = new InterfacciaDirettore();
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
	
	public InterfacciaDirettore() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(InterfacciaImpiegato.class.getResource("/progetto/images/Immagine.png")));
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Interfaccia Direttore");
		frame.move(550, 250);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		DateTimeFormatter giorno = DateTimeFormatter.ofPattern("dd");
		DateTimeFormatter mese = DateTimeFormatter.ofPattern("MM");
		JButton Logout = new JButton("Logout");
		Logout.setBounds(367, 0, 85, 21);
		Logout.setBackground(new Color(255, 255, 255));
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valore = JOptionPane.showConfirmDialog(null, "Verrai disconnesso, desideri continuare?", null, 0);
				if (valore == 0) {
					ModuloLogin.main(null);
					frame.dispose();
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Benvenuto " + Utente.getNome() + ".");
		lblNewLabel.setBounds(15, 0, 230, 30);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel(dtf.format(now));
		lblNewLabel_1.setBounds(390, 218, 54, 13);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		
		JLabel background;
		background = new JLabel("", JLabel.CENTER);
		background.setBackground(new Color(240, 240, 240));
		background.setIcon(new ImageIcon(InterfacciaImpiegato.class.getResource("/progetto/images/1673691197131-thumbnail.jpeg")));
		background.setBounds(-14, 0, 450, 241);
		frame.getContentPane().add(background);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		background.add(lblNewLabel);
		background.add(Logout);
		background.add(lblNewLabel_1);
		
		
		
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
		
		JMenu mnGestioneImpiegati = new JMenu("Gestione Dipendenti");
		menuBar.add(mnGestioneImpiegati);
		
		JMenuItem mntmVisualizzazioneDatiImpiegati = new JMenuItem("Visualizza Dati Dipendenti");
		mntmVisualizzazioneDatiImpiegati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizzaImpiegati.main(null);
				frame.dispose();
			}
		});
		mnGestioneImpiegati.add(mntmVisualizzazioneDatiImpiegati);
		
		JMenuItem mntmVisualizzazioneTurni = new JMenuItem("Visualizzazione Turni");
		mntmVisualizzazioneTurni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreTurni.lanciaInterfacciaTurniDir();
			}
		});
		mnGestioneImpiegati.add(mntmVisualizzazioneTurni);
		
		JMenuItem mntmAssunzioneDipendente = new JMenuItem("Assunzione Dipendente");
		mntmAssunzioneDipendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreAssunzione.lanciaInterfacciaAssunzione();
			}
		});
		mnGestioneImpiegati.add(mntmAssunzioneDipendente);
		
		JMenuItem mntmLicenziamentoDipendente = new JMenuItem("Licenziamento Dipendente");
		mntmLicenziamentoDipendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestoreLicenziamento.lanciaLicenziamento();
			}
		});
		mnGestioneImpiegati.add(mntmLicenziamentoDipendente);
		
		JMenu mnNewMenu_2 = new JMenu("Turni");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmFerie = new JMenuItem("Vincoli Ferie");
		mntmFerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Integer.parseInt(giorno.format(now)));
				if (Integer.parseInt(giorno.format(now)) >= 1 & Integer.parseInt(giorno.format(now)) <= 14 & Integer.parseInt(mese.format(now))==1){
					GestoreVincoliFerie.lanciaVincolo();
				} else {
					JOptionPane.showMessageDialog(null, "Impossibile inserire vincoli ferie");
				}
			}
		});
		mnNewMenu_2.add(mntmFerie);
		
		JMenuItem mntmComunicaAssenzaPer = new JMenuItem("Comunica Assenza per congedo parentale");
		mntmComunicaAssenzaPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssPcPar.main(null);
				frame.dispose();
			}
		});
		System.out.println(Integer.parseInt(mese.format(now)));
		if (Integer.parseInt(giorno.format(now)) > 21 & Integer.parseInt(giorno.format(now)) < 28 ) {
			if(Integer.parseInt(mese.format(now))==3 || Integer.parseInt(mese.format(now))==6 || Integer.parseInt(mese.format(now))==9 || Integer.parseInt(mese.format(now))==12) {
				mnNewMenu_2.add(mntmComunicaAssenzaPer);
			}
			
		}
		
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
				ModificaDatiPersonali.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_3.add(mntmModificaDatiPersonali);
	}
}