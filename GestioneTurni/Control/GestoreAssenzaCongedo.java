package java.GestioneTurni.Control;

import java.sqliteConnection;
import java.GestioneTurni.Interfacce.InterfacciaAssenza;
import java.Utils.Tempo;
import java.Utils.Utente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class GestoreAssenzaCongedo {
	public static boolean checkData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate dataAttuale = LocalDate.parse(Tempo.dataAttuale(), dtf);
	    int mese = dataAttuale.getMonthValue();
	    if (dataAttuale.getDayOfMonth() >= 21 & dataAttuale.getDayOfMonth()<= 28){
	    	if (mese == 3 || mese == 6 || mese == 9 || mese== 12) {
	    		InterfacciaAssenza.main(null);
	    		return true;
	    	}
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "Impossibile inserire ferie");
	    }
	    return false;
	}
	public static boolean creaPeriodo(LocalDate dataInizio, LocalDate dataFine) {
		int valore =verificaValiditaCongedi(giorniRichiesti(dataInizio, dataFine), sqliteConnection.queryCongediDisponibili(Utente.getMatricola()));
		if (valore == 0) {
			JOptionPane.showMessageDialog(null, "La date di fine è minore della data di inizio");
			return false;
		}
		else if (valore == 1) {
			JOptionPane.showMessageDialog(null, "Non sono disponibili ulteriori congedi parentali");
			return false;
		}
		else {
			sqliteConnection.aggiungiCongediParentali(Utente.getMatricola(), dataInizio, dataFine);
			JOptionPane.showMessageDialog(null, "Le assenze sono state caricate correttamente.");
			return true;
		}
	}
	public static int giorniRichiesti(LocalDate dataInizio, LocalDate dataFine) {
		return dataInizio.compareTo(dataFine)+1;
	}
	public static int verificaValiditaCongedi(int giorniRichiesti, int congediDisponibili) {
		if (giorniRichiesti < 1) {
			return 0;
		}
		else if ((giorniRichiesti - congediDisponibili) < 0){
			return 1;
		}
		else {
			return 2;
		}
	}
}
