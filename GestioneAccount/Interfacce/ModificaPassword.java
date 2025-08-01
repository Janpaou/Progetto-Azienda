package progetto.java.GestioneAccount.Interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.GestioneAccount.Control.GestoreModificaPassword;
import progetto.java.Utils.Utente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificaPassword {

	private JFrame frame;
	private JTextField oldPw;
	private JTextField newPw1;
	private JTextField newPw2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaPassword window = new ModificaPassword();
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
	public ModificaPassword() {
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
		frame.setTitle("Modifica Password");
		frame.move(550, 250);
		
		JLabel lblNewLabel = new JLabel("Vecchia Password");
		lblNewLabel.setBounds(42, 39, 113, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nuova Password");
		lblNewLabel_1.setBounds(42, 84, 113, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nuova Password");
		lblNewLabel_2.setBounds(42, 120, 113, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		oldPw = new JPasswordField();
		oldPw.setBounds(165, 36, 96, 19);
		frame.getContentPane().add(oldPw);
		oldPw.setColumns(10);
		
		newPw1 = new JPasswordField();
		newPw1.setBounds(165, 78, 96, 19);
		frame.getContentPane().add(newPw1);
		newPw1.setColumns(10);
		
		newPw2 = new JPasswordField();
		newPw2.setBounds(165, 117, 96, 19);
		frame.getContentPane().add(newPw2);
		newPw2.setColumns(10);
		
		JButton btnNewButton = new JButton("Conferma nuova password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verifica = GestoreModificaPassword.modificaPsw(oldPw.getText(), newPw1.getText(), newPw2.getText());
				if (verifica) {
					frame.dispose();
				}
			}
		});
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
		btnNewButton.setBounds(165, 197, 96, 21);
		frame.getContentPane().add(btnNewButton);
	}

}
