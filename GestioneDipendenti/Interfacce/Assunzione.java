package progetto.java.GestioneDipendenti.Interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;

import progetto.java.InterfacciaDirettore;
import progetto.java.GestioneDipendenti.Control.GestoreAssunzione;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

public class Assunzione {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String sesso;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assunzione window = new Assunzione();
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
	public Assunzione() {
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
		frame.setTitle("Interfaccia Assunzione");
		frame.getContentPane().setLayout(null);
		frame.move(550, 250);
		
		//PulsanteIndietro
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(0, 232, 85, 21);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				InterfacciaDirettore.main(null);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(Indietro);
		
		JButton btnNewButton_1 = new JButton("Conferma");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField_1.getText();
				String cognome= textField_2.getText();
				String email = textField_3.getText();
				String password = textField_4.getText();
				Date data = dateChooser.getDate();
				if (rdbtnNewRadioButton.isSelected()) {
					sesso = "M";
				}else {
					sesso = "F";
				}
				GestoreAssunzione.setDati(nome, cognome, data, email, password, sesso);
			}
		});
		btnNewButton_1.setBounds(148, 202, 107, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(134, 17, 144, 19);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(134, 46, 144, 19);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(134, 75, 144, 19);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(134, 104, 144, 19);
		frame.getContentPane().add(textField_4);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(40, 20, 84, 13);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(40, 49, 84, 13);
		frame.getContentPane().add(lblCognome);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(40, 78, 84, 13);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(40, 107, 84, 13);
		frame.getContentPane().add(lblPassword);
		
		rdbtnNewRadioButton = new JRadioButton("Maschio");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
			}
		});
		rdbtnNewRadioButton.setBounds(134, 129, 74, 21);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Femmina");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(false);
			}
		});
		rdbtnNewRadioButton_1.setBounds(210, 129, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel = new JLabel("Sesso");
		lblNewLabel.setBounds(40, 133, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(134, 156, 144, 19);
		frame.getContentPane().add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Data di Nascita");
		lblNewLabel_1.setBounds(40, 156, 84, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}
}
