package java.Utils;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailManager {
	public static void sendMail(String destinatario) throws Exception {
		System.out.println("Invio mail in corso...");
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		String myAccountEmail = "g.panzeca23@gmail.com";
		String password = "sonofigo3000";
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		Message message = prepareMessage(session, myAccountEmail, destinatario);
		
		Transport.send(message);
		System.out.println("Mail inviata correttamente");
		
	}
	public static Message prepareMessage(Session session, String myAccountEmail, String destinatario) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject("Prova");
			message.setText("Prova");
			return message;
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
public static ArrayList<String[]> queryMieiDati(String matricola) {
		ArrayList<String[]> datiPersonali = new ArrayList<>();
		Connection conn = sqliteConnection.dbConnector();
		String[] dati = new String[3];
		String sql  = "select * from utente u, dati_personali d where u.matricola=d.matricola and u.matricola='"+ Utente.getMatricola() +"'";
		String sqlSl = "select * from situazione_lavorativa where matricola='"+Utente.getMatricola()+"'";
		String sqlStipendio = "select * from storico_stipendio where matricola='"+Utente.getMatricola()+"'";
		String sqlAssenza = "select * from assenza where matricola='"+Utente.getMatricola()+"'";
		String sqlVincoli = "select * from vincoli_ferie where matricola='"+Utente.getMatricola()+"'";
		String sqlRitardo = "select * from ritardo where matricola='"+Utente.getMatricola()+"'";
		String sqlStraordinario = "select * from straordinario where matricola='"+Utente.getMatricola()+"'";
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	dati[0] = "Matricola";
            	dati[1] = result.getString("matricola");
            	datiPersonali.add(dati);
            	dati[0] = "Nome";
            	dati[1] = result.getString("nome");
            	datiPersonali.add(dati);
            	dati[0] = "Cognome";
            	dati[1] = result.getString("cognome");
            	datiPersonali.add(dati);
            	dati[0] = "Email";
            	dati[1] = result.getString("mail");
            	datiPersonali.add(dati);
            	dati[0] = "Indirizzo";
            	dati[1] = result.getString("indirizzo");
            	datiPersonali.add(dati);
            	dati[0] = "Cap";
            	dati[1] = result.getString("cap");
            	datiPersonali.add(dati);
            	dati[0] = "Città";
            	dati[1] = result.getString("citta");
            	datiPersonali.add(dati);
            	dati[0] = "Iban";
            	dati[1] = result.getString("iban");
            	datiPersonali.add(dati);
            }
            
		}catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlSl);
            while(result.next()) {
            	dati[0] = "Ferie rimanenti";
            	dati[1] = result.getString("ferie_rimanenti");
            	datiPersonali.add(dati);
            	dati[0] = "Congedi rimanenti";
            	dati[1] = result.getString("congedi_rimanenti");
            	datiPersonali.add(dati);
            	dati[0] = "Straordinari rimanenti";
            	dati[1] = result.getString("straordinari_rimanenti");
            	datiPersonali.add(dati);
            }
		}
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlStipendio);
            while(result.next()) {
            	dati[0] = "Stipendio";
            	dati[1] = result.getString("retribizione");
            	dati[2] = result.getString("data_emissione");
            	datiPersonali.add(dati);
            }
		}
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlAssenza);
            while(result.next()) {
            	if (result.getInt("tipo_assenza") == 0) {
            		dati[0] = "Assenza";
                	dati[1] = result.getString("data_inizio");
                	dati[2] = result.getString("data_fine");
                	datiPersonali.add(dati);
            	}
            	else if (result.getInt("tipo_assenza") == 1) {
            		dati[0] = "Congedo";
                	dati[1] = result.getString("data_inizio");
                	dati[2] = result.getString("data_fine");
                	datiPersonali.add(dati);
            	}
            	else if (result.getInt("tipo_assenza") == 2) {
            		dati[0] = "Ferie";
                	dati[1] = result.getString("data_inizio");
                	dati[2] = result.getString("data_fine");
                	datiPersonali.add(dati);
            	}
            	
            }
		}
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlStraordinario);
            while(result.next()) {
            	dati[0] = "Straordinario";
            	dati[1] = result.getString("data_straordinario");
            	dati[2] = result.getString("tipo_servizio");
            	datiPersonali.add(dati);
            }
		}
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlRitardo);
            while(result.next()) {
            	dati[0] = "Ritardo";
            	dati[1] = result.getString("data_ritardo");
            	dati[2] = result.getString("");
            	datiPersonali.add(dati);
            }
		}
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		try {
			Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlVincoli);
            while(result.next()) {
            	dati[0] = "Vincoli ferie";
            	dati[1] = result.getString("data_inizio");
            	dati[2] = result.getString("data_fine");
            	datiPersonali.add(dati);
            }
		}
		catch(Exception e) {
			erroreComunicazioneDBMS(e);
		}
		return datiPersonali;
