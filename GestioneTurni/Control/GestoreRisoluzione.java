package java.GestioneTurni.Control;

import java.sqliteConnection;
import java.Utils.Tempo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GestoreRisoluzione {
	public static boolean checkOra() {
		if (Tempo.getHour().equals(LocalTime.of(00, 10))) {
			return true;
		}
		else {
			return false;
		}
	}
	public static LocalDate checkData() {
		return Tempo.getDay();
	}
	public static void chiediOrario() {
		if (checkOra()){
			ArrayList<String> dipAss= sqliteConnection.queryDipendentiAssenti(checkData());
			while (!dipAss.isEmpty()) {
				int servizioDip = sqliteConnection.queryServizioDip(dipAss.get(0), checkData());
				ArrayList<String> dipDisp = sqliteConnection.queryDipendentiDisponibili(checkData(), servizioDip);
				ArrayList<String> dipInTurDisp = sqliteConnection.queryDipendentiInTurnoDisponibili(checkData(), servizioDip);
				if (!dipDisp.isEmpty()) {
					int random = sceltaCasuale(dipDisp);
					sqliteConnection.richiestaEmail(dipDisp.get(0));
					sqliteConnection.modificaTurni(dipAss.get(0), dipInTurDisp.get(random));
					//inviaEmail();
					dipAss.remove(0);
					dipDisp.remove(0);
				}
				else if (!dipInTurDisp.isEmpty()) {
					int random = sceltaCasuale(dipInTurDisp);
					sqliteConnection.richiestaEmail(dipInTurDisp.get(0));
					sqliteConnection.modificaTurni(dipAss.get(0), dipInTurDisp.get(random));
					//inviaEmail();
					dipAss.remove(0);
					dipInTurDisp.remove(0);
				}
				else {
					GestoreChiusura.chiusuraServizio();
				}
			}
		}
	}
	public static int sceltaCasuale(ArrayList<String> lista) {
		return ThreadLocalRandom.current().nextInt(0, lista.size());
	}
}
