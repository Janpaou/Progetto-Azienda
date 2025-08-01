package java.GestionePresenze.Control;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import progetto.java.sqliteConnection;
import progetto.java.Utils.Tempo;

public class GestorePresenza {
	public static String oraInizio;
	public static LocalTime oraInizioMax;
	public static String oraFine;
	public void prendiDatiI(String nome, String cognome, String matricola) {
		if (sqliteConnection.checkPresenza(nome, cognome, matricola)) {
			if (sqliteConnection.verificaPresenza(matricola, Tempo.getDay())) {
				checkTurnoIngresso(matricola);
			}
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Dati errati");
		}
	}
	public void prendiDatiU(String nome, String cognome, String matricola) {
		if (sqliteConnection.checkPresenza(nome, cognome, matricola)) {
			if (sqliteConnection.verificaPresenza(matricola, Tempo.getDay())) {
				checkTurnoUscita(matricola);
			}
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Dati errati");
		}
	}
	public boolean checkTurnoIngresso(String matricola) {
		oraInizio = sqliteConnection.checkOraInizioTurno(Tempo.dataAttuale(), matricola);
		System.out.println(oraInizio);
		if (oraInizio == null) {
			return false;
		}
		String oraAttuale = Tempo.oraAttuale();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime oraInizioCon = LocalTime.parse(oraInizio, dtf);
		LocalTime oraAttualeCon = LocalTime.parse(oraAttuale, dtf);
		oraInizioMax = oraInizioCon.plusMinutes(10);
		if (oraAttualeCon.isAfter(oraInizioCon) & oraAttualeCon.isBefore(oraInizioCon.plusMinutes(10))) {
			JOptionPane.showInternalMessageDialog(null, "Hai correttamente segnalato la presenza");
			return true;
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Oggi non sei abilitato a firmare");
			return false;
		}
	} 
	public boolean checkTurnoUscita(String matricola) {
		oraFine = sqliteConnection.checkOraFineTurno(Tempo.dataAttuale(), matricola);
		System.out.println(oraFine);
		if (oraFine == null) {
			return false;
		}
		String oraAttuale = Tempo.oraAttuale();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime oraFineCon = LocalTime.parse(oraFine, dtf);
		LocalTime oraAttualeCon = LocalTime.parse(oraAttuale, dtf);
		if (oraAttualeCon.isAfter(oraFineCon)) {
			JOptionPane.showInternalMessageDialog(null, "Hai correttamente segnalato l'uscita");
			return true;
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Ora non sei abilitato a firmare");
			return false;
		}
	}
	
}
