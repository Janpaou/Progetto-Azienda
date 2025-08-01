package java.GestioneDipendenti.Interfacce;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.InterfacciaDirettore;
import java.sqliteConnection;
import java.GestioneDipendenti.Control.GestoreLicenziamento;

public class Licenziamento {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Licenziamento window = new Licenziamento();
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
	public Licenziamento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "deprecation", "serial" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Interfaccia Licenziamento");
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
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 10, 416, 212);
				frame.getContentPane().add(scrollPane);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Matricola", "Nome", "Cognome", "Email", "Città"
					}
				) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, Integer.class
					};
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(49);
				table.getColumnModel().getColumn(1).setPreferredWidth(57);
				table.getColumnModel().getColumn(2).setPreferredWidth(52);
				table.getColumnModel().getColumn(3).setPreferredWidth(140);
				table.getColumnModel().getColumn(4).setPreferredWidth(35);
				scrollPane.setViewportView(table);
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				ArrayList<Object> dip = GestoreLicenziamento.richiediDatiDipendenti();
				while(!dip.isEmpty()) {
					model.addRow(new Object [] {dip.get(0)});
					dip.remove(0);
				}
				
				JButton btnNewButton = new JButton("Licenzia");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String matricola = (String) table.getValueAt(table.getSelectedRow(), 0);
						GestoreLicenziamento.licenzia(matricola);
					}
				});
				btnNewButton.setBounds(161, 228, 117, 29);
				frame.getContentPane().add(btnNewButton);
	}
}
