package progetto.java.GestioneAccount.Interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import progetto.java.GestioneAccount.Control.GestoreAutenticazione;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfacciaRecuperaPassword {

	public JFrame frmInterfacciaRecuperaPassword;
	private JTextField MatricolaP;
	private JTextField EmailP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaRecuperaPassword window = new InterfacciaRecuperaPassword();
					window.frmInterfacciaRecuperaPassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacciaRecuperaPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	void initialize() {
		frmInterfacciaRecuperaPassword = new JFrame();
		frmInterfacciaRecuperaPassword.setTitle("Interfaccia Recupera Password");
		frmInterfacciaRecuperaPassword.setBounds(100, 100, 450, 300);
		frmInterfacciaRecuperaPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInterfacciaRecuperaPassword.getContentPane().setLayout(null);
		frmInterfacciaRecuperaPassword.move(550, 250);
		
		JLabel LabelMatricola = new JLabel("Matricola");
		LabelMatricola.setBounds(97, 43, 57, 13);
		frmInterfacciaRecuperaPassword.getContentPane().add(LabelMatricola);
		
		MatricolaP = new JTextField();
		MatricolaP.setBounds(154, 40, 149, 19);
		frmInterfacciaRecuperaPassword.getContentPane().add(MatricolaP);
		MatricolaP.setColumns(10);
		
		JLabel LabelEmail = new JLabel("Email");
		LabelEmail.setBounds(97, 82, 45, 13);
		frmInterfacciaRecuperaPassword.getContentPane().add(LabelEmail);
		
		EmailP = new JTextField();
		EmailP.setBounds(154, 79, 149, 19);
		frmInterfacciaRecuperaPassword.getContentPane().add(EmailP);
		EmailP.setColumns(10);
		
		JButton Conferma = new JButton("Conferma");
		Conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricola = MatricolaP.getText();
				String email = EmailP.getText();
				boolean esito = GestoreAutenticazione.verificaDati(matricola, email);
				if (esito) {
					frmInterfacciaRecuperaPassword.dispose();
				}
			}
		});
		Conferma.setBounds(179, 141, 96, 21);
		frmInterfacciaRecuperaPassword.getContentPane().add(Conferma);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmInterfacciaRecuperaPassword.dispose();
				ModuloLogin.main(null);
			}
		});
		Indietro.setBounds(0, 232, 85, 21);
		frmInterfacciaRecuperaPassword.getContentPane().add(Indietro);
	}
}
