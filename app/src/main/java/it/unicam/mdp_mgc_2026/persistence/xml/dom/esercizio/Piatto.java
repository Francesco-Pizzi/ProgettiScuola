package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

import java.util.HashSet;
import java.util.Set;

public class Piatto extends VoceMenu {

    private final Categoria categoria;
    private final double prezzo;
    private final Set<Allergene> allergeni;

    Piatto(String nome, String descrizione, double prezzo, Categoria categoria) {
        super(nome, descrizione);

        if (prezzo <= 0) {throw new IllegalArgumentException("Il prezzo deve essere positivo");}
        if (categoria == null) {throw new IllegalArgumentException("La categoria non può essere vuoto");}

        this.categoria = categoria;
        this.prezzo = prezzo;
        this.allergeni = new HashSet<>();
    }

    public enum Categoria {
        ANTIPASTO,
        PRIMO,
        SECONDO,
        DOLCE,
    }

    public double getPrezzo() {return prezzo;}
    public Categoria getCategoria() {return categoria;}

    public Set<Allergene> getAllergeni() {return Set.copyOf(allergeni);}

    public void aggiungiAllergene(Allergene allergene) {allergeni.add(allergene);}
}

