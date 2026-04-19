package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

import java.util.List;

public class Vino extends Bevanda {
    private final String cantina;
    private final ColoreVino coloreVino;

    public Vino(String nome, String descrizione, TipoBevanda tipo, List<OpzionePrezzo> opzionePrezzo, String cantina, ColoreVino coloreVino) {
        super(nome, descrizione, tipo, opzionePrezzo);

        if (cantina == null || cantina.isEmpty()) {throw new IllegalArgumentException("Deve essere la cantina");}
        if (coloreVino == null) {throw new IllegalArgumentException("Deve essereci il colore del vino");}

        this.cantina = cantina;
        this.coloreVino = coloreVino;
    }

    public enum ColoreVino {
        BIANCO,
        ROSSO,
        SPUMANTE
    }

    public String getCantina() { return cantina; }
    public ColoreVino getColoreVino() { return coloreVino; }
}
