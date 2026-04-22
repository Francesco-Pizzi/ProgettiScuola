package it.unicam.mdp_mgc_2026.scuola.Model;

import it.unicam.mdp_mgc_2026.scuola.Contratti.Valutatore;

import java.util.Random;

public class Professore extends Persona implements Valutatore {
    private String settore;

    public Professore(String nome, String cognome, String settore) {
        super(nome, cognome);
        if (settore == null) {
            throw new IllegalArgumentException("Settore non valido");
        }
        this.settore = settore;
    }

    public String getSettore() {
        return settore;
    }
    public void setSettore(String settore) {
        this.settore = settore;
    }

    public void presenta() {
        System.out.println("Salve io sono: " + getNomeCompleto() + " ed insegno: " + getSettore());
    }

    @Override
    public int assegnaVoto(Studente studente, int risposteE) {
        Random random = new Random();
        return random.nextInt(31);
    }

    @Override
    public void presentati() {
        System.out.println("Buongiorno io sono astratto: " + getNomeCompleto());
    }
}
