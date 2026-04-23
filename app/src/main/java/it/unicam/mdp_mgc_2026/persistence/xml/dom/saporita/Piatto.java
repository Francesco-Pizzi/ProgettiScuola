package it.unicam.mdp_mgc_2026.persistence.xml.dom.saporita;

import java.util.Objects;

public class Piatto {

    private String nome;
    private double prezzo;
    private String descrizione;
    private String id;

    public Piatto(String nome, String descrizione, double prezzo, String id){
        if (nome == null || descrizione == null || id == null || prezzo < 0) throw new IllegalArgumentException();
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Piatto piatto = (Piatto) o;
        return Objects.equals(id, piatto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return getNome()+"\t€"+getPrezzo();
    }



}
