package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

public class OpzionePrezzo {
    private final String formato;
    private final double prezzo;

    public OpzionePrezzo(String formato, double prezzo) {
        if (formato == null) {throw new IllegalArgumentException("Il formato non può essere nullo o vuoto");}
        if (prezzo <= 0) {throw new IllegalArgumentException("Il prezzo deve essere positivo");}

        this.formato = formato;
        this.prezzo = prezzo;
    }

    public String getFormato() { return formato; }
    public double getPrezzo() { return prezzo; }
}
