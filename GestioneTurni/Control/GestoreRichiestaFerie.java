package java.GestioneTurni.Control;

import java.InterfacciaImpiegato;
import java.sqliteConnection;
import java.GestioneTurni.Interfacce.InterfacciaFerie;
import java.Utils.Tempo;
import java.Utils.Utente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class GestoreRichiestaFerie {
	public static void checkData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate dataAttuale = LocalDate.parse(Tempo.dataAttuale(), dtf);
	    int mese = dataAttuale.getMonthValue();
	    if (dataAttuale.getDayOfMonth() >= 21 & dataAttuale.getDayOfMonth()<= 28){
	    	if (mese == 3 || mese == 6 || mese == 9 || mese== 12) {
	    		InterfacciaFerie.main(null);	
	    	}
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "Impossibile inserire ferie");
	    }
	}
	public static boolean creaFerie(LocalDate dataInizio, LocalDate dataFine) {
		int giorniRichiesti = dataInizio.compareTo(dataFine)+1;
		int ferieDisponibili = sqliteConnection.queryFerieDisponibili(Utente.getMatricola());
		String[] vincoli = sqliteConnection.queryVincoli();
		DateTimeFormatter dataFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate vincoliInizio = LocalDate.parse(vincoli[0], dataFmt);
		LocalDate vincoliFine = LocalDate.parse(vincoli[1], dataFmt);
		int diffFerie = ferieDisponibili - giorniRichiesti;  
		if (diffFerie < 0 ) {
			if (vincoliInizio.compareTo(dataInizio) >= 0 & vincoliInizio.compareTo(dataFine) <= 0) {
				JOptionPane.showMessageDialog(null, "Un vincolo ferie non permette di inserire le date");
			}else if (vincoliFine.compareTo(dataInizio) >= 0 & vincoliFine.compareTo(dataFine) <= 0) {
				JOptionPane.showMessageDialog(null, "Un vincolo ferie non permette di inserire le date");
			}
			else if (dataInizio.isAfter(vincoliInizio) & dataInizio.isBefore(vincoliFine)) {
				JOptionPane.showMessageDialog(null, "Un vincolo ferie non permette di inserire le date");
			}
			else if (giorniRichiesti < 1) {
				JOptionPane.showMessageDialog(null, "La date di fine è minore della data di inizio");
			}
			else {
				sqliteConnection.aggiugiFerie(Utente.getMatricola(), dataInizio, dataFine, diffFerie);
				InterfacciaImpiegato.main(null);
				return true;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Giorni di ferie disponibili insufficienti");
		}
		return false;
	}
}
