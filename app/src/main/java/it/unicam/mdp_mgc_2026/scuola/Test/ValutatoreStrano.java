package it.unicam.mdp_mgc_2026.scuola.Test;

import it.unicam.mdp_mgc_2026.scuola.Model.Studente;
import it.unicam.mdp_mgc_2026.scuola.Contratti.Valutatore;

public class ValutatoreStrano implements Valutatore {

    @Override
    public int assegnaVoto(Studente studente, int risposteE) {
        return -10;
    }
}
