package java.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tempo {
	
	public static String oraAttuale() {
		DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm");
		String ora = hour.format(LocalDateTime.now());
		return ora;
	}
	public static String dataAttuale() {
		DateTimeFormatter day = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String giorno = day.format(LocalDateTime.now());
		return giorno;
	}
	public static boolean checkData(int mese,int giorno) {
		
		LocalDate dataOdierna = LocalDate.now();
		int anno= dataOdierna.getYear();
		LocalDate localDate = LocalDate.of(anno, mese, giorno);
		boolean y = dataOdierna.equals(localDate);
		return y;
		}
	public static LocalTime getHour() {
		return LocalTime.now();
	}
	public static LocalDate getDay() {
		return LocalDate.now();
	}
}
