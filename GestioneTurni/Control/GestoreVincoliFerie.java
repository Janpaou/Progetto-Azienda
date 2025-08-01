package java.GestioneTurni.Control;

import java.sqliteConnection;
import java.GestioneTurni.Interfacce.InterfacciaVincoli;
import java.Utils.Tempo;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class GestoreVincoliFerie {
	public static void checkData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate dataAttuale = LocalDate.parse(Tempo.dataAttuale(), dtf);
	    if (dataAttuale.getDayOfMonth() <= 14 & dataAttuale.getDayOfMonth()>= 1 & dataAttuale.getMonthValue() == 1){
	    	InterfacciaVincoli.main(null);
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "Impossibile inserire vincoli");
	    }
	}
	public static void creaVincolo(LocalDate dataInizio, LocalDate dataFine) {
		Date vincoloInizio = Date.valueOf((dataInizio));
		Date vincoloFine = Date.valueOf(dataFine);
		sqliteConnection.queryVincolo(vincoloInizio, vincoloFine);
	}
}
