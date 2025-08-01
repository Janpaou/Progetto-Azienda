package progetto.java.GestioneDipendenti.Interfacce;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import progetto.java.InterfacciaImpiegato;
import progetto.java.GestioneDipendenti.Control.GestoreTurni;

public class VisualizzaTurniDip {

	private JFrame frame;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaTurniDip window = new VisualizzaTurniDip();
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
	public VisualizzaTurniDip() {
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
		frame.setTitle("Interfaccia Turni");
		frame.move(550, 250);
		//PulsanteIndietro
				JButton Indietro = new JButton("Indietro");
				Indietro.setBounds(0, 232, 85, 21);
				Indietro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						InterfacciaImpiegato.main(null);
					}
				});
				frame.getContentPane().setLayout(null);
				frame.getContentPane().add(Indietro);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 10, 416, 212);
				frame.getContentPane().add(scrollPane);
				
				table = new JTable();
				table.setEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Servizio", "Data", "Ora Inizio", "Ora Fine"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(81);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(1).setPreferredWidth(81);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(2).setPreferredWidth(115);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(3).setPreferredWidth(88);
				scrollPane.setViewportView(table);
				GestoreTurni.mostraTabellaDip();
				
	}
	public static void mostraTabella(ArrayList<Object[]> turni) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		while(!turni.isEmpty()) {
			model.addRow(turni.get(0));
			turni.remove(0);
	}
	}
}
