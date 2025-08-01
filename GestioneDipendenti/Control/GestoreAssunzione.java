package progetto.java.GestioneDipendenti.Control;

import java.util.Date;

import javax.swing.JOptionPane;

import progetto.java.sqliteConnection;
import progetto.java.GestioneDipendenti.Interfacce.Assunzione;
import progetto.java.Utils.Dipendente;
import progetto.java.Utils.Tempo;

public class GestoreAssunzione {
	public static void lanciaInterfacciaAssunzione() {
   	 
   	 //Data in cui io posso assumere
   	 int mese=12;
   	 int giorno=30;
   	 
   	 Tempo tempo= new Tempo();
   	 
   	 if(tempo.checkData(mese, giorno)) {
   		 Assunzione.main(null);
   	 }else {
   		 JOptionPane.showMessageDialog(null, "Impossibile assumere");
   	 }
	}
   	 
   	 public static void setDati(String nome,String cognome, Date data, String email, String password, String sesso) {
   		 Dipendente dip = new Dipendente(nome, cognome, data, email, password, sesso);
   		 boolean flag = sqliteConnection.cercaDipendente(dip);
   		
   		
   		 if(flag ==false) {
   			sqliteConnection.caricaDipendente(dip);
   			
   			 
   			 //invia email;
   			 JOptionPane.showMessageDialog(null, "Operazione avvenuta con successo");
   		 }else {
   			
   			JOptionPane.showMessageDialog(null, "Impossibile aggiungere dipendente");
   		 }
   	   
}
}
