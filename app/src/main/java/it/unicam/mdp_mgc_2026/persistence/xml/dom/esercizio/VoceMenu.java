package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

public abstract class VoceMenu {
    private final String nome;
    private final String descrizione;

    protected VoceMenu(String nome, String descrizione) {
        if (nome == null || nome.isEmpty()) { throw new IllegalArgumentException("Il nome non può essere nullo o vuoto"); }

        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
}
