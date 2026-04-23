package it.unicam.mdp_mgc_2026.persistence.xml.dom.saporita;

public class Primo extends Piatto{

    public Primo(String nome, String descrizione, double prezzo, String id){
        super(nome, descrizione, prezzo, id);
    }
    @Override
    public String toString() {
        return "Primo: "+getNome()+", "+getDescrizione()+" €"+getPrezzo();
    }
}
