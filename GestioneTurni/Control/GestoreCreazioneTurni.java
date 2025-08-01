package java.GestioneTurni.Control;

import java.Utils.Tempo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import progetto.java.sqliteConnection;

public class GestoreCreazioneTurni {
	public static void calcolaDataOra() {
		if (checkData() & checkOra()){
			ArrayList<String> matricole = sqliteConnection.queryMatricole();
			int servizio = 1;
			for (int i = 0; i < matricole.size(); i++) {
				System.out.println(matricole.get(i));	
				ArrayList<String> astensioni = sqliteConnection.queryAstenzioni(matricole.get(i));
				if (i+1  >= 10 & i+1 < 20){
					servizio = 2;
				}
				else if (i+1  >= 20 &  i+1 < 30){
					servizio = 3;
				}
				else if (i+1  >= 30 &  i+1 < 40){
					servizio = 4;
				}
				else{
					int dipRimanenti = matricole.size() - 40;
					if (i  >= dipRimanenti/4 & i < dipRimanenti/2 ){
						servizio = 2;
					}
					else if (i < dipRimanenti/4){
						servizio = 1;
					}
					else if (i  >= dipRimanenti/2 & i < dipRimanenti/2 + dipRimanenti/4){
						servizio = 3;
					}
					else {
						servizio = 4;
					}
				}
				boolean turni = CalcolaTurni(matricole.get(i), astensioni, servizio);
				if (turni) {
					System.out.println("Turni matricola correttamente caricati");
				}
				else {
					System.out.println("Impossibile caricare turni");
				}
			}
		}
	}
	public static boolean checkData() {
		if (Tempo.getDay().getDayOfMonth() == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean checkOra() {
		if (Tempo.getHour().equals(LocalTime.of(00, 05))) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean CalcolaTurni(String matricola, ArrayList<String> astensioni, int servizio) {
		DateTimeFormatter day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ArrayList<LocalDate> turni = new ArrayList<LocalDate>();
		LocalDate dataAttuale = LocalDate.now();
		int fineTrimestre = dataAttuale.getMonthValue() +3;
		LocalDate dataInizio;
		LocalDate dataFine;
		do {
			if (astensioni.isEmpty()) {
				dataInizio  = LocalDate.parse(dataAttuale.getYear()+1 +"-01-01", day);
				dataFine = LocalDate.parse(dataAttuale.getYear()+1 +"-01-01", day);
			}else {
				dataInizio = LocalDate.parse(astensioni.get(0), day);
				System.out.println(dataInizio);
				astensioni.remove(0);
				dataFine = LocalDate.parse(astensioni.get(0), day);
				System.out.println(dataFine);
				astensioni.remove(0);
			}
		while((dataAttuale.getMonthValue() < fineTrimestre) & (dataAttuale.isBefore(dataFine) || astensioni.isEmpty())) {
			if((dataAttuale.isBefore(dataInizio) || dataAttuale.isAfter(dataFine)) & dataAttuale.getDayOfWeek()!= DayOfWeek.SATURDAY & dataAttuale.getDayOfWeek()!= DayOfWeek.SUNDAY) {
				turni.add(dataAttuale);
			}
			dataAttuale = dataAttuale.plusDays(1);
		}
		} while (!astensioni.isEmpty());
		return sqliteConnection.inserireTurni(turni, matricola, servizio);
	} 
}
