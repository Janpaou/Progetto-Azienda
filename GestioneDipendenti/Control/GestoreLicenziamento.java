import java.sqliteConnection;
import java.GestioneDipendenti.Interfacce.Licenziamento;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GestoreLicenziamento {
          
	public static void lanciaLicenziamento(){
		
		//giorno e mese in cui si può licenziare;
		int mese=1;
		int giorno=1;
		
		Tempo t= new Tempo();
		boolean flag = t.checkData(mese, giorno);
		
		if(flag) {
			
			//crea InterfacciaLicenziamento passando dati presi prima
			Licenziamento.main(null);
		}else {
			
			JOptionPane.showMessageDialog(null, "Data non valida");
		}
	}
	public static ArrayList<Object> richiediDatiDipendenti() {
		//query richiedi nome cognome matricola e email di tutti i dipendenti
		return sqliteConnection.queryDatiDipendenti();
	}
	
	public static void licenzia(String matricola) {
		//queri elimina dipendente passando la matricola
		sqliteConnection.licenzia(matricola);
		//invia email
		//pannello conferma
	}
}
