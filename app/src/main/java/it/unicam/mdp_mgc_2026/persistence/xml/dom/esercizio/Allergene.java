package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

public enum Allergene {
    GLUTINE(1, "Glutine"),
    CROSTACEI(2, "Crostacei"),
    UOVA(3, "Uova"),
    PESCE(4, "Pesce"),
    LATTE(5, "Latte"),
    FRUTTA_A_GUSCIO(6, "Frutta a guscio");

    private final int codice;
    private final String descrizione;

    Allergene(int codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public int getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static Allergene daCodice(int codice) {
        for (Allergene a : Allergene.values()) {
            if (a.getCodice() == codice) {
                return a;
            }
        }
        throw new IllegalArgumentException("Codice allergene non valido: " + codice);
    }
}
