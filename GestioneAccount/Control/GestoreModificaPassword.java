package progetto.java.GestioneAccount.Control;

import javax.swing.JOptionPane;

import progetto.java.InterfacciaDirettore;
import progetto.java.InterfacciaImpiegato;
import progetto.java.sqliteConnection;
import progetto.java.GestioneAccount.Interfacce.ModificaPassword;
import progetto.java.Utils.Utente;

public class GestoreModificaPassword {
	public static void lanciaModuloModificaPassword() {
		ModificaPassword.main(null);
	}
	public static boolean modificaPsw(String old_psw, String new_psw, String re_psw) {
		if (new_psw.equals(re_psw)) {
			String verifica = sqliteConnection.querycontrollaPsw(old_psw);
			if (verifica.equals(old_psw)) {
				if (modificaPsw(new_psw)) {
					boolean flag = sqliteConnection.modificaPsw(password);
					if (verifica) {
						JOptionPane.showMessageDialog(null, "Password aggiornata.");
						if (Utente.getRuolo()==1) {
							InterfacciaDirettore.main(null);;
						}else {
						InterfacciaImpiegato.main(null);
						}
					return true;
					}
				}
				else {  
				//messaggio di errore
					JOptionPane.showMessageDialog(null, "La vecchia password non coincide.");
				}
			}
			else {
			//messaggio di errore
				JOptionPane.showMessageDialog(null, "Le due password non coincidono.");
			}
			return false;
		}
}
