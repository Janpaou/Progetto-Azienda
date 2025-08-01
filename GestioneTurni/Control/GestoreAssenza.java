package java.GestioneTurni.Control;

import java.sqliteConnection;
import java.Utils.Tempo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
public class GestoreAssenza {
      

	public static boolean lanciaGestoreAssenza(){
		return checkOra();
	}
	
	public static void verificaData(String matricola,LocalDate data,String motivo) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(data.isAfter(LocalDate.parse(Tempo.dataAttuale(), formatter))) {
			sqliteConnection.queryMemorizzaAssenza(matricola, data, motivo);
			JOptionPane.showMessageDialog(null, "Assenza inserita");
		}else {
			JOptionPane.showMessageDialog(null, "Impossibile inserire assenza");
		}
	}
	public static boolean checkOra() {
		LocalTime oraFine = LocalTime.of(23, 59);
		LocalTime oraInizio = LocalTime.of(8, 00);
		return Tempo.getHour().isBefore(oraFine) & Tempo.getHour().isBefore(oraInizio);
	}
}
