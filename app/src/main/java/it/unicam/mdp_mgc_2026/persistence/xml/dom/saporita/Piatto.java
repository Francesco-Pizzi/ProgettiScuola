package it.unicam.mdp_mgc_2026.persistence.xml.dom.saporita;

public class Piatto {

    private String nome, descrizione;
    private float prezzo;

    public Piatto(String nome, float prezzo, String descrizione){
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
    }
}
