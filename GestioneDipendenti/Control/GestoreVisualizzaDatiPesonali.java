package progetto.java.GestioneDipendenti.Control;

import progetto.java.sqliteConnection;
import progetto.java.GestioneDipendenti.Interfacce.DatiPersonali;
import progetto.java.Utils.Utente;

public class GestoreVisualizzaDatiPesonali {
	public static void GestoreVisualizzaDati() {
		String[] dati =sqliteConnection.queryMieiDati(Utente.getMatricola());
		DatiPersonali.main(null, dati);
	}
}
