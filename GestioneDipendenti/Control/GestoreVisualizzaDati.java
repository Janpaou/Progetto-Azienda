package java.GestioneDipendenti.Control;

import java.sqliteConnection;
import java.GestioneDipendenti.Interfacce.VisualizzaImpiegati;
import java.util.ArrayList;

public class GestoreVisualizzaDati {
	public static void lanciaVisualizzaDati() {
		VisualizzaImpiegati.main(null);
	}
	public static ArrayList<Object> richiediDatiDipendenti() {
		//query richiedi nome cognome matricola e email di tutti i dipendenti
		return sqliteConnection.queryDatiDipendenti();
	}
}
