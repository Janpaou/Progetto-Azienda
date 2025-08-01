package progetto.java.Utils;

import java.util.Date;

import progetto.java.sqliteConnection;

public class Dipendente {
	private String matricola;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String sesso;
	private Date data;
	
	public Dipendente(String nome, String cognome, Date data, String email, String password, String sesso){
		this.setNome(nome);
		this.setCognome(cognome);
		this.setMatricola();
		this.setEmail(email);
		this.setPassword(password);
		this.setSesso(sesso);
		this.setData(data);
	
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola() {
		String m = sqliteConnection.getStrMaxMatricola();
		int matricola = Integer.parseInt(m) +1;
		this.matricola = "00"+ matricola;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public java.sql.Date getData() {
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		return sqlDate;
	}
	public void setData(Date data) {
		
		this.data = data;
	}
	
}
