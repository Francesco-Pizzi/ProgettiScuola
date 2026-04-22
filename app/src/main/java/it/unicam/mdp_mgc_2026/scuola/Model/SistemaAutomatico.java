package it.unicam.mdp_mgc_2026.scuola.Model;

import it.unicam.mdp_mgc_2026.scuola.Contratti.Valutatore;

public class SistemaAutomatico implements Valutatore {
    String nome;

    public SistemaAutomatico(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Argomento non valido");
        }
        this.nome = nome;
    }

    @Override
    public int assegnaVoto(Studente studente, int risposteE) {
        return risposteE/2 ;
    }
}
