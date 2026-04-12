package it.unicam.mdp_mgc_2026.scuola;

public class Studente extends Persona {
    private final int matricola;

    public Studente(String nome, String cognome, int matricola) {
        super(nome, cognome);
        if (matricola <= 0) {
            throw new IllegalArgumentException("Nome, cognome, o matricola non validi");
        }
        this.matricola = matricola;
    }

    public int getMatricola() {
        return matricola;
    }

    public void saluta(){
        System.out.println("Buongiorno, io sono: " + getNomeCompleto());
    }

}
