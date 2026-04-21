package it.unicam.mdp_mgc_2026.scuola;

import java.util.Random;

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

    public void sostieniEsame (Studente studente){
        Random random = new Random();
        int risposteE = random.nextInt(61);
        int voto = valutatore.assegnaVoto(studente, risposteE);
        System.out.println(studente.getNomeCompleto() + " Risposte esatte: " + risposteE + " Voto assegnato: " + voto);
        if ((voto >= 18 && voto <= 30) || voto == 31){
            studente.getLibretto().registraEsameSuperato(this, voto);
            System.out.println("Esame registrato");
        } else {
            System.out.println("Esame non superato");
        }
    }
}
