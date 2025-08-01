package java.GestioneDipendenti.Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import progetto.java.sqliteConnection;
import progetto.java.Utils.Tempo;

public class CalcoloStipendio {
	public static void calcoloStipendio() {
		ArrayList<Double> stipendio;
		ArrayList<String> matricole = java.sqliteConnection.queryMatricole();
		
		if (checkData().getDayOfMonth() == 15) {
			while(!matricole.isEmpty()) {
				double stipendioPrec = sqliteConnection.queryRichiediStipendio(matricole.get(0));
				String email = java.sqliteConnection.richiestaEmail(matricole.get(0));
				sqliteConnection.queryMemorizzaStipendio(matricole.get(0), stipendioPrec);
				//richiedi email
				//invia email
				matricole.remove(0);
			}
		}
		else if (checkData().getDayOfMonth() == 1){
			if (checkData().getMonthValue() == 1) {
				stipendio =sqliteConnection.queryStipendio(checkData().getMonthValue(), checkData().getYear(), true);
				while(!stipendio.isEmpty()) {
					sqliteConnection.queryMemorizzaStipendio(matricole.get(0), stipendio.get(0));
					matricole.remove(0);
					stipendio.remove(0);
				}
			}else {
				stipendio = sqliteConnection.queryStipendio(checkData().getMonthValue(), checkData().getYear(), false);
				while(!stipendio.isEmpty()) {
					sqliteConnection.queryMemorizzaStipendio(matricole.get(0), stipendio.get(0));
					matricole.remove(0);
					stipendio.remove(0);
				}
			}
		}
	}
	public static LocalDate checkData() {
		return LocalDate.now();
	}
	public static LocalTime checkOra() {
		return LocalTime.now();
	}
}
