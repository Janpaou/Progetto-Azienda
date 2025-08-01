package java.GestioneAccount.Control;

import java.sqliteConnection;
import java.GestioneAccount.Interfacce.ModificaDatiPersonali;

public class GestoreModificaDati {
	public static void lanciaModificaDati() {
		ModificaDatiPersonali.main(null);
	}
	public static String[] richiediDatiPersonali() {
		return sqliteConnection.richiediDatiPersonali();
	}
	public static void modificaDato(String valore, String dato){
		sqliteConnection.modificaDato(valore, dato);
	}
}
