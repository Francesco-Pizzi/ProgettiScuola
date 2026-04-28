package it.unicam.mdp_mgc_2026.scuola.Model;

import it.unicam.mdp_mgc_2026.scuola.Contratti.Valutatore;

import java.util.Random;

import static it.unicam.mdp_mgc_2026.scuola.Utilita.FormattatoreVoto.formatta;

public class Esame {
    private String nome;
    private Valutatore valutatore;

    public Esame(String nome, Valutatore valutatore) {
        if (nome == null || valutatore == null) {
            throw new IllegalArgumentException("Campi non validi");
        }
        this.nome = nome;
        this.valutatore = valutatore;
    }

    public String getNome() {return this.nome;}

    public void sostieniEsame(Studente studente) {
        Random random = new Random();
        int risposteE = random.nextInt(61);
        int voto = valutatore.assegnaVoto(studente, risposteE);
        System.out.println(studente.getNomeCompleto() + " Risposte esatte: " + risposteE + " Voto assegnato: " + voto);
        if ((voto >= 18 && voto <= 30) || voto == 31) {
            studente.getLibretto().registraEsameSuperato(this, voto);
            System.out.println("Esame registrato");
        } else {
            System.out.println("Esame non superato");
        }
    }

    public void sostieniEsami(Studente studente, int risposteE) {
        int voto = valutatore.assegnaVoto(studente, risposteE);

        stampaEsito(studente, voto);

        if (esameSuperato(voto)) {
            registraEsito(studente, voto);
            {
                stampaEsameRegistrato();
            }
        } else {
            stampaEsameNonSuperato();
        }
    }

    public void stampaEsito(Studente studente, int voto) {
        System.out.println("Esame di: " + nome);
        System.out.println("Studente: " + studente.getNomeCompleto());
        System.out.println("Voto assegnato: " + formatta(voto));
    }

    public void registraEsito(Studente studente, int voto) {
        studente.getLibretto().registraEsameSuperato(this, voto);
    }

    private boolean esameSuperato(int voto) {
        return(voto>= 18 && voto <= 30) || voto == 31;
    }

    public void stampaEsameRegistrato() {
        System.out.println("Esame registrato");
    }

    public void stampaEsameNonSuperato() {
        System.out.println("Esame non superato");
    }
}
