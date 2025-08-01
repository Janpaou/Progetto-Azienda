package progetto.java.GestioneDipendenti.Control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import progetto.java.sqliteConnection;
import progetto.java.Utils.Tempo;

public class GestoreReset {
	public static void GestoreResetDipendenti() {
	DateTimeFormatter hourDTF = DateTimeFormatter.ofPattern("HH:mm");
	DateTimeFormatter dayDTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String oraAttuale = Tempo.oraAttuale();
	String dataAttuale = Tempo.dataAttuale();
	LocalDate date = LocalDate.parse(dataAttuale, hourDTF);
	LocalTime hour = LocalTime.parse(oraAttuale, dayDTF);
	
	if (date.getDayOfMonth()== 31 & date.getMonthValue()== 10 & hour.equals(LocalTime.parse("01:05", hourDTF))) {
		//reset ferie e congedi
		sqliteConnection.resetFerie();
	}
	if (date.getDayOfMonth()== 1 & date.getMonthValue()== 1 & hour.equals(LocalTime.parse("00:05", hourDTF))) {
		//reset ritardi e assenze
		sqliteConnection.resetRitAss();
	}
	if (date.getDayOfWeek()== DayOfWeek.SUNDAY) {
		//reset straordinari
		sqliteConnection.resetStraordinari();
	}
	}
}
