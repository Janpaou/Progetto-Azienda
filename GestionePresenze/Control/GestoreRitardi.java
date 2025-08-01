package java.GestionePresenze.Control;

import java.sqliteConnection;
import java.Utils.Tempo;
import java.Utils.Utente;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

public class GestoreRitardi {
	public static LocalDate checkData() {
		return Tempo.getDay();
	}
	public static LocalTime checkOra() {
		return Tempo.getHour();
	}
	public static void lanciaInterfacciaRitardi() {
		if (sqliteConnection.verificaPresenza(Utente.getMatricola(), checkData())) {
			LocalTime oraInizio = sqliteConnection.oraInizioturno(Utente.getMatricola(), checkData());
			if (verificaOra(oraInizio)) {
				sqliteConnection.registraRitardo(Utente.getMatricola(), checkData());
				JOptionPane.showMessageDialog(null, "Hai registrato il ritardo");
			}
			else {
				sqliteConnection.registraIngresso(Utente.getMatricola(), checkData(), checkOra());
				JOptionPane.showMessageDialog(null, "Hai registrato la presenza");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Non risulti in turno oggi");
		}
	}
	public static boolean verificaOra(LocalTime oraInizio) {
		return checkOra().isAfter(oraInizio.plusMinutes(10));
	}
}
