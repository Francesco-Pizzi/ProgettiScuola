package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

import java.util.ArrayList;
import java.util.List;

public class Bevanda extends VoceMenu {
    private final TipoBevanda tipo;
    private final List<OpzionePrezzo> opzioniPrezzo;

    public Bevanda(String nome, String descrizione, TipoBevanda tipo, List<OpzionePrezzo> opzionePrezzo) {
        super (nome, descrizione);

        if (tipo == null) {throw new IllegalArgumentException("Il tipo non può essere nullo");}
        if (opzionePrezzo == null)  {throw new IllegalArgumentException("La bevanda deve avere almeno un'opzione di prezzo");}

        this.tipo = tipo;
        this.opzioniPrezzo = opzionePrezzo;
    }

    public enum TipoBevanda {
        ACQUA,
        BIBITA,
        BIRRA,
        VINO
    }

    public TipoBevanda getTipo() { return tipo; }
    public List<OpzionePrezzo> getOpzioniPrezzo() { return opzioniPrezzo; }
}
