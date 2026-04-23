package it.unicam.mdp_mgc_2026.persistence.xml.dom.saporita;

public class Secondo extends Piatto{

    public Secondo(String nome, String descrizione, double prezzo, String id){
        super(nome, descrizione, prezzo, id);
    }
    @Override
    public String toString() {
        return "Secondo: "+getNome()+", "+getDescrizione()+" €"+getPrezzo();
    }

}
