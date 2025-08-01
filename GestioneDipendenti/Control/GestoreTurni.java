package progetto.java.GestioneDipendenti.Control;

import progetto.java.sqliteConnection;
import progetto.java.GestioneDipendenti.Interfacce.VisualizzaTurniDip;
import progetto.java.GestioneDipendenti.Interfacce.VisualizzaTurniDir;

public class GestoreTurni {
	public static void lanciaInterfacciaTurniDip() {
		VisualizzaTurniDip.main(null);
		
	}
	public static void mostraTabellaDip() {
		VisualizzaTurniDip.mostraTabella(sqliteConnection.queryTurniDip());
	}
	public static void lanciaInterfacciaTurniDir() {
		VisualizzaTurniDir.main(null);
	}
	public static void mostraTabellaDir() {
		VisualizzaTurniDir.mostraTabella(sqliteConnection.queryTurniDir());
	}
}
