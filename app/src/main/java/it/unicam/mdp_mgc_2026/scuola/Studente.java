package it.unicam.mdp_mgc_2026.scuola;

public class Studente extends Persona {
    private final int matricola;
    private final Libretto libretto;

    public Studente(String nome, String cognome, int matricola) {
        super(nome, cognome);
        if (matricola <= 0) {
            throw new IllegalArgumentException("Nome, cognome, o matricola non validi");
        }
        this.matricola = matricola;
        this.libretto = new Libretto(this);
    }

    public int getMatricola() {
        return matricola;
    }

    public void saluta(){
        System.out.println("Buongiorno, io sono: " + getNomeCompleto());
    }

    @Override
    public void presentati(){
        System.out.println("Buongiorno, io sono astratto: " + getNomeCompleto());
    }
    public Libretto getLibretto() {return libretto;}
}
