package progetto.java.GestioneAccount.Interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import progetto.java.GestioneAccount.Control.GestoreAutenticazione;

public class ModuloLogin {

	private JFrame frmLogin;
	private JTextField Matricola;
	private JPasswordField Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModuloLogin window = new ModuloLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModuloLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.move(550, 250);
		
		JLabel lblNewLabel = new JLabel("Matricola");
		
		Matricola = new JTextField();
		Matricola.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		
		Password = new JPasswordField();
		Password.setColumns(10);
		
		
		
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Il pulsante funziona");
				String matricola = Matricola.getText();
				String password = Password.getText();
				//Connection to Control
				boolean verifica = GestoreAutenticazione.verificaCredenziali(matricola, password);
				if (verifica) {
					frmLogin.dispose();
				}
				
			}
		});
		
		JButton forPass = new JButton("Recupera Password");
		forPass.setBackground(new Color(255, 255, 255));
		forPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				GestoreAutenticazione.interfacciaRecuperaPassword();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(105)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(Matricola)
					.addGap(165))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(105)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(Password)
					.addGap(165))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(175)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					.addGap(165))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(146)
					.addComponent(forPass, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(Matricola, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1))
						.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(forPass)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		frmLogin.getContentPane().setLayout(groupLayout);
		

		
	}
}
