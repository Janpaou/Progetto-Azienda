package progetto.java.Utils;

public class Utente {
	private static String matricola;
	private static String nome;
	private static int ruolo;
	
	public Utente(String matricola, String nome, int ruolo) {
		this.setMatricola(matricola);
		this.setNome(nome);
		this.setRuolo(ruolo);
	}

	public static int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		Utente.ruolo = ruolo;
	}

	public static String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		Utente.nome = nome;
	}

	public static String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		Utente.matricola = matricola;
	}
}
