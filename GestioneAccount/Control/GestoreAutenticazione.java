package progetto.java.GestioneAccount.Control;

import javax.swing.JOptionPane;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.sqliteConnection;
import progetto.java.GestioneAccount.Interfacce.InterfacciaRecuperaPassword;
import progetto.java.Utils.Utente;

public class GestoreAutenticazione {
	public static boolean verificaCredenziali(String matricola, String password) {
		String[] utente = sqliteConnection.queryVerificaCredenziali(matricola, password);
		
		new Utente(utente[0], utente[2], utente[3]);
		if (!utente == null) {
			if (Utente.getRuolo() == 1) {
				InterfacciaDirettore.main(null);
			}else {
				InterfacciaImpiegato.main(null);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Matricola o password errati.");
		}
		return verifica;
	}
	public static void lanciaInterfacciaRecuperaPassword() {
		InterfacciaRecuperaPassword.main(null);
	}
	// verifica dati non segue il sequence
	public static boolean verificaDati(String matricola, String email) {
		boolean verifica = sqliteConnection.queryVerificaDati(matricola, email);
		if (verifica){
			sqliteConnection.queryRichiediPassword(matricola);
			return true;	
		}else{
			JOptionPane.showMessageDialog(null, "Dati errati");
			return false;
		}
		
	}
}
