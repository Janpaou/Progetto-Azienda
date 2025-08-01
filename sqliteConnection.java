package java;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import GestioneAccount.Interfacce.ModuloLogin;
import java.Utils.Dipendente;
import java.Utils.Tempo;
import java.Utils.Utente;

public class sqliteConnection {
	private static void erroreComunicazioneDBMS(Exception e) {
		System.out.println(e);
        JOptionPane.showMessageDialog(null, "Errore comunicazione con il database.");
    }
	
	public static Connection dbConnector() {
		Connection conn= null;
		try {
			String url = "jdbc:sqlite:sqlite.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			return conn;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	public static boolean checkPresenza(String nome, String cognome, String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from dati_personali d where d.matricola='"+ matricola +"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
            	String nomeQuery = result.getString("nome");
            	String cognomeQuery = result.getString("cognome");
            	if (nomeQuery.toLowerCase().equals(nome.toLowerCase()) & cognomeQuery.toLowerCase().equals(cognome.toLowerCase())) {
                	return true;
                }
            }
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			return false;
		}
		return false;
	}
	public static String checkOraInizioTurno(String giorno, String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from turno where data_turno='"+ giorno +"' and matricola='" +matricola+ "'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            	String ora_inizio = result.getString("ora_inizio");
            	return ora_inizio;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			
		}
		return null;
	}
	public static String checkOraFineTurno(String giorno, String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from turno where data_turno='"+giorno+"' and matricola='" +matricola+ "'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            	String ora_fine = result.getString("ora_fine");
            	return ora_fine;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			
		}
		return null;
	}
	public static boolean cercaDipendente(Dipendente dip) {
		Connection conn = sqliteConnection.dbConnector();
		int m = getIntMaxMatricola();
		String sql = "select matricola from utente";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	for (int i = 1; i < m; i++) {
            		if (result.getString(i).equals(dip.getMatricola())) {
            			conn.close();
                        return false;
            		}
            	}
            }
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			
		}
		return true;
	}

	public static boolean caricaDipendente(Dipendente dip) {
		Connection conn = sqliteConnection.dbConnector();
		String sqlDati  ="insert into dati_personali values('"+dip.getMatricola()+"', '"+dip.getNome()+"', '"+dip.getCognome()+"', '', '', '', '"+dip.getData()+"', '', '"+dip.getSesso()+"')";
		System.out.println(sqlDati);
		String sqlUtente =  "insert into utente values('"+dip.getMatricola()+"', '"+dip.getEmail()+"', '"+dip.getPassword()+"', '0')";
		System.out.println(sqlUtente);
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sqlDati);
            statement.executeUpdate(sqlUtente);
            conn.close();
            return true;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			
		}
		return false;
	}
	public static int getIntMaxMatricola() {
		Connection conn = sqliteConnection.dbConnector();
		String sql = "select count(matricola) from utente";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int numMaxMatricola = result.getInt(1);
			System.out.println(numMaxMatricola);
			conn.close();
			return numMaxMatricola;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return 0;
	}
	public static String getStrMaxMatricola() {
		Connection conn = sqliteConnection.dbConnector();
		String sql = "select * from utente";
		String matricola = "000";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
					System.out.println(result.getString("matricola"));
					matricola = result.getString("matricola");
				
			}
			return matricola;
			
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return "000";
	}
	public static void resetFerie() {
		Connection conn = sqliteConnection.dbConnector();
		String sqlFerie = "UPDATE situazione_lavorativa SET ferie_rimanenti='10'";
		String sqlCongedi = "UPDATE situazione_lavorativa SET congedi_rimanenti='10'";
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sqlFerie);
			statement.executeUpdate(sqlCongedi);
			
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static void resetRitAss() {
		Connection conn = sqliteConnection.dbConnector();
		String sqlRitardo = "delete from ritardo";
		String sqlAssenza = "delete from assenza";
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sqlRitardo);
			statement.executeUpdate(sqlAssenza);
			
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static void resetStraordinari() {
		Connection conn = sqliteConnection.dbConnector();
		String sql = "UPDATE situazione_lavorativa SET straordinari_rimanenti='10'";
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static ArrayList<String> queryMatricole() {
		Connection conn = sqliteConnection.dbConnector();
		String sql = "select matricola from utente";
		ArrayList<String> matricole = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				matricole.add(result.getString("matricola"));
			}
			return matricole;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static ArrayList<String> queryAstenzioni(String matricola) {
		ArrayList<String> astenzioni = new ArrayList<String>();
		Connection conn = sqliteConnection.dbConnector();
		String sqlFerie = "select * from ferie WHERE matricola='"+matricola+"'";
		String sqlCongedi = "select * from congedo_parentale WHERE matricola='"+matricola+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sqlFerie);
			while(result.next()) {
				String data_inizioF = result.getString("data_inizio");
				astenzioni.add(data_inizioF);
				String data_fineF = result.getString("data_fine");
				astenzioni.add(data_fineF);
				
			}
			ResultSet result2 = statement.executeQuery(sqlCongedi);
			while(result2.next()) {
				String data_inizio = result.getString("data_inizio");
				astenzioni.add(data_inizio);
				String data_fine = result.getString("data_fine");
				astenzioni.add(data_fine);
			}
			return astenzioni;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static boolean inserireTurni(ArrayList<LocalDate> turni, String matricola, servizio) {
		Connection conn = sqliteConnection.dbConnector();
		try {
			Statement statement = conn.createStatement();
			while(!turni.isEmpty()) {
				statement.executeUpdate("insert into turno values('"+servizio+"', '"+matricola+"', '0', '"+turni.get(0)+"', '08:00', '16:00')");
				turni.remove(0);
			}
			return true;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			return false;
		}
		
	}
	public static ArrayList<Object[]> queryTurniDip() {
		ArrayList<Object[]> turni = new ArrayList<Object[]>();
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from turno where matricola='"+Utente.getMatricola()+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String servizio = result.getString("id_servizio");
				String data = result.getString("data_turno");
				String oraInizio = result.getString("ora_inizio");
				String oraFine = result.getString("ora_fine");
				turni.add(new Object[] {servizio, data, oraInizio, oraFine});
			}
		return turni;
			
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static ArrayList<Object[]> queryTurniDir() {
		ArrayList<Object[]> turni = new ArrayList<Object[]>();
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from turno";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String matricola = result.getString("matricola");
				String servizio = result.getString("id_servizio");
				String data = result.getString("data_turno");
				String oraInizio = result.getString("ora_inizio");
				String oraFine = result.getString("ora_fine");
				turni.add(new Object[] {matricola, servizio, data, oraInizio, oraFine});
			}
		return turni;
			
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static boolean queryVerificaCredenziali(String matricola, String password){
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from utente u, dati_personali d where u.matricola=d.matricola and u.matricola='"+ matricola +"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	String id = result.getString("matricola");
            	String pw = result.getString("pass");
            	int ruolo = result.getInt("flag_ruolo"); 
            	String nome = result.getString("nome");
            	new Utente(id, nome, ruolo);
            	
            	if (pw.equals(password) & id.equals(matricola)) {
            		System.out.println("Dati corretti");
            		conn.close();
            		return true;
            	} else if ( !pw.equals(password) || id.equals(matricola)) {
            		System.out.println("Riprova sarai più fortunato");
            		
            	}

           }
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
			return false;
		}
		return  false;
	}
	public static boolean queryVerificaDati(String matricola, String email) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from utente where matricola='"+ matricola +"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
        	String mail = result.getString("mail");
        	if (mail == null) {
        		JOptionPane.showMessageDialog(null, "Matricola Errata");
        	}
            while(result.next()) {
            	String password = result.getString("pass");
            	System.out.println(password +", "+ mail);
            	System.out.println(mail);
            	System.out.println(email);
            	
            if (email.toLowerCase().equals(mail.toLowerCase())) {
            	JOptionPane.showMessageDialog(null, "La password è: "+ password);
				ModuloLogin.main(null);
				return true;
            }else {
            	JOptionPane.showMessageDialog(null, "Email Errata");
            }
            }
		}catch(Exception e){
			erroreComunicazioneDBMS(e);
		}
		return false;
	}
	public static String controllaPsw(String old_psw) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from utente where matricola='"+ Utente.getMatricola() +"'";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String password = result.getString("pass");
            conn.close();
            return password;
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static boolean modificaPsw(String new_psw) {
		Connection conn = sqliteConnection.dbConnector();
		String sqlUpdate ="update utente set pass='"+ new_psw +"'where matricola='"+ Utente.getMatricola() +"'";
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sqlUpdate);
            conn.close();
            return true;
            }
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return false;
	}
	public static String[] richiediDatiPersonali() {
		String[] dati = new String[5];
		
		Connection conn = sqliteConnection.dbConnector();
		try {
			Statement statement = conn.createStatement();
			String sql ="select * from dati_personali d, utente u where d.matricola=u.matricola and  u.matricola='"+ Utente.getMatricola() +"'";
			ResultSet result =statement.executeQuery(sql);
			while (result.next()) {
				dati[0] = result.getString("mail");
				dati[1] = result.getString("indirizzo");
				dati[2] = result.getString("cap");
				dati[3] = result.getString("citta");
				dati[4] = result.getString("iban");
				
			}
			conn.close();
		} catch(Exception e){
			erroreComunicazioneDBMS(e);
		}
		
		return dati;
	}
	public static void modificaDato(String valore, String dato) {
		Connection conn = sqliteConnection.dbConnector();
		String sql;
		if (valore.equals("mail")) {
			sql ="update utente set "+valore+"='"+ dato +"'where matricola='"+ Utente.getMatricola() +"'";
		}else {
			sql ="update dati_personali set "+valore+"='"+ dato +"'where matricola='"+ Utente.getMatricola() +"'";
		}
		try {
			Statement statement = conn.createStatement();
			
            statement.executeUpdate(sql);
            conn.close();
		} catch(Exception e){
			erroreComunicazioneDBMS(e);
		}
	}
	public static ArrayList<Double> queryStipendio(int mese, int anno, boolean gennaio) {
		ArrayList<String> matricole = queryMatricole();
		Connection conn = sqliteConnection.dbConnector();
		String sql;
		ArrayList<Double> stipendio = new ArrayList<Double>();
		while (!matricole.isEmpty()) {
			if (gennaio) {
				sql  = "SELECT retribuzione, straordinario, retribuzione_straordinario from turno t, servizio s WHERE t.id_servizio=s.cod_s and matricola='"+matricole.get(0)+"' AND data_turno>='"+(anno-1)+"-"+(mese-1)+"-01' AND data_turno<'"+anno+"-"+mese+"-01' ";
			}else {
				sql  = "SELECT retribuzione, straordinario, retribuzione_straordinario from turno t, servizio s WHERE t.id_servizio=s.cod_s and matricola='"+matricole.get(0)+"' AND data_turno>='"+anno+"-"+(mese-1)+"-01' AND data_turno<'"+anno+"-"+mese+"-01' ";
			}
			matricole.remove(0);
			try {
				Statement statement = conn.createStatement();
	            ResultSet result = statement.executeQuery(sql);
	            while(result.next()) {
	            	if (result.getBoolean("straordinario")) {
	            		stipendio.add(result.getDouble("retribuzione_straordinario"));
	            	}
	            	else {
	            		stipendio.add(result.getDouble("retribuzione"));
	            	}
	            }
	            if (gennaio) {
	            	stipendio.add(stipendio.get(stipendio.size()) * 2);
	            }
	            conn.close();
	            return stipendio;
	            
			}catch(Exception e) {
				erroreComunicazioneDBMS(e);
			}
		}
	return null;
	}
	public static double queryStipendioPrec(int mese, int anno, boolean gennaio) {
		Connection conn = sqliteConnection.dbConnector();
		String sql = "select ";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            double stipendio = result.getDouble(0);
            conn.close();
            return stipendio;
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return 0;
	}
	public static String[] queryMieiDati(String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String[] dati = new String[4];
		String sql  = "select * from dati_personali d, utente u where d.matricola=u.matricola and u.matricola='"+ Utente.getMatricola() +"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	dati[0] = result.getString("matricola");
            	dati[1] = result.getString("nome");
            	dati[2] = result.getString("cognome");
            	dati[3] = result.getString("mail");
            }
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return dati;
	}
	public static void queryVincolo(Date dataInizio, Date dataFine) {
		Connection conn = java.sqliteConnection.dbConnector();
		String sql = "insert into vincoli_ferie values('"+dataInizio+"', '"+dataFine+"')";
		try {
			Statement statement = conn.createStatement();
			JOptionPane.showMessageDialog(null, "Vincoli ferie inseriti correttamente");
			statement.executeQuery(sql);
		} catch (SQLException e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static int queryFerieDisponibili(String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from situazione_lavorativa s where s.matricola='"+matricola+"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	int ferieRimanenti = result.getInt("ferie_rimanenti");
            	System.out.println(ferieRimanenti);
            	return ferieRimanenti;
            }
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return 0;
	}
	public static String[] queryVincoli() {
		String[] vincoli = new String[2];
		Connection conn = sqliteConnection.dbConnector();
		String sqlVincoli = "select * from vincoli_ferie";
		try {
			Statement statementVincoli = conn.createStatement();
			ResultSet result =statementVincoli.executeQuery(sqlVincoli);
			while (result.next()) {
				vincoli[0] = result.getString("data_inizio");
				vincoli[1] = result.getString("data_fine");
				return vincoli;
			}
		} catch (SQLException e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static void aggiugiFerie(String matricola, LocalDate dataInizio, LocalDate dataFine, int diffFerie) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "insert into ferie values('"+matricola+"', '"+Date.valueOf(dataInizio)+"', '"+Date.valueOf(dataFine)+"')";
		String sqlInsert ="update situazione_lavorativa set ferie_rimanenti='"+(dataInizio.compareTo(dataFine)+1)+"' where matricola_dip='"+"001"+"'";
		System.out.println(sql);
		System.out.println(sqlInsert);
		try {
			Statement statement = conn.createStatement();
            statement.executeQuery(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            statement.executeQuery(sqlInsert);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static int queryCongediDisponibili(String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from situazione_lavorativa s where s.matricola_dip='"+ matricola +"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.getInt("congedi_rimanenti");
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	return 0;
	}
	public static void aggiungiCongediParentali(String matricola, LocalDate dataInizio, LocalDate dataFine) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "insert into congedo_parentale values('"+matricola+"', '"+dataInizio+"', '"+dataFine+"')";
		String sqlInsert ="update situazione_lavorativa set ferie_rimanenti='"+(dataInizio.compareTo(dataFine)+1)+"' where matricola_dip='"+"001"+"'";
		System.out.println(sql);
		System.out.println(sqlInsert);
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sqlInsert);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static void queryMemorizzaStipendio(String matricola, Double stipendio) {
		Connection conn = sqliteConnection.dbConnector();
		String sqlStipendioNuovo = "insert into stipendio_precedente('"+ Integer.parseInt(matricola)+"', '"+matricola+"', '"+stipendio+"')";
		String sqlStipendioStorico = "insert into storico_stipendio('"+ matricola+"', '"+Date.valueOf(Tempo.dataAttuale())+"', '"+stipendio+"')";
		String sqlDelete = "delete from stipendio_precedente where matricola='"+matricola+"'";
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sqlStipendioStorico);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sqlDelete);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sqlStipendioNuovo);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static ArrayList<Object> queryDatiDipendenti() {
		ArrayList<Object> dipendenti = new ArrayList<>();
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select * from dati_personali d, utente u where d.matricola=u.matricola";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	String id = result.getString("matricola");
            	String nome = result.getString("nome");
            	String cognome = result.getString("cognome");
            	String email = result.getString("mail");
            	String citta = result.getString("citta");
            	System.out.println(id +", "+ nome +", " + cognome +", "+ email);
            	dipendenti.add(new Object [] {id, nome, cognome, email, citta});
            }
		}catch(Exception e) {
			System.out.println(e);
		}
		return dipendenti;
	}
	public static void licenzia(String matricola) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "delete * from utente u, dati_personali d and where u.matricola=d.matricola and  u.matricola='"+matricola+"'";
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static void queryMemorizzaAssenza(String matricola, LocalDate data, String motivo) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "insert into assenza values('"+matricola+"', '"+Date.valueOf(data)+"', '"+motivo+"')";
		try {
			Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static ArrayList<String> queryDipendentiAssenti(LocalDate data){
		ArrayList<String> dipAss = new ArrayList<>();
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select matricola from assenza where data_assenza='"+Date.valueOf(data)+"'";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	dipAss.add(result.getString(0));
            }
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return dipAss;
	}
	public static int queryServizioDip(String matricola, LocalDate data) {
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select id_servizio from turno t, assenza a where t.matricola=a.matricola and t.matricola='"+matricola+"' and a.data_assenza='"+Date.valueOf(data)+"'";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.getInt(0);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return -1;
	}
	public static ArrayList<String> queryDipendentiDisponibili(LocalDate data, int servizio){
		ArrayList<String> dipDisp = new ArrayList<>();
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select matricola from turno t, situazione_lavorativa s where t.matricola=s.matricola and straordinari_rimanenti > 0 and data_turno> '"+Date.valueOf(data)+"' and t.id_servizio='"+servizio+"'";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	dipDisp.add(result.getString(0));
            }
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return dipDisp;
	}
	public static ArrayList<String> queryDipendentiInTurnoDisponibili(LocalDate data, int servizio){
		ArrayList<String> dipInTurnoDisp = new ArrayList<>();
		Connection conn = sqliteConnection.dbConnector();
		String sql  = "select matricola from turno t, situazione_lavorativa s where t.matricola=s.matricola and straordinari_rimanenti > 0 and data_turno='"+Date.valueOf(data)+"' and t.id_servizio='"+servizio+"'";
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	dipInTurnoDisp.add(result.getString(0));
            }
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return dipInTurnoDisp;
	}
	public static String richiestaEmail(String matricola) {
		Connection conn = dbConnector();
		String sql = "select mail from utente where matricola='"+matricola+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			return result.getString(0);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static void  modificaTurni(String matricolaAss, String matricolaDisp) {
		Connection conn = dbConnector();
		String sql = "update turno set matricola='"+matricolaDisp+"', straordinario='true' where matricola='"+matricolaAss+"'and data_turno='"+Date.valueOf(Tempo.getDay())+"'";
		try {
			Statement statement = conn.createStatement();
			statement.executeQuery(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static boolean verificaPresenza(String matricola, LocalDate data) {
		Connection conn = dbConnector();
		String sql = "select * from turno where matricola='"+matricola+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				LocalDate dataTurno = Date.valueOf(result.getString("data_turno")).toLocalDate();
				if (dataTurno.isEqual(data)) {
					return true;
				}
			}
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return false;
	}
	public static LocalTime oraInizioturno(String matricola, LocalDate data) {
		Connection conn = dbConnector();
		String sql = "select ora_inizio from turno where matricola='"+matricola+"' and data_turno='"+Date.valueOf(data)+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			LocalTime oraInizio = Time.valueOf(result.getString(0)).toLocalTime();
				return oraInizio;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static void registraIngresso(String matricola, LocalDate dataTurno, LocalTime oraInizio) {
		Connection conn = dbConnector();
		String sql = "update turno set flag_ingresso='true' where matricola='"+matricola+"' and data_turno='"+dataTurno+"' ";
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static void registraRitardo(String matricola, LocalDate dataTurno) {
		Connection conn = dbConnector();
		String sql = "insert into assenza values('"+matricola+"', '"+Date.valueOf(dataTurno)+"')";
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
	}
	public static ArrayList<String> richiediDipendenteServizio(LocalDate data, int servizio) {
		ArrayList<String> dipServizio = new ArrayList<>();
		Connection conn = dbConnector();
		String sql = "select matricola from turno where id_servizio='"+(servizio + 1)+"' and data_turno='"+Date.valueOf(data)+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet result =statement.executeQuery(sql);
			while(result.next()) {
					dipServizio.add(result.getString(0));
			}
			return dipServizio;
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return null;
	}
	public static void modificaTabelle(ArrayList<String> matricole, int servizio, LocalDate data) {
		Connection conn = dbConnector();
		while(!matricole.isEmpty()) {
			String sql = "update turno set id_servizio='"+servizio+"' where matricola='"+matricole.get(0)+"' and data_turno='"+data+"' ";
			matricole.remove(0);
			try {
				Statement statement = conn.createStatement();
				statement.executeUpdate(sql);
			}catch(Exception e) {
				erroreComunicazioneDBMS(e);
			}
		}
	}
	public static void riassegnaDipendenti(ArrayList<String> matricole, int servizio, LocalDate data) {
		Connection conn = dbConnector();
		while(!matricole.isEmpty()) {
			String sql = "update turno set id_servizio='"+(servizio-1)+"' where matricola='"+matricole.get(0)+"' and data_turno='"+data+"'";
			matricole.remove(0);
			try {
				Statement statement = conn.createStatement();
				statement.executeUpdate(sql);
			}catch(Exception e) {
				erroreComunicazioneDBMS(e);
			}
		}
	}
}
	

